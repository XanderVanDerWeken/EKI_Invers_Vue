<template>
    <div id="app">
        <Header />
        <div class="container">
            <Board />
            <Stats v-bind:stats="stats" />
            <ControlPanel />
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

</style>
