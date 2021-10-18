package noughtsAndCrossesV3;

import java.util.Arrays;

import ncErrors.outOfRangeError;

public class NCGridV3 {
	
	
	/**
	 * Represents the allowed content of squares in the grid as an enum
	 *
	 */
	public enum SquareStatus {NOUGHT,CROSS,EMPTY} 
	/**
	 * Represents the state of the game using an enum
	 *
	 */
	public enum GameStatus {NOUGHTWIN,CROSSWIN,DRAW,STILLPLAYING}
	
	protected int rowCount;		// number of rows in the grid
	protected int colCount;		// number of columns in the grid
	protected SquareStatus theGrid[][];
	protected int numberOccupiedSquares;
	GameStatus CurrentStatus = GameStatus.STILLPLAYING;
	
	
	
	/**
	 * NCGridV3 represents a noughts and crosses grid.
	 * The code is adapted from NCGrid used in earlier labs. The constructor requires two arguments (to allow for rectangular grids,
	 * in case an m by n game is implemented).
	 * This implementation is restricted to square grids. If the specified number of rows  differs from the number of columns, 
	 * the larger value is taken as the dimension of the square array and an error message is printed,
	 * @param rowSize the number of rows in the grid
	 * @param colSize the number of columns in the grid
	 */
	public  NCGridV3 (int rowSize, int colSize)			// row and col sizes might be different in extended versions; here they are forced to be the same
	{
		numberOccupiedSquares=0;

		int gridSize = java.lang.Math.max(rowSize, colSize);

		if(rowSize != colSize)			
		{
			System.err.println("this version only accepts SQUARE grids - expanding to " + gridSize + " by " + gridSize);
		}
		
		rowCount =  gridSize;
		colCount = gridSize;

		
		theGrid = new SquareStatus[rowCount][rowCount];
		for(int row=0; row <rowCount; row++)
		{
			for(int col=0; col <colCount; col++)
				theGrid[row][col] = SquareStatus.EMPTY;

		}
		
	}

	/**
	 * Provides access to the grid size 
	 * @return row dimension of the  grid
	 */
	public int getGridRowDimension()
	{
		return rowCount;
	}
	/**
	 * Provides access to the grid size 
	 * For square grids, this method is superfluous and is equivalent to getGridRowDimension().
	 * @see getGridRowDimension()
	 * @return column dimension of the  grid
	 */
	public int getGridColDimension()
	{
		return colCount;
	}
	
		
	/**
	 * @param value  the row / column co-ordinate to be checked
	 * @param max	 the dimension of the grid (row or column), value can range from zero to max-1
	 * @return true if the value is in the specified range
	 */
	private boolean inRange(int value, int max)
	{
		return (value >= 0) && (value < max);
	}

	/**
	 * Finds the content of a grid square
	 * @param row  the row coordinate of the square
	 * @param col the column coordinate of the square
	 * @return EMPTY, CROSS or NOUGHT according to the square content
	 * @throws outOfRangeError   if row or col is out of range
	 */
	public SquareStatus getSquareStatus(int row, int col) throws outOfRangeError
	{
		if(inRange(row, rowCount) && inRange(col, colCount))
			return theGrid[row][col];
		else
			throw new outOfRangeError(); // row,col);
	}
	
	/**
	 * Finds the content of a grid square without checking for OutOfRange values
	 * Intended for use within the class and subclasses (hence the protected declaration) as we can (or should be able to)
	 *  guarantee that only valid coordinates are passed in.
	 * @param row the row coordinate of the square
	 * @param col the column coordinate of the square
	 * @return EMPTY, CROSS or NOUGHT according to the square content
	 */
	protected SquareStatus getSquareStatusNoChecking(int row, int col) // same as above without error checking
	{
				return theGrid[row][col];
	}
	
	/**
	 * Changes an empty square to have content specifed by value (CROSS or NOUGHT).
	 * If the square is not empty, it returns false but takes no other action
	 * @param row  the row coordinate of the square
	 * @param col the column coordinate of the square
	 * @param value the requested new content of the square 
	 * @return true if the square was empty and is now set to the required value, false is the square was not empty
	 * @throws outOfRangeError if row or col is out of range
	 */
	public boolean  setSquareStatus(int row, int col, SquareStatus value) throws outOfRangeError
	{
		
		boolean ret=inRange(row, rowCount) && inRange(col,colCount) ;
		if(ret)
		{
			switch( theGrid[row][col])
			{
			case EMPTY:
				theGrid[row][col] = value;
				numberOccupiedSquares++;
				break;

			case NOUGHT:
				ret = false;
				break;
			case CROSS:
				ret = false;		// This should this be an error; currently we silently forfeit the turn
				break;
			}
		}
		else
			throw new outOfRangeError(); // if row or col is not valid;
		
		if (ret == false) {
			if (value == SquareStatus.NOUGHT) {
				CurrentStatus = GameStatus.CROSSWIN;
			} else if (value == SquareStatus.CROSS) {
				CurrentStatus = GameStatus.NOUGHTWIN;
			}
		}
			
		
		return ret;
	}
	
