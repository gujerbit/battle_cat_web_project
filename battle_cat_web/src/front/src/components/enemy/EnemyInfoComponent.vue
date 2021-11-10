<template>
  <main>
    <div id="element">
      <div class="elements">
        <div class="property">
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/attack_power_down.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/stop.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/slow.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/knock_back.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/warp.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/ancient_curse.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/attack_invalid.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/attack_power_up.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/survive.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/ultimate_base_crash.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/critical.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/burrow.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/resurrection.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/barrier_breaker.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/soul_attack.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/mini_wave.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/wave.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/thermal_wave.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/poison.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/attack_power_down_invalid.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/stop_invalid.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/slow_invalid.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/knock_back_invalid.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/wave_invalid.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/thermal_wave_invalid.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/attack_type_single.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/attack_type_multi.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/attack_type_long.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/property_search/attack_type_wide.png"
            alt=""
          />
        </div>
        <div class="target">
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/target/red.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/target/fly.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/target/black_none.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/target/metal_none.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/target/angel.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/target/alien.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/target/zombie.png"
            alt=""
          />
          <img
            @click="menuControl($event)"
            class="unselect"
            src="./../../assets/res/elements/target/ancient.png"
            alt=""
          />
        </div>
        <div class="search-area">
          <input type="text" class="search-form" @input="enterUnitSearch($event)" placeholder="검색">
          <div class="include-area">
            <p class="include-text">포함</p>
            <img
              src="./../../assets/temp/search_icon.png"
              alt=""
              class="includingBtn"
              @click="menuControl($event)"
            />
          </div>
        </div>
      </div>
      <router-link to="" class="element-toggle">
        <div class="toggleBtn isDown" />
      </router-link>
    </div>
    <content-component :unitData="unitData" />
  </main>
</template>

<script>
import { onBeforeMount } from 'vue';
import contentComponent from '../enemy/EnemyInfoContentComponent.vue';

