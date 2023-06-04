<template>
  <div>
    <h2>How To Play</h2>
    <p>
      Invers ist ein abstraktes Spiel für zwei Spieler, bei dem die Spieler abwechselnd Spielsteine aus einem 6x6-Gitter schieben und umdrehen.
      Eine Seite ist einfarbig rot oder gelb, die andere ist ein roter oder gelber Punkt.
    </p>
    <p>
      Beim nächsten Zug schieben Sie den umgekehrten Spielstein in das Gitter und zwingen einen anderen Spielstein heraus.<br>
      Man dreht die Spielsteine nur einmal um, um die Punktseite zu zeigen, und man kann den Punkt des Gegners nicht herausrutschen lassen.<br>
      Nach und nach verriegelt sich das Brett, es sei denn, Sie beginnen, Ihre eigenen umgedrehten Spielsteine herauszuschieben.
    </p>
    <p>
      Der Gewinner ist der erste, der alle seine Steine umdreht, oder derjenige, der die meisten umgedreht hat, wenn keine weiteren Züge mehr möglich sind.
    </p>
    <a href="https://spiele.tips/invers">Mehr Informationen</a>
  </div>
</template>

<script lang="ts">
import {defineComponent} from "vue";

export default defineComponent({
  name: 'HowTo'
})
</script>

<style scoped>

</style>