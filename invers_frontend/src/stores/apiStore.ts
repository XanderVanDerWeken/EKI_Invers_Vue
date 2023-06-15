import { defineStore } from "pinia";
import type {Piece} from "@/models/Piece";
import {jsx} from "vue/jsx-runtime";

interface State {
    scorePlayerOne: number;
    scorePlayerTwo: number;
    activePlayer: number;
    kindPlayerOne: string;
    kindPlayerTwo: string;
    colorPlayerOne: string;
    colorPlayerTwo: string;
    makeMoveResult: string;
    board: Piece[];
    validMoves: Moves[];
}

export const useApiStore = defineStore('api', {
   state: (): State => {
       return {
           scorePlayerOne:0,
           scorePlayerTwo:0,
           activePlayer:0,
           kindPlayerOne:"",
           kindPlayerTwo:"",
           colorPlayerOne:"",
           colorPlayerTwo:"",
           makeMoveResult: "",
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
           this.fetchBoard();
           this.fetchOptions();
           this.fetchValidMoves();
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
        async fetchOptions() {
           await fetch('http://localhost:8080/game/options')
               .then(response => {
                   response.json()
                       .then(data => {
                           this.kindPlayerOne = data.kindPlayerOne;
                           this.kindPlayerTwo = data.kindPlayerTwo;
                           this.colorPlayerOne = data.colorPlayerOne;
                           this.colorPlayerTwo = data.colorPlayerTwo;
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
                            this.validMoves = data as Moves[];
                        })
                        .catch(error => console.error( error ));
                })
                .catch(error => console.error( error ));
        },
        async postMove( direction: string, index: number ) {
            await fetch(`http://localhost:8080/players/makeMove/${direction}/${index}`, {
                method: 'POST',
            }).then(response => {
                if(!response.ok) {
                    throw new Error('Network response was not ok');
                }
                response.text()
                    .then(data => {
                        this.makeMoveResult = data;
                    })
                    .catch(error => console.error( error ));
            })
            .catch(error => console.error( error ));
        },
        async playGame() {
            await fetch('http://localhost:8080/game/play', {
                method: 'POST'
            })
                .then(response => {

                })
                .catch(error => console.error( error ));
        },
        async resetGame() {
           await fetch('http://localhost:8080/game/reset', {
               method: 'POST'
           })
               .then(response => {
                   
               })
               .catch(error => console.error( error ));
       },
        async putChangePlayer(playerNum: number, kind: string) {
           await fetch(`http://localhost:8080/game/changePlayer/${playerNum}/${kind}`, {
               method: 'PUT'
           })
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