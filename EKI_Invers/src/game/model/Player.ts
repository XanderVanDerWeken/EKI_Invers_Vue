import type {Piece} from './Piece'

export abstract class Player {
    abstract makeMove( index: Number, piece: Piece ): void;
};

export class ComPlayer extends Player {
    public makeMove( index: Number, piece: Piece ): void {
        
    }
};

export class UserPlayer extends Player {
    public makeMove( index: Number, piece: Piece ): void {
        
    }
};