<template>
  <div class="select">
    <div class="rarity">
      <router-link
        @click="activeMenu($event)"
        class="normal unselect"
        name="normal"
        to=""
        ><img
          :src="'/../../../../../imgs/res/elements/rarity/normal.png'"
          alt=""
      /></router-link>
      <router-link
        @click="activeMenu($event)"
        class="special unselect"
        name="special"
        to=""
        ><img
          :src="'/../../../../../imgs/res/elements/rarity/special.png'"
          alt=""
      /></router-link>
      <router-link
        @click="activeMenu($event)"
        class="rare unselect"
        name="rare"
        to=""
        ><img :src="'/../../../../../imgs/res/elements/rarity/rare.png'" alt=""
      /></router-link>
      <router-link
        @click="activeMenu($event)"
        class="super_rare unselect"
        name="super_rare"
        to=""
        ><img
          :src="'/../../../../../imgs/res/elements/rarity/super_rare.png'"
          alt=""
      /></router-link>
      <router-link
        @click="activeMenu($event)"
        class="uber_rare unselect"
        name="uber_rare"
        to=""
        ><img
          :src="'/../../../../../imgs/res/elements/rarity/uber_rare.png'"
          alt=""
      /></router-link>
    </div>
    <div class="target">
      <router-link
    @click="activeMenu($event)"
    class="target_red unselect"
    name="target_red"
    to=""
    ><img :src='"/../../../../../imgs/res/elements/target/target_red.png"' alt=""></router-link
  >
      <router-link
    @click="activeMenu($event)"
    class="target_fly unselect"
    name="target_fly"
    to=""
    ><img :src='"/../../../../../imgs/res/elements/target/target_fly.png"' alt=""></router-link
  >
      <router-link
    @click="activeMenu($event)"
    class="target_black unselect"
    name="target_black"
    to=""
    ><img :src='"/../../../../../imgs/res/elements/target/target_black.png"' alt=""></router-link
  >
      <router-link
    @click="activeMenu($event)"
    class="target_metal unselect"
    name="target_metal"
    to=""
    ><img :src='"/../../../../../imgs/res/elements/target/target_metal.png"' alt=""></router-link
  >
      <router-link
    @click="activeMenu($event)"
    class="target_angel unselect"
    name="target_angel"
    to=""
    ><img :src='"/../../../../../imgs/res/elements/target/target_angel.png"' alt=""></router-link
  >
      <router-link
    @click="activeMenu($event)"
    class="target_alien unselect"
    name="target_alien"
    to=""
    ><img :src='"/../../../../../imgs/res/elements/target/target_alien.png"' alt=""></router-link
  >
      <router-link
    @click="activeMenu($event)"
    class="target_zombie unselect"
    name="target_zombie"
    to=""
    ><img :src='"/../../../../../imgs/res/elements/target/target_zombie.png"' alt=""></router-link
  >
      <router-link
    @click="activeMenu($event)"
    class="target_ancient unselect"
    name="target_ancient"
    to=""
    ><img :src='"/../../../../../imgs/res/elements/target/target_ancient.png"' alt=""></router-link
  >
    </div>
    <div class="property">
      <router-link
    @click="activeMenu($event)"
    class="attack_power_down unselect"
    name="attack_power_down"
    to=""
    ><img :src='"/../../../../../imgs/res/elements/property_search/attack_power_down.png"' alt=""></router-link
  >
      <router-link
    @click="activeMenu($event)"
    class="stop unselect"
    name="stop"
    to=""
    ><img :src='"/../../../../../imgs/res/elements/property_search/stop.png"' alt=""></router-link
  >
      <router-link
    @click="activeMenu($event)"
    class="slow unselect"
    name="slow"
    to=""
    ><img :src='"/../../../../../imgs/res/elements/property_search/slow.png"' alt=""></router-link
  >
      <router-link
    @click="activeMenu($event)"
    class="target_only unselect"
    name="target_only"
    to=""
    ><img :src='"/../../../../../imgs/res/elements/property_search/target_only.png"' alt=""></router-link
  >
    </div>
  </div>
</template>

<script>
import axios from "axios";

const DOMAIN = "http://localhost:8090";

export default {
  data() {
    return {
      searchData: [],
    };
  },
  components: {
    // unitInfoSelectElementComponent,
  },
  methods: {
    async activeMenu(event) {
      let className = event.currentTarget.className;
      let name = event.currentTarget.name;
      let isActive = className.includes("unselect");

      if (isActive) {
        event.currentTarget.classList.remove("unselect");
        this.searchData.push(name);
      } else {
        event.currentTarget.classList.add("unselect");
        let temp = this.searchData.filter(data => data !== name);
        this.searchData = temp;
      }

      console.log(this.searchData);

      let a = await axios.get(`${DOMAIN}/unit_data_search/${this.searchData}`);
      console.log(a);
    },
  },
};
</script>

<style scoped>
.select {
  height: 10vh;
  font-size: 2rem;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  text-align: center;
  gap: 0.2%;
}

.rarity {
  height: 100%;
  display: grid;
  grid-template-rows: repeat(2, 1fr);
  grid-template-columns: repeat(3, 1fr);
}

.target {
  height: 100%;
  display: grid;
  grid-template-rows: repeat(2, 1fr);
  grid-template-columns: repeat(4, 1fr);
}

.rarity img {
  width: 80%;
}

.target img {
  width: 90%;
}

.unselect {
  filter: grayscale(1);
}
</style>