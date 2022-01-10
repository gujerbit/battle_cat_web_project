<template>
  <div id="element">
    <div class="content">
      <ul class="main">
        <li :class="searchInfo.search.split('_')[0] === value.split('/')[1] ? 'select' : ''" @click="selectMenu(idx, index)" @mouseover="subMenuShowTrigger.show[idx] = true; subMenuShowTrigger.render = true;" @mouseleave="subMenuShowTrigger.show[idx] = false;" v-for="(value, idx) in mainMenus" :key="value">{{value.split('/')[0]}}</li>
        <div class="subs" v-if="subMenuShowTrigger.render">
          <ul @mouseover="subMenuShowTrigger.show[idx] = true; subMenuShowTrigger.render = true;" @mouseleave="subMenuShowTrigger.show[idx] = false; subMenuShowTrigger.render = false;" class="sub" v-for="(value, idx) in subMenus" :key="value">
            <li @click="selectMenu(idx, index)" v-show="subMenuShowTrigger.show[idx]" v-for="(item, index) in value" :key="item">{{item.split('/')[0]}}</li>
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
    const mainMenus = ['세계편/world', '미래편/future', '우주편/space', '마계편/hell', '레전드 스토리/legend', '신레전드 스토리/origin_legend', '스페셜 스테이지/special', '콜라보 스테이지/collaboration'];
    const subMenus = [
      ['메인/main', '좀비 습격/zombie'],
      ['메인/main', '좀비 습격/zombie'],
      [],
      [],
      [],
      [],
      ['게릴라/guerrilla', '요일/week', '월간/month', '드롭/drop', '각성/arousal', '발굴/excavation', '이벤트/event', '기타/etc'],
      [],
    ];
    let subMenuShowTrigger = ref({
      show: [false, false, false, false, false, false, false, false],
      render: false,
    });
    let searchInfo = ref({
      search: '',
    });

    const selectMenu = (main, sub) => {
      searchInfo.value.search = mainMenus[main].split('/')[1];

      if(subMenus[main].length > 0) {
        if(sub !== undefined) searchInfo.value.search += ('_' + subMenus[main][sub].split('/')[1]);
        else return;
      }
    };

    return { mainMenus, subMenus, subMenuShowTrigger, searchInfo, selectMenu };
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
  height: 70%;
  margin: 0 auto;
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
  padding-top: 1.5px;
  border-bottom: 1.5px solid #ffc038;
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
  height: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  font-size: 1.8rem;
}

.sub > li:hover {
  padding-top: 1.5px;
  border-bottom: 1.5px solid #ffc038;
}

.select {
  color: #ffc038;
}
</style>