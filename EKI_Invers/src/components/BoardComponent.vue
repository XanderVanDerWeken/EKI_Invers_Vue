<template>
    <table>
        <tr>
            <td v-for="cell in pieces" v-bind:class="resolveClass( cell )">
                {{ cell }}
            </td>
        </tr>
    </table>
</template>

<script lang="ts">
    import { defineComponent } from 'vue'
    import { Piece } from '../game/model/Piece'

    export default defineComponent({
        name: "Board",
        props: {
            pieces: {
                type: Array<Piece>,
                default: [] as Piece[]
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