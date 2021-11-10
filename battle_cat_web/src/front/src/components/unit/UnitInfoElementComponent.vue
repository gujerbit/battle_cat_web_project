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
      <button @click="includeSetting()" class="include">
        {{searchInfo.include ? '포함' : '미포함'}}
      </button>
    </div>
  </div>
</template>

<script>
import { ref, watchEffect, getCurrentInstance } from 'vue';
import { setSearchUnitInfo } from '../../js/unit/unitInfo.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

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
      search: '',
      include: true,
    });

    const setProperty = (event, value) => {
      if(value.includes('attack_type')) {
        if(!event.currentTarget.className.includes('select')) searchInfo.value.attackType.push(value.split('attack_type_')[1]);
        else searchInfo.value.attackType = searchInfo.value.attackType.filter(res => res !== value.split('attack_type_')[1]);
      } else {
        if(!event.currentTarget.className.includes('select')) searchInfo.value.property.push(value);
        else searchInfo.value.property = searchInfo.value.property.filter(res => res !== value);
      }

      setSearchUnitInfo(searchInfo.value, proxy.store);
      setStyle(event);
    };

    const setRarity = (event, value) => {
      if(!event.currentTarget.className.includes('select')) searchInfo.value.rarity.push(value);
      else searchInfo.value.rarity = searchInfo.value.rarity.filter(res => res !== value);
      setSearchUnitInfo(searchInfo.value, proxy.store);
      setStyle(event);
    };

    const setTarget = (event, value) => {
      if(!event.currentTarget.className.includes('select')) searchInfo.value.target.push(value.includes('_none') ? value.split('_none')[0] : value);
      else searchInfo.value.target = searchInfo.value.target.filter(res => res !== (value.includes('_none') ? value.split('_none')[0] : value));
      
      setSearchUnitInfo(searchInfo.value, proxy.store);
      if(event.currentTarget.src.includes('_none')) event.currentTarget.src = require(`../../assets/res/elements/target/${value.split('_none')[0]}.png`);
      else event.currentTarget.src = require(`../../assets/res/elements/target/${value}_none.png`);
      setStyle(event);
    };

    const setSearchData = (event) => {
      searchInfo.value.search = event.currentTarget.value;
      setSearchUnitInfo(searchInfo.value, proxy.store);
    };

    const setStyle = (event) => {
      if(event.currentTarget.className.includes('select')) event.currentTarget.classList.remove('select');
      else event.currentTarget.classList.add('select');
    };

    const includeSetting = () => {
      searchInfo.value.include = !searchInfo.value.include;
    };

    watchEffect(() => {
      setSearchUnitInfo(searchInfo.value, proxy.store);
    });

    return { elements, searchInfo, setProperty, setRarity, setTarget, setSearchData, includeSetting };
  }
}
</script>

<style scoped src="../../css/unit/unitInfoElementComponent.css" />