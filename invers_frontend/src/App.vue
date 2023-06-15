<template>
  <div id="app">
    <Header />
    <div id="main">
      <div id="mainLeft">
        <Board />
      </div>
      <div id="mainRight">
        <Stats />
        <ControlPanel />
      </div>
    </div>
    <HowTo />
  </div>
</template>

<script lang="ts">
  import { defineComponent } from 'vue'
  import Header from '@/components/HeaderComponent.vue'
  import Board from '@/components/BoardComponent.vue'
  import Stats from '@/components/StatsComponent.vue'
  import ControlPanel from '@/components/ControlPanelComponent.vue'
  import HowTo from '@/components/HowToComponent.vue'
  import { useApiStore } from "@/stores/apiStore";

  export default defineComponent({
    name: 'Application',
    components: {
      Header,
      Board,
      Stats,
      ControlPanel,
      HowTo
    },
    setup() {
      const apiStore = useApiStore();

      function updateAllValues() {
        apiStore.updateValues();
      }
      return {
        updateAllValues
      }
    },
    mounted() {
      setInterval(this.updateAllValues, 2000);
    }
  });
</script>

<style scoped>
* {
  outline: dotted red;
}

#main {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  align-content: normal;
}

#mainLeft {
  display: block;
}

#mainRight {
  display: block;
  margin-left: 100px;
}
</style>
