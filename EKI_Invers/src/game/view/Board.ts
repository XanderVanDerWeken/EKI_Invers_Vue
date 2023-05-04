class Board {
    private pieces: Array<Piece>;

    constructor() {
        this.pieces = this.initializePieces();
    };

    public getValue(row: number, col: number ): Piece {
        //TODO: Implement
        return this.pieces[0];
    }

    public setValue( row: number, col: number, piece: Piece ) {
        //TODO: Calculate Index Correct
        this.pieces[0] = piece
    }

    public pushLine(row: number, col: number, direction: Direction) {

    }

    public flipField(row: number, col: number) {
        let value = this.getValue(row, col);
        if(value === Piece.RED) {
            this.setValue(row, col, Piece.RED_DOT);
        } else if(value === Piece.YELLOW) {
            this.setValue(row, col, Piece.YELLOW_DOT);
        }
    }

    public getValueForDisplay(row: number, col: number): Piece {
        // TODO: Implement this
        const pieces: Array<Piece> = []
        return pieces[0];
    }

    private initializePieces(): Array<Piece> {
        const pieces: Array<Piece> = [];
        for (let i = 0; i < 100; i++) {
            pieces.push( Piece.EMPTY );
        }
        return pieces;
    };
};
