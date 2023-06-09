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
  <p id="validMoveText">{{getIfMoveWasValid()}}</p>
</template>

<script setup lang="ts">
import { computed } from "vue";
import { useApiStore } from "@/stores/apiStore";

const apiStore = useApiStore();

const makeMove = (direction: string, index: number) => {
  apiStore.postMove( direction, index );
};

const getIfMoveWasValid = () => {
  if(apiStore.makeMoveResult === 'Invalid Move') {
    return `${apiStore.makeMoveResult} !!`;
  }
  else {
    return '';
  }
};

const getValidMoves = () => apiStore.validMoves;

const getValidDirection = (direction: string, index: number) => {
  const result = getValidMoves().find((move) => move.direction === direction);
  if(result != undefined) {
    if( result.indexes.includes(index) ) {
      return "validMove";
    }
  }
  return "invalidMove";
};

const matrix = computed(() => {
  return apiStore.boardMatrix;
});
</script>

<style scoped>
* {
  --backColRed: red;
  --backColYel: yellow;
  --backColFlip: black;
  --backColValidMove: green;
  --backColInvalidMove: red;
}

td {
  width: 50px;
  height: 50px;
}

#validMoveText {
  font-weight: bold;
  color: var(--backColInvalidMove);
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
  border: 5px solid var(--backColFlip);
  background-image: radial-gradient(circle, transparent 30%, var(--backColFlip) 40%);
}

.validMove {
  background-color: var(--backColValidMove);
}

.invalidMove {
  background-color: var(--backColInvalidMove);
}

</style>