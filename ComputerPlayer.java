package noughtsAndCrossesV3;

import ncErrors.outOfRangeError;
import noughtsAndCrossesV3.NCGridV3.SquareStatus;

import java.util.ArrayList;
import java.util.Random;


public class ComputerPlayer   extends GenericPlayer implements NCPlayer 
{
	Random theGenerator;
	public ComputerPlayer()
	{
		super(); 		// no further initialisation required
		theGenerator = new Random();
	}



 
/**
 * Basic implementation of getNextMove - scan through grid until we find an empty square, choose it
 * 
 * @see noughtsAndCrossesV3.GenericPlayer#getNextMove(noughtsAndCrossesV3.NCGridV3)
 */

	@Override
	public GridCoordinate getNextMove(NCGridV3 currentGrid) 
	{
		int theRow;
		int theCol;
		GridCoordinate theSquare = null;
		NCGridV3.SquareStatus Opponent;
		int[] nextMove = {-1, -1};
		
		if (getMySymbol() == NCGridV3.SquareStatus.CROSS)
		{
			Opponent = NCGridV3.SquareStatus.NOUGHT;
		}
		else
		{
			Opponent = NCGridV3.SquareStatus.CROSS;
		}
		int lineCount;
		
		
		//check if you are about to win a row 
		for (int row = 0; row < currentGrid.getGridRowDimension(); row++)
		{
			lineCount = 0;
			nextMove[0] = -1;
			nextMove[1] = -1;
			for (int col =0; col < currentGrid.getGridColDimension(); col++)
			{
				if (currentGrid.getSquareStatusNoChecking(row, col) == getMySymbol())
				{
					lineCount++;
				}
				else if(currentGrid.getSquareStatusNoChecking(row, col) == NCGridV3.SquareStatus.EMPTY)
				{
					nextMove[0] = row;
					nextMove[1] = col;
				}
			}
			if (lineCount == currentGrid.getGridRowDimension() - 1 && (nextMove[0] != -1 && nextMove[1] != -1))
			{
				theSquare = new GridCoordinate(nextMove[0], nextMove[1]);
				return theSquare;
			}
		}
		
		//check if you are about to win a column 
		for (int col = 0; col < currentGrid.getGridRowDimension(); col++)
		{
			lineCount = 0;
			nextMove[0] = -1;
			nextMove[1] = -1;
			for (int row =0; row < currentGrid.getGridColDimension(); row++)
			{
				if (currentGrid.getSquareStatusNoChecking(row, col) == getMySymbol())
				{
					lineCount++;
				}
				else if(currentGrid.getSquareStatusNoChecking(row, col) == NCGridV3.SquareStatus.EMPTY)
				{
					nextMove[0] = row;
					nextMove[1] = col;
				}
			}
			if (lineCount == currentGrid.getGridRowDimension() - 1 && (nextMove[0] != -1 && nextMove[1] != -1))
			{
				theSquare = new GridCoordinate(nextMove[0], nextMove[1]);
				return theSquare;
			}
		}
		
		//check if you are about to win a diagonal from left to right
		lineCount = 0;
		nextMove[0] = -1;
		nextMove[1] = -1;
		for (int row = 0; row < currentGrid.getGridRowDimension(); row++)
		{
			if (currentGrid.getSquareStatusNoChecking(row, row) == getMySymbol())
			{
				lineCount++;
			}
			else if(currentGrid.getSquareStatusNoChecking(row,row) == NCGridV3.SquareStatus.EMPTY)
			{
				nextMove[0] = row;
				nextMove[1] = row;
			}			
		}
		if (lineCount == currentGrid.getGridRowDimension() - 1 && (nextMove[0] != -1 && nextMove[1] != -1))
		{
			theSquare = new GridCoordinate(nextMove[0],nextMove[1]);
				return theSquare;
		}
		
		//Check if the opponent if about to win a diagonal from right to left
		lineCount = 0;
		nextMove[0] = -1;
		nextMove[1] = -1;
		for (int row = 0; row < currentGrid.getGridRowDimension(); row++)
		{
			if (currentGrid.getSquareStatusNoChecking(row,currentGrid.getGridRowDimension()-row-1) == getMySymbol())
			{	
				lineCount++;
			}
			else if(currentGrid.getSquareStatusNoChecking(row,currentGrid.getGridRowDimension()-row-1) == NCGridV3.SquareStatus.EMPTY)
			{
				nextMove[0] = row;
				nextMove[1] = currentGrid.getGridRowDimension()-row-1;
			}	
		}
		if (lineCount == currentGrid.getGridRowDimension() - 1 && (nextMove[0] != -1 && nextMove[1] != -1))
		{
			theSquare = new GridCoordinate(nextMove[0],nextMove[1]);
			return theSquare;
		}
		
		
		
		// Check if the opponent if about to win a row
		for (int row = 0; row < currentGrid.getGridRowDimension(); row++)
		{
			lineCount = 0;
			nextMove[0] = -1;
			nextMove[1] = -1;
			for (int col =0; col < currentGrid.getGridColDimension(); col++)
			{
				if (currentGrid.getSquareStatusNoChecking(row, col) == Opponent)
				{
					lineCount++;
				}
				else if(currentGrid.getSquareStatusNoChecking(row, col) == NCGridV3.SquareStatus.EMPTY)
				{
					nextMove[0] = row;
					nextMove[1] = col;
				}
			}
			if (lineCount == currentGrid.getGridRowDimension() - 1 && (nextMove[0] != -1 && nextMove[1] != -1))
			{
				theSquare = new GridCoordinate(nextMove[0], nextMove[1]);
				return theSquare;
			}
		}
		
		// Check if the opponent if about to win a column
		for (int col =0; col < currentGrid.getGridColDimension(); col++)
		{
			lineCount = 0;
			nextMove[0] = -1;
			nextMove[1] = -1;
			for (int row = 0; row < currentGrid.getGridRowDimension(); row++)
			{
				if (currentGrid.getSquareStatusNoChecking(row, col) == Opponent)
				{
					lineCount++;
				}
				else if(currentGrid.getSquareStatusNoChecking(row, col) == NCGridV3.SquareStatus.EMPTY)
				{
					nextMove[0] = row;
					nextMove[1] = col;
				}
			}
			if (lineCount == currentGrid.getGridRowDimension() - 1 && (nextMove[0] != -1 && nextMove[1] != -1))
			{
				theSquare = new GridCoordinate(nextMove[0], nextMove[1]);
				return theSquare;
			}
		}
		
		//Check if the opponent if about to win a diagonal from left to right
		lineCount = 0;
		nextMove[0] = -1;
		nextMove[1] = -1;
		for (int row = 0; row < currentGrid.getGridRowDimension(); row++)
		{
			if (currentGrid.getSquareStatusNoChecking(row, row) == Opponent)
			{
				lineCount++;
			}
			else if(currentGrid.getSquareStatusNoChecking(row,row) == NCGridV3.SquareStatus.EMPTY)
			{
				nextMove[0] = row;
				nextMove[1] = row;
			}			
		}
		if (lineCount == currentGrid.getGridRowDimension() - 1 && (nextMove[0] != -1 && nextMove[1] != -1))
		{
			theSquare = new GridCoordinate(nextMove[0],nextMove[1]);
				return theSquare;
		}
		
		//Check if the opponent if about to win a diagonal from right to left
		lineCount = 0;
		nextMove[0] = -1;
		nextMove[1] = -1;
		for (int row = 0; row < currentGrid.getGridRowDimension(); row++)
		{
			if (currentGrid.getSquareStatusNoChecking(row,currentGrid.getGridRowDimension()-row-1) == Opponent)
			{	
				lineCount++;
			}
			else if(currentGrid.getSquareStatusNoChecking(row,currentGrid.getGridRowDimension()-row-1) == NCGridV3.SquareStatus.EMPTY)
			{
				nextMove[0] = row;
				nextMove[1] = currentGrid.getGridRowDimension()-row-1;
			}	
		}
		if (lineCount == currentGrid.getGridRowDimension() - 1 && (nextMove[0] != -1 && nextMove[1] != -1))
		{
			theSquare = new GridCoordinate(nextMove[0],nextMove[1]);
			return theSquare;
		}
		
		
		
		
		
		// If I'm not about to lose or win, put a random 
		ArrayList<GridCoordinate> freeSquares = new ArrayList<GridCoordinate>();
		int randomSelection;

		for (theRow = 0; theRow < currentGrid.getGridRowDimension(); theRow++)
		{
			for (theCol = 0;  theCol < currentGrid.getGridColDimension(); theCol++)
			{
				try
				{
				if(currentGrid.getSquareStatus(theRow, theCol)==NCGridV3.SquareStatus.EMPTY)
					freeSquares.add(new GridCoordinate(theRow, theCol));
				}
				catch (outOfRangeError e)
				{
					// and ignore it - another exercise for the reader  
				}
			}
		}
		// pick a number between 0 and the number of available squares
		randomSelection = theGenerator.nextInt(freeSquares.size());
		return freeSquares.get(randomSelection);
	}
			

}
