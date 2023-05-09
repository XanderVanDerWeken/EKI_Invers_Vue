import type {Piece} from '@/game/model/Piece'

export abstract class Player {
    public piece: Piece;
    public dottedPiece: Piece;

    protected constructor(piece: Piece, dottedPiece: Piece) {
        this.piece = piece;
        this.dottedPiece = dottedPiece;
    }

    abstract makeMove( index: Number, piece: Piece ): void;
};

export class ComPlayer extends Player {

    constructor(piece: Piece, dottedPiece: Piece) {
        super(piece, dottedPiece);
    }

    public makeMove( index: Number, piece: Piece ): void {
        
    }
};

export class UserPlayer extends Player {

    constructor(piece: Piece, dottedPiece: Piece) {
        super(piece, dottedPiece);
    }

    public makeMove( index: Number, piece: Piece ): void {
        
    }
};