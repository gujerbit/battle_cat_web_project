<template>
  <main>
    <div class="wrap">
      <element-component />
      <section>
        <div class="content">
          <router-link :to="`/unitInfo/${value.id.split('-')[0]}`" class="unit-content" v-for="value in info.current" :key="value">
            <img :src="require(`./../../assets/res/unit/${value.image_dir}`)" alt="">
            <p>{{value.name.length > 10 ? value.name.substring(0, 9) + '..' : value.name}}</p>
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
      current: [], //현재 유닛
    });

    const pageInfo = ref({
      totalPage: [], //전체 페이지
      divisionPage: 27, //한 번호에 몇 개씩 데이터를 보여줄 것인지 정하는 변수
      currentPage: 1, //현재 페이지
      viewPage: 0, //현재 페이지 배열의 index
    });
    //다음 페이지 index
    const nextPage = () => {
      if(pageInfo.value.totalPage.length - 1 > pageInfo.value.viewPage) { //현재 페이지 배열의 인덱스가 전체 페이지 배열의 크기 보다 작다면
        pageInfo.value.viewPage++; //현재 페이지 배열의 index 증가
        pageInfo.value.currentPage = pageInfo.value.totalPage[pageInfo.value.viewPage][0]; //해당 페이지 배열의 index에 있는 번호 중 가장 앞 번호를 현재 번호로 설정
        contentUpdate(); //현재 번호에 해당하는 내용 업데이트
      }
    };
    //이전 페이지 index
    const prevPage = () => {
      if(0 < pageInfo.value.viewPage) { //현재 페이지 배열의 index가 0보다 크다면
        pageInfo.value.viewPage--; //현재 페이지 배열의 index 감소
        pageInfo.value.currentPage = pageInfo.value.totalPage[pageInfo.value.viewPage][0]; //해당 페이지 배열의 index에 있는 번호 중 가장 앞 번호를 현재 번호로 설정
        contentUpdate(); //현재 번호에 해당하는 내용 업데이트
      }
    };
    //현재 번호 설정
    const selectPage = (page) => {
      pageInfo.value.currentPage = page; //번호 설정
      contentUpdate(); //현재 번호에 해당하는 내용 업데이트
    };
    //현재 번호에 해당하는 내용 업데이트
    const contentUpdate = () => {
      info.value.current = pagination(info.value.all, pageInfo.value.currentPage, pageInfo.value.divisionPage); //현재 번호에 해당하는 내용 페이지네이션
      pageInfo.value.totalPage = pageDivision(info.value.all, pageInfo.value.divisionPage); //페이지 배열 설정
    };

    onBeforeMount(() => {
      proxy.store.commit('setSearchUnitInfo', []); //검색 값 초기화
      info.value.all = getUnitInfo(proxy.store); //전체 유닛 데이터 받아오기
      contentUpdate(); //현재 번호에 해당하는 내용 업데이트
    });

    watchEffect(() => {
      if(getSearchUnitInfo(proxy.store) !== undefined) { //검색 값이 존재한다면
        if(getSearchUnitInfo(proxy.store).length > 0) { //검색 결과가 존재한다면
          info.value.current = pagination(getSearchUnitInfo(proxy.store), pageInfo.value.currentPage, pageInfo.value.divisionPage); //현재 번호에 해당하는 검색 결과 페이지네이션
          pageInfo.value.totalPage = pageDivision(getSearchUnitInfo(proxy.store), pageInfo.value.divisionPage); //페이지 배열 설정
        } else { //검색 결과가 존재하지 않는다면
          info.value.current = []; //현재 값 초기화
          pageInfo.value.totalPage = [1]; //전체 페이지 번호 1로 고정
        }
      } else if(info.value.all.length > 0) contentUpdate(); //검색 값이 존재하지 않는다면 현재 번호에 해당하는 내용 업데이트
    });

    return { info, pageInfo, nextPage, prevPage, selectPage };
  },
  components: {
    elementComponent,
  },
}
</script>

<style scoped src="../../css/unit/unitInfoComponent.css" />