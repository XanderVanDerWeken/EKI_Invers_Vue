<template>
    <div>
        <h2>Statistics</h2>
        <p>Player {{ activePlayer() }} turn</p>
        <p>{{ scorePlayerOne() }} : {{ scorePlayerTwo() }}</p>
    </div>
</template>

<script lang="ts">
    import { defineComponent } from 'vue'
    import {useApiStore} from "@/stores/apiStore";

    export default defineComponent({
        name: "Stats",
        setup() {
          const apiStore = useApiStore();

          function scorePlayerOne(): number {
            return apiStore.scorePlayerOne;
          }
          function scorePlayerTwo(): number {
            return apiStore.scorePlayerTwo;
          }
          function activePlayer(): number {
            return apiStore.activePlayer;
          }

          function updateStats() {
            apiStore.fetchStats()
          }

          return {
            scorePlayerOne,
            scorePlayerTwo,
            activePlayer,

            updateStats
          }
        },
        mounted() {
          this.updateStats()
        }
    })
</script>

<style scoped>

</style>