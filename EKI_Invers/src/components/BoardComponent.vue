<template>
    <directionModal :show-modal="showModal" :modal-text="modalText" @direction-selected="handleDirectionSelected" />
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
    import { defineComponent, ref } from 'vue'
    import DirectionModal from '@/components/DirectionModal.vue'
    import { Piece } from '../game/model/Piece'
    import type { Direction } from '@/game/model/Direction';
    import { useGameStore } from '@/stores/gameStore';

    export default defineComponent({
        name: "Board",
        components: {
            DirectionModal
        },
        data() {
            return {
                showModal: false,
                modalText: '',
                selectedRow: -1,
                selectedCol: -1,
            };
        },
        setup() {
            const game = useGameStore();

            return {
                game,
            }
        },
        computed: {
            matrix() : Array<Array<Piece>> {   
                return this.game.boardMatrix;
            }
        },
        methods: {
            handleCellClick(rowIndex: number, colIndex: number) {
                this.selectedRow = rowIndex;
                this.selectedCol = colIndex;
                this.modalText = `Select direction for piece at row ${rowIndex}, column ${colIndex}:`

                this.showModal = true;
            },
            handleDirectionSelected(direction: Direction) {
                var pos = (this.selectedRow * 10) + this.selectedCol;
                this.game.makeMove(pos, direction)
                this.showModal = false;
            },
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
                    case Piece.EMPTY:
                        return "empty-cell"
                    default:
                        return "border";
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

</style>