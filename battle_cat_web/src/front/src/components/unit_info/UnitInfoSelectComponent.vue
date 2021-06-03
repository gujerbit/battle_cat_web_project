<template>
  <div class="select">
    <div class="rarity">
      <unit-info-select-element-component
        :className="'normal'"
        :selectName="'기본 캐릭터'"
      />
      <unit-info-select-element-component
        :className="'special'"
        :selectName="'EX'"
      />
      <unit-info-select-element-component
        :className="'rare'"
        :selectName="'레어'"
      />
      <unit-info-select-element-component
        :className="'super-rare'"
        :selectName="'슈퍼 레어'"
      />
      <unit-info-select-element-component
        :className="'uber-rare'"
        :selectName="'울트라 슈퍼 레어'"
      />
      <unit-info-select-element-component
        :className="'legend-rare'"
        :selectName="'레전드 레어'"
      />
    </div>
    <div class="target">
        <unit-info-select-element-component
        :className="'red-enemy'"
        :selectName="'빨간 적'"
      />
      <unit-info-select-element-component
        :className="'fly-enemy'"
        :selectName="'떠있는 적'"
      />
      <unit-info-select-element-component
        :className="'black-enemy'"
        :selectName="'검은 적'"
      />
      <unit-info-select-element-component
        :className="'metal-enemy'"
        :selectName="'메탈 적'"
      />
      <unit-info-select-element-component
        :className="'angel-enemy'"
        :selectName="'천사'"
      />
      <unit-info-select-element-component
        :className="'alien-enemy'"
        :selectName="'에이리언'"
      />
      <unit-info-select-element-component
        :className="'zombie-enemy'"
        :selectName="'좀비'"
      />
      <unit-info-select-element-component
        :className="'ancient-enemy'"
        :selectName="'고대종'"
      />
    </div>
    <div class="property"></div>
  </div>
</template>

<script>
import unitInfoSelectElementComponent from "./UnitInfoSelectElementComponent.vue";

export default {
  components: {
    unitInfoSelectElementComponent,
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
</style>