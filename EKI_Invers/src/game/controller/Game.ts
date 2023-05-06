import {Player, UserPlayer, ComPlayer} from '../model/Player'
import {Board} from '../view/Board'
import type {Piece} from '../model/Piece'

export class Game {
    private player1: Player;
    private player2: Player;
    private board: Board;
    
    constructor() {
        this.player1 = new UserPlayer();
        this.player2 = new ComPlayer();
        this.board = new Board();
    };

    public getBoard() : Array<Piece> {
        return this.board.getPieces();
    }

    public resetBoard() {
        this.board = new Board();
    };
};