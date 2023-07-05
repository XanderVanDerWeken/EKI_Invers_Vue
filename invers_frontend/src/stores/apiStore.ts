import { defineStore } from "pinia";
import type {Piece} from "@/models/Piece";

const baseUrl: string = "http://localhost:8080";

interface State {
    stats: {
        scorePlayerOne: number,
        scorePlayerTwo: number,
        activePlayer: number
    },
    options: {
        kindPlayerOne: string,
        kindPlayerTwo: string,
        colorPlayerOne: string,
        colorPlayerTwo: string
    },
    board: Piece[],
    makeMoveResult: string,
    validMoves: Moves[]
}

export const useApiStore = defineStore('api', {
   state: (): State => {
       return {
           stats: {
               scorePlayerOne:0,
               scorePlayerTwo:0,
               activePlayer:0
           },
           options: {
               kindPlayerOne:"",
               kindPlayerTwo:"",
               colorPlayerOne:"",
               colorPlayerTwo:""
           },
           board: [],
           makeMoveResult:"",
           validMoves: []
       };
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
           this.fetchPeriodically();
           this.fetchValidMoves();
       },
        async fetchPeriodically() {
            try {
                const response = await fetch(`${baseUrl}/game/periodicUpdate`);
                const data = await response.json();
                // Setting Stats
                this.stats.scorePlayerOne = data.stats.scorePlayer1;
                this.stats.scorePlayerTwo = data.stats.scorePlayer2;
                this.stats.activePlayer = data.stats.activePlayer;

                // Setting Options
                this.options.kindPlayerOne = data.options.kindPlayerOne;
                this.options.kindPlayerTwo = data.options.kindPlayerTwo;
                this.options.colorPlayerOne = data.options.colorPlayerOne;
                this.options.colorPlayerTwo = data.options.colorPlayerTwo;

                // Setting Board
                this.board = data.board;

            } catch (error) {
                console.error( error );
            }
        },
        async fetchValidMoves() {
            try {
                const response = await fetch(`${baseUrl}/players/possibleMoves`);
                this.validMoves= await response.json();
            } catch (error) {
                console.error( error );
            }
        },
        async postMove( direction: string, index: number ) {
            try {
                const response = await fetch(`${baseUrl}/players/makeMove/${direction}/${index}`, {
                    method: 'POST',
                });
                this.makeMoveResult = await response.text()
            } catch (error) {
                console.error( error );
            }
        },
        async playGame() {
            try {
                await fetch(`${baseUrl}/game/play`, {
                    method: 'POST',
                });
            } catch (error) {
                console.error( error );
            }
        },
        async resetGame() {
            try {
                await fetch(`${baseUrl}/game/reset`, {
                    method: 'POST',
                })
            } catch (error) {
                console.error( error );
            }
       }
    }
});

export interface Moves {
    direction: string;
    indexes: number[];
}