<template>
  <div>
    <h2>Statistics</h2>
    <p>Player {{ activePlayer() }} turn</p>
    <p>{{ scorePlayerOne() }} : {{ scorePlayerTwo() }} ({{ difference }})</p>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { useApiStore } from "@/stores/apiStore";

export default defineComponent({
  name: "Stats",
  setup() {
    const apiStore = useApiStore();

    function scorePlayerOne(): number {
      return apiStore.stats.scorePlayerOne;
    }
    function scorePlayerTwo(): number {
      return apiStore.stats.scorePlayerTwo;
    }
    function activePlayer(): number {
      return apiStore.stats.activePlayer;
    }

    return {
      scorePlayerOne,
      scorePlayerTwo,
      activePlayer
    }
  },
  computed: {
    difference(): number {
      return this.scorePlayerOne() - this.scorePlayerTwo()
    }
  }
});
</script>

<style scoped>

</style>