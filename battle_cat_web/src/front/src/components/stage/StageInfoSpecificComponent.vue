<template>
    <main>
        <section>
            <div class="content" v-for="(value, idx) in stageData" :key="value">
                <p class="title" v-if="idx === 0 && value.sortation.split('/')[1]">{{value.sortation.split('/')[1]}}</p>
                <template v-for="(baseItem, index) in value.sortation.split('/')">
                    <table class="base-item" border="1" v-if="value.sortation.split('/')[2] && index > 1" :key="baseItem">
                        <tr v-if="index === 2">
                            <th>성 강화 재료</th>
                            <th>수량</th>
                            <th>획득 확률</th>
                        </tr>
                        <tr>
                            <td rowspan="2">
                            <img :src="require(`./../../assets/res/etc/item/${baseItem.split(',')[0]}`)" alt="">
                            </td>
                        </tr>
                        <tr>
                            <td>{{baseItem.split(',')[1]}}</td>
                            <td>{{baseItem.split(',')[2]}}</td>
                        </tr>
                    </table>
                </template>
                <article class="basic-info">
                    <p class="step" v-if="idx === 0">{{value.step}}성</p>
                    <table class="basic" border="1">
                        <tr>
                            <th>스테이지 ID</th>
                            <th>스테이지 이름</th>
                            <th>분류</th>
                            <th>소비 통솔력</th>
                            <th>획득 경험치</th>
                            <th>성체력</th>
                            <th>스테이지 길이</th>
                            <th>적 출격 제한</th>
                            <th>드롭 보상</th>
                        </tr>
                        <tr>
                            <td>{{value.id}}</td>
                            <td>{{value.name}}</td>
                            <td v-if="!value.sortation.includes('편')">{{value.sortation.split('/')[0]}}</td>
                            <td v-if="value.sortation.includes('편')">{{value.step}}장</td>
                            <td>{{value.stamina}}</td>
                            <td>{{value.drop_xp}}</td>
                            <td>{{value.base_hp}}</td>
                            <td>{{value.stage_length}}</td>
                            <td>{{value.max_enemys}}</td>
                            <template v-for="item in value.reward.split('/')">
                                <td v-if="item.split(',')[0] === 'treasure' && !value.sortation.includes('미래편')" :key="item">
                                    <img :src="require('./../../assets/res/stage/treasure/' + item.split(',')[1])" alt="" class="treasure" />
                                </td>
                                <table v-if="value.sortation.includes('미래편')" :key="item">
                                    <tr v-if="item.split(',')[0] === 'treasure'">
                                        <td>
                                            <img :src="require('./../../assets/res/stage/treasure/' + item.split(',')[1])" alt="" class="treasure" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <table v-if="item.split(',')[0] === 'score'" :key="item" class="item" border="1">
                                            <tr>
                                                <td rowspan="2">
                                                    <img :src="require('./../../assets/res/etc/item/' + item.split(',')[1])" alt="" />
                                                </td>
                                                <th>수량</th>
                                                <th>스코어</th>
                                            </tr>
                                            <tr>
                                                <td>{{item.split(',')[2]}}</td>
                                                <td>{{item.split(',')[3]}}</td>
                                            </tr>
                                        </table>
                                    </tr>
                                </table>
                                <table v-if="item.split(',')[0] === 'drop'" :key="item" class="item" border="1">
                                    <tr>
                                        <td rowspan="2">
                                            <img :src="require(`./../../assets/res/unit/${value.reward.split('|')[0].split(',')[1]}`)" alt="">
                                        </td>
                                        <th>내용</th>
                                        <th>확률</th>
                                    </tr>
                                    <tr>
                                        <td>{{value.reward.split('|')[0].split(',')[2]}}</td>
                                        <td>{{value.reward.split('|')[0].split(',')[3]}}</td>
                                    </tr>
                                </table>
                                <table v-if="item.split(',')[0] === 'item'" :key="item" class="item" border="1">
                                    <tr>
                                        <td rowspan="2">
                                            <img :src="require('./../../assets/res/etc/item/' + item.split(',')[1])" alt="" />
                                        </td>
                                        <th>수량</th>
                                        <th>획득 확률</th>
                                        <th>획득 제한</th>
                                    </tr>
                                    <tr>
                                        <td>{{item.split(',')[2]}}</td>
                                        <td>{{item.split(',')[3]}}</td>
                                        <td v-if="item.split(',')[4] === 'infinite'">무제한</td>
                                        <td v-else>{{item.split(',')[4]}}개</td>
                                    </tr>
                                </table>
                                <table v-if="item.split(',')[0] === 'score' && !value.sortation.includes('미래편')" :key="item" class="item" border="1">
                                    <tr>
                                        <td rowspan="2">
                                            <img :src="require('./../../assets/res/etc/item/' + item.split(',')[1])" alt="" />
                                        </td>
                                        <th>수량</th>
                                        <th>스코어</th>
                                    </tr>
                                    <tr>
                                        <td>{{item.split(',')[2]}}</td>
                                        <td>{{item.split(',')[3]}}</td>
                                    </tr>
                                </table>
                            </template>
                        </tr>
                    </table>
                </article>
                <article class="enemy-info" v-for="(item, index) in value.enemy_info.split('/')" :key="item">
                    <router-link :to="`/enemyInfo/${content.id}`" v-for="content in enemyData[idx][index]" :key="content">
                        <table class="enemy" border="1">
                            <tr>
                                <th rowspan="2">
                                    <img :src="require(`./../../assets/res/enemy/${content.image_dir}`)" alt="">
                                </th>
                                <th>적군 ID</th>
                                <th>적 이름</th>
                                <th>배율</th>
                                <th>등장 수</th>
                                <th>등장 기지체력</th>
                                <th>첫 등장 시간</th>
                                <th>재 등장 간격</th>
                            </tr>
                            <tr>
                                <td>{{content.id}}</td>
                                <td>{{content.name}}</td>
                                <td>{{item.split(',')[1]}}</td>
                                <td v-if="item.split(',')[2] === 'infinite'">무제한</td>
                                <td v-else>{{item.split(',')[2]}}</td>
                                <td>{{item.split(',')[3]}}</td>
                                <td>{{item.split(',')[4]}}f ({{(item.split(',')[4] / 30).toFixed(2)}}초)</td>
                                <td class="reproduce">
                                    <span>{{item.split(',')[5].split('~')[0]}}f</span>
                                    <span v-if="item.split(',')[5].split('~')[1]"> ~ {{item.split(',')[5].split('~')[1]}}f</span> <br>
                                    <span>({{(item.split(',')[5].split('~')[0] / 30).toFixed(2)}}초)</span>
                                    <span v-if="item.split(',')[5].split('~')[1]"> ~ ({{(item.split(',')[5].split('~')[1] / 30).toFixed(2)}}초)</span>
                                </td>
                            </tr>
                        </table>
                    </router-link>
                </article>
            </div>
        </section>
    </main>
