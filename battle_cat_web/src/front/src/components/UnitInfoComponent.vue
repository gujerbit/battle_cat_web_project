<template>
  <main>
      <nav>
        <div class="elements">
          <unit-info-element-component :title="'레어도'" :type="0" />
          <unit-info-element-component :title="'타겟'" :type="1" />
          <unit-info-element-component :title="'효과/능력'" :type="2" />
        </div>
        <div class="contents">

        </div>
      </nav>
  </main>
</template>

<script>
import unitInfoElementComponent from './UnitInfoElementComponent.vue';

export default {
  components: {
    unitInfoElementComponent
  }
}
</script>

<style scoped>

</style>