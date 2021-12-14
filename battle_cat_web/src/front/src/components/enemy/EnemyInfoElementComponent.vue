<template>
  <div id="element">
    <div class="content">
      <div class="property">
        <div v-for="value in elements.property" :key="value" class="data">
          <img @click="setProperty($event, value)" :src="require(`../../assets/res/elements/property_search/${value}.png`)" alt="">
        </div>
      </div>
      <section>
        <div class="rarity">
          <div v-for="value in elements.rarity" :key="value" class="data">
            <img @click="setRarity($event, value)" :src="require(`../../assets/res/elements/rarity/${value}.png`)" alt="">
          </div>
        </div>
        <div class="target">
          <div v-for="value in elements.target" :key="value" class="data">
            <img @click="setTarget($event, value)" :src="require(`../../assets/res/elements/target/${value}_none.png`)" alt="">
          </div>
        </div>
      </section>
    </div>
    <div class="search">
      <input @input="setSearchData($event)" type="text" placeholder="유닛 이름 검색">
      <button @click="setInclude()" class="include">{{searchInfo.include ? '포함' : '미포함'}}</button>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    const elements = {
      property: ['attack_power_down', 'stop', 'slow', 'target_only', 'strength', 'hard', 'ultimate_hard', 'super_damage', 'ultimate_damage', 'knock_back', 'warp', 'ancient_curse', 'attack_invalid', 'attack_power_up', 'survive', 'ultimate_base_crash', 'critical', 'zombie_killer', 'barrier_breaker', 'shield_breaker', 'soul_attack', 'more_money', 'metal', 'mini_wave', 'wave', 'thermal_wave', 'attack_power_down_invalid', 'stop_invalid', 'slow_invalid', 'knock_back_invalid', 'wave_invalid', 'thermal_wave_invalid', 'warp_invalid', 'ancient_curse_invalid', 'poison_invalid', 'wave_stopper', 'attack_type_single', 'attack_type_multi', 'attack_type_long', 'attack_type_wide', 'attack_power_down_defense', 'stop_defense', 'slow_defense', 'knock_back_defense', 'wave_defense', 'thermal_wave_defense', 'warp_defense', 'ancient_curse_defense', 'poison_defense', 'hp_increase', 'attack_power_increase', 'move_speed_increase', 'knock_back_count_increase', 'cost_decrease', 'produce_speed_increase'],
      rarity: ['normal', 'special', 'rare', 'super_rare', 'uber_rare', 'legend_rare'],
      target: ['red', 'fly', 'black', 'metal', 'angel', 'alien', 'zombie', 'devil', 'ancient']
    };

    const searchInfo = ref({
      property: [],
      rarity: [],
      target: [],
      attackType: [],
      search: '', //유닛 이름
      include: true, //OR 검색 == true & AND 검색 == false
    });

    return { elements, searchInfo };
  }
}
</script>

<style scoped>
#element {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: 87.5% 12.5%;
}

#element * {
  text-decoration: none;
}

.content {
  width: 90%;
  height: 100%;
  margin: 0 auto;
  display: grid;
  grid-template-rows: 3fr 1fr;
  overflow: auto;
  border: 1.5px solid #ffc038;
}

.content::-webkit-scrollbar {
  display: none;
}

.data {
  width: 80%;
  height: 80%;
}

.data img {
  width: 100%;
  height: 100%;
}

.property {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(20, 1fr);
  justify-items: center;
  align-items: center;
}

section {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: 1fr 1.5fr;
}

.rarity {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  justify-items: center;
  align-items: center;
}

.target {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  justify-items: center;
  align-items: center;
}

.search {
  width: 90%;
  height: 100%;
  margin: 0 auto;
  display: flex;
  justify-content: center;
  align-items: top;
}

.search input {
  width: 90%;
  height: 100%;
  position: relative;
  outline: 0;
  border: 1.5px solid #ffc038;
  border-top: none;
  background-color: #ffffff;
  text-align: center;
  font-size: 2.8rem;
}

.search .include {
  width: 10%;
  height: 100%;
  border: 1.5px solid #ffc038;
  background-color: #ffffff;
  border-left: none;
  border-top: none;
  outline: 0;
  text-align: center;
  font-size: 2.5rem;
  cursor: pointer;
}

img {
  cursor: pointer;
  transition: all 0.5s;
  filter: grayscale(1);
}

img:hover {
  transform: scale(0.9);
}

.select {
  filter: grayscale(0);
}
</style>