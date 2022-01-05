<template>
  <main>
    <div class="wrap">
      <enemy-info-element-component />
      <section>
        <div class="content">
          <router-link :to="`/enemyInfo/${value.id}`" class="unit-content" v-for="value in info.current" :key="value">
            <img :src="require(`./../../assets/res/enemy/${value.image_dir}`)" alt="">
            <p>{{value.name.length > 10 ? value.name.substring(0, 9) + '...' : value.name}}</p>
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
import { getEnemyInfo, getSearchEnemyInfo } from '../../js/enemy/enemyInfo.js';
import { pagination, pageDivision } from '../../js/util/pagination.js';

import enemyInfoElementComponent from './EnemyInfoElementComponent.vue';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const info = ref({
      all: [],
      current: [],
    });

    const pageInfo = ref({
      totalPage: [], //전체 페이지
      divisionPage: 36, //한 번호에 몇 개씩 데이터를 보여줄 것인지 정하는 변수
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
      proxy.store.commit('setSearchEnemyInfo', []);
      info.value.all = getEnemyInfo(proxy.store);
      contentUpdate();
    });

    watchEffect(() => {
      if(getSearchEnemyInfo(proxy.store) !== undefined) { //검색 값이 존재한다면
        if(getSearchEnemyInfo(proxy.store).length > 0) { //검색 결과가 존재한다면
          info.value.current = pagination(getSearchEnemyInfo(proxy.store), pageInfo.value.currentPage, pageInfo.value.divisionPage); //현재 번호에 해당하는 검색 결과 페이지네이션
          pageInfo.value.totalPage = pageDivision(getSearchEnemyInfo(proxy.store), pageInfo.value.divisionPage); //페이지 배열 설정
        } else { //검색 결과가 존재하지 않는다면
          info.value.current = []; //현재 값 초기화
          pageInfo.value.totalPage = [1]; //전체 페이지 번호 1로 고정
        }
      } else if(info.value.all.length > 0) contentUpdate(); //검색 값이 존재하지 않는다면 현재 번호에 해당하는 내용 업데이트
    });

    return { info, pageInfo, nextPage, prevPage, selectPage };
  },
  components: {
    enemyInfoElementComponent
  }
}
</script>

<style scoped>
main {
  width: 100%;
  height: 100%;
}

main * {
  text-decoration: none;
}
  
.wrap {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: 45% 54%;
  margin-top: 0.25%;
}
  
section {
  width: 90%;
  height: 100%;
  margin: 0 auto;
  display: grid;
  grid-template-rows: 92.5% 7.5%;
}
  
.content {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(9, 1fr);
  grid-template-rows: repeat(4, 1fr);
  justify-items: center;
  align-items: center;
  overflow: auto;
}
  
.content::-webkit-scrollbar {
  display: none;
}
  
.unit-content {
  width: 100%;
  height: 100%;
  transition: all 0.25s;
}

.unit-content:hover {
  opacity: 0.5;
}
  
.unit-content img {
  width: 100%;
  height: 70%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
  border: 1.5px solid #ffc038;
  border-top: none;
  border-left: none;
}

.unit-content:nth-child(9n - 8) img, .unit-content:nth-child(9n - 8) p {
  border-left: 1.5px solid #ffc038;
}

.unit-content:nth-child(n + 28) p {
  border-bottom: none;
}
  
.unit-content p {
  width: 100%;
  height: 30%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
  font-size: 2.3rem;
  border: 1.5px solid #ffc038;
  border-top: none;
  border-left: none;
}
  
.pages {
  width: 100%;
  height: 100%;
  margin: 0 auto;
  display: flex;
  justify-content: space-around;
  align-items: center;
  border: 1.5px solid #ffc038;
}
  
.pages p {
  width: 2.5%;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}
  
.page {
  width: 100%;
  height: 100%;
  font-size: 2.8rem;
}
  
.select {
  color: #ffc038;
  pointer-events: none;
}
  
.prev, .next {
  width: 100%;
  height: 100%;
  font-size: 2.8rem;
}
  
.main-page {
  width: 4.5%;
  height: 100%;
  border: 2px solid #ffc038;
  border-left: none;
  border-radius: 0 15px 15px 0;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  position: absolute;
  top: 0;
  left: 0;
  writing-mode: vertical-lr;
  display: flex;
  justify-content: center;
  align-items: center;
  opacity: 0;
  transition: all 1.5s;
  font-size: 3rem;
}
  
.main-page:hover {
  opacity: 1;
}
</style>