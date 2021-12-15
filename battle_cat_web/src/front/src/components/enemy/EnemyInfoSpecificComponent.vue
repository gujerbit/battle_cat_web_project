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
              <button>통합</button>
            </div>
          </div>
        </div>
        <div class="image">
          <div class="image-title">
            <p>캐릭터 이미지</p> <p>속성</p> <p>캐릭터 능력/효과</p>
          </div>
          <div class="image-content">
            <div class="thumbnail">
              <img :src="require(`./../../assets/res/enemy/${value.image_dir}`)" alt="">
            </div>
            <div class="target">
              <img v-for="item in value.target.split('/')" :key="item" :src="require(`../../assets/res/elements/property/target/${item}.png`)" alt="">
            </div>
            <div class="element">
              <template v-if="value.property.length > 0">
                <img v-for="item in value.property.split('/')" :key="item" :src="require(`../../assets/res/elements/property_search/${item.split(',')[0]}.png`)" alt="">
              </template>
              <img v-for="item in value.attack_type.split('/')" :key="item" :src="require(`../../assets/res/elements/property_search/attack_type_${item}.png`)" alt="">
            </div>
          </div>
        </div>
        <div class="content">
          <section>
            <div class="content-title">
              <p>체력</p> <p>히트백</p> <p>공격력</p> <p>DPS</p> <p>인식 사거리 (타격 범위)</p> <p>처치 시 돈 획득량</p>
            </div>
            <div class="content-data">
              <p>{{unitData.hp}}</p>
              <p>{{value.hit_back}}</p>
              <div class="attack-power">
                <div class="content-division-forward">
                  <p>{{unitData.combineAttackPower}}</p>
                </div>
                <div class="content-division-backend" v-if="unitData.attackPower.length > 1">
                  <template v-for="(item, idx) in unitData.attackPower" :key="item">
                    <p v-if="idx === 0">(</p>
                    <p v-if="idx !== 0">/</p>
                    <p>{{item}}</p>
                    <p v-if="idx === unitData.attackPower.length - 1">)</p>
                  </template>
                </div>
              </div>
              <p>{{unitData.dps}}</p>
              <p>{{unitData.attackRange}}</p>
              <p>{{unitData.dropMoney}}</p>
            </div>
          </section>
          <section>
            <div class="content-title">
              <p>공격주기</p> <p>공격간격</p> <p>선딜</p> <p>후딜</p> <p>이동속도</p>
            </div>
            <div class="content-data">
              <div class="attack-freq">
                <p>{{(unitData.attackFreq / 30).toFixed(1)}}초</p>
                <p>{{unitData.attackFreq}}F</p>
              </div>
              <div class="attack-cycle">
                <div class="content-division-forward">
                  <template v-for="(item, idx) in unitData.attackSpeed" :key="item">
                    <p v-if="idx !== 0">/</p>
                    <p>{{Math.abs(((unitData.attackFreq / 30) - (item / 30) - (unitData.attackEndSpeed / 30))).toFixed(1)}}</p>
                    <p v-if="idx === unitData.attackSpeed.length - 1">초</p>
                  </template>
                </div>
                <div class="content-division-backend">
                  <template v-for="(item, idx) in unitData.attackSpeed" :key="item">
                    <p v-if="idx !== 0">/</p>
                    <p>{{unitData.attackFreq - item - unitData.attackEndSpeed}}</p>
                    <p v-if="idx === unitData.attackSpeed.length - 1">F</p>
                  </template>
                </div>
              </div>
              <div class="attack-speed">
                <div class="content-division-forward">
                  <template v-for="(item, idx) in unitData.attackSpeed" :key="item">
                    <p v-if="idx !== 0">/</p>
                    <p>{{(item / 30).toFixed(1)}}</p>
                    <p v-if="idx === unitData.attackSpeed.length - 1">초</p>
                  </template>
                </div>
                <div class="content-division-backend">
                  <template v-for="(item, idx) in unitData.attackSpeed" :key="item">
                    <p v-if="idx !== 0">/</p>
                    <p>{{item}}</p>
                    <p v-if="idx === unitData.attackSpeed.length - 1">F</p>
                  </template>
                </div>
              </div>
              <div class="attack-end-speed">
                <p>{{(unitData.attackEndSpeed / 30).toFixed(1)}}초</p>
                <p>{{unitData.attackEndSpeed}}F</p>
              </div>
              <div class="move-speed">
                <p v-if="index === 2 && settingUnitData.moveSpeed !== 0">{{settingUnitData.moveSpeed}}</p>
                <p v-else>{{unitData.moveSpeed}}</p>
              </div>
            </div>
          </section>
          <div class="property" v-if="unitData.property.length > 0">
            <div class="property-header">
              <p>능력/효과</p>
              <!-- <button @click="unitData.propertyApply[index] = !unitData.propertyApply[index]">{{unitData.propertyApply[index] ? '적용' : '미적용'}}</button> -->
            </div>
            <div class="property-content">
              <p v-for="item in unitData.property" :key="item">{{item.split(',')[1]}}</p>
            </div>
          </div>
          <div class="description">
            <p>인게임 설명</p>
            <p>{{unitData.description}}</p>
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
      dropMoney: 0, //죽었을 때 돈 드랍량
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

main * {
  text-decoration: none;
}
  
#content {
  width: 90%;
  height: 100%;
  margin: 0 auto;
  margin-top: 0.25%;
}

.unit-info {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: 10% 25% 58.5%;
}

.title {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: repeat(2, 1fr);
  justify-items: center;
  align-items: center;
}
  
.title-header, .title-content {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
}
  
