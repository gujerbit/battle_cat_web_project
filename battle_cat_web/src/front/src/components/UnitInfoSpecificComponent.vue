<template>
  <main>
      <section>
          d
      </section>
      <section>
          <div class="content" v-for="value in unitData" :key="value">
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
                <div class="level">
                    <p>레벨</p>
                    <input type="number" v-model="level">
                </div>
                <div class="id">
                    <p>ID</p>
                    <p>{{value.id}}</p>
                </div>
                <div class="name">
                    <p>이름</p>
                    <p>{{value.name}}</p>
                </div>
                <div class="description">
                    <p>인게임 설명</p>
                    <p>{{value.description}}</p>
                </div>
                <div class="rarity">
                    <p>레어도</p>
                    <p v-if="value.rarity === 'normal'">기본 캐릭터</p>
                    <p v-if="value.rarity === 'special'">EX 캐릭터</p>
                    <p v-if="value.rarity === 'rare'">레어</p>
                    <p v-if="value.rarity === 'super-rare'">슈퍼 레어</p>
                    <p v-if="value.rarity === 'uber-rare'">울트라 슈퍼 레어</p>
                    <p v-if="value.rarity === 'legend-rare'">레전드 레어</p>
                </div>
                <div class="hp">
                    <p>체력 / 히트백</p>
                    <p>{{value.hp * level}} / {{value.hit_back}}</p>
                </div>
                <div class="attack-power">
                    <p>공격력</p>
                    <p>{{value.attack_power}}</p>
                </div>
                <div class="move-speed">
                    <p>이동속도</p>
                    <p>{{value.move_speed}}</p>
                </div>
                <div class="produce-speed">
                    <p>생산속도</p>
                    <p>{{value.produce_speed}}</p>
                </div>
                <div class="attack-speed">
                    <p>선딜 / 공격 주기</p>
                    <p>{{value.attack_speed}} / {{value.attack_freq}}</p>
                </div>
                <div class="attack-range">
                    <p>사거리</p>
                    <p>{{value.attack_range}}</p>
                </div>
                <div class="cost">
                    <p>가격</p>
                    <p>{{value.cost}}</p>
                </div>
                <div class="property">
                    <p>능력</p>
                    <div class="property-text">
                        <p v-for="item in value.property.split('/')" :key="item">{{item.split(',')[1]}}</p>
                    </div>
                </div>
                <div class="instinct" v-if="value.instinct !== ''">
                    <p>본능</p>
                    <div class="instinct-text">
                        <p v-for="item in value.instinct.split('/')" :key="item">{{item.split(',')[1]}}</p>
                    </div>
                </div>
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
            level: 1,
        }
    },
    async mounted() {
        let unitId = this.$route.params.unitData.split('-')[0];
        
        let { data } = await axios.get(`${DOMAIN}/unit_data_id/${unitId}`);
        this.unitData = data;
    },
}
</script>

<style scoped>
main {
    width: 100%;
}

section {
    width: 50%;
    margin: 0 auto;
    font-size: 1.5em;
}

.content {
    display: grid;
    grid-template-rows: 25% 75%;
    border: 2px solid #000000;
    margin: 0.5vh 0;
    height: 50vh;
    min-height: 500px;
}

.images {
    width: 100%;
    min-height: 120px;
    display: flex;
    justify-content: space-around;
    align-items: center;
    border-bottom: 2px solid #000000;
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

.texts {
    display: grid;
}

.level, .id, .name, .description, .rarity, .hp, .attack-power, .move-speed, .produce-speed, .attack-speed, .attack-range, .cost, .property {
    display: grid;
    grid-template-columns: 10% 90%;
}

.level input {
    font-size: 0.7em;
    width: 5%;
    height: 80%;
    min-width: 50px;
}
</style>