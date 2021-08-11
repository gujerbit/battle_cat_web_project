<template>
  <main>
      <div id="contents" v-for="value in unitData" :key="value">
          <img :src="require(`./../assets/res/unit/${value.image_dir}`)" alt="">
          <p>{{value.id}}</p>
          <p>{{value.name}}</p>
          <p>{{value.description}}</p>
          <p v-if="value.rarity === 'normal'">기본 캐릭터</p>
          <p v-if="value.rarity === 'special'">EX 캐릭터</p>
          <p v-if="value.rarity === 'rare'">레어</p>
          <p v-if="value.rarity === 'super-rare'">슈퍼 레어</p>
          <p v-if="value.rarity === 'uber-rare'">울트라 슈퍼 레어</p>
          <p v-if="value.rarity === 'legend-rare'">레전드 레어</p>
          <p>{{value.hp}}</p>
          <p>{{value.hit_back}}</p>
          <p>{{value.attack_power}}</p>
          <p>{{value.move_speed}}</p>
          <p>{{value.produce_speed}}</p>
          <p>{{value.attack_speed}}</p>
          <p>{{value.attack_freq}}</p>
          <p>{{value.attack_range}}</p>
          <p>{{value.cost}}</p>
          <img src="./../assets/res/elements/property/attack_type_single.png" alt="" v-if="value.attack_type.includes('single')">
          <img src="./../assets/res/elements/property/attack_type_multi.png" alt="" v-if="value.attack_type.includes('multi')">
          <img src="./../assets/res/elements/property/attack_type_long.png" alt="" v-if="value.attack_type.includes('long')">
          <img src="./../assets/res/elements/property/attack_type_wide.png" alt="" v-if="value.attack_type.includes('wide')">
          <img src="./../assets/res/elements/property/target/target_red.png" alt="" v-if="value.target.includes('red')">
          <img src="./../assets/res/elements/property/untarget/untarget_red.png" alt="" v-else>
          <img src="./../assets/res/elements/property/target/target_fly.png" alt="" v-if="value.target.includes('fly')">
          <img src="./../assets/res/elements/property/untarget/untarget_fly.png" alt="" v-else>
          <img src="./../assets/res/elements/property/target/target_black.png" alt="" v-if="value.target.includes('black')">
          <img src="./../assets/res/elements/property/untarget/untarget_black.png" alt="" v-else>
          <img src="./../assets/res/elements/property/target/target_metal.png" alt="" v-if="value.target.includes('metal')">
          <img src="./../assets/res/elements/property/untarget/untarget_metal.png" alt="" v-else>
          <img src="./../assets/res/elements/property/target/target_angel.png" alt="" v-if="value.target.includes('angel')">
          <img src="./../assets/res/elements/property/untarget/untarget_angel.png" alt="" v-else>
          <img src="./../assets/res/elements/property/target/target_alien.png" alt="" v-if="value.target.includes('alien')">
          <img src="./../assets/res/elements/property/untarget/untarget_alien.png" alt="" v-else>
          <img src="./../assets/res/elements/property/target/target_zombie.png" alt="" v-if="value.target.includes('zombie')">
          <img src="./../assets/res/elements/property/untarget/untarget_zombie.png" alt="" v-else>
          <img src="./../assets/res/elements/property/target/target_ancient.png" alt="" v-if="value.target.includes('ancient')">
          <img src="./../assets/res/elements/property/untarget/untarget_ancient.png" alt="" v-else>
          <div v-if="value.property !== ''">
              <img :src="require(`./../assets/res/elements/property/${item.split(',')[0]}.png`)" alt="" v-for="item in value.property.split('/')" :key="item">
          </div>
          <p v-for="item in value.property.split('/')" :key="item">{{item.split(',')[1]}}</p>
          <p>{{value.instinct}}</p>
          <p></p>
      </div>
  </main>
</template>

<script>
import axios from 'axios';

const DOMAIN = 'http://localhost:8090';

export default {
    data() {
        return {
            unitData: [],
        }
    },
    async mounted() {
        let unitId = this.$route.params.unitData.split('-')[0];
        
        let { data } = await axios.get(`${DOMAIN}/unit_data_id/${unitId}`);
        this.unitData = data;
    }
}
</script>

<style>

</style>