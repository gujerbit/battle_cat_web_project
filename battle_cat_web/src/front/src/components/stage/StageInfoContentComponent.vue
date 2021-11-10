<template>
  <div id="content">
    <router-link :to="`/stageInfo/${value.id}`" class="stage-data" v-for="value in stageData" :key="value">
      <div class="toggle-area" @click="viewContent($event)" v-if="value.id.split('-')[1].length === 3 && value.id.split('-')[1].substring(1, 2) === '0'">
        <p class="title">{{value.sortation.split('/')[1]}}</p>
        <div class="element-toggle">
          <div class="toggleBtn isUp" />
        </div>
      </div>
      <div class="toggle-area" @click="viewContent($event)" v-if="value.id.split('-')[1].length === 4 && value.id.split('-')[1].substring(2, 3) === '0'">
        <p class="title">{{value.sortation.split('/')[1]}}</p>
        <div class="element-toggle">
          <div class="toggleBtn isUp" />
        </div>
      </div>
      <template v-for="(baseItem, index) in value.sortation.split('/')">
        <table class="base-item" border="1" v-if="value.sortation.split('/')[2] && !value.sortation.split('/')[2].includes('스테이지') && index > 1" :key="baseItem">
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
  data() {
    return {
      tempList: []
    }
  },
  mounted() {
    
  },
  methods: {
    viewContent(event) {
      let toggleBtn = event.currentTarget.lastChild.lastChild;
      this.tempList = event.currentTarget.parentNode.parentNode.childNodes;

      if(toggleBtn.className.includes('isUp')) {
        toggleBtn.classList.remove('isUp');
        toggleBtn.classList.add('isDown');

        for(let i = 0; i < this.tempList.length; i++) {
          if(this.tempList[i].className === 'stage-data') {
            let stageId = this.tempList[i].getAttribute('href').split('/')[2].split('-')[1].substring(0, 1);

            if(event.currentTarget.parentNode.getAttribute('href').split('/')[2].split('-')[1].substring(0, 1) === stageId) {
              for(let j = 0; j < this.tempList[i].childNodes.length; j++) {
                if(this.tempList[i].childNodes[j].tagName === 'TABLE') {
                  this.tempList[i].childNodes[j].style.display = 'table';
                }
              }
            }
          }
        }
      } else if(toggleBtn.className.includes('isDown')) {
        toggleBtn.classList.add('isUp');
        toggleBtn.classList.remove('isDown');
         for(let i = 0; i < this.tempList.length; i++) {
          if(this.tempList[i].className === 'stage-data') {
            let stageId = this.tempList[i].getAttribute('href').split('/')[2].split('-')[1].substring(0, 1);

            if(event.currentTarget.parentNode.getAttribute('href').split('/')[2].split('-')[1].substring(0, 1) === stageId) {
              for(let j = 0; j < this.tempList[i].childNodes.length; j++) {
                if(this.tempList[i].childNodes[j].tagName === 'TABLE') {
                  this.tempList[i].childNodes[j].style.display = 'none';
                }
              }
            }
          }
        }
      }

      event.preventDefault();
    }
  },
  props: ["stageData", "step"],
};
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

.legend {
  display: none;
}

.base-item {
  display: none;
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

.title {
    border: 1px solid #000000;
    font-size: 2.5rem;
    text-align: center;
    margin-top: 30px;
    border-bottom: none;
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

.base-item th, .base-item td {
  width: 35%;
}

.element-toggle {
  height: 4vh;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #000000;
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
</style>