.title-header p {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 2.8rem;
  border: 1.5px solid #ffc038;
  border-top: none;
  border-right: none;
  color: #ffffff;
  background-color: #ffc038;
}

.title-header p:last-child {
  border-right: 1.5px solid #ffc038;
}
  
.title-content p, .title-content {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 2.3rem;
  border: 1.5px solid #ffc038;
  border-top: none;
  border-right: none;
}
  
.title-content > :last-child {
  border-right: 1.5px solid #ffc038;
}

.increase {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: 45% 45% 10%;
  justify-items: center;
  align-items: center;
  font-size: 2.3rem;
  border: 1.5px solid #ffc038;
  border-top: none;
  border-right: none;
}

.increase input {
  width: 100%;
  height: 100%;
  border: none;
  border-right: 1.5px solid #ffc038;
}

.increase button {
  width: 100%;
  height: 100%;
  border: none;
  background-color: #ffffff;
}
  
.image {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: 20% 80%;
  overflow: auto;
}
  
.image-title {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
}
  
.image-title p {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 2.8rem;
  color: #ffffff;
  background-color: #ffc038;
}
  
.image-content {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
}
  
.thumbnail {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1.5px solid #ffc038;
  border-top: none;
}
  
.thumbnail img {
  width: 80%;
  height: 85%;
}
  
.target, .element {
  width: 100%;
  height: 100%;
  display: grid;
  justify-items: center;
  align-items: center;
  border: 1.5px solid #ffc038;
  border-left: none;
  border-top: none;
}

.target {
  grid-template-columns: repeat(5, 1fr);
  grid-template-rows: repeat(2, 1fr);
}

.element {
  grid-template-columns: repeat(8, 1fr);
  grid-template-rows: repeat(2, 1fr);
}
  
.target img {
  width: 60%;
  height: 80%;
}

.element img {
  width: 90%;
  height: 75%;
}
  
.content {
  width: 100%;
  height: 100%;
  overflow: auto;
}
  
.content::-webkit-scrollbar, .image::-webkit-scrollbar {
  display: none;
}
  
section {
  width: 100%;
  height: 20%;
  display: grid;
  grid-template-rows: 35% 65%;
}
  
section p {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
  
.content-title, .content-data {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(6, 1fr);
}
  
.content-title p {
  background-color: #ffc038;
  font-size: 2.8rem;
  color: #ffffff;
}
  
.content-data > p {
  font-size: 2.3rem;
  border: 1.5px solid #ffc038;
  border-left: none;
}
  
.content-data > :first-child {
  border-left: 1.5px solid #ffc038;
}
  
.attack-power, .attack-speed, .attack-cycle, .cost, .attack-end-speed, .produce-speed, .attack-freq, .move-speed {
  width: 100%;
  height: 100%;
  font-size: 2.3rem;
  border: 1.5px solid #ffc038;
  border-left: none;
}
  
.attack-speed, .attack-cycle, .attack-end-speed, .produce-speed, .attack-freq {
  display: grid;
  grid-template-rows: repeat(2, 1fr);
  justify-items: center;
  align-items: center;
}

.attack-power {
  display: grid;
  grid-template-rows: auto;
  justify-items: center;
  align-items: center;
}
  
.content-division-forward, .content-division-backend, .cost, .move-speed {
  display: flex;
  justify-content: center;
  align-items: center;
}
  
.attack-power p, .attack-speed p, .attack-cycle p, .cost p, .attack-end-speed p, .produce-speed p, .attack-freq p, .move-speed p {
  width: 100%;
  height: 100%;
}
  
.property, .instinct, .description {
  width: 100%;
  height: 35%;
  display: grid;
  grid-template-rows: 22.5% 77.5%;
  border: 1.5px solid #ffc038;
  border-top: none;
}

.description > p:first-child, .instinct > p:first-child {
  background-color: #ffc038;
  font-size: 2.8rem;
  color: #ffffff;
  display: flex;
  justify-content: center;
  align-items: center;
}

.property-header {
  width: 100%;
  height: 100%;
  background-color: #ffc038;
  position: relative;
}

.property-header p {
  display: flex;
  justify-content: center;
  align-items: center;
  color: #ffffff;
  font-size: 2.8rem !important;
}

.property-header button {
  position: absolute;
  top: 0;
  right: 0.25%;
  width: 7.5%;
  height: 90%;
  border: none;
  border: 1.5px solid #000000;
  border-radius: 5px;
  background-color: #ffffff;
  cursor: pointer;
}

.property .property-content {
  display: grid;
}
  
.description p, .property p {
  width: 100%;
  height: 100%;
  text-align: center;
  font-size: 2.3rem;
}
  
.instinct-content {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(5, 1fr);
}
  
.instinct-data {
  width: 100%;
  height: 100%;
  margin: 0 auto;
  display: grid;
  grid-template-rows: 70% 30%;
  border-left: 1.5px solid #ffc038;
}

.instinct-data:first-child {
  border-left: none;
}
  
.instinct-data .text, .instinct-data .input {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}
  
.instinct-data .text span {
  font-size: 2.3rem;
}
  
.instinct-data .input .instinct-level {
  width: 100%;
  height: 100%;
  text-align: center;
  font-size: 2.3rem;
  border: none;
  border-top: 1.5px solid #ffc038;
  outline: 0;
}
  
.unselect {
  filter: grayscale(1);
}
  
.main-page, .unit-info-page {
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
  left: 0;
  border-left: none;
  border-radius: 0 15px 15px 0;
}
  
.unit-info-page {
  right: 0;
  border-right: none;
  border-radius: 15px 0 0 15px;
}
  
.main-page:hover, .unit-info-page:hover {
  opacity: 1;
}
</style>