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
                    <img :src="require(`./../assets/res/unit/${value.image_dir}`)" alt="">
                </div>
                <div class="element">
                    <div class="target">
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
                    </div>
                    <div class="etc">
                        <img src="./../assets/res/elements/property/attack_type_single.png" alt="" v-if="value.attack_type.includes('single')">
                        <img src="./../assets/res/elements/property/attack_type_multi.png" alt="" v-if="value.attack_type.includes('multi')">
                        <img src="./../assets/res/elements/property/attack_type_long.png" alt="" v-if="value.attack_type.includes('long')">
                        <img src="./../assets/res/elements/property/attack_type_wide.png" alt="" v-if="value.attack_type.includes('wide')">
                        <span v-if="value.property !== ''">
                            <img :src="require(`./../assets/res/elements/property/${item.split(',')[0]}.png`)" alt="" v-for="item in value.property.split('/')" :key="item">
                        </span>
                    </div>
                </div>
            </article>
            <article class="texts">
                <table border="1" class="info">
                    <tr>
                        <th>레벨</th>
                        <td class="level">
                            <input type="number" v-model="oneLevel" class="level-input" v-if="value.id.split('-')[1] === '0'">
                            <input type="number" v-model="twoLevel" class="level-input" v-if="value.id.split('-')[1] === '1'">
                            <input type="number" v-model="threeLevel" class="level-input" v-if="value.id.split('-')[1] === '2'">
                        </td>
                        <th>레어도</th>
                        <td v-if="value.rarity === 'normal'">기본 캐릭터</td>
                        <td v-if="value.rarity === 'special'">EX 캐릭터</td>
                        <td v-if="value.rarity === 'rare'">레어</td>
                        <td v-if="value.rarity === 'super-rare'">슈퍼 레어</td>
                        <td v-if="value.rarity === 'uber-rare'">울트라 슈퍼 레어</td>
                        <td v-if="value.rarity === 'legend-rare'">레전드 레어</td>
                        <th>가격</th>
                        <td>{{value.cost}}</td>
                    </tr>
                    <tr>
                        <th>체력</th>
                        <td v-if="value.id.split('-')[1] === '0' && oneLevel <= 60">{{(value.hp * (((oneLevel - 1) * 0.2) + 1)).toFixed(0)}}</td>
                        <td v-if="value.id.split('-')[1] === '0' && oneLevel > 60">{{(value.hp * (((oneLevel - (1 + (oneLevel % 60) / 2)) * 0.2) + 1)).toFixed(0)}}</td>
                        <td v-if="value.id.split('-')[1] === '1' && twoLevel <= 60">{{(value.hp * (((twoLevel - 1) * 0.2) + 1)).toFixed(0)}}</td>
                        <td v-if="value.id.split('-')[1] === '1' && twoLevel > 60">{{(value.hp * (((twoLevel - (1 + (twoLevel % 60) / 2)) * 0.2) + 1)).toFixed(0)}}</td>
                        <td v-if="value.id.split('-')[1] === '2' && threeLevel <= 60">{{(value.hp * (((threeLevel - 1) * 0.2) + 1)).toFixed(0)}}</td>
                        <td v-if="value.id.split('-')[1] === '2' && threeLevel > 60">{{(value.hp * (((threeLevel - (1 + (threeLevel % 60) / 2)) * 0.2) + 1)).toFixed(0)}}</td>
                        <th>공격력</th>
                        <td v-if="value.id.split('-')[1] === '0' && oneLevel <= 60">{{(value.attack_power * (((oneLevel - 1) * 0.2) + 1)).toFixed(0)}}</td>
                        <td v-if="value.id.split('-')[1] === '0' && oneLevel > 60">{{(value.attack_power * (((oneLevel - (1 + (oneLevel % 60) / 2)) * 0.2) + 1)).toFixed(0)}}</td>
                        <td v-if="value.id.split('-')[1] === '1' && twoLevel <= 60">{{(value.attack_power * (((twoLevel - 1) * 0.2) + 1)).toFixed(0)}}</td>
                        <td v-if="value.id.split('-')[1] === '1' && twoLevel > 60">{{(value.attack_power * (((twoLevel - (1 + (twoLevel % 60) / 2)) * 0.2) + 1)).toFixed(0)}}</td>
                        <td v-if="value.id.split('-')[1] === '2' && threeLevel <= 60">{{(value.attack_power * (((threeLevel - 1) * 0.2) + 1)).toFixed(0)}}</td>
                        <td v-if="value.id.split('-')[1] === '2' && threeLevel > 60">{{(value.attack_power * (((threeLevel - (1 + (threeLevel % 60) / 2)) * 0.2) + 1)).toFixed(0)}}</td>
                        <th>DPS (초당 공격력)</th>
                        <td v-if="value.id.split('-')[1] === '0' && oneLevel <= 60">{{Math.round((value.attack_power * (((oneLevel - 1) * 0.2) + 1)).toFixed(0) / (value.attack_freq / 30))}}</td>
                        <td v-if="value.id.split('-')[1] === '0' && oneLevel > 60">{{Math.round((value.attack_power * (((oneLevel - (1 + (oneLevel % 60) / 2)) * 0.2) + 1)).toFixed(0) / (value.attack_freq / 30))}}</td>
                        <td v-if="value.id.split('-')[1] === '1' && twoLevel <= 60">{{Math.round((value.attack_power * (((twoLevel - 1) * 0.2) + 1)).toFixed(0) / (value.attack_freq / 30))}}</td>
                        <td v-if="value.id.split('-')[1] === '1' && twoLevel > 60">{{Math.round((value.attack_power * (((twoLevel - (1 + (twoLevel % 60) / 2)) * 0.2) + 1)).toFixed(0) / (value.attack_freq / 30))}}</td>
                        <td v-if="value.id.split('-')[1] === '2' && threeLevel <= 60">{{Math.round((value.attack_power * (((threeLevel - 1) * 0.2) + 1)).toFixed(0) / (value.attack_freq / 30))}}</td>
                        <td v-if="value.id.split('-')[1] === '2' && threeLevel > 60">{{Math.round((value.attack_power * (((threeLevel - (1 + (threeLevel % 60) / 2)) * 0.2) + 1)).toFixed(0) / (value.attack_freq / 30))}}</td>
                    </tr>
                    <tr>
                        <th>공격속도 (선딜)</th>
                        <td>{{value.attack_speed}}f ({{(value.attack_speed / 30).toFixed(2)}}초)</td>
                        <th>공격주기</th>
                        <td>{{value.attack_freq}}f ({{(value.attack_freq / 30).toFixed(2)}}초)</td>
                        <th>생산속도</th>
                        <td>{{value.produce_speed}}f ({{(value.produce_speed / 30).toFixed(2)}}초)</td>
                    </tr>
                    <tr>
                        <th>히트백</th>
                        <td>{{value.hit_back}}</td>
                        <th>이동속도</th>
                        <td>{{value.move_speed}}</td>
                        <th>사거리</th>
                        <td>{{value.attack_range}}</td>
                    </tr>
                    <tr v-if="value.property !== ''">
                        <th>능력</th>
                        <td colspan="5">
                            <p v-for="item in value.property.split('/')" :key="item">{{item.split(',')[1]}}</p>
                        </td>
                    </tr>
                    <tr v-if="value.instinct !== ''">
                        <th>본능</th>
                        <td colspan="5">
                            <p v-for="item in value.instinct.split('/')" :key="item">{{item.split(',')[1]}}</p>
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
import axios from 'axios';

const DOMAIN = 'http://localhost:8090';

export default {
    data() {
        return {
            unitData: [],
            oneLevel: 1,
            twoLevel: 10,
            threeLevel: 30,
        }
    },
    async mounted() {
        let unitId = this.$route.params.unitData.split('-')[0];
        
        let { data } = await axios.get(`${DOMAIN}/unit_data_id/${unitId}`);
        this.unitData = data;

        // if(this.oneLevel < 1) console.log('일났다!!');
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
    border: 2px solid #000000;
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
    height: 100%;
    display: flex;
    align-items: center;
}

.unit-img img {
    height: 120%;
}

.element img {
    width: 7%;
}

table {
    width: 100%;
    height: 100%;
    text-align: center;
    border-collapse: collapse;
}

th {
    font-size: 1.15em;
    width: 15%;
}

td {
    width: 15%;
}

input {
    width: 100%;
    height: 100%;
    border: none;
    text-align: center;
    font-size: 1em;
}
</style>