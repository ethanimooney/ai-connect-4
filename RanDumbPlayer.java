package Connect4PD;

import java.util.*;

public class RanDumbPlayer implements ConnectFourPlayer {
  
  Random r;
  public RanDumbPlayer()
  {
    r = new Random();
  }
  
  public int getMove(String[] board, char toMove)
  {
    int i = r.nextInt(7);
    while(board[0].charAt(i)!='.')
      i = r.nextInt(7);
    return i;
  }
}

//import java.util.ArrayList;
//
//public class RanDumbPlayer implements ConnectFourPlayer {
//  //Assume Red is max player, Black is min player
//  public int getMove(String[] board, char toMove) {
//    ArrayList<Integer> bestMoves = new ArrayList<Integer>();
//    int bestValue = (toMove == 'R'?-2000000000:2000000000);
//
//    for (int col = 0; col < board[0].length(); col++) {
//      int row = 0;
//
//	    while(row < board.length && board[row].charAt(col) == '.'){
//        row++;
//      }
//
//      row--;
//
//      if(row >= 0) { 
//        board[row] = board[row].substring(0,col) + toMove + board[row].substring(col+1);
//        int value = minimax(board, 3, (toMove == 'R'?'B':'R'));
//        board[row] = board[row].substring(0,col) + '.' + board[row].substring(col+1);
//
//        if(toMove =='R' && value > bestValue || toMove == 'B' && value < bestValue) {
//          bestMoves = new ArrayList<Integer>();
//          bestMoves.add(col);
//          bestValue = value;
//        }
//        else if (value == bestValue) {
//          bestMoves.add(col);
//        }
//      }
//    }
//    return bestMoves.get((int)(Math.random()*bestMoves.size()));
//  }
//
//  //Heuristic Function
//  public int evaluateBoard(String[] board, char toMove)
//  {
//      int score = 0;
//      char myChar;
//      if (toMove == 'R') {
//          myChar = 'B';
//      }
//      else {
//          myChar = 'R';
//      }
//
//      for(int row = 0; row < 6; row++) {
//          for(int col = 0; col < 7; col++) {
//            if (board[row].charAt(col) == toMove) {
//                if (row < 5) {
//                    if (board[row + 1].charAt(col) == toMove) {
//                        score -= 5;
//                        if (row < 4) {
//                        if (board[row + 2].charAt(col) == toMove) {
//                            score -= 10;
//                            if (row > 0) {
//                                if (board[row - 1].charAt(col) == myChar) {
//                                    score += 20;
//                                }
//                            }
//                        }
//                        }
//                    }
//                }
//            }
//        }
//      }
//      for(int row = 0; row < 6; row++) {
//          for(int col = 0; col < 7; col++) {
//            if (board[row].charAt(col) == toMove) {
//                if (col < 6) {
//                if (board[row].charAt(col + 1) == toMove) {
//                    score -= 5;
//                    if (col < 5) {
//                    if (board[row].charAt(col + 2) == toMove) {
//                        score -= 10;
//                        if (col > 0) {
//                            if (board[row].charAt(col - 1) == myChar) {
//                                score += 20;
//                            }
//                        }
//                    }
//                    }
//                }
//                }
//            }
//        }
//     }
//for(int row = 0; row < 6; row++) {
//          for(int col = 0; col < 7; col++) {
//            if (board[row].charAt(col) == toMove) {
//                if (row > 0 && row < 5 && col > 0 && col < 6) {
//                if (board[row + 1].charAt(col + 1) == toMove || board[row - 1].charAt(col - 1) == toMove) {
//                    score -= 5;
//                    if (row > 1 && row < 4 && col > 1 && col < 5) {
//                    if (board[row + 2].charAt(col + 2) == toMove) {
//                        score -= 10;
//                        if (row > 0 && col > 0) {
//                            if (board[row - 1].charAt(col - 1) == myChar) {
//                                score += 20;
//                            }
//                        }
//                    }
//                    if (board[row - 2].charAt(col - 2) == toMove) {
//                        score -= 10;
//                        if (row < 5 && col > 0) {
//                            if (board[row + 1].charAt(col - 1) == myChar) {
//                                score += 20;
//                            }
//                        }
//                    }
//                    }
//                }
//                }
//            }
//        }
//     }
//      return score;
//  }
//
//  public int minimax(String[] board, int depth, char toMove) {
//    int end = endState(board);
//    if(end != -1) return end;
//    if(depth == 0) return evaluateBoard(board, toMove);
//
//    int bestValue = (toMove == 'R'?-2000000000:2000000000);
//    
//    for (int col = 0; col < board[0].length(); col++) {
//      int row = 0;
//
//	    while(row < board.length && board[row].charAt(col) == '.'){
//        row++;
//      }
//      
//      row--;
//
//      if(row >= 0) { 
//        board[row] = board[row].substring(0,col) + toMove + board[row].substring(col+1);
//        int value = minimax(board, depth - 1, (toMove == 'R'?'B':'R'));
//        board[row] = board[row].substring(0,col) + '.' + board[row].substring(col+1);
//
//        if(toMove =='R' && value > bestValue || toMove == 'B' && value < bestValue) {
//          bestValue = value;
//        }
//      }
//    }
//    return bestValue;
//  }
//
//  public int endState(String[] board) {
//    for(int i = 0; i < board.length; i++) //horizontal win
//      for(int j = 0; j < board[i].length() - 3; j++)
//        if(board[i].charAt(j)!='.' && 
//           board[i].charAt(j)==board[i].charAt(j+1) &&
//           board[i].charAt(j+1)==board[i].charAt(j+2) &&
//           board[i].charAt(j+2)==board[i].charAt(j+3))
//           return (board[i].charAt(j) == 'R'?1000000:-1000000);
//    for(int i = 0; i < board.length-3; i++)
//    {
//      for(int j = 0; j < board[i].length(); j++) //vertical win
//        if(board[i].charAt(j)!='.' && 
//           board[i].charAt(j)==board[i+1].charAt(j) &&
//           board[i+1].charAt(j)==board[i+2].charAt(j) &&
//           board[i+2].charAt(j)==board[i+3].charAt(j))
//           return (board[i].charAt(j) == 'R'?1000000:-1000000);
//      for(int j = 0; j < board[i].length()-3; j++) //tlbr
//        if(board[i].charAt(j)!='.' && 
//           board[i].charAt(j)==board[i+1].charAt(j+1) &&
//           board[i+1].charAt(j+1)==board[i+2].charAt(j+2) &&
//           board[i+2].charAt(j+2)==board[i+3].charAt(j+3))
//           return (board[i].charAt(j) == 'R'?1000000:-1000000);
//      for(int j = 3; j < board[i].length(); j++) //trbl
//        if(board[i].charAt(j)!='.' && 
//           board[i].charAt(j)==board[i+1].charAt(j-1) &&
//           board[i+1].charAt(j-1)==board[i+2].charAt(j-2) &&
//           board[i+2].charAt(j-2)==board[i+3].charAt(j-3))
//           return (board[i].charAt(j) == 'R'?1000000:-1000000);
//    }
//    return board[0].indexOf(".")!=-1?-1:0;
//  }
//	}