</template>

<script>
export default {
    data() {
        return {
            stageData: [],
            enemyData: [[], [], [], []],
        }
    },
    async mounted() {
        let stageId = this.$route.params.stageData.split('-');
        let sortation = stageId[0];
        let stage = '';

        if(stageId[1].length === 2 || stageId[1].length === 3) {
            stage = stageId[1].substring(0, 1);
        } else if(stageId[1].length === 4) {
            stage = stageId[1].substring(0, 2);
        } else if(stageId[1].length === 5) {
            stage = stageId[1].substring(0, 3);
        }
        
        stageId = [sortation, stage];

        let { data: stageInfo } = await this.axios.get(`/stage_data_id/${stageId}`);
        this.stageData = stageInfo;

        for(let i = 0; i < stageInfo.length; i++) {
            let tempEnemyInfo = stageInfo[i].enemy_info.split('/');
            let tempEnemyData = [];
            for(let j = 0; j < tempEnemyInfo.length; j++) {
                let unitId = tempEnemyInfo[j].split(',')[0];
                let { data: enemyInfo } = await this.axios.get(`/enemy_data_id/${unitId}`);
                tempEnemyData.push(enemyInfo);
            }
            this.enemyData[i] = tempEnemyData;
        }
    },
    methods: {
    }
}
</script>

<style scoped>
main {
    width: 100%;
}

section {
    width: 80%;
    margin: 1% auto;
}

table {
    width: 100%;
    text-align: center;
    border-collapse: collapse;
    border-color: #000000;
    margin: 1px 0;
}

th {
    width: 10%;
    font-size: 1.7rem;
}

td {
    width: 10%;
    font-size: 1.5rem;
}

.basic img {
    width: 100%;
    display: flex;
}

.enemy-info {
    margin: 1px 0;
}

.title {
    border: 1px solid #000000;
    font-size: 2.5rem;
    text-align: center;
    margin: 1px 0;
}

.item img {
  width: 85%;
  display: flex;
  margin: 0 auto;
}

.item {
  margin: 0;
  border: none;
}

.item th {
    font-size: 1.5rem;
}

.item td {
    font-size: 1.3rem;
}

.item th, .item td {
  width: 25%;
  border-bottom: none;
  border-color: #000000;
  border-right: none;
}

.item tr:nth-child(1) td:nth-child(1) {
  border-left: none;
}

.basic tr:nth-child(1) th:nth-child(9) {
  border-bottom: none;
}

.base-item th, .base-item td {
  width: 35%;
}

.base-item th {
    font-size: 2rem;
}

.base-item td {
    font-size: 1.8rem;
}

.basic-info {
    margin-top: 1%;
}

.step {
    border: 1px solid #000000;
    font-size: 2.5rem;
    text-align: center;
}
</style>