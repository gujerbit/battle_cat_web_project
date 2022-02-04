<template>
  <main>
    <p>{{stageData}}</p>
    <div v-for="value in stageData.data" :key="value">
      <p>ID {{value.id}}</p>
      <p>Name {{value.name}}</p>
      <p>Sortation {{stageData.sortation}}</p>
      <p>Base HP {{value.base_hp}}</p>
      <br>
      <div v-for="item in value.enemy_info.split('/')" :key="item">
        <p>{{item}}</p>
      </div>
      <br>
    </div>
  </main>
</template>

<script>
import { ref, onBeforeMount, getCurrentInstance } from 'vue';
import { useRoute } from 'vue-router';
import { getSpecificStageInfo } from '../../js/stage/stageInfo';

export default {
  setup() {
    const { proxy } = getCurrentInstance();
    const route = useRoute();

    const stageData = ref({
      data: [],
      sortation: '',
    });

    const mainMenus = ['세계편/world', '미래편/future', '우주편/space', '마계편/hell', '레전드 스토리/legend', '신레전드 스토리/originLegend', '스페셜 스테이지/special', '콜라보 스테이지/collaboration'];

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

    onBeforeMount(() => {
      stageData.value.data = getSpecificStageInfo(proxy.store, route.params.stageId);
      let sortation = stageData.value.data[0].sortation.split('_');

      for(let i = 0; i < mainMenus.length; i++) {
        for(let j = 0; j < subMenus.length; j++) {
          if(sortation[0] === mainMenus[i].split('/')[1]) stageData.value.sortation = mainMenus[i].split('/')[0];
          if(subMenus[j].length > 0) {
            for(let k = 0; k < subMenus[j].length; k++) {
              if(sortation[1] === subMenus[j][k].split('/')[1]) {
                stageData.value.sortation += '-' + subMenus[j][k].split('/')[0];

                return;
              }
            }
          }
        }
      }
    });

    return { stageData };
  }
}
</script>

<style scoped>

</style>