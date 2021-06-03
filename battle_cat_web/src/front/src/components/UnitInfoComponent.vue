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
          <unit-info-select-component />
        </div>
      </nav>
      <div class="contents">
          
      </div>
    </div>
  </main>
</template>

<script>
import unitInfoSelectComponent from './unit_info/UnitInfoSelectComponent.vue';

export default {
  data() {
    return {
    }
  },
  components: {
    unitInfoSelectComponent
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
  gap: 0.2%;
}

.element {
  height: 100%;
  line-height: 5vh;
  background-color: #fab92c;
  border-radius: 10px 10px 0 0;
}
</style>