import { removeSessionStorage } from '../../util/value.js';
import { rejectAlert } from '../../util/alert.js';
import { getAccountInfo } from '../../community/admin/admin.js';

export { descriptionChange, nameChange, codeChange, profileImgChange, userRemove, searchUser };

async function descriptionChange(description, beforeDescription, update, axios) {
  update.description = !update.description;

  if(!update.description) {
    if(beforeDescription !== description) {
      if(description.length <= 100) {
        try {
          let { data } = await axios.post('/change_description', {
            email: getAccountInfo().email,
            description: description
          }, {
            headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
          });

          if(data > 0) alert('자기 소개 문구가 성공적으로 수정되었습니다!');
          else alert('자기 소개 문구 변경 실패');

          location.reload();
        } catch (error) {
          rejectAlert();
        }
      } else {
        alert('자기 소개 문구는 최대 100자까지 입력하실 수 있습니다');
        update.description = true;
      }
    }
  } else beforeDescription = description;
}

async function nameChange(name, beforeName, update, axios) {
  update.name = !update.name;

  if(!update.name) {
    if(beforeName !== name) {
      try {
        let { data:check } = await axios.post('/check_name', {
          name: name
        }, {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });

        if(check) {
          try {
            let { data } = await axios.post('/change_name', {
              email: getAccountInfo().email,
              name: name
            }, {
              headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
            });
    
            if(data > 0) {
              let user = getAccountInfo();
              user.name = name;
              window.sessionStorage.setItem('user-info', JSON.stringify(user));

              alert('닉네임이 성공적으로 변경되었습니다!');
              location.href = `/userInfo/${name}`;
            }
            else {
              alert('닉네임 변경 실패');
              location.reload();
            }
          } catch (error) {
            rejectAlert();
          }
        } else {
          alert('중복된 닉네임입니다');
          update.name = true;
        }
      } catch (error) {
        rejectAlert();
      }
    }
  } else beforeName = name;
}

async function codeChange(code, beforeCode, update, axios) {
  update.code = !update.code;

  if(!update.code) {
    if(beforeCode !== code) {
      // try {
      //   let { data:check } = await axios.post('/check_code', {
      //     code: code
      //   }, {
      //     headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
      //   });
  
      //   if(check) {
          
      //   } else {
      //     alert('중복된 문의 코드입니다! 이미 가입된 계정이 있는지 다시 한 번 확인해주세요!');
      //     update.code = true;
      //   }
      // } catch (error) {
      //   rejectAlert();
      // }
      try {
        let { data } = await axios.post('/change_code', {
          email: getAccountInfo().email,
          code: code
        }, {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });

        if(data > 0) alert('문의 코드가 성공적으로 변경되었습니다!');
        else alert('문의 코드 변경 실패');

        location.reload();
      } catch (error) {
        rejectAlert();
      }
    }
  } else beforeCode = code;
}

async function profileImgChange(userInfo, value, axios) {
  try {
    let { data } = await axios.post('/change_profile_img', {
      email: userInfo.email,
      profile_img: value
    }, {
      headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
    });
  
    if(data > 0) {
      const info = JSON.parse(window.sessionStorage.getItem('user-info'));
      info.profile_img = value;
      window.sessionStorage.setItem('user-info', JSON.stringify(info));
      alert('프로필 이미지가 성공적으로 변경되었습니다!');
      location.reload();
    } else alert('프로필 이미지 변경 실패');
  } catch (error) {
    rejectAlert();
  }
}

async function userRemove(userInfo, axios) {
  if(confirm('정말로 회원 탈퇴를 진행하시겠습니까??')) {
    try {
      let { data } = await axios.post('user_remove', {
        email: userInfo.email,
        password: userInfo.password,
      }, {
        headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
      });
  
      if(data > 0) {
        removeSessionStorage(['jwt-auth-token', 'user-info']);
        alert('회원 탈퇴 성공!');
        location.href = '/login';
      } else alert('비밀번호가 맞지 않습니다! 다시 한 번 비밀번호를 확인해주세요');
    } catch (error) {
      rejectAlert();
    }
  } else alert('회원 탈퇴가 취소되었습니다!');
}

function searchUser(data, value, grade) {
  let result = [];
  
  data.forEach(res => {
    if(value.length > 0) {
      if(res.name.includes(value)) {
        if(grade === res.grade || grade === 'all') result.push(res);
      }
    } else {
      if(grade === res.grade || grade === 'all') result.push(res);
    }
  });

  return result;
}