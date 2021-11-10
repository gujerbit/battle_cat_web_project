<template>
  <main>
      <section>
          <div class="content" v-for="value in unitData" :key="value">
              <article class="basic-info">
                  <table class="basic" border="1">
                      <tr>
                          <th class="none-left">ID</th>
                          <td>{{value.id}}</td>
                          <th>이름</th>
                          <td class="none-right">{{value.name}}</td>
                      </tr>
                  </table>
              </article>
              <article class="images">
                  <div class="unit-img">
                      <img :src="require(`./../../assets/res/enemy/${value.image_dir}`)" alt="">
                  </div>
                  <div class="element">
                      <div class="target">
                        <!-- <div class="target">
                            <img src="./../../assets/res/elements/property/target/target_red.png" alt="" v-if="value.target.includes('red')">
                            <img src="./../../assets/res/elements/property/untarget/untarget_red.png" alt="" v-else>
                            <img src="./../../assets/res/elements/property/target/target_fly.png" alt="" v-if="value.target.includes('fly')">
                            <img src="./../../assets/res/elements/property/untarget/untarget_fly.png" alt="" v-else>
                            <img src="./../../assets/res/elements/property/target/target_black.png" alt="" v-if="value.target.includes('black')">
                            <img src="./../../assets/res/elements/property/untarget/untarget_black.png" alt="" v-else>
                            <img src="./../../assets/res/elements/property/target/target_metal.png" alt="" v-if="value.target.includes('metal')">
                            <img src="./../../assets/res/elements/property/untarget/untarget_metal.png" alt="" v-else>
                            <img src="./../../assets/res/elements/property/target/target_angel.png" alt="" v-if="value.target.includes('angel')">
                            <img src="./../../assets/res/elements/property/untarget/untarget_angel.png" alt="" v-else>
                            <img src="./../../assets/res/elements/property/target/target_alien.png" alt="" v-if="value.target.includes('alien')">
                            <img src="./../../assets/res/elements/property/untarget/untarget_alien.png" alt="" v-else>
                            <img src="./../../assets/res/elements/property/target/target_zombie.png" alt="" v-if="value.target.includes('zombie')">
                            <img src="./../../assets/res/elements/property/untarget/untarget_zombie.png" alt="" v-else>
                            <img src="./../../assets/res/elements/property/target/target_ancient.png" alt="" v-if="value.target.includes('ancient')">
                            <img src="./../../assets/res/elements/property/untarget/untarget_ancient.png" alt="" v-else>
                        </div>
                        <div class="etc">
                            <img src="./../../assets/res/elements/property/attack_type_single.png" alt="" v-if="value.attack_type.includes('single')">
                            <img src="./../../assets/res/elements/property/attack_type_multi.png" alt="" v-if="value.attack_type.includes('multi')">
                            <img src="./../../assets/res/elements/property/attack_type_long.png" alt="" v-if="value.attack_type.includes('long')">
                            <img src="./../../assets/res/elements/property/attack_type_wide.png" alt="" v-if="value.attack_type.includes('wide')">
                        </div> -->
                        <span v-if="value.property !== ''">
                            <img :src="require(`./../../assets/res/elements/property/${item.split(',')[0]}.png`)" alt="" v-for="item in value.property.split('/')" :key="item">
                        </span>
                      </div>
                  </div>
              </article>
              <article class="texts">
                  <table class="info" border="1">
                      <tr>
                          <th>배율</th>
                          <td class="level">
                              <input type="number" v-model="level" @input="levelChange()">
                          </td>
                          <th>체력</th>
                          <td>{{(value.hp * (level / 100)).toFixed(0)}}</td>
                          <th>히트백</th>
                          <td>{{value.hit_back}}</td>
                      </tr>
                      <tr>
                          <th>공격력</th>
                          <td>{{(value.attack_power * (level / 100)).toFixed(0)}}</td>
                          <th>DPS (초당 공격력)</th>
                          <td>{{Math.round((value.attack_power * (level / 100)) / (value.attack_freq / 30)).toFixed(0)}}</td>
                          <th>사거리</th>
                          <td>{{value.attack_range}}</td>
                      </tr>
                      <tr>
                          <th>공격속도 (선딜)</th>
                          <td>{{value.attack_speed}}</td>
                          <th>공격주기</th>
                          <td>{{value.attack_freq}}</td>
                          <th>이동속도</th>
                          <td>{{value.move_speed}}</td>
                      </tr>
                      <tr v-if="value.property !== ''">
                          <th>능력</th>
                          <td colspan="5">
                              <p v-for="item in value.property.split('/')" :key="item">{{item.split(',')[1]}}</p>
                          </td>
                      </tr>
                      <tr>
                          <th>인게임 설명</th>
                          <td colspan="5">{{value.description}}</td>
                      </tr>
                  </table>
              </article>
          </div>
      </section>
  </main>
</template>

<script>
import { onBeforeMount } from 'vue';

export default {
    setup() {
        onBeforeMount(() => {
            alert('준비중입니다.');
            location.href = '/';
        });
    },
    data() {
        return {
            unitData: [],
            level: 100,
        }
    },
    async mounted() {
        let unitId = this.$route.params.unitData;
        
        let { data } = await this.axios.get(`/enemy_data_id/${unitId}`);
        this.unitData = data;
    },
    methods: {
        levelChange() {
            if(this.level < 1) {
                this.level = 1;
            } else if(this.level > 100000) {
                this.level = 100000;
            }
        }
    }
}
</script>

<style scoped>
main {
    width: 100%;
}

section {
    width: 40%;
    margin: 0 auto;
    font-size: 1.5em;
}

.content {
    display: grid;
    grid-template-rows: 10% 25% 65%;
    margin: 0.5vh 0;
    height: 50vh;
    min-height: 500px;
}

.images {
    width: 100%;
    height: 100%;
    min-height: 120px;
    display: flex;
    justify-content: space-around;
    align-items: center;
    border-left: 1px solid #000000;
    border-right: 1px solid #000000;
}

img {
    margin: 0 0.5vw;
}

.unit-img {
    display: flex;
    align-items: center;
}

.element img {
    width: 7%;
}

table {
    width: 100%;
    height: 100%;
    text-align: center;
    border-collapse: collapse;
    margin: 0;
    border-color: #000000;
}

th {
    width: 15%;
    font-size: 1.7rem;
}

td {
    width: 15%;
    font-size: 1.5rem;
}

input {
    width: 100%;
    height: 100%;
    border: none;
    text-align :center;
    font-size: 1em;
}
</style>