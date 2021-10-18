package noughtsAndCrossesV3;

import java.util.Scanner;

import ncErrors.outOfRangeError;
import noughtsAndCrossesV3.NCGridV3.GameStatus;
import noughtsAndCrossesV3.NCGridV3.SquareStatus;


public class GameRunnerV3 
{
	 static final String INITIAL_INSTRUCTIONS = "Welcome to Noughts and Crosses. First player is crosses, second player is noughts.\n";
	 static final String EACHROUND_INSTRUCTIONS= "\n enter row from 0 - %d and col from 0 - %d separated by a space (0 0 = top left)\n";
	 static final int gridSize = 4;
	 
	public static void main(String[] args)  throws Exception
	{
		NCGridV3 theGrid = new NCGridV3(gridSize, gridSize);
		GameRunnerV3 theGame = new GameRunnerV3();
		NCPlayer p1 = null;
		NCPlayer p2 = null;
		//Scanner sc = new Scanner(System.in);		// only needed if we include human players 
		//HumanPlayer p1 = new HumanPlayer(sc, theGame);
		if (args[1].equals("SimpleComputerPlayer")) {
			p1 = new SimpleComputerPlayer();
		} else if (args[1].equals("ComputerPlayer")) {
			p1 = new ComputerPlayer();
		} else if (args[1].equals("HumanPlayer")) {
			p1 = new HumanPlayer(null, theGame);
			Scanner sc = new Scanner(System.in);	
		} else {
			throw new Exception();
		}
		if (args[2].equals("SimpleComputerPlayer")) {
			p2 = new SimpleComputerPlayer();
		} else if (args[2].equals("RandomComputerPlayer")) {
			p2 = new RandomComputerPlayer();
		} else if (args[2].equals("ComputerPlayer")) {
			p2 = new ComputerPlayer();
		} else if (args[2].equals("HumanPlayer")) {
			p2 = new HumanPlayer(null, theGame);
			Scanner sc = new Scanner(System.in);	
		} 	else {
			throw new Exception();
		}
		
		((GenericPlayer) p1).setMySymbol(SquareStatus.CROSS);
		((GenericPlayer) p2).setMySymbol(SquareStatus.NOUGHT);
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(INITIAL_INSTRUCTIONS);
		
		
		String GamesLine;
		int NoOfGames = 0;
		Scanner sc = new Scanner(System.in);
		if (Integer.parseInt(args[0]) < 0) {
			while (true) {
				System.out.println("Please select the Number of Games you want to play!");
				try {
					GamesLine = sc.nextLine();
					NoOfGames = Integer.parseInt(GamesLine);
					// NoOfGames = sc.nextInt();
					if (NoOfGames > 0)
						break;
				} 
				catch (NumberFormatException error) 
				{
					continue;
				}

			}
		} else 
		{
			NoOfGames = Integer.parseInt(args[0]);
		}
		
		NCPlayer nextToPlay = p1;		// arbitrary decision that p1 goes first
		
		int NoughtWins = 0;
		int CrossWins = 0;
		int NoOfDraws = 0;
		int GamesPlayed = 0;
		for (int i = 0; i < NoOfGames; i++) 
		{
			
			while (theGrid.getGameStatus().equals(GameStatus.STILLPLAYING)) 
			{
				GridCoordinate nextMove = nextToPlay.getNextMove(theGrid);
				try {
//					System.out.println(nextMove.getRow());
//					System.out.println(nextMove.getCol());       //these were for debugging  
//					System.out.println(nextToPlay.getMySymbol());
					theGrid.setSquareStatus(nextMove.getRow(), nextMove.getCol(), nextToPlay.getMySymbol());

				} catch (outOfRangeError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

	
				switch(theGrid.getGameStatus() )
				{
					case CROSSWIN:
						System.out.println("Cross wins");
						CrossWins = CrossWins + 1;
						GamesPlayed = GamesPlayed + 1;
						break;
					case NOUGHTWIN:
						System.out.println("a win for Noughts");
						NoughtWins = NoughtWins + 1;
						GamesPlayed = GamesPlayed + 1;
						break;
					case DRAW:
						System.out.println("honours even");
						NoOfDraws = NoOfDraws + 1;
						GamesPlayed = GamesPlayed + 1;
						break;
					default:
						break;

					
		
				}
				
				if(nextToPlay.equals(p1))
					nextToPlay = p2;
				else
					nextToPlay = p1;
				
				
			} 

			System.out.println("No of Games Played: " + GamesPlayed);
			System.out.println("Games won by Cross: " + CrossWins);
			System.out.println("Games won by Nought: " + NoughtWins);
			System.out.println("Draws: " + NoOfDraws);
			theGame.displayGrid(theGrid);
			theGrid = new NCGridV3(gridSize, gridSize); //each time needs to be initialized 
		}
	}
		
	
	public  void displayInstructions (int rDim, int cDim)
	{
		System.out.printf(EACHROUND_INSTRUCTIONS, rDim-1, cDim-1 );
	}
	public  void displayGrid (NCGridV3 theGrid)
	{
		System.out.println(theGrid.getStringForGrid());
	}
	
}
