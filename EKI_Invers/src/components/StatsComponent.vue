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
          player: {
            type: Number,
            default: -1
          },
          pointsPl1: {
            type: Number,
            default: -1
          },
          pointsPl2: {
            type: Number,
            default: -1
          }  
        },
        computed: {
            currentPlayer: function() {
                return "Player " + this.player + " turn";
            },
            currentPoints: function() {
                return this.pointsPl1 + " : " + this.pointsPl2;
            }
        }
    })
</script>

<style scoped>

</style>