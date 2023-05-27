<template>
  <div class="board">
    <div id="headerTable">
      <table>
        <tr>
          <td v-for="colIndex in 6" :key="colIndex">
            <button v-on:click="shiftCol(colIndex, 'down')"
                    :class="getValidDirection('DOWN', colIndex)">Shift</button>
          </td>
        </tr>
      </table>
    </div>
    <div id="leftTable">
      <table>
        <tr v-for="rowIndex in 6" :key="rowIndex">
          <td>
            <button v-on:click="shiftRow(rowIndex, 'right')"
                    :class="getValidDirection('RIGHT', rowIndex)">Shift</button>
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
            <button v-on:click="shiftRow(rowIndex, 'left')"
                    :class="getValidDirection('LEFT', rowIndex)">Shift</button>
          </td>
        </tr>
      </table>
    </div>
    <div class="boardPart" id="footerTable">
      <table>
        <tr>
          <td v-for="colIndex in 6" :key="colIndex">
            <button v-on:click="shiftCol(colIndex, 'up')"
                    :class="getValidDirection('UP', colIndex)">Shift</button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import type { Piece } from '@/game/model/Piece'
import {useApiStore} from "@/stores/apiStore";
import type { Moves } from "@/stores/apiStore";
import { Direction } from "@/game/model/Direction";

export default defineComponent({
        name: "Board",
        setup() {
            const apiStore = useApiStore();

            function shiftRow(row: number, direction: string) {
              // Todo: Implement
              if(direction === 'left') {

              }
              else {

              }
            }

            function shiftCol(col: number, direction: string) {
              // Todo: Implement
              if(direction === 'up') {

              }
              else {

              }
            }

            function getValidMoves(): Moves[] {
              return apiStore.validMoves;
            }

            return {
              apiStore,
              shiftRow,
              shiftCol,
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

#rightTable { grid-area: rightTable; }

#centerTable { grid-area: centerTable; }

#leftTable { grid-area: leftTable; }

#headerTable { grid-area: headerTable; }

#footerTable { grid-area: footerTable; }


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