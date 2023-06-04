<template>
  <div class="board">
    <div id="headerTable">
      <table>
        <tr>
          <td v-for="colIndex in 6" :key="colIndex">
            <button v-on:click="makeMove('down', colIndex)"
                    :class="getValidDirection('DOWN', colIndex) + ' rowButtons'">Shift</button>
          </td>
        </tr>
      </table>
    </div>
    <div id="leftTable">
      <table>
        <tr v-for="rowIndex in 6" :key="rowIndex">
          <td>
            <button v-on:click="makeMove('right', rowIndex)"
                    :class="getValidDirection('RIGHT', rowIndex) + ' colButtons'">Shift</button>
          </td>
        </tr>
      </table>
    </div>
    <div id="centerTable">
      <table>
        <tbody>
        <tr v-for="(row, rowIndex) in matrix" :key="rowIndex">
          <td v-for="(cell, colIndex) in row" :key="colIndex"
              :class="cell.toLowerCase()"
          />
        </tr>
        </tbody>
      </table>
    </div>
    <div id="rightTable">
      <table>
        <tr v-for="rowIndex in 6" :key="rowIndex">
          <td>
            <button v-on:click="makeMove('left', rowIndex)"
                    :class="getValidDirection('LEFT', rowIndex) + ' colButtons'">Shift</button>
          </td>
        </tr>
      </table>
    </div>
    <div class="boardPart" id="footerTable">
      <table>
        <tr>
          <td v-for="colIndex in 6" :key="colIndex">
            <button v-on:click="makeMove('up', colIndex)"
                    :class="getValidDirection('UP', colIndex) + ' rowButtons'">Shift</button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import type { Piece } from '@/models/Piece'
import { useApiStore } from "@/stores/apiStore";
import type { Moves } from "@/stores/apiStore";

export default defineComponent({
  name: "Board",
  setup() {
    const apiStore = useApiStore();

    function makeMove( direction: string, index: number ) {
      apiStore.postMove( direction, index );
      if(apiStore.makeMoveResult === 'Invalid Move') {
        alert("Move was invalid. Try again!")
      }
    }

    function getValidMoves(): Moves[] {
      return apiStore.validMoves;
    }

    return {
      apiStore,
      makeMove,
      getValidMoves
    }
  },
  computed: {
    matrix() : Array<Array<Piece>> {
      return this.apiStore.boardMatrix;
    },
  },
  methods: {
    getValidDirection(direction: string, index: number) : string{
      const result = this.getValidMoves().find((move) => move.direction === direction);
      if(result != undefined) {
        if( result.indexes.includes(index) ) {
          return "validMove";
        }
      }
      return "invalidMove";
    },
  }
});
</script>

<style scoped>
* {
    --backColRed: red;
    --backColYel: yellow;
    --backColEmp: white;
    --backColFlip: black;
    --backColValidMove: green;
    --backColInvalidMove: red;
}

td {
    width: 50px;
    height: 50px;
}

.board {
  display: grid;
  grid-template-columns: 0.3fr 2.4fr 0.3fr;
  grid-template-rows: 0.3fr 2.3fr 0.3fr;
  gap: 2px 2px;
  grid-auto-flow: row;
  justify-content: center;
  align-content: center;
  justify-items: center;
  align-items: center;
  grid-template-areas:
    ". headerTable ."
    "leftTable centerTable rightTable"
    ". footerTable .";
}

.rowButtons {
  padding: 8px;
  margin-right: 7px;
  margin-left: 7px;
}

.colButtons {
  padding: 8px;
  margin-bottom: 13px;
  margin-top: 13px;
}

#rightTable {
  grid-area: rightTable;
}

#centerTable {
  grid-area: centerTable;
}

#leftTable {
  grid-area: leftTable;
}

#headerTable {
  grid-area: headerTable;
}

#footerTable {
  grid-area: footerTable;
}


.border {
    display: none;
}

.empty-cell {
    background-color: var(--backColEmp);
}

.red,
.red_dot {
    background-color: var(--backColRed);
}

.yellow,
.yellow_dot {
    background-color: var(--backColYel);
}

.red,
.yellow {
    border: 5px solid var(--backColFlip);
} 

.red_dot,
.yellow_dot {
    background-image: radial-gradient(circle, transparent 30%, var(--backColFlip) 40%);
}

.validMove {
  background-color: var(--backColValidMove);
}

.invalidMove {
  background-color: var(--backColInvalidMove);
}

</style>