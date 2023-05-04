<template>
    <table>
        <tbody>
            <tr v-for="(row, rowIndex) in boardData" :key="rowIndex">
                <td v-for="(cell, columnIndex) in row" :key="columnIndex" :class="cellClass( cell )" @click="handleCellClick(rowIndex, columnIndex)">
                    {{ cell }}
                </td>
            </tr>
        </tbody>
    </table>
</template>

<script lang="ts">
    export default {
        name: 'Board',
        props: {
            boardData: {
                type: Array<Piece>,
                required: true
            }
        },
        data() {
            
        },
        cellClass(value: Piece): any {
            switch( value ) {
                case Piece.EMPTY:
                    return 'empty-cell';
                case Piece.RED:
                    return 'red';
                case Piece.RED_DOT:
                    return 'red_dot';
                case Piece.YELLOW:
                    return 'yellow';
                case Piece.YELLOW_DOT:
                    return 'yellow_dot';
    };
        },
        methods: {
            handleCellClick(row:Number, col:Number) {
                this.$emit('move', { row, col })
            }
        }
    }
</script>

<style scoped>
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