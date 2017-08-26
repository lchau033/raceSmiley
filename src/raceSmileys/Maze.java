package raceSmileys;
/*
 * Assignment 3
 * CSI4106
 * Lia Chauvel
 * 6770728
 * 
 * class used to represent the maze
 */

public class Maze {
	// maze int representation please read assumptions to understand code
	public static final int MAZE_1 = 1;
	public static final int MAZE_2 = 2;
	public static final int SMILEY = 2;
	public static final int OBSTACLE = -1;
	public static final int UNAVAILABLE_OSTACLE = -2;
	public static final int UNAVAILABLE = 3;
	public static final int EMPTY_SQUARE = 0;
	public static final int HOME = 1;
	
	// Coordinates for the home of both mazes
	public static final int[] HOME_COORDINATES = {3,3};
	
	// first Maze given
	private static int[][] maze1 = {{0,0,0,-1,0,0,0},
		  {0,-1,0,0,-1,0,0},
		  {0,-1,0,0,-1,0,0},
		  {0,-1,-1,1,-1,0,0},
		  {0,0,-1,0,0,0,0},
		  {0,0,-1,-1,-1,0,0},
		  {0,0,0,0,0,0,0}};
	
	// second Maze given
	private static int[][] maze2 = {{0,0,0,-1,0,0,0,0,0},
		   {0,-1,0,0,-1,0,0,0,0},
		   {0,-1,0,0,-1,0,0,-2,-2},
		   {0,-1,-1,1,-1,0,0,3,3},
		   {0,0,-1,0,0,0,0,3,3},
		   {0,0,-1,-1,-1,0,0,3,3},
		   {0,0,0,0,0,0,0,-2,-2},
		   {0,-1,0,0,0,0,0,0,0},
		   {0,0,3,3,3,0,0,0,0}};
	
	// maze of the object
	private static int[][] maze;
	
	// either 1 or 2
	private static int mazeChoice;
	
	// smileys in the maze
	private static Smiley[] smileys;
	
	// counter to make sure the maze doesn't have to many smileys
	private static int countSmileyInMaze;
	
	// check if the maze has been created
	private static boolean mazeCreated = false;
	
	// constructor that will make the pointer, maze point to the right static maze according to the user's choice
	public static void createMaze(int maze){
		// maze should be 1 or 2
		if(maze!=Maze.MAZE_1 && maze!=Maze.MAZE_2){
			throw new IllegalArgumentException("There are only two mazes, maze 1 and maze 2");
		} else if(Maze.mazeCreated){
			throw new IllegalStateException("A maze has already been created, please reset the maze");
		}
		
		// maze assignment
		if(maze == Maze.MAZE_1){
			Maze.maze = Maze.maze1;
			mazeChoice = Maze.MAZE_1;
		}else{
			Maze.maze = Maze.maze2;
			mazeChoice = Maze.MAZE_2;
		}
		
		// the maze has been assigned so the maze is considered created
		mazeCreated = true;
		
		// array containing all smiley squares so they can be removed at the end and they're can only be a maximum of NUM_SMILEY in the maze
		Maze.smileys = new Smiley[Smiley.NUM_SMILEY];
		countSmileyInMaze = 0;
	}
	
	// method to check if the place sent in parameters is a good place for a smiley to be positioned at the beginning of a race 
	// according to the maze pointed by maze
	public static boolean goodSmileyStartPos(int i, int j){
		if(!mazeCreated){
			throw new IllegalStateException("Maze has not been created");
		}
		// first we check according to the dimensions of the maze, then if the square is empty or if it's the home
		return i >= 0 && i < Maze.maze.length && j >= 0 && j < Maze.maze[0].length && Maze.maze[i][j] >= Maze.EMPTY_SQUARE && Maze.maze[i][j] <= Maze.HOME;
	}
	
	// method to check if the place sent in parameters is a good place for a smiley to be positioned
	public static boolean goodSmileyPos(int i, int j){
		if(!mazeCreated){
			throw new IllegalStateException("Maze has not been created");
		}
		return i >= 0 && i < Maze.maze.length && j >= 0 && j < Maze.maze[0].length && Maze.maze[i][j] >= Maze.EMPTY_SQUARE && Maze.maze[i][j] <= Maze.SMILEY;
	}
	
	// method to check if the square sent in parameters is an obstacle
	public static boolean obstacle(int i, int j){
		if(!mazeCreated){
			throw new IllegalStateException("Maze has not been created");
		}
		// all squares with negative values are obstacles
		if(i >= 0 && i < Maze.maze.length && j >= 0 && j < Maze.maze[0].length){
			return Maze.maze[i][j] < 0;
		}else{
			throw new ArrayIndexOutOfBoundsException("The coordinate " + i + ", " + j + " is not in the maze");
		}
	}
	
