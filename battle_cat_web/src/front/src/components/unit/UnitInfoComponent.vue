<template>
  <main>
    <div class="wrap">
      <element-component />
      <section>
        <div class="content">
          <router-link :to="`/unitInfo/${value.id.split('-')[0]}`" class="unit-content" v-for="value in info.current" :key="value">
            <img :src="require(`./../../assets/res/unit/${value.image_dir}`)" alt="">
            <p>{{value.name.length > 9 ? value.name.substring(0, 8) + '..' : value.name}}</p>
          </router-link>
        </div>
        <div class="pages">
          <p class="prev" @click="prevPage()">&lt;</p>
          <p :class="'page ' + `${pageInfo.currentPage === value ? 'select' : ''}`" v-for="value in pageInfo.totalPage[pageInfo.viewPage]" :key="value" @click="selectPage(value)">{{value}}</p>
          <p class="next" @click="nextPage()">&gt;</p>
        </div>
      </section>
    </div>
    <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
  </main>
</template>

<script>
import { ref, getCurrentInstance, onBeforeMount, watchEffect } from 'vue';
import { getUnitInfo, getSearchUnitInfo } from '../../js/unit/unitInfo.js';
import { pagination, pageDivision } from '../../js/util/pagination.js';
import elementComponent from './UnitInfoElementComponent.vue'

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const info = ref({
      all: [], //전체 유닛
      current: [], //페이지네이션
    });

    const pageInfo = ref({
      totalPage: [], //전체 페이지
      divisionPage: 27, //한 번호에 몇 개씩 데이터를 보여줄 것인지 정하는 변수
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
      info.value.current = pagination(info.value.all, pageInfo.value.currentPage, pageInfo.value.divisionPage); //현재 번호에 해당하는 내용 페이지네이션
      pageInfo.value.totalPage = pageDivision(info.value.all, pageInfo.value.divisionPage); //현재 요일 전체 데이터를 기반으로 총 페이지 수 정하기
    };

    onBeforeMount(() => { //dom 부착 전
      // const loadData = setInterval(() => {
      //   if(getUnitInfo(proxy.store) !== undefined) {
          
      //     clearInterval(loadData);
      //   }
      // }, 100);
      info.value.all = getUnitInfo(proxy.store);
      contentUpdate();
    });

    watchEffect(() => {
      if(getSearchUnitInfo(proxy.store) !== undefined) {
        if(getSearchUnitInfo(proxy.store).length > 0) {
          info.value.current = pagination(getSearchUnitInfo(proxy.store), pageInfo.value.currentPage, pageInfo.value.divisionPage); //현재 번호에 해당하는 내용 페이지네이션
          pageInfo.value.totalPage = pageDivision(getSearchUnitInfo(proxy.store), pageInfo.value.divisionPage); //현재 요일 전체 데이터를 기반으로 총 페이지 수 정하기
        } else {
          info.value.current = [];
          pageInfo.value.totalPage = [1];
        }
      } else if(info.value.all.length > 0) contentUpdate();
    });

    return { pageInfo, info, nextPage, prevPage, selectPage };
  },
  components: {
    elementComponent,
  },
}
</script>

<style scoped src="../../css/unit/unitInfoComponent.css" />