import { defineStore } from "pinia";

interface State {
    scorePlayerOne: number
    scorePlayerTwo: number
    activePlayer: number
}

export const useApiStore = defineStore('api', {
   state: (): State => {
       return {
           scorePlayerOne:0,
           scorePlayerTwo:0,
           activePlayer:0
       }
   },
    actions: {
       async fetchStats() {
            await fetch('http://localhost:8080/game/stats')
                .then(response => {
                    response.json()
                        .then(data => {
                            console.log(data);
                            this.scorePlayerOne = data.scorePlayer1;
                            this.scorePlayerTwo = data.scorePlayer2;
                            this.activePlayer = data.activePlayer;
                        })
                        .catch(error => console.error( error ));
                })
                .catch(error => console.error( error ));
       }
    }
});