	/**
	 * getNumberOfOccupiedSquares reports the number of grid squares that have been set to non-empty by the setSquareStatus method.
	 * It can be used to determine that a game is drawn, if there are no unoccupied squares left.
	 * @return the number of grid squares that are occupied
	 */
	public int getNumberOfOccupiedSquares()
	{
		return numberOccupiedSquares;		// this is updated in successful calls to setSquareStatus
	}
	/**
	 * getGameStatus checks whether the game is over or not.
	 * @return CROSSWIN or NOUGHTWIN if either player has won,
	 * otherwise STILLPLAYING if neither player has won and there are empty squares in the grid
	 * or DRAW if there are no empty squares in the grid   
	 * 
	 * Note this code WILL NOT WORK FOR CONNECT-4  because it assumes a line is the same size as the grid
	 * i.e. starts in row or column 0
	 * In connect-4 (6 rows, 7 columns), a winning line of 4 could start in column 0-2 (etc)
	 * 
	 * NB it is important to perform the check for all squares occupied AFTER the check for complete lines as it is possible for 
	 * the last move to make a winning line and fill the grid
	 */
	public GameStatus getGameStatus() 
	{
		GameStatus theStatus = CurrentStatus;
		
		/* first, check each row   */
		for(int counter =0; (theStatus == GameStatus.STILLPLAYING ) && (counter < rowCount); counter++)
		{
			theStatus = checkForLineOfN(counter, 0, 0, 1);		// check row- NOTE COMMENT ABOVE about non-square grids

		}
		/*  and column */
		for(int counter =0; (theStatus == GameStatus.STILLPLAYING ) && (counter < colCount); counter++)
		{
				theStatus = checkForLineOfN(0, counter, 1, 0);		// check column- NOTE COMMENT ABOVE about non-square grids

		}

		if(theStatus == GameStatus.STILLPLAYING)
			theStatus = checkForLineOfN(0, 0, 1, 1);		// check diag  - NOTE COMMENT ABOVE about non-square grids
		if(theStatus == GameStatus.STILLPLAYING)
			theStatus = checkForLineOfN(0, rowCount-1, 1, -1);		// check other diag

		// finally, declare a draw if no-one has won and there are no more squares
		
		if((theStatus == GameStatus.STILLPLAYING) && (getNumberOfOccupiedSquares() == rowCount*colCount))
			theStatus = GameStatus.DRAW;
		
		
		return theStatus;
	}
	
	/**
	 * check to see whether there is a line of <gridSize>  identical symbols (NOUGHT or CROSS)
	 * starting at square (startRow, startCol) and moving in direction specified 
	 * by incrementRow, incrementCol. These are added to startRow/Col values to find the next square
	 * in the series. Hardcoded to look for lines of length rowCount (stored as a class variable). 
	 * Returns GameStatus if rowCount CROSS or NOUGHT symbols are found, false otherwise.
	 */
	private GameStatus checkForLineOfN(int startRow, int startCol, int incrementRow, int incrementCol)
	{
		SquareStatus firstSquareContent = getSquareStatusNoChecking(startRow, startCol);
		GameStatus ret;
		boolean lineOfN = (firstSquareContent != SquareStatus.EMPTY);
		for(int count=1; lineOfN && (count < rowCount);count++)
		{
			startRow += incrementRow;
			startCol += incrementCol;
			lineOfN= ( getSquareStatusNoChecking(startRow, startCol) == firstSquareContent);
		}
		if(!lineOfN)
			ret = GameStatus.STILLPLAYING;
		else if(firstSquareContent == SquareStatus.CROSS) 
			ret = GameStatus.CROSSWIN;
		else
			ret = GameStatus.NOUGHTWIN;

	
			
			return ret;
	}
	
	/**
	 * getStringForGrid builds a string representation of the grid, illustrates use of  StringBuffer
	 * to accumulate the string
	 * @return a string representing the grid (using simple ascii characters) and content of each square (empty, X, or O)
	 */
public String getStringForGrid ()
{
	StringBuffer ret = new StringBuffer();
	int limit =  rowCount;
	
	for(int row=0; row < rowCount; row++)
	{
		if(row > 0)
			{
			ret.append( "___");
			for (int col = 1; col < colCount; col++)
				ret.append( "|___");
			ret.append("\n   ");
			for (int col = 1; col < colCount; col++)
				ret.append("|   ");
			ret.append("\n");
			}
		for (int col = 0; col < colCount; col++)
		{
			if(col > 0 )
				ret.append("|");
			try {
				switch(getSquareStatus(row, col))
				{
				case CROSS:
					ret.append(" X ");
					break;
				case NOUGHT:
					ret.append(" O ");
					break;
				case EMPTY:
					ret.append("   ");
					break;
				}
			}
			catch (outOfRangeError e) 
			{
				System.err.println("Row or col out of range - should not happen " + e.toString());
				e.printStackTrace();
				System.exit(1);
			}	
				
				
			}
		ret.append("\n");
		}
	return ret.toString();
	}


}
