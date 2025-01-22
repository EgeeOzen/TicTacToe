// // //You are supposed to add your comments

import java.util.*;


class AIplayer {
    List<PointsAndScores> rootsChildrenScores; 
    private static final int MAX_DEPTH = 6; // Maximum depth for the minimax algorithm

    public AIplayer() {
    }

    // Method to return the best move based on scores
    public Point returnBestMove() {
        List<Point> bestMoves = new ArrayList<>();
        int maxScore = Integer.MIN_VALUE;

        // Find the maximum score among all the child nodes
        for (PointsAndScores ps : rootsChildrenScores) {
            if (ps.score > maxScore) {
                maxScore = ps.score;
            }
        }

        // Collect all the moves with the maximum score
        for (PointsAndScores ps : rootsChildrenScores) {
            if (ps.score == maxScore) {
                bestMoves.add(ps.point);
            }
        }

        // Choose randomly among the best moves
        Random random = new Random();
        return bestMoves.get(random.nextInt(bestMoves.size()));
    }

    // Method to return the minimum score from a list
    public int returnMin(List<Integer> list) {
        if (list.isEmpty()) {
            return Integer.MAX_VALUE; // Return max value if the list is empty
        }

        int min = Integer.MAX_VALUE;
        int index = -1;

        // Find the minimum score and its index
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) < min) {
                min = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    // Method to return the maximum score from a list
    public int returnMax(List<Integer> list) {
        if (list.isEmpty()) {
            return Integer.MIN_VALUE; 
        }

        int max = Integer.MIN_VALUE;

        // Find the maximum score
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) > max) {
                max = list.get(i);
            }
        }
        return max;
    }

    // Method to initiate the minimax algorithm
    public void callMinimax(int depth, int turn, Board b) {
        rootsChildrenScores = new ArrayList<>();
        minimax(depth, turn, b, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // Minimax algorithm implementation
    public int minimax(int depth, int turn, Board b, int alpha, int beta) {
        if (depth >= MAX_DEPTH || b.isGameOver()) {
            return evaluate(b, depth); 
        }
    
        List<Point> pointsAvailable = b.getAvailablePoints();
        if (pointsAvailable.isEmpty()) return 0; // If no more moves available, return 0
    
        List<Integer> scores = new ArrayList<>();
    
        // Iterate through all available moves
        for (int i = 0; i < pointsAvailable.size(); ++i) {
            Point point = pointsAvailable.get(i);
    
            if (turn == 1) {
                b.placeAMove(point, 1); 
                int currentScore = minimax(depth + 1, 2, b, alpha, beta); 
                scores.add(currentScore);
                if (depth == 0)
                    rootsChildrenScores.add(new PointsAndScores(currentScore, point)); // Store score for root node
    
                alpha = Math.max(alpha, currentScore); // Update alpha value
            } else if (turn == 2) {
                b.placeAMove(point, 2); 
                int currentScore = minimax(depth + 1, 1, b, alpha, beta); 
                scores.add(currentScore);
                beta = Math.min(beta, currentScore); 
            }
            b.placeAMove(point, 0); 
    
            if (alpha >= beta) // Alpha-beta pruning
                break;
        }
        return turn == 1 ? returnMax(scores) : returnMin(scores); // Return max or min score based on the turn
    }
    
    // Evaluate the board position
    private int evaluate(Board b, int depth) {
        int score = 0;
        if (b.hasXWon()) { 
            score = 10 - depth; 
        } else if (b.hasOWon()) { 
            score = depth - 10; 
        }
        return score;
    }
}
