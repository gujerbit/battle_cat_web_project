<template>
  <div id="content">
    <router-link :to="`/stageInfo/${value.id}`" class="stage-data" v-for="value in searchData" :key="value">
      <div class="toggle-area" v-if="value.id.split('-')[1].length === 3 && value.id.split('-')[1].substring(1, 2) === '0'">
        <p class="title">{{value.sortation.split('/')[1]}}</p>
      </div>
      <table :class="`stage ${value.sortation.includes('편') ? 'non-legend' : 'legend'}`" border="1" v-if="value.step === step">
        <tr>
          <th>스테이지 이름</th>
          <th>소비 통솔력</th>
          <th>획득 경험치</th>
          <th>성체력</th>
          <th>스테이지 길이</th>
          <th>적 출격 제한</th>
          <th>드롭 보상</th>
        </tr>
        <tr>
          <td>{{ value.name }}</td>
          <td>{{ value.stamina }}</td>
          <td>{{ value.drop_xp }}XP</td>
          <td>{{ value.base_hp }}</td>
          <td>{{ value.stage_length }}</td>
          <td>{{ value.max_enemys }}</td>
          <template v-for="item in value.reward.split('/')">
            <td v-if="item.split(',')[0] === 'treasure' && !value.sortation.includes('미래편')" :key="item">
              <img :src="require('./../../assets/res/stage/treasure/' + item.split(',')[1])" alt="" class="treasure" />
            </td>
            <table v-if="value.sortation.includes('미래편')" :key="item">
              <tr v-if="item.split(',')[0] === 'treasure'" style="border-top: 1px solid #000000;">
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
    </router-link>
  </div>
</template>

<script>
export default {
    props: ['searchData', 'step'],
}
</script>

<style scoped>
#content {
  width: 55%;
  margin: 0 auto;
  margin-top: 1vh;
}

table {
  width: 100%;
  text-align: center;
  border-collapse: collapse;
  border-color: #000000;
  margin: 1px 0;
}

th {
  width: 12%;
  font-size: 1.7rem;
}

td {
  font-size: 1.5rem;
}

.treasure {
  width: 100%;
  display: flex;
}

.item img {
  width: 85%;
  display: flex;
  margin: 0 auto;
}

.stage-data:nth-child(1) .title {
  margin-top: 0;
}

.item {
  margin: 0;
  border: none;
  display: table;
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

.stage tr:nth-child(1) th:nth-child(7) {
  border-bottom: none;
}

.title {
    border: 1px solid #000000;
    font-size: 2.5rem;
    text-align: center;
    margin-top: 30px;
}
</style>