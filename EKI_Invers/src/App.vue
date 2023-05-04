<template>
    <h1>Inversi Spiel</h1>
    <div id="app">
        <Board :boardData="boardData" @move="handleMove" />
    </div> 
</template>

<script lang="ts">
    import Board from './components/Board.vue'
    //import Statistics from './components/Stats.vue'

    export default {
        name: 'Application',
        components: {
            Board,
        },
        data() {
            return {
                boardData: []
            }
        },
        mounted() {
            // Fetch initial board
        },
        methods: {
            handleMove(move) {
                // Handle Move from board component
            }
        }
    }

</script>

<style scoped>

</style>
