import { clearValue } from "../../util/value.js";

export { findEmail, getPasswordChangeCode, checkPasswordChangeCode, changePassword };

async function findEmail(find, show, result, axios) {
  let { data } = await axios.post('/find_email', {
    code: find.code
  });

  if(data.length > 0) {
    show.emailResult = true;
    result.email = data;
  } else alert('이메일 찾기 실패. 문의코드를 다시 한 번 확인해주세요');
}

async function getPasswordChangeCode(find, result, axios) {
  document.querySelector('.loading').style.display = 'flex';

  let { data } = await axios.post('/get_password_change_code', {
    email: find.email,
    code: find.code
  });

  if(!data) {
    alert('코드 발급 실패. 이메일과 문의코드를 다시 한 번 확인해주세요');
    document.querySelector('.loading').style.display = 'none';
  }
  else {
    alert('해당 이메일로 회원가입 코드를 전송했습니다!');
    document.querySelector('.loading').style.display = 'none';

    let temp = {
      email: find.email,
      passwordChangeCode: data,
      expireTime: new Date().setMinutes(new Date().getMinutes() + 10),
    };

    window.sessionStorage.setItem('passwordChangeInfo', JSON.stringify(temp));
    result.email = find.email;
    result.passwordChangeCode = data;
    result.expireTime = temp.expireTime;
  }
}

function checkPasswordChangeCode(find, result, show) {
  if(result.passwordChangeCode === find.passwordChangeCode) {
    let today = new Date();

    if(result.expireTime - today.getTime() <= 0) {
      alert('만료된 회원가입 코드입니다');
      window.sessionStorage.removeItem('passwordChangeInfo');
      location.reload();
    } else {
      alert('회원가입 코드 확인 완료');
      show.passwordChangeCodeCheck = true;
    }
  } else alert('올바르지 않은 비밀번호 변경 코드입니다');
}

async function changePassword (find, result, axios) {
  let { data } = await axios.post('/change_password', {
    email: result.email,
    password: find.password,
  });

  if(data > 0) {
    alert('성공적으로 비밀번호를 변경하였습니다');
    clearValue(find);
    clearValue(result);
    location.href = '/login';
  } else alert('비밀번호를 변경 중 문제가 발생했습니다');
}