<template>
  <main>
      <div class="user-info" v-for="value in userInfo.data" :key="value">
        <div class="summary">
          <div class="profile-img">
            <img v-if="value.email !== undefined" :src="require(`../../../assets/res/unit/${value.profileImg}`)" alt="">
            <img v-else :src="require(`../../../assets/res/unit/${value.profileImg}`)" alt="">
          </div>
          <div class="account">
            <p class="name">{{value.name}}
              <span class="grade">[{{value.grade === 'user' ? '유저' : '관리자'}}]</span>
              <span class="reject" v-if="value.foreverReject">[영구 차단됨]</span>
              <span class="reject" v-else-if="new Date(value.rejectEndDate).getTime() - new Date().getTime() > 0">[차단됨 {{new Date(new Date(value.rejectEndDate).getTime() - new Date().getTime()).getDate() - 1}}일 남음]</span>
            </p>
            <p v-if="value.email !== undefined">이메일: {{value.email}}</p>
            <p v-if="value.code !== undefined">문의 코드: {{value.code}}</p>
            <p>가입날짜: {{value.regDate}}</p>
          </div>
          <div class="update">
            <button>정보 수정</button>
          </div>
        </div>
        <div class="description">
          <div class="description-header">
            <p class="title">자기 소개</p>
            <button v-if="value.email !== undefined" @click="descriptionChange(value.email)">{{update.description ? '완료' : '수정'}}</button>
            <p class="length" v-if="update.description" :style="userInfo.description.length > 100 ? { color: '#f11212' } : ''">{{userInfo.description.length}}</p>
          </div>
          <textarea v-model="userInfo.description" :disabled="!update.description" />
        </div>
      </div>
      <button @click="logout()">logout</button>
      <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
      <router-link to="/community" class="community-page">커뮤니티 화면으로 돌아가기</router-link>
  </main>
</template>

<script>
import { ref, onBeforeMount, getCurrentInstance } from 'vue';
import { useRoute } from 'vue-router';

export default {
  setup() {
    const route = useRoute();
    const { proxy } = getCurrentInstance();

    const userInfo = ref({
      data: [],
      description: '',
      beforeDescription: '',
    });

    const update = ref({
      info: false,
      description: false,
    });

    const descriptionChange = async (email) => {
      update.value.description = !update.value.description;

      if(!update.value.description) {
        if(userInfo.value.beforeDescription !== userInfo.value.description) {
          if(userInfo.value.description.length <= 100) {
            let { data } = await proxy.axios.post('/user_description_change', {
              email: email,
              description: userInfo.value.description
            });

            if(data > 0) {
              const info = JSON.parse(window.sessionStorage.getItem('user-info'));
              info.description = userInfo.value.description;
              window.sessionStorage.setItem('user-info', JSON.stringify(info));
              alert('자기 소개 문구가 성공적으로 수정되었습니다!');
            }
          } else {
            alert('자기 소개 문구는 최대 100자까지 입력하실 수 있습니다');
            update.value.description = true;
          }
        }
      } else userInfo.value.beforeDescription = userInfo.value.description;
    };

    const logout = () => {
      alert('로그아웃 완료');
      window.sessionStorage.removeItem('jwt-auth-token');
      window.sessionStorage.removeItem('user-info');
      location.href = '/login';
    };

    const getUserInfo = async (name) => {
      let { data } = await proxy.axios.get(`/user_info/${name}`);
      const info = {
        name: data.name,
        grade: data.grade,
        regDate: data.reg_date,
        profileImg: data.profile_img,
        description: data.description,
        rejectEndDate: data.reject_end_date,
        foreverReject: data.forever_reject,
        reputation: data.reputation,
      };

      userInfo.value.data.push(info);
      userInfo.value.description = data.description;
    };

    onBeforeMount(() => {
      const userName = route.params.userName;
      const temp = JSON.parse(window.sessionStorage.getItem('user-info'));

      if(userName === temp.name) {
        const info = {
          email: temp.email,
          name: temp.name,
          code: temp.code,
          grade: temp.grade,
          regDate: temp.reg_date,
          profileImg: temp.profile_img,
          description: temp.description,
          reportCount: temp.report_count,
          rejectEndDate: temp.reject_end_date,
          foreverReject: temp.forever_reject,
          reputation: temp.reputation,
        };

        userInfo.value.data.push(info);
        userInfo.value.description = temp.description;
        
      } else getUserInfo(userName);
    });

    return { userInfo, update, logout, descriptionChange };
  }
}
</script>

<style scoped>
main {
  width: 100%;
  height: 100%;
}

.user-info {
  width: 90%;
  height: 100%;
  margin: 0 auto;
  margin-top: 0.5%;
}

.summary {
  width: 100%;
  height: 25%;
  border: 2px solid #ffc038;
  border-radius: 15px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.profile-img {
  width: 20%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.profile-img img {
  width: 80%;
  height: 80%;
}

.account {
  width: 70%;
  height: 100%;
  display: grid;
  justify-items: left;
  align-items: center;
}

.account p, .grade, .reject {
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 3rem;
}

.name {
  font-size: 5rem !important;
}

.grade, .reject {
  font-size: 4.5rem !important;
}

.reject {
  color: #f11212;
}

.update {
  width: 10%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: flex-start;
}

.update button {
  width: 80%;
  height: 20%;
  background-color: #ffffff;
  border: 2px solid #ffc038;
  border-radius: 15px;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.3rem;
  margin-top: 5%;
}

.description {
  width: 100%;
  height: 20%;
  border: 2px solid #ffc038;
  border-radius: 15px;
  margin-top: 0.5%;
  padding: 1%;
}

.description-header {
  width: 100%;
  height: 25%;
  display: flex;
  justify-content: left;
  align-items: center;
  margin-bottom: 0.5%;
}

.title {
  width: 10%;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 3rem;
}

.description-header button {
  width: 7%;
  height: 95%;
  background-color: #ffffff;
  border: 2px solid #ffc038;
  border-radius: 15px;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.3rem;
}

.description textarea {
  width: 100%;
  height: 70%;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 3rem;
  resize: none;
}

.description textarea::-webkit-scrollbar {
  display: none;
}

.description-header .length {
  width: 82%;
  text-align: right;
  font-size: 2rem;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
}

button {
  transition: all 1s;
  cursor: pointer;
}

button:hover {
  transform: scale(95%);
}

.main-page, .community-page {
  width: 4.5%;
  height: 100%;
  border: 2px solid #ffc038;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  position: absolute;
  top: 0;
  writing-mode: vertical-lr;
  display: flex;
  justify-content: center;
  align-items: center;
  opacity: 0;
  transition: all 1.5s;
  font-size: 3rem;
}

.main-page {
  border-left: none;
  border-radius: 0 15px 15px 0;
  left: 0;
}

.community-page {
  border-right: none;
  border-radius: 15px 0 0 15px;
  right: 0;
}
  
.main-page:hover, .community-page:hover {
  opacity: 1;
}
</style>