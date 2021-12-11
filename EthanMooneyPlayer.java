package Connect4PD;

import java.util.ArrayList;

public class EthanMooneyPlayer implements ConnectFourPlayer {
  //Assume Red is max player, Black is min player
  public int getMove(String[] board, char toMove) {
    int bestMove = 0;
    int bestValue = (toMove == 'R'?-2000000000:2000000000);

    for (int col = 0; col < board[0].length(); col++) {
      int row = 0;

	    while(row < board.length && board[row].charAt(col) == '.'){
        row++;
      }

      row--;

      if(row >= 0) { 
        board[row] = board[row].substring(0,col) + toMove + board[row].substring(col+1);
        int value = minimax(board, 3, (toMove == 'R'?'B':'R'));
        board[row] = board[row].substring(0,col) + '.' + board[row].substring(col+1);

        if(toMove =='R' && value > bestValue || toMove == 'B' && value < bestValue) {
        	bestMove = col;
          bestValue = value;
        }
        else if (value == bestValue) {
        }
      }
    }
    return bestMove;
  }

  //Heuristic Function
  public int evaluateBoard(String[] board, char toMove) {
	  int finalScore = 0;
	  
	  int horCount = 0;
	  
	  for(int i = 0; i < 6; i++) {
		  int tempCount = 0;
		  
		  for(int j = 0; j < 7; j++) {
			  if(board[i].charAt(j) == toMove) {
				  tempCount++;
			  }
		  }
		  
		  if(tempCount > horCount) {
			  horCount = tempCount;
		  }
	  }
	  
	  int  vertCount = 0;
	  
	  for(int k = 0; k < 7; k++) {
		  int tempCount = 0;
		  
		  for(int l = 0; l < 6; l++) {
			  if(board[l].charAt(k) == toMove) {
				  tempCount++;
			  }
		  }
		  
		  if(tempCount > vertCount) {
			  vertCount = tempCount;
		  }
	  }
	
	  if(horCount > vertCount) {
		  if(horCount == 3) {
			  finalScore = 10000;
		  }
		  else if (horCount == 2) {
			  finalScore = 5000;
		  }
		  else if(horCount == 1) {
			  finalScore = 2500;
		  }
	  }
	  else {
		  if(vertCount == 3) {
			  finalScore = 10000;
		  }
		  else if (vertCount == 2) {
			  finalScore = 5000;
		  }
		  else if(vertCount == 1) {
			  finalScore = 2500;
		  }
	  }
	  
	  return finalScore;

  }

  public int minimax(String[] board, int depth, char toMove) {
    int end = endState(board);
    if(end != -1) return end;
    if(depth == 0) return evaluateBoard(board, toMove);

    int bestValue = (toMove == 'R'?-2000000000:2000000000);
    
    for (int col = 0; col < board[0].length(); col++) {
      int row = 0;

	    while(row < board.length && board[row].charAt(col) == '.'){
        row++;
      }
      
      row--;

      if(row >= 0) { 
        board[row] = board[row].substring(0,col) + toMove + board[row].substring(col+1);
        int value = minimax(board, depth - 1, (toMove == 'R'?'B':'R'));
        board[row] = board[row].substring(0,col) + '.' + board[row].substring(col+1);

        if(toMove =='R' && value > bestValue || toMove == 'B' && value < bestValue) {
          bestValue = value;
        }
      }
    }
    return bestValue;
  }

  public int endState(String[] board) {
    for(int i = 0; i < board.length; i++) //horizontal win
      for(int j = 0; j < board[i].length() - 3; j++)
        if(board[i].charAt(j)!='.' && 
           board[i].charAt(j)==board[i].charAt(j+1) &&
           board[i].charAt(j+1)==board[i].charAt(j+2) &&
           board[i].charAt(j+2)==board[i].charAt(j+3))
           return (board[i].charAt(j) == 'R'?1000000:-1000000);
    for(int i = 0; i < board.length-3; i++)
    {
      for(int j = 0; j < board[i].length(); j++) //vertical win
        if(board[i].charAt(j)!='.' && 
           board[i].charAt(j)==board[i+1].charAt(j) &&
           board[i+1].charAt(j)==board[i+2].charAt(j) &&
           board[i+2].charAt(j)==board[i+3].charAt(j))
           return (board[i].charAt(j) == 'R'?1000000:-1000000);
      for(int j = 0; j < board[i].length()-3; j++) //tlbr
        if(board[i].charAt(j)!='.' && 
           board[i].charAt(j)==board[i+1].charAt(j+1) &&
           board[i+1].charAt(j+1)==board[i+2].charAt(j+2) &&
           board[i+2].charAt(j+2)==board[i+3].charAt(j+3))
           return (board[i].charAt(j) == 'R'?1000000:-1000000);
      for(int j = 3; j < board[i].length(); j++) //trbl
        if(board[i].charAt(j)!='.' && 
           board[i].charAt(j)==board[i+1].charAt(j-1) &&
           board[i+1].charAt(j-1)==board[i+2].charAt(j-2) &&
           board[i+2].charAt(j-2)==board[i+3].charAt(j-3))
           return (board[i].charAt(j) == 'R'?1000000:-1000000);
    }
    return board[0].indexOf(".")!=-1?-1:0;
  }
	}