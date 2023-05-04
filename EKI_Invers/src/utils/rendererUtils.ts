export function handleCellClass(value: Piece) :String {
    switch( value ) {
        case Piece.EMPTY:
            return 'empty-cell';
        case Piece.RED:
            return 'red';
        case Piece.RED_DOT:
            return 'red_dot';
        case Piece.YELLOW:
            return 'yellow';
        case Piece.YELLOW_DOT:
            return 'yellow_dot';
    }
};