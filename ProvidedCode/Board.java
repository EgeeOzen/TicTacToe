//You are supposed to add your comments

import java.util.*;

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + (x+1) + ", " + (y+1) + "]"; 
    }
}


class PointsAndScores {
    int score;
    Point point;

    PointsAndScores(int score, Point point) {
        this.score = score;
        this.point = point;
    }
}

// Class representing the game board
class Board {
    List<Point> availablePoints; 
    Scanner scan = new Scanner(System.in);
    int[][] board = new int[5][5]; // Change board size to 5x5

    public Board() {
    }

    // Method to check if the game is over
    public boolean isGameOver() {
        return (hasXWon() || hasOWon() || getAvailablePoints().isEmpty());
    }

    // Method to calculate heuristic value for a player
    public int calculateHeuristicValue(int player) {
        // Calculate heuristic value based on the current state of the board
        if (hasXWon()) {
            return player == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else if (hasOWon()) {
            return player == 2 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            int heuristicValue = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (board[i][j] == player) {
                        // Adjust heuristic based on player's pieces
                        heuristicValue += 1;
                    } else if (board[i][j] != 0) {
                        // Adjust heuristic based on opponent's pieces
                        heuristicValue -= 1;
                    }
                }
            }
            return heuristicValue;
        }
    }

    public boolean hasXWon() {
        for (int i = 0; i < 5; ++i) {
            if ((board[i][0] == 1 && board[i][1] == 1 && board[i][2] == 1 && board[i][3] == 1 && board[i][4] == 1) ||
                (board[0][i] == 1 && board[1][i] == 1 && board[2][i] == 1 && board[3][i] == 1 && board[4][i] == 1)) {
                return true;
            }
        }
        if ((board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1 && board[3][3] == 1 && board[4][4] == 1) ||
            (board[0][4] == 1 && board[1][3] == 1 && board[2][2] == 1 && board[3][1] == 1 && board[4][0] == 1)) {
            return true;
        }
        return false;
    }

    public boolean hasOWon() {
        for (int i = 0; i < 5; ++i) {
            if ((board[i][0] == 2 && board[i][1] == 2 && board[i][2] == 2 && board[i][3] == 2 && board[i][4] == 2) ||
                (board[0][i] == 2 && board[1][i] == 2 && board[2][i] == 2 && board[3][i] == 2 && board[4][i] == 2)) {
                return true;
            }
        }
        if ((board[0][0] == 2 && board[1][1] == 2 && board[2][2] == 2 && board[3][3] == 2 && board[4][4] == 2) ||
            (board[0][4] == 2 && board[1][3] == 2 && board[2][2] == 2 && board[3][1] == 2 && board[4][0] == 2)) {
            return true;
        }
        return false;
    }

    // Method to get available points on the board
    public List<Point> getAvailablePoints() {
        availablePoints = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (board[i][j] == 0) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }
    
    // Method to get the state of a particular point
    public int getState(Point point){
    	return board[point.x][point.y];
    }

    // Method to place a move on the board
    public void placeAMove(Point point, int player) {
        board[point.x][point.y] = player;   
    }

    // Method to display the current state of the board
    public void displayBoard() {
        System.out.println();

        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (board[i][j] == 1)           
                    System.out.print("X "); // Print X for player X
                else if (board[i][j] == 2)
                    System.out.print("O "); // Print O for player O
                else
                    System.out.print(". "); // Print . for empty spaces
            }
            System.out.println();
        }
    }
}
