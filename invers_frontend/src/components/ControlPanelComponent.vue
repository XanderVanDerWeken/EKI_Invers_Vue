<template>
  <h2>Control Panel</h2>
  <button v-on:click="startGame()">Start Game</button>
  <button v-on:click="resetBoard()">Reset Game</button>
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

    return {
      startGame,
      resetBoard
    };
  },
})
</script>

<style scoped>
button {
    width: 100px;
    height: 40px;
}
</style>