	// method to set the square sent in parameters to a smiley
	public static void setSmileyAt(int i, int j) {
		if(!mazeCreated){
			throw new IllegalStateException("Maze has not been created");
		}
		// check if it's a good spot for a smiley and if there is still room for another smiley in the maze
		if(goodSmileyStartPos(i, j) && countSmileyInMaze + 1 <= Smiley.NUM_SMILEY){
			// change symbol in the maze and add a smiley to the smiley array
			Maze.maze[i][j] = Maze.SMILEY;
			Maze.smileys[Maze.countSmileyInMaze++] = new Smiley(i, j);
		} else{
			throw new ArrayIndexOutOfBoundsException("Smiley wasn't set at a correct place");
		}
	}
	
	// reset's the maze by removing all smileys
	public static void resetMaze(){
		if(!mazeCreated){
			throw new IllegalStateException("Maze has not been created");
		}
		
		for(int i = 0; i < smileys.length; i++){
			maze[smileys[i].getX()][smileys[i].getY()] = Maze.EMPTY_SQUARE;
		}
		maze = null;
		
		// maze was reset and set to null
		Maze.mazeCreated = false;
	}
	
	// getter for length of the maze
	public static int getMazeLength(){
		if(!mazeCreated){
			throw new IllegalStateException("Maze has not been created");
		}
		return maze.length;
	}
	
	// getter for width of the maze
	public static int getMazeWidth(){
		if(!mazeCreated){
			throw new IllegalStateException("Maze has not been created");
		}
		return maze[0].length;
	}
	
	// static method to print maze 1 or maze 2
	public static void printMaze(int maze){
		if(maze!=Maze.MAZE_1 && maze!=Maze.MAZE_2){
			throw new IllegalArgumentException("There are only two mazes, maze 1 and maze 2");
		}
		
		boolean firstUnavailableRow;
		int[][] pointer;
		
		// point to the right maze
		if(maze == Maze.MAZE_1){
			pointer = maze1;
		} else{
			pointer = maze2;
		}
		
		
		// center maze
		System.out.print("    ");
		
		// show coordinates to user
		for(int i = 0; i < pointer.length; i++){
			System.out.print(i+"  ");
		}
		System.out.println();
		
		
		for(int i = 0; i < pointer.length; i++){
			// center and show coordinates
			System.out.print("  ");
			System.out.print(i);
			firstUnavailableRow = true;
			for(int j = 0; j < pointer[0].length; j++){
				// put a wall before an empty space
				if((pointer[i][j] == Maze.UNAVAILABLE || pointer[i][j] == Maze.UNAVAILABLE_OSTACLE) && firstUnavailableRow){
					System.out.print("|  ");
					firstUnavailableRow = false;
				}else if((pointer[i][j] == Maze.UNAVAILABLE || pointer[i][j] == Maze.UNAVAILABLE_OSTACLE) && !firstUnavailableRow){
				// put an empty space without any walls 
					System.out.print("   ");
				}else{
					// choose the right symbol for the other squares
					System.out.print("|");
					if (pointer[i][j] == Maze.OBSTACLE){
						System.out.print("X ");
					} else if (pointer[i][j] == Maze.HOME){
						System.out.print("H ");
					} else if (pointer[i][j] == Maze.SMILEY){
						System.out.print(":)");
					} else {
						System.out.print("  ");
					}
					if (j == pointer[0].length - 1){
						System.out.print("|");
					}
				}
			}
		System.out.print("\n   ");
		firstUnavailableRow = true;
		// keep the second line of each square on track with the ones on top, by checking the values for empty spaces without walls, or walls
		for( int k = 0; k < pointer.length; k++){
			if((pointer[i][k] == Maze.UNAVAILABLE || pointer[i][k] == Maze.UNAVAILABLE_OSTACLE) && firstUnavailableRow){
				System.out.print("|  ");
				firstUnavailableRow = false;
			} else if ((pointer[i][k] == Maze.UNAVAILABLE || pointer[i][k] == Maze.UNAVAILABLE_OSTACLE) && !firstUnavailableRow){
					System.out.print("   ");
			}else{
				System.out.print("|__");
				if(k == pointer.length - 1){
					System.out.print("|");
				}
			}
		}
		System.out.println();
		}
	}
	
	// method to print the maze pointed by maze
	public static void printMaze(){
		if(!mazeCreated){
			throw new IllegalStateException("Maze has not been created");
		}
		Maze.printMaze(mazeChoice);
	}
	
	public static boolean mazeCreated(){
		return Maze.mazeCreated;
	}
	
	// getters for smileys in the maze
	public static Smiley[] getSmileys(){
		if(!mazeCreated){
			throw new IllegalStateException("Maze has not been created");
		}
		return smileys.clone();
	}
	
	public static int getSmileysInMaze(){
		return Maze.countSmileyInMaze;
	}

}
