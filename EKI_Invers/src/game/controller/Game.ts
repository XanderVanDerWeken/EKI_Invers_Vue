class Game {
    private player1: Player;
    private player2: Player;
    private board: Board;
    
    constructor() {
        this.player1 = new UserPlayer();
        this.player2 = new ComPlayer();
        this.board = new Board();
    };

    public resetBoard() {
        this.board = new Board();
    };
};