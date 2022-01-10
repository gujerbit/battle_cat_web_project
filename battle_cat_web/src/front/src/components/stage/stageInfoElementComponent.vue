<template>
  <div id="element">
    <div class="content">
      <ul class="main">
        <li @mouseover="subMenuShowTrigger.show[idx] = true; subMenuShowTrigger.render = true;" @mouseleave="subMenuShowTrigger.show[idx] = false;" v-for="(value, idx) in mainMenus" :key="value">{{value}}</li>
        <div class="subs" v-if="subMenuShowTrigger.render">
          <ul @mouseover="subMenuShowTrigger.show[idx] = true; subMenuShowTrigger.render = true;" @mouseleave="subMenuShowTrigger.show[idx] = false; subMenuShowTrigger.render = false;" class="sub" v-for="(value, idx) in subMenus" :key="value">
            <li v-show="subMenuShowTrigger.show[idx]" v-for="item in value" :key="item">{{item}}</li>
          </ul>
        </div>
      </ul>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    const mainMenus = ['세계편', '미래편', '우주편', '마계편', '레전드 스토리', '신레전드 스토리', '스페셜 스테이지', '콜라보 스테이지'];
    const subMenus = [
      ['메인', '좀비 습격'],
      ['메인', '좀비 습격'],
      [],
      [],
      [],
      [],
      ['게릴라', '요일', '월간', '드롭', '각성', '발굴', '이벤트', '기타'],
      [],
    ];
    let subMenuShowTrigger = ref({
      show: [false, false, false, false, false, false, false, false],
      render: false,
    });

    return { mainMenus, subMenus, subMenuShowTrigger, };
  }
}
</script>

<style scoped>
#element {
  width: 100%;
  height: 100%;
}

.content {
  width: 90%;
  height: 100%;
  margin: 0 auto;
  border-bottom: 1.5px solid #ffc038;
}

.main {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-around;
  align-items: center;
  position: relative;
}

.main > li {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 2.3rem;
  cursor: pointer;
}

.main > li:hover {
  background-color: #dddddd;
}

.subs {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 100%;
  left: 0;
  display: flex;
}

.sub {
  width: 100%;
  height: 100%;
}

.sub > li {
  width: 100%;
  height: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: all 1s;
}
</style>