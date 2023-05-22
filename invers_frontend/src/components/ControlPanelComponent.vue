<template>
    <h2>Control Panel</h2>
    <button v-on:click="resetBoard">Reset Game</button>
</template>

<script lang="ts">
    import { defineComponent} from 'vue'
    import { useGameStore } from '@/stores/gameStore';

    export default defineComponent({
        name: 'ControlPanel',
        setup() {
            const game = useGameStore();

            function resetBoard() {
                alert("reset");
                game.resetBoard();
            }

            return {
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