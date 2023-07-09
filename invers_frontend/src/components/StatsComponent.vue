<template>
  <div>
    <h2>Statistics</h2>
    <p>Player {{ activePlayer() }} turn</p>
    <p>{{ scorePlayerOne() }} : {{ scorePlayerTwo() }} ({{ difference }})</p>
    <p>Player 1 has {{ piecesPlayerOne }} Pieces in Hand</p>
    <p>Player 2 has {{ piecesPlayerTwo }} Pieces in Hand</p>
  </div>
</template>

<script setup lang="ts">
import { computed } from "vue";
import { useApiStore } from "@/stores/apiStore";

const apiStore = useApiStore();

const activePlayer = () => apiStore.stats.activePlayer;
const scorePlayerOne = () => apiStore.stats.scorePlayerOne;
const scorePlayerTwo = () => apiStore.stats.scorePlayerTwo;

const piecesPlayerOne = computed(() => apiStore.stats.piecesInHandPlayerOne );
const piecesPlayerTwo = computed(() => apiStore.stats.piecesInHandPlayerTwo );

const difference = computed(() => {
  return apiStore.stats.scorePlayerOne - apiStore.stats.scorePlayerTwo;
});
</script>

<style scoped>

</style>