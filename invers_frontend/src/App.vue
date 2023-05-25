<template>
    <div id="app">
        <Header />
        <div class="container">
          <Board />

          <div class="controls">
            <Stats v-bind:stats="stats" />
            <ControlPanel />
          </div>
        </div>
        
    </div> 
</template>

<script lang="ts">
    import { defineComponent } from 'vue'
    import { useGameStore } from '@/stores/gameStore'

    import Header from '@/components/HeaderComponent.vue'
    import Board from '@/components/BoardComponent.vue'
    import Stats from '@/components/StatsComponent.vue'
    import ControlPanel from '@/components/ControlPanelComponent.vue'

    export default defineComponent({
        name: 'Application',
        components: {
            Header,
            Board,
            Stats,
            ControlPanel
        },
        setup() {
            const game = useGameStore();

            const stats = {
                currentPlayer : game.currentPlayer,
                scorePlayerOne : game.scorePlayerOne,
                scorePlayerTwo : game.scorePlayerTwo
            }

            return {
                game,
                stats
            };
        },
    });

</script>

<style scoped>
* {
    outline: dotted red;
}

.controls {
  display: grid;
  grid-template-columns: 0.9fr 0.9fr;
  grid-template-rows: 1fr;
  gap: 2px 2px;
  grid-auto-flow: row;
  grid-template-areas:
    "StatsComponent ControlComponent";
}

ControlPanel { grid-area: ControlComponent; }

Stats { grid-area: StatsComponent; }

</style>
