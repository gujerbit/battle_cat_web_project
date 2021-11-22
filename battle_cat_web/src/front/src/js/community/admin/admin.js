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
      location.reload();
    } else alert('차단 실패');
  }
}

async function userForeverReject(name, axios) {
  if(confirm('정말 해당 사용자를 영구 차단하시겠습니까?')) {
    let { data } = await axios.post('/user_forever_reject', {
      name: name
    });

    if(data > 0) {
      alert('해당 사용자를 영구 차단하였습니다');
      location.reload();
    } else alert('차단 실패');
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
      location.reload();
    }
  }
}