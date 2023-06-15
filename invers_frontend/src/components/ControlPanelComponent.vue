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
    <button v-on:click="changePlayer(0)">Change Player 1</button>
    <button v-on:click="changePlayer(1)">Change Player 2</button>
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
      return apiStore.kindPlayerOne;
    }
    function kindPlayerTwo(): string {
      return apiStore.kindPlayerTwo;
    }
    function colorPlayerOne(): string {
      return apiStore.colorPlayerOne;
    }
    function colorPlayerTwo(): string {
      return apiStore.colorPlayerTwo;
    }

    function changePlayer(playerNum: number) {
      apiStore.putChangePlayer(playerNum);
    }

    return {
      startGame,
      resetBoard,
      kindPlayerOne,
      kindPlayerTwo,
      colorPlayerOne,
      colorPlayerTwo,
      changePlayer
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