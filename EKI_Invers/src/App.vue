<template>
    <div id="app">
        <Header />
        <Board />
        <Stats />
    </div> 
</template>

<script lang="ts">
    import { defineComponent } from 'vue'
    import Header from './components/HeaderComponent.vue'
    import Board from './components/BoardComponent.vue'
    import Stats from './components/StatsComponent.vue'

    export default defineComponent({
        name: 'Application',
        components: {
            Header,
            Board,
            Stats,
        },
    });

</script>

<style scoped>

* {
    outline: dotted red;
}
</style>
