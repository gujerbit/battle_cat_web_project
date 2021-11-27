import { rejectAlert } from '../../util/alert.js';

export { userReject, userForeverReject, userRejectRelease, userGradeSetting, getAccountInfo };

async function userReject(name, value, axios) {
  if(confirm(`정말 해당 사용자를 ${value}일 차단하시겠습니까?`)) {
    try {
      let time = value * 24 * 60 * 60 * 1000;
      let rejectEndDate = new Date(new Date().getTime() + time);

      let { data } = await axios.post('/user_reject', {
        name: name,
        reject_end_date: rejectEndDate,
      }, {
        headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
      });

      if(data > 0) {
        alert(`해당 사용자를 ${value}일 차단하였습니다`);
        setAdminLog(name, `${getAccountInfo().name}님이 ${name}님을 ${value}일 차단하였습니다`, axios);
      }
      else {
        alert('차단 실패');
        setAdminLog(name, `${getAccountInfo().name}님이 ${name}님을 ${value}일 차단하는데 실패하였습니다`, axios);
      }

      location.reload();
    } catch (error) {
      rejectAlert();
    }
  }
}

async function userForeverReject(name, axios) {
  if(confirm('정말 해당 사용자를 영구 차단하시겠습니까?')) {
    try {
      let { data } = await axios.post('/user_forever_reject', {
        name: name
      }, {
        headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
      });
  
      if(data > 0) {
        alert('해당 사용자를 영구 차단하였습니다');
        setAdminLog(name, `${getAccountInfo().name}님이 ${name}님을 영구 차단하였습니다`, axios);
      }
      else {
        alert('차단 실패');
        setAdminLog(name, `${getAccountInfo().name}님이 ${name}님을 영구 차단하는데 실패하였습니다`, axios);
      }
  
      location.reload();
    } catch (error) {
      rejectAlert();
    }
  }
}

async function userRejectRelease(name, axios) {
  if(confirm('정말 해당 사용자의 차단을 해제하시겠습니까?')) {
    try {
      let { data } = await axios.post('/user_reject_release', {
        name: name,
        reject_end_date: new Date()
      }, {
        headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
      });
  
      if(data > 0) {
        alert('해당 사용자의 차단을 해제하였습니다');
        setAdminLog(name, `${getAccountInfo().name}님이 ${name}님을 차단 해제하였습니다`, axios);
      }
      else {
        alert('차단 해제 실패');
        setAdminLog(name, `${getAccountInfo().name}님이 ${name}님을 차단 해체하는데 실패하였습니다`, axios);
      }
  
      location.reload();
    } catch (error) {
      rejectAlert();
    }
  }
}

async function userGradeSetting(name, grade, axios) {
  if(confirm(`정말 해당 사용자를 ${grade} 등급으로 전환하시겠습니까?`)) {
    // try {
    //   let { data:userGrade } = await axios.post('/check_grade', {
    //     name: name,
    //   }, {
    //     headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
    //   });

    //   if(userGrade)
    // } catch (error) {
      
    // } 유저 권한 변경되었을 시 코드 처리해줘야 함
    try {
      let { data } = await axios.post('/user_grade_setting', {
        name: name,
        grade: grade === '유저' ? 'user' : grade === '관리자' ? 'admin' : 'operator',
      }, {
        headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
      });
  
      if(data > 0) {
        alert(`해당 사용자의 등급을 ${grade}로 전환하였습니다`);
        setAdminLog(name, `${getAccountInfo().name}님이 ${name}님의 등급을 ${grade}로 전환하였습니다`, axios);
      } else {
        alert('등급 전환 실패');
        setAdminLog(name, `${getAccountInfo().name}님이 ${name}님의 등급을 ${grade}로 전환하는데 실패하였습니다`, axios);
      }
  
      location.reload();
    } catch (error) {
      rejectAlert();
    }
  }
}

async function setAdminLog(target, content, axios) {
  try {
    await axios.post('/set_admin_log', {
      name: getAccountInfo().name,
      target: target,
      content: content,
      log_date: new Date()
    }, {
      headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
    });
  } catch (error) {
    rejectAlert();
  }
}

function getAccountInfo() {
  try {
    return JSON.parse(window.sessionStorage.getItem('user-info'));
  } catch (error) {
    rejectAlert();
  }
}