package raceSmileys;

import java.util.Scanner;

/*
 * Assignment 3
 * CSI4106
 * Lia Chauvel
 * 6770728
 * 
 * class used to communicate with the user and manage the whole program
 */

public class Main {
	
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String choice = "";
		
		// allow the user to simulate a race until he presses 3
		while(!choice.equals("3")){
			
			// show maze 1 to the user
			System.out.print("Here is maze 1 without any smileys:\n");
			Maze.printMaze(Maze.MAZE_1);
			
			// show maze 2 to the user
			System.out.println("\nHere is maze 2 without any smileys:");
			Maze.printMaze(Maze.MAZE_2);
			
			// ask the user to choose a maze
			do{
				System.out.println("\nPlease choose a maze, \n\tfor maze 1 enter 1; \n\tfor maze 2 enter 2; \n\tto quit enter 3");
				choice = sc.nextLine();
			}while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3"));
			
			int num = Integer.parseInt(choice);
			
			// create the correct maze or exit
			switch(num){
				case 1: 
					Maze.createMaze(Maze.MAZE_1);
					break;
				case 2:
					Maze.createMaze(Maze.MAZE_2);
					break;
				case 3:
					System.exit(0);
					break;
			}
			
			boolean correctCoordinate;
			// ask the user for coordinates for the smileys and place them in maze
			for(int i = 0; i < Smiley.NUM_SMILEY ; i++){
				do{
					correctCoordinate = true;
					System.out.println("Please enter the coordinates of smiley " + (i + 1) + " without placing it \non an obstacle, the home square or another smiley");
					int j = sc.nextInt();
					int k = sc.nextInt();
					sc.nextLine();
					if(!Maze.goodSmileyStartPos(j, k)){
						correctCoordinate = false;
					} else{
						Maze.setSmileyAt(j, k);
					}
				}while(!correctCoordinate);
			}
			
			// print the maze with the smileys in it
			System.out.println("Here is the maze with the smileys:");
			Maze.printMaze();
			
			// create a new race and simulate it, then print the result
			Race.simulateRace();
			System.out.println(Race.raceToString());
			
			// ask the user if he wants to simulate another race
			System.out.println("\nTo simulate another race please press 1, press 3 to exit");
			choice = sc.nextLine();
			if(!choice.equals("3")){
				// in order for the squares containing smileys to go back to their original values, so another race can be simulated
				Maze.resetMaze();
			}
		}
		System.exit(0);
		
	}

	
	
	
}
