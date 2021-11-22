export { userReject, userForeverReject, userRejectRelease };

async function userReject(name, value, axios) {
  if(confirm(`정말 해당 사용자를 ${value}일 차단하시겠습니까?`)) {
    let time = value * 24 * 60 * 60 * 1000;
    let rejectEndDate = new Date(new Date().getTime() + time);

    let { data } = await axios.post('/user_reject', {
      name: name,
      reject_end_date: rejectEndDate,
    });

    if(data > 0) {
      alert(`해당 사용자를 ${value}일 차단하였습니다`);
      setAdminLog(getAccountInfo().email, getAccountInfo().name, `${getAccountInfo().name}(${emailSetting(getAccountInfo().email)})님이 ${name}님을 ${value}일 차단하였습니다`, axios);
    }
    else {
      alert('차단 실패');
      setAdminLog(getAccountInfo().email, getAccountInfo().name, `${getAccountInfo().name}(${emailSetting(getAccountInfo().email)})님이 ${name}님을 ${value}일 차단하는데 실패하였습니다`, axios);
    }

    location.reload();
  }
}

async function userForeverReject(name, axios) {
  if(confirm('정말 해당 사용자를 영구 차단하시겠습니까?')) {
    let { data } = await axios.post('/user_forever_reject', {
      name: name
    });

    if(data > 0) {
      alert('해당 사용자를 영구 차단하였습니다');
      setAdminLog(getAccountInfo().email, getAccountInfo().name, `${getAccountInfo().name}(${emailSetting(getAccountInfo().email)})님이 ${name}님을 영구 차단하였습니다`, axios);
    }
    else {
      alert('차단 실패');
      setAdminLog(getAccountInfo().email, getAccountInfo().name, `${getAccountInfo().name}(${emailSetting(getAccountInfo().email)})님이 ${name}님을 영구 차단하는데 실패하였습니다`, axios);
    }

    location.reload();
  }
}

async function userRejectRelease(name, axios) {
  if(confirm('정말 해당 사용자의 차단을 해제하시겠습니까?')) {
    let { data } = await axios.post('/user_reject_release', {
      name: name,
      reject_end_date: new Date()
    });

    if(data > 0) {
      alert('해당 사용자의 차단을 해제하였습니다');
      setAdminLog(getAccountInfo().email, getAccountInfo().name, `${getAccountInfo().name}(${emailSetting(getAccountInfo().email)})님이 ${name}님을 차단 해제하였습니다`, axios);
    }
    else {
      alert('차단 해제 실패');
      setAdminLog(getAccountInfo().email, getAccountInfo().name, `${getAccountInfo().name}(${emailSetting(getAccountInfo().email)})님이 ${name}님을 차단 해체하는데 실패하였습니다`, axios);
    }

    location.reload();
  }
}

async function setAdminLog(email, name, content, axios) {
  await axios.post('/set_admin_log', {
    email: email,
    name: name,
    content: content,
    log_date: new Date()
  });
}

function getAccountInfo() {
  return JSON.parse(window.sessionStorage.getItem('user-info'));
}

function emailSetting(email) {
  let showEmail = email.substring(0, 3);

  for(let i = 0; i < email.split('@')[0].substring(3).length; i++) showEmail += '*';

  return showEmail + '@' + email.split('@')[1];
}