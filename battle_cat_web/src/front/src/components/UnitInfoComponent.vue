<template>
  <main>
    <div id="element">
      <div class="elements">
        <div class="property">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/attack_power_down.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/stop.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/slow.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/target_only.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/strength.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/hard.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/ultimate_hard.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/super_damage.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/ultimate_damage.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/knock_back.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/warp.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/ancient_curse.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/attack_invalid.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/attack_power_up.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/survive.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/ultimate_base_crash.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/critical.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/zombie_killer.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/barrier_breaker.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/soul_attack.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/more_money.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/metal.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/mini_wave.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/wave.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/thermal_wave.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/attack_power_down_invalid.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/stop_invalid.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/slow_invalid.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/knock_back_invalid.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/wave_invalid.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/thermal_wave_invalid.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/warp_invalid.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/ancient_curse_invalid.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/poison_invalid.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/wave_stopper.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/attack_type_single.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/attack_type_multi.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/attack_type_long.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/attack_type_wide.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/attack_power_down_defense.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/stop_defense.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/slow_defense.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/knock_back_defense.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/wave_defense.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/thermal_wave_defense.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/warp_defense.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/ancient_curse_defense.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/poison_defense.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/hp_increase.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/attack_power_increase.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/move_speed_increase.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/knock_back_count_increase.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/cost_decrease.png" alt="">
          <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/property_search/produce_speed_increase.png" alt="">
        </div>
        <article>
          <div class="rarity">
            <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/rarity/rarity_normal.png" alt="">
            <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/rarity/rarity_special.png" alt="">
            <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/rarity/rarity_rare.png" alt="">
            <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/rarity/rarity_super_rare.png" alt="">
            <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/rarity/rarity_uber_rare.png" alt="">
            <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/rarity/rarity_legend_rare.png" alt="">
          </div>
          <div class="target">
            <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/target/target_red.png" alt="">
            <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/target/target_fly.png" alt="">
            <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/target/target_black_none.png" alt="">
            <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/target/target_metal_none.png" alt="">
            <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/target/target_angel.png" alt="">
            <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/target/target_alien.png" alt="">
            <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/target/target_zombie.png" alt="">
            <img @click="menuControl($event)" class="unselect" src="./../assets/res/elements/target/target_ancient.png" alt="">
          </div>
        </article>
      </div>
      <router-link to="" class="element-toggle">
        <div class="toggleBtn isUp" />
      </router-link>
    </div>
    <content-component :unitData="unitData" />
  </main>
</template>

<script>
import axios from 'axios';
import contentComponent from './UnitInfoContentComponent.vue';

const DOMAIN = 'http://localhost:8090'

export default {
  data() {
    return {
      searchData: [],
      unitData: []
    }
  },
  async mounted() {
    let toggleArea = document.querySelector('.element-toggle');
    let toggleBtn = document.querySelector('.toggleBtn');
    let elements = document.querySelector('.elements');

    toggleArea.addEventListener('click', function() {
      if(toggleBtn.className.includes('isUp')) {
        toggleBtn.classList.add('isDown');
        toggleBtn.classList.remove('isUp');
        elements.style.display = 'none';
      } else if(toggleBtn.className.includes('isDown')) {
        toggleBtn.classList.add('isUp');
        toggleBtn.classList.remove('isDown');
        elements.style.display = 'grid';
      }
    });

    let { data } = await axios.get(`${DOMAIN}/unit_data`);
    this.unitData = data;
  },
  methods: {
    async menuControl(event) {
      let select = event.currentTarget.className.includes('unselect');
      let src = event.currentTarget.src;
      let element = src.split('/')[4].split('.')[0];

      if(element.includes('target_black') || element.includes('target_metal')) {
        if(element.includes('target_black')) {
          if(select) {
            this.searchData.push('target_black');
            event.currentTarget.src = require('./../assets/res/elements/target/target_black.png');
            event.currentTarget.classList.remove('unselect');
          } else {
            let tempList = this.searchData.filter(data => data != 'target_black');
            this.searchData = tempList;
            event.currentTarget.src = require('./../assets/res/elements/target/target_black_none.png');
            event.currentTarget.classList.add('unselect');
          }
        } else if(element.includes('target_metal')) {
          if(select) {
            this.searchData.push('target_metal');
            event.currentTarget.src = require('./../assets/res/elements/target/target_metal.png');
            event.currentTarget.classList.remove('unselect');
          } else {
            let tempList = this.searchData.filter(data => data != 'target_metal');
            this.searchData = tempList;
            event.currentTarget.src = require('./../assets/res/elements/target/target_metal_none.png');
            event.currentTarget.classList.add('unselect');
          }
        }
      } else {
        if(select) {
          this.searchData.push(element);
          event.currentTarget.classList.remove('unselect');
        } else {
          // let tempList = this.searchData.filter(data => data != element);
          // this.searchData = tempList;
          let tempList = this.searchData.filter(data => data != element);
          this.searchData = tempList;
          event.currentTarget.classList.add('unselect');
        }
      }

      if(this.searchData.length !== 0) {
        let { data } = await axios.get(`${DOMAIN}/unit_data_search/${this.searchData}`);
        this.unitData = data;
      } else {
        let { data } = await axios.get(`${DOMAIN}/unit_data`);
        this.unitData = data;
      }
    }
  },
  components: {
    contentComponent,
  }
}
</script>

<style scoped>
main {
  width: 100%;
}

article {
  display: grid;
  grid-template-columns: 40% 60%;
}

#element {
  width: 55%;
  height: 100%;
  margin: 0 auto;
}

.elements {
  display: grid;
  grid-template-rows: repeat(2, 1fr);
  /* background-color: #e6ac31; */
}

.property, .rarity, .target {
  display: grid;
  justify-items: center;
  align-items: center;
}

.property img {
  width: 80%;
  cursor: pointer;
}

.rarity img {
  width: 85%;
  cursor: pointer;
}

.target img {
  width: 80%;
  cursor: pointer;
}

.property {
  grid-template-columns: repeat(18, 1fr);
  row-gap: 0.5vh;
  border: 2px solid #000000;
  border-top: none;
  border-bottom: none;
}

.rarity {
  grid-template-columns: repeat(3, 1fr);
  border: 2px solid #000000;
  border-top: none;
  border-bottom: none;
  border-right: none;
}

.target {
  grid-template-columns: repeat(4, 1fr);
  column-gap: 0.5vw;
  border: 2px solid #000000;
  border-top: none;
  border-bottom: none;
  border-left: none;
}

.element-toggle {
  height: 4vh;
  display: flex;
  justify-content: center;
  align-items: center;
  /* background-color: #e6ac31; */
  border: 2px solid #000000;
  border-radius: 0 0 20px 20px;
}

.toggleBtn {
  width: 0;
  height: 0;
  border-left: 1vw solid transparent;
  border-right: 1vw solid transparent;
}

.isUp {
  border-bottom: 1vw solid black;
}

.isDown {
  border-top: 1vw solid black;
}

.unselect {
  filter: grayscale(1);
}
</style>