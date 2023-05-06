<template>
    
</template>

<script lang="ts">
    import { defineComponent } from 'vue'

    export default defineComponent({
        name: "Board"
    });
</script>

<style scoped>
td {
    width: 50px;
    height: 50px;
}

.empty-cell {
    background-color: white;
}

.red .red_dot {
    background-color: red;
}

.yellow .yellow_dot {
    background-color: yellow;
}

</style>