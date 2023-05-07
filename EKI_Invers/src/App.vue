<template>
    <div id="app">
        <Header />
        <Board v-bind:pieces="game.pieces" />
        <Stats 
            v-bind:player="(game.currentPlayer as number)" 
            v-bind:points-pl1="(game.scorePlayerOne as number)" 
            v-bind:points-pl2="(game.scorePlayerTwo as number)" 
        />
        <ControlPanel />
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

            return {
                game,
            };
        },
    });

</script>

<style scoped>

* {
    outline: dotted red;
}
</style>
