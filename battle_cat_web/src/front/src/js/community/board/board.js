import { rejectAlert } from '../../util/alert.js';
import { getAccountInfo } from '../admin/admin.js';

export { writing, countUpdate, getCountData };

async function writing(title, content, length, type, axios) {
  if(title.length <= 0) {
    alert('제목을 입력하세요!');
    return;
  }

  if(length.length <= 1) {
    alert('내용을 입력하세요!');
    return;
  }

  if(content.length > 10000) {
    alert(`내용 용량 초과! ${content.length}/10000`);
    return;
  }
  
  try {
    let { data } = await axios.post('/board_writing', {
      email: getAccountInfo().email,
      name: getAccountInfo().name,
      title: title,
      content: content,
      writing_date: new Date(),
      type: type,
    }, {
      headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
    });
  
    if(data > 0) {
      alert('글 작성 완료');
      location.href = '/board';
    } else alert('글 작성 실패');
  } catch (error) {
    rejectAlert();
  }
}

async function countUpdate(idx, type, axios) {
  try {
    let { data } = await axios.post(`/count_update`, {
      board_idx: idx,
      type: type,
      email: getAccountInfo().email,
      name: getAccountInfo().name,
    }, {
      headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
    });

    if(data <= 0) {
      if(type === 'good') alert('이미 해당 게시물에 추천을 하였습니다!');
      else if(type === 'bad') alert('이미 해당 게시물에 비추천을 하였습니다!');
    } else {
      if(type === 'good' || type === 'bad') location.reload();
    }
  } catch (error) {
    rejectAlert();
  }
}

function getCountData(data, idx, type) {
  let count = 0;

  data.forEach(res => {
    if(res.board_idx === idx && res.type === type) count++;
  });

  return count;
}