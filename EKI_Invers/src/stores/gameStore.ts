import { defineStore } from 'pinia';

import type { Piece } from '@/game/model/Piece';
import { Game } from '@/game/controller/Game';
const gameObj = new Game();

interface State {
    game: Game
}

export const useGameStore = defineStore('game', {
    state: (): State => {
        return {
            game : gameObj
        }
    },
    getters: {
        pieces(): Array<Piece> {
            return this.game.getBoard();
        },
        boardMatrix(): Array<Array<Piece>> {
            const matrix: Array<Array<Piece>> = [];
            for(let i = 0; i < 10; i++) {
                matrix.push(this.game.getBoard().slice(i * 10, (i + 1) * 10));
            }
            return (matrix as Array<Array<Piece>>);
        },
        currentPlayer(): Number {
            return this.game.getCurrentPlayer();
        },
        scorePlayerOne(): Number {
            return this.game.getScorePlayer1();
        },
        scorePlayerTwo(): Number {
            return this.game.getScorePlayer2();
        }
    },
    actions: {
        resetBoard() {
            this.game.resetBoard();
        },
        makeMove(rowIndex: number, colIndex: number) {
            //TODO: Implement
        }
    }
});