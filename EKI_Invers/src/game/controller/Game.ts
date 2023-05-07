import {Player, UserPlayer, ComPlayer} from '@/game/model/Player'
import {Board} from '@/game/view/Board'
import {Piece} from '@/game/model/Piece'

export class Game {
    private player1: Player;
    private player2: Player;
    private board: Board;
    private currentPlayer: Number;
    
    constructor() {
        this.player1 = new UserPlayer( Piece.RED, Piece.RED_DOT );
        this.player2 = new ComPlayer( Piece.YELLOW, Piece.YELLOW_DOT );
        this.board = new Board();
        this.currentPlayer = 1
    };

    public getScorePlayer1(): Number {
        return this.getScorePlayer(this.player1);
    }

    public getScorePlayer2(): Number {
        return this.getScorePlayer(this.player2);
    }

    private getScorePlayer(player: Player): Number {
        var count = 0;
        for(var piece in this.getBoard()) {
            if(piece === player.piece || piece === player.dottedPiece) {
                count++;
            }
        }
        return count;
    }

    public getBoard() : Array<Piece> {
        return this.board.getPieces();
    }

    public resetBoard() {
        this.board = new Board();
    };

    public getCurrentPlayer() : Number {
        return this.currentPlayer;
    }
};