<template>
    <div>
        <h2>Statistics</h2>
        <p>{{ currentPlayer }}</p>
        <p>{{ currentPoints }}</p>
    </div>
</template>

<script lang="ts">
    import { defineComponent } from 'vue'

    export default defineComponent({
        name: "Stats",
        props: {
          stats: {
            type: Object,
            default: {}
          }
        },
        computed: {
            currentPlayer: function() {
                return "Player " + this.stats.currentPlayer + " turn";
            },
            currentPoints: function() {
                return this.stats.scorePlayerOne + " : " + this.stats.scorePlayerTwo;
            }
        }
    })
</script>

<style scoped>

</style>