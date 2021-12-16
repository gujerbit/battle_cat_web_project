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
              <input @mouseover="scrollIncreaseSetting(-1)" @mouseleave="watching.scroll = false" v-model="unitData.increase[0]" v-if="unitData.increaseCombination" class="increase-combination" type="number" onfocus="this.select()">
              <template v-else>
                <input @mouseover="scrollIncreaseSetting(0)" @mouseleave="watching.scroll = false" v-model="unitData.increase[1]" class="increase-division" type="number" onfocus="this.select()">
                <input @mouseover="scrollIncreaseSetting(1)" @mouseleave="watching.scroll = false" v-model="unitData.increase[2]" class="increase-division" type="number" onfocus="this.select()">
              </template>
              <button @click="unitData.increaseCombination = !unitData.increaseCombination">{{unitData.increaseCombination ? '통합' : '개별'}}</button>
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
              <template v-for="item in elements.target" :key="item">
                <img v-if="value.target.includes(item)" :src="require(`../../assets/res/elements/property/target/${item}.png`)" alt="">
              </template>
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
              <p>{{settingUnitData.hp}}</p>
              <p>{{value.hit_back}}</p>
              <div class="attack-power">
                <div class="content-division-forward">
                  <p>{{unitData.combineAttackPower}}</p>
                </div>
                <div class="content-division-backend" v-if="value.attack_power.split('/').length > 1">
                  <template v-for="(item, idx) in value.attack_power.split('/')" :key="item">
                    <p v-if="idx === 0">(</p>
                    <p v-if="idx !== 0">/</p>
                    <p>{{item}}</p>
                    <p v-if="idx === value.attack_power.split('/').length - 1">)</p>
                  </template>
                </div>
              </div>
              <p>{{Math.round(unitData.combineAttackPower / (value.attack_freq / 30))}}</p>
              <p>{{value.attack_range}}</p>
              <p>{{value.drop_money}}</p>
            </div>
          </section>
          <section>
            <div class="content-title">
              <p>공격주기</p> <p>공격간격</p> <p>선딜</p> <p>후딜</p> <p>이동속도</p>
            </div>
            <div class="content-data">
              <div class="attack-freq">
                <p>{{(value.attack_freq / 30).toFixed(1)}}초</p>
                <p>{{value.attack_freq}}F</p>
              </div>
              <div class="attack-cycle">
                <div class="content-division-forward">
                  <template v-if="value.attack_speed.split('/').length > 1">
                    <template v-for="(item, idx) in value.attack_speed.split('/')" :key="item">
                      <p v-if="idx !== 0">/</p>
                      <p>{{Math.abs(((value.attack_freq / 30) - (item / 30) - (value.attack_end_speed / 30))).toFixed(1)}}</p>
                      <p v-if="idx === value.attack_speed.split('/').length - 1">초</p>
                    </template>
                  </template>
                  <p v-else>{{Math.abs((value.attack_freq / 30) - (value.attack_speed / 30) - (value.attack_end_speed / 30)).toFixed(1)}}초</p>
                </div>
                <div class="content-division-backend">
                  <template v-if="value.attack_speed.split('/').length > 1">
                    <template v-for="(item, idx) in value.attack_speed" :key="item">
                      <p v-if="idx !== 0">/</p>
                      <p>{{value.attack_freq - item - value.attack_end_speed}}</p>
                      <p v-if="idx === value.attack_speed.split('/').length - 1">F</p>
                    </template>
                  </template>
                  <p v-else>{{value.attack_freq - value.attack_speed - value.attack_end_speed}}F</p>
                </div>
              </div>
              <div class="attack-speed">
                <div class="content-division-forward">
                  <template v-if="value.attack_speed.split('/').length > 1">
                    <template v-for="(item, idx) in value.attack_speed.split('/')" :key="item">
                      <p v-if="idx !== 0">/</p>
                      <p>{{(item / 30).toFixed(1)}}</p>
                      <p v-if="idx === value.attack_speed.split('/').length - 1">초</p>
                    </template>
                  </template>
                  <p v-else>{{(value.attack_speed / 30).toFixed(1)}}초</p>
                </div>
                <div class="content-division-backend">
                  <template v-if="value.attack_speed.split('/').length > 1">
                    <template v-for="(item, idx) in value.attack_speed" :key="item">
                      <p v-if="idx !== 0">/</p>
                      <p>{{item}}</p>
                      <p v-if="idx === value.attack_speed.split('/').length - 1">F</p>
                    </template>
                  </template>
                  <p v-else>{{value.attack_speed}}F</p>
                </div>
              </div>
              <div class="attack-end-speed">
                <p>{{(value.attack_end_speed / 30).toFixed(1)}}초</p>
                <p>{{value.attack_end_speed}}F</p>
              </div>
              <div class="move-speed">
                <p>{{value.move_speed}}</p>
              </div>
            </div>
          </section>
          <div class="property" v-if="value.property.length > 0">
            <div class="property-header">
              <p>능력/효과</p>
            </div>
            <div class="property-content">
              <p v-for="item in value.property.split('/')" :key="item">{{item.split(',')[1]}}</p>
            </div>
          </div>
          <div class="description">
            <p>인게임 설명</p>
            <p>{{value.description}}</p>
          </div>
        </div>
      </div>
    </div>
    <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
    <router-link to="/enemyInfo" class="enemy-info-page">적군 캐릭터 정보 화면으로 돌아가기</router-link>
  </main>
