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
    }
});