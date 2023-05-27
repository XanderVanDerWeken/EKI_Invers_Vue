import { defineStore } from "pinia";
import type {Piece} from "@/game/model/Piece";
import type {Direction} from "@/game/model/Direction";

interface State {
    scorePlayerOne: number;
    scorePlayerTwo: number;
    activePlayer: number;
    board: Piece[];
    validMoves: Moves[];
}

export const useApiStore = defineStore('api', {
   state: (): State => {
       return {
           scorePlayerOne:0,
           scorePlayerTwo:0,
           activePlayer:0,
           board: [],
           validMoves: []
       }
   },
    getters: {
        boardMatrix(): Array<Array<Piece>> {
            const matrix: Array<Array<Piece>> = [];
            for(let i = 0; i < 10; i++) {
                matrix.push(this.board.slice(i * 10, (i + 1) * 10));
            }
            return (matrix as Array<Array<Piece>>);
        },
    },
    actions: {
       updateValues() {
           this.fetchStats();
           this.fetchBoard();
           this.fetchStats();
       },
       async fetchStats() {
            await fetch('http://localhost:8080/game/stats')
                .then(response => {
                    response.json()
                        .then(data => {
                            this.scorePlayerOne = data.scorePlayer1;
                            this.scorePlayerTwo = data.scorePlayer2;
                            this.activePlayer = data.activePlayer;
                        })
                        .catch(error => console.error( error ));
                })
                .catch(error => console.error( error ));
       },
        async fetchBoard() {
            await fetch('http://localhost:8080/game/board')
                .then(response => {
                    response.json()
                        .then(data => {
                            this.board = data;
                        })
                        .catch(error => console.error( error ))
                })
                .catch(error => console.error( error ));
        },
        async fetchValidMoves() {
            await fetch('http://localhost:8080/players/possibleMoves')
                .then(response => {
                    response.json()
                        .then(data => {
                            this.validMoves = data;
                        })
                        .catch(error => console.error( error ));
                })
                .catch(error => console.error( error ));
        },
       async resetGame() {
           await fetch('http://localhost:8080/game/reset')
               .then(response => {

               })
               .catch(error => console.error( error ));
       }
    }
});

export interface Moves {
    direction: string;
    indexes: number[];
}