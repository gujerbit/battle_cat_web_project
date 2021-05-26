<template>
  <main>
    <div class="main-container">
      <nav>
        <div class="elements">
          <div class="title">
            <div class="element">레어도</div>
            <div class="element">타겟</div>
            <div class="element">효과/능력</div>
          </div>
          <div class="select">
            <div class="rarity">
              <router-link @click="activeMenu($event)" name="normal" class="normal select-active" to="">기본 캐릭터</router-link>
              <router-link @click="activeMenu($event)" name="special" class="special" to="">EX</router-link>
              <router-link name="rare" class="rare" to="">레어</router-link>
              <router-link name="super-rare" class="super-rare" to="">슈퍼 레어</router-link>
              <router-link name="uber-rare" class="uber-rare" to="">울트라 슈퍼 레어</router-link>
              <router-link name="legend-rare" class="legend-rare" to="">레전드 레어</router-link>
            </div>
            <div class="target">
              <router-link name="red-enemy" class="red-enemy" to="">빨간 적</router-link>
              <router-link name="fly-enemy" class="fly-enemy" to="">떠있는 적</router-link>
              <router-link name="black-enemy" class="black-enemy" to="">검은 적</router-link>
              <router-link name="metal-enemy" class="metal-enemy" to="">메탈 적</router-link>
              <router-link name="engel-enemy" class="engel-enemy" to="">천사</router-link>
              <router-link name="alien-enemy" class="alien-enemy" to="">에이리언</router-link>
              <router-link name="zombie-enemy" class="zombie-enemy" to="">좀비</router-link>
              <router-link name="acient-enemy" class="acient-enemy" to="">고대종</router-link>
            </div>
            <div class="property">
              
            </div>
          </div>
        </div>
      </nav>
      <div class="contents">
          
      </div>
    </div>
  </main>
</template>

<script>
export default {
  data() {
    return {
      findList: []
    }
  },
  methods: {
    activeMenu(event) {
      let className = event.currentTarget.className;
      let name = event.currentTarget.name;
      let isActive = className.includes('select-active');
      
      if(isActive) {
        event.currentTarget.classList.remove('select-active');
        this.findList = this.findList.filter((value) => value !== name);
      } else { 
        event.currentTarget.classList.add('select-active');
        this.findList.push(name);
      }

      console.log(this.findList);
    }
  }
}
</script>

<style scoped>
main {
  width: 100%;
}

.main-container {
  width: 80%;
  margin: 0 auto;
  margin-top: 0.5%;
}

nav, .contents, .elements {
  width: 100%;
}

.title {
  width: 100%;
  height: 5vh;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  text-align: center;
  font-size: 2.5rem;
}

.element {
  height: 100%;
  line-height: 5vh;
  background-color: #fab92c;
  border-radius: 10px 10px 0 0;
}

.select {
  height: 10vh;
  font-size: 2rem;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  text-align: center;
}

.select a {
  background-color: #bbbbbb;
  border-radius: 10px;
  height: 50%;
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

.select-active {
  background-color: #fab92c !important;
}
</style>