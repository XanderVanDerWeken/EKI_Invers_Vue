<template>
    <table>
        <tbody>
            <tr v-for="(row, rowIndex ) in matrix" :key="rowIndex">
                <td v-for="(cell, colIndex) in row" :key="colIndex" 
                    :class="resolveClass(cell)" v-on:click="handleCellClick(rowIndex, colIndex)"
                />
            </tr>
        </tbody>
    </table>
</template>

<script lang="ts">
    import { defineComponent } from 'vue'
    import { Piece } from '../game/model/Piece'
    import { useGameStore } from '@/stores/gameStore';

    export default defineComponent({
        name: "Board",
        computed: {
            matrix() : Array<Array<Piece>> {   
                return this.game.boardMatrix;
            }
        },
        setup() {
            const game = useGameStore();

            function handleCellClick(rowIndex: number, colIndex: number): void {
                game.makeMove(rowIndex, colIndex);
            }

            return {
                game,

                handleCellClick
            }
        },
        methods: {
            resolveClass(cell : Piece): String {
                switch(cell) {
                    case Piece.RED:
                        return "red";
                    case Piece.RED_DOT:
                        return "red_dot";
                    case Piece.YELLOW:
                        return "yellow";
                    case Piece.YELLOW_DOT:
                        return "yellow_dot";
                    default:
                        return "";
                }
            }
        }
    });
</script>

<style scoped>
* {
    --backColRed: red;
    --backColYel: yellow;
    --backColEmp: white;
    --backColFlip: black;
}

td {
    width: 50px;
    height: 50px;
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

</style>