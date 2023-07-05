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

<script lang="ts">
import { defineComponent} from 'vue'
import { useApiStore } from '@/stores/apiStore';

export default defineComponent({
  name: 'ControlPanel',
  setup() {
    const apiStore = useApiStore();

    function startGame() {
      apiStore.playGame();
      alert("Game has been started");
    }

    function resetBoard() {
      apiStore.resetGame();
      alert("Game has been reset.");
    }

    function kindPlayerOne(): string {
      return apiStore.options.kindPlayerOne;
    }
    function kindPlayerTwo(): string {
      return apiStore.options.kindPlayerTwo;
    }
    function colorPlayerOne(): string {
      return apiStore.options.colorPlayerOne;
    }
    function colorPlayerTwo(): string {
      return apiStore.options.colorPlayerTwo;
    }

    return {
      startGame,
      resetBoard,
      kindPlayerOne,
      kindPlayerTwo,
      colorPlayerOne,
      colorPlayerTwo
    };
  },
})
</script>

<style scoped>
button {
  display: block;
  width: 100px;
  height: 40px;
  margin: 10px;
}
</style>