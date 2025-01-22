//You are supposed to add your comments
public class TicTacToe {

    public static void main(String[] args) {
        AIplayer AI= new AIplayer(); 
        Board b = new Board(); 
        
        b.displayBoard(); // Display the initial state of the board

        System.out.println("Who makes first move? (1)Computer (2)User: ");
        int choice = b.scan.nextInt(); 
        
        // If computer makes the first move
        if(choice == 1){
            AI.callMinimax(0, 1, b); // Invoke minimax algorithm to determine the best move for AI
            for (PointsAndScores pas : AI.rootsChildrenScores) {
                System.out.println("Point: " + pas.point + " Score: " + pas.score); 
            }
            b.placeAMove(AI.returnBestMove(), 1); // Place the AI's move on the board
            b.displayBoard(); 
        }
        
        // Game loop until game over
        while (!b.isGameOver()) {
            System.out.println("Your move: line (1, 2, 3, 4 or 5) colunm (1, 2, 3, 4 or 5)");
            Point userMove = new Point(b.scan.nextInt()-1, b.scan.nextInt()-1); // Read user's move
            // Check if the chosen move is valid
            while (b.getState(userMove)!=0) {
                System.out.println("Invalid move. Make your move again: ");
                userMove.x=b.scan.nextInt()-1;
                userMove.y=b.scan.nextInt()-1;
            }
            b.placeAMove(userMove, 2); // Place the user's move on the board
            b.displayBoard(); 
            
            if (b.isGameOver()) { 
                break;
            } 
            
            AI.callMinimax(0, 1, b); 
            for (PointsAndScores pas : AI.rootsChildrenScores) {
                System.out.println("Point: " + pas.point + " Score: " + pas.score); 
            }
            b.placeAMove(AI.returnBestMove(), 1); // Place the AI's move on the board
            b.displayBoard(); 
        }
        
        // Display game result
        if (b.hasXWon()) {
            System.out.println("Unfortunately, you lost!");
        } else if (b.hasOWon()) {
            System.out.println("You win!");
        } else {
            System.out.println("It's a draw!");
        }
    }    
}
