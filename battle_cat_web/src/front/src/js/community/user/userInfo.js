import { removeSessionStorage } from '../../util/value.js';

export { descriptionChange, nameChange, codeChange, profileImgChange, userRemove };

async function descriptionChange(userInfo, update, axios) {
  update.description = !update.description;

  if(!update.description) {
    if(userInfo.beforeDescription !== userInfo.description) {
      if(userInfo.description.length <= 100) {
        let { data } = await axios.post('/change_description', {
          email: userInfo.email,
          description: userInfo.description
        });

        if(data > 0) {
          const info = JSON.parse(window.sessionStorage.getItem('user-info'));
          info.description = userInfo.description;
          window.sessionStorage.setItem('user-info', JSON.stringify(info));
          alert('자기 소개 문구가 성공적으로 수정되었습니다!');
        }
      } else {
        alert('자기 소개 문구는 최대 100자까지 입력하실 수 있습니다');
        update.description = true;
      }
    }
  } else userInfo.beforeDescription = userInfo.description;
}

async function nameChange(userInfo, update, axios) {
  update.name = !update.name;

  if(!update.name) {
    if(userInfo.beforeName !== userInfo.name) {
      let { data:check } = await axios.post('/check_name', {
        name: userInfo.name
      });

      if(check) {
        let { data } = await axios.post('/change_name', {
          email: userInfo.email,
          name: userInfo.name
        });

        if(data > 0) {
          const info = JSON.parse(window.sessionStorage.getItem('user-info'));
          info.name = userInfo.name;
          window.sessionStorage.setItem('user-info', JSON.stringify(info));
          alert('닉네임이 성공적으로 변경되었습니다!');
          location.href = `/userInfo/${userInfo.name}`;
        }
        else alert('닉네임 변경 실패');
      } else {
        alert('중복된 닉네임입니다');
        update.name = true;
      }
    }
  } else userInfo.beforeName = userInfo.name;
}

async function codeChange(userInfo, update, axios) {
  update.code = !update.code;

  if(!update.code) {
    if(userInfo.beforeCode !== userInfo.code) {
      let { data:check } = await axios.post('/check_code', {
        code: userInfo.code
      });

      if(check) {
        let { data } = await axios.post('/change_code', {
          email: userInfo.email,
          code: userInfo.code
        });

        if(data > 0) {
          const info = JSON.parse(window.sessionStorage.getItem('user-info'));
          info.code = userInfo.code;
          window.sessionStorage.setItem('user-info', JSON.stringify(info));
          alert('문의 코드가 성공적으로 변경되었습니다!');
          location.reload();
        }
        else alert('문의 코드 변경 실패');
      } else {
        alert('중복된 문의 코드입니다! 이미 가입된 계정이 있는지 다시 한 번 확인해주세요!');
        update.code = true;
      }
    }
  } else userInfo.beforeCode = userInfo.code;
}

async function profileImgChange(userInfo, value, axios) {
  let { data } = await axios.post('/change_profile_img', {
    email: userInfo.email,
    profile_img: value
  });

  if(data > 0) {
    const info = JSON.parse(window.sessionStorage.getItem('user-info'));
    info.profile_img = value;
    window.sessionStorage.setItem('user-info', JSON.stringify(info));
    alert('프로필 이미지가 성공적으로 변경되었습니다!');
    location.reload();
  } else alert('프로필 이미지 변경 실패');
}

async function userRemove(userInfo, axios) {
  if(confirm('정말로 회원 탈퇴를 진행하시겠습니까??')) {
    let { data } = await axios.post('user_remove', {
      email: userInfo.email,
      password: userInfo.password,
    });

    if(data > 0) {
      removeSessionStorage(['jwt-auth-token', 'user-info']);
      alert('회원 탈퇴 성공!');
      location.href = '/login';
    } else alert('비밀번호가 맞지 않습니다! 다시 한 번 비밀번호를 확인해주세요');
  } else alert('회원 탈퇴가 취소되었습니다!');
}