<template>
  <header>
    <h1>Inversi Spiel</h1>
    <p>Kombinatorisches Spiel, für das Modul "<i>Einführung Künstliche Intelligenz</i>", durch Xander Van der Weken</p>
  </header>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: "Header"
});
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Roboto&display=swap');

header {
  font-family: 'Roboto', sans-serif;
}

h1 {
  font-weight: 900;
}
</style>