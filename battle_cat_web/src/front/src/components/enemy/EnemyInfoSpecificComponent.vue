<template>
  <main>
    <div id="content">
      <div class="unit-info" v-for="value in unitData.data" :key="value">
        <div class="title">
          <div class="title-header">
            <p>ID</p> <p>이름</p> <p>배율 (공격력/체력)</p>
          </div>
          <div class="title-content">
            <p>{{value.id}}</p>
            <p>{{value.name}}</p>
            <div class="increase">
              <input type="number">
              <input type="number">
            </div>
          </div>
        </div>
        <div class="image">
          <div class="image-title">
            <p></p>
          </div>
        </div>
      </div>
    </div>
    <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
    <router-link to="/enemyInfo" class="enemy-info-page">적군 캐릭터 정보 화면으로 돌아가기</router-link>
  </main>
</template>

<script>
import { ref, getCurrentInstance, onBeforeMount } from 'vue';
import { useRoute } from 'vue-router';
import { getSpecificEnemyInfo } from '../../js/enemy/enemyInfo';

export default {
  setup() {
    const { proxy } = getCurrentInstance();
    const route = useRoute();

    const elements = {
      property: ['attack_power_down', 'stop', 'slow', 'knock_back', 'warp', 'ancient_curse', 'attack_invalid', 'attack_power_up', 'survive', 'ultimate_base_crash', 'critical', 'soul_attack', 'mini_wave', 'wave', 'thermal_wave', 'poison', 'burrow', 'resurrection', 'barrier_breaker', 'shield_breaker', 'attack_power_down_invalid', 'stop_invalid', 'slow_invalid', 'knock_back_invalid', 'wave_invalid', 'thermal_wave_invalid', 'warp_invalid', 'ancient_curse_invalid', 'attack_type_single', 'attack_type_multi', 'attack_type_long', 'attack_type_wide'],
      target: ['red', 'fly', 'black', 'metal', 'angel', 'alien', 'zombie', 'devil', 'ancient']
    };

    const unitData = ref({
      data: [],
      increase: [100, 100], //공격력 배율, 체력 배율
      increaseCombination: true, //배율 통합할건지?
      attackPower: [],
      dps: 0,
      hp: 0,
      hitBack: 0,
      attackSpeed: [],
      attackEndSpeed: 0,
      attackFreq: 0,
      moveSpeed: 0,
      attackRange: '',
      drop_money: 0, //죽었을 때 돈 드랍량
      description: '',
      property: [],
      combineAttackPower: 0,
    });

    onBeforeMount(() => {
      unitData.value.data = getSpecificEnemyInfo(proxy.store, route.params.unitId);
    });

    return { elements, unitData };
  }
}
</script>

<style scoped>
main {
  width: 100%;
  height: 100%;
}
</style>