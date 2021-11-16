export { checkPassword, checkCheckPassword, checkName, checkCode, checkEmail };

const specialPattern = /[!@#$%^&*()_+={}:;<>?/|.,`~'"[\]\\-]/;
const includePattern = /[!@#$%^&*?.]/;
const decludePattern = /[()_+={}:;<>/|,`~'"[\]\\-]/;
const numberPattern = /[0-9]/;
const emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;

function checkPassword(value, tip) {
  if(value.password.length !== 0) {
    if(value.password.length < 8 || value.password.length > 20) tip.password = '비밀번호는 8자리 이상, 20자리 이하여야 합니다.';
    else if(value.password.search(/\s/) != -1) tip.password = '공백은 포함하실 수 없습니다.';
    else if(decludePattern.test(value.password)) tip.password = '해당 특수문자는 포함하실 수 없습니다.';
    else if(!includePattern.test(value.password)) tip.password = '!,@,#,$,%,^,&,*,?,.중 하나가 포함되어야 합니다.';
    else if(numberPattern.test(value.password.split('')[0])) tip.password = '처음은 문자로 시작하셔야 합니다.';
    else tip.password = '';
  } else tip.password = '';
}

function checkCheckPassword(value, tip) {
  if(value.checkPassword.length !== 0) {
    if(value.checkPassword !== value.password) tip.checkPassword = '비밀번호가 맞지 않습니다.';
    else tip.checkPassword = '';
  } else tip.checkPassword = '';
}

function checkName(value, tip) {
  if(value.name.length !== 0) {
    if(value.name.length > 20) tip.name = '닉네임은 20자리 이하여야 합니다.';
    else if(value.name.search(/\s/) != -1) tip.name = '공백은 포함하실 수 없습니다.';
    else if(decludePattern.test(value.name)) tip.name = '해당 특수문자는 포함하실 수 없습니다.';
    else tip.name = '';
  } else tip.name = '';
}

function checkCode(value, tip) {
  if(value.code.length !== 0) {
    if(value.code.length !== 9) tip.code = '문의코드는 9자리입니다.';
    else if(value.code.search(/\s/) != -1) tip.code = '공백은 포함하실 수 없습니다.';
    else if(specialPattern.test(value.code)) tip.code = '해당 특수문자는 포함하실 수 없습니다.';
    else tip.code = '';
  } else tip.code = '';
}

function checkEmail(value, tip) {
  if(!emailPattern.test(value.email) && value.email.length > 0) tip.email = '올바른 이메일을 입력해주세요';
  else tip.email = '';
}