</template>

<script>
import { ref, getCurrentInstance, onBeforeMount, watchEffect } from 'vue';
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
      increase: [100, 100, 100], //통합 배율, 공격력 배율, 체력 배율
      increaseCombination: true, //배율 통합할건지?
      hp: 0,
      attackPower: [],
      combineAttackPower: 0,
    });

    const settingUnitData = ref({
      hp: 0,
      attackPower: [],
    });

    const watching = ref({
      scroll: false,
    });

    const scrollIncreaseSetting = (type) => {
      watching.value.scroll = true;

      window.onmousewheel = e => {
        if(watching.value.scroll) {
          if(unitData.value.increaseCombination) unitData.value.increase[0] += e.wheelDelta / 180;
          else {
            if(type === 0) unitData.value.increase[1] += e.wheelDelta / 180;
            else if(type === 1) unitData.value.increase[2] += e.wheelDelta / 180;
          }
        }
      }
    };

    onBeforeMount(() => {
      unitData.value.data = getSpecificEnemyInfo(proxy.store, route.params.unitId);
      unitData.value.hp = unitData.value.data[0].hp;
      unitData.value.attackPower.push(unitData.value.data[0].attack_power.split('/'));

      settingUnitData.value.attackPower.forEach(res => {
        unitData.value.combineAttackPower += res * 1;
      });
    });

    watchEffect(() => {
      for(let i = 0; i < unitData.value.increase.length; i++) {
        if(unitData.value.increase[i] > 1000000) unitData.value.increase[i] = 1000000;
        if(unitData.value.increase[i] < 1) unitData.value.increase[i] = 1;
      }

      let hp = unitData.value.hp;
      let attackPower = unitData.value.attackPower;

      if(unitData.value.increaseCombination) {
        settingUnitData.value.hp = Math.round(hp * unitData.value.increase[0] / 100);
        
        for(let i = 0; i < attackPower.length; i++) settingUnitData.value.attackPower[i] = Math.round(attackPower[i] * unitData.value.increase[0] / 100);
      } else {
        settingUnitData.value.hp = Math.round(hp * unitData.value.increase[1] / 100);
        
        for(let i = 0; i < attackPower.length; i++) settingUnitData.value.attackPower[i] = Math.round(attackPower[i] * unitData.value.increase[2] / 100);
      }

      unitData.value.combineAttackPower = 0;

      settingUnitData.value.attackPower.forEach(res => {
        unitData.value.combineAttackPower += res * 1;
      });
    });

    return { elements, unitData, settingUnitData, watching, scrollIncreaseSetting };
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
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 2.3rem;
  border: 1.5px solid #ffc038;
  border-top: none;
  border-right: none;
}

.increase input {
  outline: 0;
  text-align: center;
}

.increase input::-webkit-inner-spin-button {
  display: none;
}

.increase-combination {
  width: 80%;
  height: 100%;
  border: none;
  border-right: 1.5px solid #ffc038;
}

.increase-division {
  width: 40%;
  height: 100%;
  border: none;
  border-right: 1.5px solid #ffc038;
}

.increase button {
  width: 20%;
  height: 100%;
  border: none;
  background-color: #ffffff;
  cursor: pointer;
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

section:nth-child(2) .content-title, section:nth-child(2) .content-data {
  grid-template-columns: repeat(5, 1fr);
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
  
.main-page, .enemy-info-page {
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
  
.enemy-info-page {
  right: 0;
  border-right: none;
  border-radius: 15px 0 0 15px;
}
  
.main-page:hover, .enemy-info-page:hover {
  opacity: 1;
}
</style>