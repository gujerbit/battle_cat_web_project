import { rejectAlert } from '../../util/alert.js';
import { getAccountInfo } from '../admin/admin.js';

export { writing, countUpdate, getCountData, searchBoardData, deleteBoard, updating, writingComment };

async function writing(title, content, text, type, axios) {
  if(title.length <= 0) {
    alert('제목을 입력하세요!');
    return;
  }

  if(text.length <= 1) {
    alert('내용을 입력하세요!');
    return;
  }

  if(content.length > 10000) {
    alert(`내용 용량 초과! ${content.length}/10000`);
    return;
  }
  
  try {
    let { data } = await axios.post('/write_board', {
      email: getAccountInfo().email,
      title: title,
      content: content,
      text: text,
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

async function updating(idx, title, content, text, type, axios) {
  if(title.length <= 0) {
    alert('제목을 입력하세요!');
    return;
  }

  if(text.length <= 1) {
    alert('내용을 입력하세요!');
    return;
  }

  if(content.length > 10000) {
    alert(`내용 용량 초과! ${content.length}/10000`);
    return;
  }
  
  if(confirm('정말로 해당 게시글을 수정하시겠습니까??')) {
    const password = prompt('비밀번호를 입력해주세요');

    if(password === null) location.reload();
    else {
      try {
        let { data:check } = await axios.post('/change_board_check', {
          email: getAccountInfo().email,
          password: password,
        }, {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });
  
        if(check) {
          try {
            let { data } = await axios.post('/update_board', {
              idx: idx,
              email: getAccountInfo().email,
              title: title,
              content: content,
              text: text,
              type: type,
            }, {
              headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
            });
  
            if(data > 0) {
              alert('게시물 수정 성공!');
              location.href = `/board_data/${idx}`;
            } else alert('게시물 수정 실패');
          } catch (error) {
            rejectAlert();
          }
        } else {
          alert('다시 한 번 비밀번호를 확인해주세요!');
          location.reload();
        }
      } catch (error) {
        rejectAlert();
      } 
    }
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

function searchBoardData(data, type, value, valueType) {
  let result = [];
  
  if(type !== 'all' || value.length > 0) {
    if(type !== 'all' && value.length > 0) {
      data.forEach(res => {
        if(res.type === type) result.push(res);
      });

      if(valueType === 'all') result = result.filter(res => res.name.includes(value) || res.title.includes(value) || res.text.includes(value));
      else if(valueType === 'title_content') result = result.filter(res => res.title.includes(value) || res.text.includes(value));
      else if(valueType === 'title') result = result.filter(res => res.title.includes(value));
      else if(valueType === 'content') result = result.filter(res => res.text.includes(value));
      else if(valueType === 'writer') result = result.filter(res => res.name.includes(value));

      return result;
    } else if(type === 'all' && value.length > 0) {
      data.forEach(res => {
        if(valueType === 'all' && (res.name.includes(value) || res.title.includes(value) || res.text.includes(value))) result.push(res);
        else if(valueType === 'title_content' && (res.title.includes(value) || res.text.includes(value))) result.push(res);
        else if(valueType === 'title' && res.title.includes(value)) result.push(res);
        else if(valueType === 'content' && res.text.includes(value)) result.push(res);
        else if(valueType === 'writer' && res.name.includes(value)) result.push(res);
      });

      return result;
    } else if(type !== 'all' && value.length <= 0) {
      data.forEach(res => {
        if(res.type === type) result.push(res);
      });

      return result;
    }
  } else if(type === 'all' && value.length <= 0) return data;
}

async function deleteBoard(idx, axios) {
  if(confirm('정말로 해당 게시글을 삭제하시겠습니까??')) {
    const password = prompt('비밀번호를 입력해주세요');

    if(password === null) location.reload();
    else {
      try {
        let { data:check } = await axios.post('/change_board_check', {
          email: getAccountInfo().email,
          password: password,
        }, {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });
  
        if(check) {
          try {
            let { data } = await axios.post('/delete_board', {
              idx: idx,
              email: getAccountInfo().email,
            }, {
              headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
            });
  
            if(data > 0) {
              alert('게시물 삭제 성공!');
              location.href = '/board';
            } else {
              alert('게시물 삭제 실패');
              location.reload();
            }
          } catch (error) {
            rejectAlert();
          }
        } else {
          alert('다시 한 번 비밀번호를 확인해주세요!');
          location.reload();
        }
      } catch (error) {
        rejectAlert();
      }
    }
  }
}

async function writingComment(boardIdx, commentIdx, saltIdx, comment, parentComment, axios) {
  if(comment.length <= 0) {
    alert('댓글을 입력해주세요!');

    return;
  }

  if(comment.length > 1000) {
    alert('댓글은 최대 1000자까지 입력 가능합니다!');

    return;
  }

  try {
    let { data } = await axios.post('/writing_comment', {
      board_idx: boardIdx,
      comment_idx: commentIdx,
      salt_idx: saltIdx,
      email: getAccountInfo().email,
      comment: comment,
      parent_comment: parentComment,
      comment_date: new Date(),
    },{
      headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
    });

    if(data <= 0) alert('댓글 작성 실패');

    location.reload();
  } catch (error) {
    rejectAlert();
  }
}