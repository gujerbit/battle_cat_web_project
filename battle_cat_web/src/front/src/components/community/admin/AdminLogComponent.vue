<template>
  <div id="admin-log">
    <div class="log">
      <div class="content" v-for="value in log.current" :key="value">
        <p>
          <router-link class="name" :to="`/userInfo/${value.name}`">{{value.name}}</router-link>
          {{value.content.split(`${value.name}`)[1].split(`${value.target}`)[0]}}
          <router-link class="target" :to="`/userInfo/${value.target}`">{{value.target}}</router-link>
          {{value.content.split(`${value.name}`)[1].split(`${value.target}`)[1]}}
        </p>
        <p>{{value.log_date}}</p>
      </div>
    </div>
    <div class="pages">
      <p class="prev" @click="prevPage()">&lt;</p>
      <p :class="'page ' + `${pageInfo.currentPage === value ? 'select' : ''}`" v-for="value in pageInfo.totalPage[pageInfo.viewPage]" :key="value" @click="selectPage(value)">{{value}}</p>
      <p class="next" @click="nextPage()">&gt;</p>
    </div>
  </div>
</template>

<script>
import { ref, onBeforeMount, getCurrentInstance } from 'vue';
import { pagination, pageDivision } from '../../../js/util/pagination.js';
import { rejectAlert } from '../../../js/util/alert.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const log = ref({
      data: [],
      current: [],
    });

    const pageInfo = ref({
      totalPage: [], //전체 페이지
      divisionPage: 15, //한 번호에 몇 개씩 데이터를 보여줄 것인지 정하는 변수
      currentPage: 1, //현재 페이지
      viewPage: 0, //현재 번호
    });

    const nextPage = () => { //다음 페이지
      if(pageInfo.value.totalPage.length - 1 > pageInfo.value.viewPage) { //전체 페이지 수보다 현재 페이지 수가 적다면
        pageInfo.value.viewPage++; //현재 페이지 수 증가
        pageInfo.value.currentPage = pageInfo.value.totalPage[pageInfo.value.viewPage][0]; //해당 페이지에 있는 번호 중 가장 앞 번호를 현재 번호로 설정
        contentUpdate(); //현재 번호에 해당하는 내용 업데이트
      }
    };

    const prevPage = () => { //이전 페이지
      if(0 < pageInfo.value.viewPage) { //현재 페이지가 0보다 크다면
        pageInfo.value.viewPage--; //현재 페이지 감소
        pageInfo.value.currentPage = pageInfo.value.totalPage[pageInfo.value.viewPage][0]; //해당 페이지에 있는 번호 중 가장 앞 번호를 현재 번호로 설정
        contentUpdate(); //현재 번호에 해당하는 내용 업데이트
      }
    };

    const selectPage = (page) => { //번호 선택할 때 실행됨
      pageInfo.value.currentPage = page; //사용자가 누른 번호를 현재 번호로 설정
      contentUpdate(); //현재 번호에 해당하는 내용 업데이트
    };

    const contentUpdate = () => { //현재 번호에 해당하는 내용 업데이트
      log.value.current = pagination(log.value.data, pageInfo.value.currentPage, pageInfo.value.divisionPage); //현재 번호에 해당하는 내용 페이지네이션
      pageInfo.value.totalPage = pageDivision(log.value.data, pageInfo.value.divisionPage); //현재 요일 전체 데이터를 기반으로 총 페이지 수 정하기
    };

    onBeforeMount(async () => {
      const temp = 1;

      if(temp !== 1) {
        try {
          let { data:size } = await proxy.axios.get('/get_admin_log_size', {
            headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
          });

          const sizeArr = [];
          const data = [];

          for(let i = 0; i < Math.ceil(size / 100); i++) sizeArr.push(i * 100);

          for(let i = 0; i < sizeArr.length; i++) {
            let { data:logs } = await proxy.axios.get(`/get_admin_log/${sizeArr[i]}`, {
              headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
            });

            for(let j = 0; j < logs.length; j++) data.push(logs[j]);
          }

          log.value.data = data.reverse();
          contentUpdate();
        } catch (error) {
          rejectAlert();
        }
      }
    });

    return { log, pageInfo, nextPage, prevPage, selectPage };
  }
}
</script>

<style scoped src="../../../css/community/admin/adminLogComponent.css" />