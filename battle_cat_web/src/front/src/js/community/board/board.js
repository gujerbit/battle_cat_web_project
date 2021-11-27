import { rejectAlert } from '../../util/alert.js';
import { getAccountInfo } from '../admin/admin.js';

export { writing, viewCountUpdate };

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
    });
  
    if(data > 0) {
      alert('글 작성 완료');
      location.href = '/board';
    } else alert('글 작성 실패');
  } catch (error) {
    rejectAlert();
  }
}

async function viewCountUpdate(idx, axios) {
  try {
    await axios.get(`/view_count_update/${idx}`, {
      headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
    });
  } catch (error) {
    rejectAlert();
  }
}