export default {
  setup() {
    onBeforeMount(() => {
      alert('준비중입니다.');
      location.href = '/';
    });
  },
  data() {
    return {
      searchData: [],
      unitData: [],
      unitName: '',
      including: true,
    };
  },
  async mounted() {
    let toggleArea = document.querySelector(".element-toggle");
    let toggleBtn = document.querySelector(".toggleBtn");
    let elements = document.querySelector(".elements");

    toggleArea.addEventListener("click", function () {
      if (toggleBtn.className.includes("isUp")) {
        toggleBtn.classList.add("isDown");
        toggleBtn.classList.remove("isUp");
        elements.style.display = "none";
        toggleArea.style.borderRadius = '20px 20px 20px 20px';
      } else if (toggleBtn.className.includes("isDown")) {
        toggleBtn.classList.add("isUp");
        toggleBtn.classList.remove("isDown");
        elements.style.display = "grid";
        toggleArea.style.borderRadius = '0 0 20px 20px';
      }
    });

    let { data } = await this.axios.get(`/enemy_data`);
    this.unitData = data;
  },
  methods: {
    async menuControl(event) {
      let select = event.currentTarget.className.includes("unselect");
      let src = event.currentTarget.src;
      let element = src.split("/")[4].split(".")[0];

      if (
        element.includes("target_black") ||
        element.includes("target_metal")
      ) {
        if (element.includes("target_black")) {
          if (select) {
            this.searchData.push("target_black");
            event.currentTarget.src = require("./../../assets/res/elements/target/black.png");
            event.currentTarget.classList.remove("unselect");
          } else {
            let tempList = this.searchData.filter(
              (data) => data != "target_black"
            );
            this.searchData = tempList;
            event.currentTarget.src = require("./../../assets/res/elements/target/black_none.png");
            event.currentTarget.classList.add("unselect");
          }
        } else if (element.includes("target_metal")) {
          if (select) {
            this.searchData.push("target_metal");
            event.currentTarget.src = require("./../../assets/res/elements/target/metal.png");
            event.currentTarget.classList.remove("unselect");
          } else {
            let tempList = this.searchData.filter(
              (data) => data != "target_metal"
            );
            this.searchData = tempList;
            event.currentTarget.src = require("./../../assets/res/elements/target/metal_none.png");
            event.currentTarget.classList.add("unselect");
          }
        }
      } else {
        if (!event.currentTarget.className.includes("includingBtn")) {
          if (select) {
            this.searchData.push(element);
            event.currentTarget.classList.remove("unselect");
          } else {
            let tempList = this.searchData.filter((data) => data != element);
            this.searchData = tempList;
            event.currentTarget.classList.add("unselect");
          }
        } else {
          let includeText = event.currentTarget.parentNode.firstChild;

          if (select) {
            includeText.innerHTML = "포함";
            event.currentTarget.classList.remove("unselect");
            this.including = true;
          } else {
            includeText.innerHTML = "미포함";
            event.currentTarget.classList.add("unselect");
            this.including = false;
          }
        }
      }

      if(this.searchData.length !== 0 && this.unitName !== '' && this.including) {
        let tempName = 'name_' + this.unitName;
        let searchInfo = [this.searchData, tempName];
        let { data } = await this.axios.get(`/enemy_data_name_search_include/${searchInfo}`);
        this.unitData = data;
      } else if(this.searchData.length !== 0 && this.unitName !== '' && !this.including) {
        let tempName = 'name_' + this.unitName;
        let searchInfo = [this.searchData, tempName];
        let { data } = await this.axios.get(`/enemy_data_name_search/${searchInfo}`);
        this.unitData = data;
      } else if(this.searchData.length === 0 && this.unitName !== '') {
        let { data } = await this.axios.get(`/enemy_data_name/${this.unitName}`);
        this.unitData = data;
      } else if (this.searchData.length !== 0 && this.unitName === '') {
          if (this.including) {
            let { data } = await this.axios.get(`/enemy_data_search_include/${this.searchData}`);
            this.unitData = data;
          } else {
            let { data } = await this.axios.get(`/enemy_data_search/${this.searchData}`);
            this.unitData = data;
          }
      } else {
        let { data } = await this.axios.get(`/enemy_data`);
        this.unitData = data;
      }
    },
    async enterUnitSearch(event) {
      this.unitName = event.currentTarget.value;

      if(this.searchData.length !== 0 && this.unitName !== '' && this.including) {
        let tempName = 'name_' + this.unitName;
        let searchInfo = [this.searchData, tempName];
        let { data } = await this.axios.get(`/enemy_data_name_search_include/${searchInfo}`);
        this.unitData = data;
      } else if(this.searchData.length !== 0 && this.unitName !== '' && !this.including) {
        let tempName = 'name_' + this.unitName;
        let searchInfo = [this.searchData, tempName];
        let { data } = await this.axios.get(`/enemy_data_name_search/${searchInfo}`);
        this.unitData = data;
      } else if(this.searchData.length !== 0 && this.unitName === '') {
        if (this.including) {
          let { data } = await this.axios.get(`/enemy_data_search_include/${this.searchData}`);
          this.unitData = data;
        } else {
          let { data } = await this.axios.get(`/enemy_data_search/${this.searchData}`);
          this.unitData = data;
        }
      } else if(this.searchData.length === 0 && this.unitName !== '') {
        let { data } = await this.axios.get(`/enemy_data_name/${this.unitName}`);
        this.unitData = data;
      } else {
        let { data } = await this.axios.get(`/enemy_data`);
        this.unitData = data;
      }
    },
  },
  components: {
      contentComponent,
  }
};
</script>

<style scoped>
main {
  width: 100%;
}

#element {
  width: 55%;
  margin: 0 auto;
}

.elements {
  display: none;
  grid-template-rows: repeat(2, 1fr);
}

.property, .target {
  display: grid;
  justify-items: center;
  align-items: center;
}

.property img {
  width: 80%;
  cursor: pointer;
}

.target img {
  width: 50%;
  cursor: pointer;
}

.property {
  grid-template-columns: repeat(15, 1fr);
  row-gap: 0.5vh;
  border: 2px solid #000000;
  border-radius: 20px 20px 0 0;
  border-bottom: none;
  padding-top: 1vh;
}

.target {
  grid-template-columns: repeat(4, 1fr);
  column-gap: 0.5vw;
  border: 2px solid #000000;
  border-top: none;
  border-bottom: none;
}

.element-toggle {
  height: 4vh;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 2px solid #000000;
  border-radius: 20px 20px 20px 20px;
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

.search-area {
  display: flex;
  align-items: center;
  border: 2px solid #000000;
  border-top: none;
  border-bottom: none;
  padding-bottom: 0.5vh;
}

.search-form {
  height: 90%;
  width: 90%;
  margin-left: 0.5vw;
  border: 2px solid #000000;
  border-radius: 7px;
  outline: none;
  font-size: 2.5rem;
  text-align: center;
}

.include-area {
  display: grid;
  grid-template-rows: 30% 70%;
  justify-items: center;
}

.includingBtn {
  width: 40%;
  border-radius: 25%;
  margin-top: 0.4vh;
  cursor: pointer;
}

.include-text {
  text-align: center;
  font-size: 1.5rem;
}
</style>