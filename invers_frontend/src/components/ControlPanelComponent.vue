<template>
  <div>
    <h2>Control Panel</h2>
    <button v-on:click="startGame()">Start Game</button>
    <button v-on:click="resetBoard()">Reset Game</button>
  </div>
  <div>
    <h2>Options</h2>
    <p>Player 1 ({{ colorPlayerOne() }}) is {{ kindPlayerOne() }}</p>
    <p>Player 2 ({{ colorPlayerTwo() }}) is {{ kindPlayerTwo() }}</p>
  </div>
</template>

<script setup lang="ts">
import { useApiStore } from "@/stores/apiStore";

const apiStore = useApiStore();

const startGame = () => {
  apiStore.playGame();
  alert("Game has been started");
};

const resetBoard = () => {
  apiStore.resetGame();
  alert("Game has been reset");
};

const colorPlayerOne = () => apiStore.options.colorPlayerOne;
const colorPlayerTwo = () => apiStore.options.colorPlayerTwo;
const kindPlayerOne = () => apiStore.options.kindPlayerOne;
const kindPlayerTwo = () => apiStore.options.kindPlayerTwo;
</script>

<style scoped>
button {
  display: block;
  width: 100px;
  height: 40px;
  margin: 10px;
}
</style>