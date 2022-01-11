<template>
  <main>
    <div class="wrap">
      <stage-info-element-component />
      <section>
        <div class="content">
          <div class="stage" v-for="value in info.current" :key="value">
            <template v-if="value != null">
              <div class="header">
                <p>스테이지 이름</p>
                <p>적 출격 정보</p>
                <p>클리어 보상</p>
                <p>소비 통솔력</p>
              </div>
              <div class="stage-content">
                <p class="name">{{value.name}}</p>
                <div class="enemy-info">
                  <div class="enemy-header">
                    <p>이미지</p>
                    <p>이름</p>
                    <p>배율</p>
                    <p>출격 수</p>
                  </div>
                  <div class="enemy-content-area">
                    <div class="enemy-content" v-for="item in value.enemy_info.split('/')" :key="item">
                      <img :src="require(`../../assets/res/enemy/${getEnemy(item.split(',')[0]).image_dir}`)" alt="">
                      <p class="enemy-name">{{getEnemy(item.split(',')[0]).name}}</p>
                      <p class="enemy-power">{{item.split(',')[1]}}%</p>
                      <p class="enemy-count">{{item.split(',')[2] > 0 ? item.split(',')[2] : '무제한'}}</p>
                    </div>
                  </div>
                </div>
                <div class="reward">
                  <div class="reward-header">
                    <p>이름</p>
                    <p>획득량</p>
                    <p>확률</p>
                  </div>
                  <div class="reward-content-area">
                    <div class="reward-content" v-for="item in value.reward.split('/')" :key="item">
                      <p class="reward-name">{{item.split(',')[0]}}</p>
                      <p class="reward-count">{{item.split(',')[1]}}</p>
                      <p class="reward-drop">{{item.split(',')[2]}}</p>
                    </div>
                  </div>
                </div>
                <p class="stamina">{{value.stamina}}</p>
              </div>
            </template>
          </div>
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

import stageInfoElementComponent from './stageInfoElementComponent.vue';
import { getSearchStageInfo } from '../../js/stage/stageInfo.js';
import { pagination, pageDivision } from '../../js/util/pagination.js';
import { getEnemyInfo } from '../../js/enemy/enemyInfo';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const info = ref({
      all: [],
      current: [],
      enemy: [],
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

    const getEnemy = (id) => {
      let enemy = {};

      info.value.enemy.forEach(res => {
        if(res.id === id) enemy = res;
      });

      return enemy;
    }

    onBeforeMount(() => {
      info.value.enemy = getEnemyInfo(proxy.store);
    });

    watchEffect(() => {
      info.value.all = getSearchStageInfo(proxy.store);
      if(info.value.all !== undefined) contentUpdate();
    });

    return { info, pageInfo, nextPage, prevPage, selectPage, getEnemy };
  },
  components: {
    stageInfoElementComponent
  },
};
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
  margin-top: 0.25%;
  display: grid;
  grid-template-rows: 10% 90%;
}

section {
  width: 90%;
  height: 100%;
  margin: 0 auto;
  display: grid;
  grid-template-rows: 94.5% 4.5%;
  overflow: auto;
}

.content {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: repeat(10, 20%);
}

.stage {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(1, 1fr);
  grid-template-rows: 20% 80%;
  justify-items: center;
  align-items: center;
}

.header, .stage-content {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: 15% 50% 30% 5%;
  grid-template-rows: repeat(1, 100%);
  justify-items: center;
  align-items: center;
}

.enemy-info {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: 25% 75%;
  justify-items: center;
  align-items: center;
}

.enemy-header {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: 35% 25% 20% 20%;
  justify-items: center;
  align-items: center;
}

.enemy-content-area {
  width: 100%;
  height: 100%;
  overflow: auto;
}

.enemy-content-area::-webkit-scrollbar {
  display: none;
}

.enemy-content {
  width: 100%;
  height: 60%;
  display: grid;
  grid-template-columns: 35% 25% 20% 20%;
  justify-items: center;
  align-items: center;
}

.enemy-content > img {
  width: 45%;
  height: 95%;
}

.reward {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: 25% 75%;
  justify-items: center;
  align-items: center;
}

.reward-header {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  justify-items: center;
  align-items: center;
}

.reward-content-area {
  width: 100%;
  height: 100%;
  overflow: auto;
}

.reward-content-area::-webkit-scrollbar {
  display: none;
}

.reward-content {
  width: 100%;
  height: 60%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  justify-items: center;
  align-items: center;
}

.stamina {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
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