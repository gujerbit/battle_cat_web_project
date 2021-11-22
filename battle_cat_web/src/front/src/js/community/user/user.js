import { removeSessionStorage } from "../../util/value.js";

export { login, getRegisterCode, checkRegisterCode, register, logout, checkReject };

async function login(loginInfo, axios) {
  if(loginInfo.email.length > 0 && loginInfo.password.length > 0) {
    let { data, headers } = await axios.post('/login_process', {
      email: loginInfo.email,
      password: loginInfo.password,
    });

    if(data.status) {
      window.sessionStorage.setItem('jwt-auth-token', headers['jwt-auth-token']);
      window.sessionStorage.setItem('user-info', JSON.stringify(data.data));

      alert('로그인 성공!');
      location.href = '/community';
    } else {
      alert('로그인 실패. ID와 비밀번호를 다시 한 번 확인해주세요.');
    }
  } else {
    alert('ID와 비밀번호를 모두 입력해주세요');
  }
}

async function getRegisterCode(registerInfo, axios) {
  document.querySelector('.loading').style.display = 'flex';

  let { data:idCheck } = await axios.post('/check_email', {
    email: registerInfo.email
  });

  if(idCheck) {
    let { data:registerCode } = await axios.post('/register_code', {
      email: registerInfo.email
    });

    alert('해당 이메일로 회원가입 코드를 전송했습니다!');
    document.querySelector('.loading').style.display = 'none';

    let registerTemp = {
      email: registerInfo.email,
      registerCode: registerCode,
      expireTime: new Date().setMinutes(new Date().getMinutes() + 10),
    };

    window.sessionStorage.setItem('registerInfo', JSON.stringify(registerTemp));
    registerInfo.registerCode = registerCode;
    registerInfo.expireTime = registerTemp.expireTime;
  } else {
    alert('해당 이메일은 이미 사용중입니다!');
    document.querySelector('.loading').style.display = 'none';
  }
}

function checkRegisterCode(registerInfo) {
  if(registerInfo.inputCode === registerInfo.registerCode) {
    let today = new Date();

    if(registerInfo.expireTime - today.getTime() <= 0) {
      alert('만료된 회원가입 코드입니다');
      window.sessionStorage.removeItem('registerInfo');
      location.href = '/register';
    } else {
      alert('회원가입 코드 확인 완료');
      registerInfo.registerCodeCheck = true;
      window.sessionStorage.setItem('registerCodeCheck', true);
    }
  } else alert('올바르지 않은 회원가입 코드입니다');
}

async function register(registerInfo, axios, router) {
  let { data:checkName } = await axios.post('/check_name', {
    name: registerInfo.name
  });

  if(checkName) {
    let { data:checkCode } = await axios.post('/check_code', {
      code: registerInfo.code
    });

    if(checkCode) {
      let { data } = await axios.post('/register_process', {
        email: registerInfo.email,
        password: registerInfo.password,
        name: registerInfo.name,
        code: registerInfo.code
      });

      if(data > 0) {
        alert('회원가입이 정상적으로 처리되었습니다');
        router.push('/login');
      } else {
        alert('회원가입 중 문제가 발생했습니다');
        location.reload();
      }

      removeSessionStorage(['registerInfo', 'registerCodeCheck'])
    } else alert('중복된 문의코드입니다. 이미 가입한 계정이 있는지 다시 한 번 확인해주세요');
  } else alert('중복된 닉네임입니다');
}

function logout() {
  removeSessionStorage(['jwt-auth-token', 'user-info']);
  alert('로그아웃 완료');
  location.href = '/login';
}

async function checkReject(axios) {
  if(window.sessionStorage.getItem('user-info') !== null) {
    const user = JSON.parse(window.sessionStorage.getItem('user-info'));

    let { data } = await axios.post('check_reject', {
      name: user.name,
    });

    if(data.forever_reject) {
      alert('해당 계정은 영구차단 되었습니다!');
      window.sessionStorage.removeItem('jwt-auth-token');
      window.sessionStorage.removeItem('user-info');
      location.href = '/login';
    } else if(new Date(data.reject_end_date).getTime() - new Date().getTime() > 0) {
      alert(`해당 계정은 차단된 계정입니다! 남은 차단 일수: ${new Date(new Date(data.reject_end_date).getTime() - new Date().getTime()).getDate() - 1}일`);
      window.sessionStorage.removeItem('jwt-auth-token');
      window.sessionStorage.removeItem('user-info');
      location.href = '/login';
    }
  }
}