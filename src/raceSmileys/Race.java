package raceSmileys;

import java.util.AbstractMap;

/*
 * Assignment 3
 * CSI4106
 * Lia Chauvel
 * 6770728
 * 
 * class used to simulate the race between the smileys
 * 
 */

public class Race {
	
	// smileys in the race 
	private static Smiley[] smileys;
	private static Integer winner;
	
	public static void simulateRace(){
		
		smileys = Maze.getSmileys();
		
		if(!Maze.mazeCreated() || Maze.getSmileysInMaze() < Smiley.NUM_SMILEY){
			throw new IllegalStateException("Maze needs to be created with " + Smiley.NUM_SMILEY + " smileys in it before the race can start");
		}
		
		// find the shortest path towards home according to A* for each smiley
		for(int i = 0; i < smileys.length; i++){
			findShortestPath(i);
		}
		
		// find the first smiley who reached home the fastest
		int min = smileys[0].getPathLength();
		winner = 0;
		for(int i = 1; i < smileys.length; i++){
			if(smileys[i].getPathLength() < min){
				winner = i;
				min = smileys[i].getPathLength();
			}
		}
	}
	
	
	// calculate the heuristic of a square according to it's coordinates
	private static int calcHeuristic(int i, int j){
		// distance to home vertically
		int vertical = Maze.HOME_COORDINATES[0] - i;
		
		// distance to home horizontally
		int horizontal = Maze.HOME_COORDINATES[1] - j;
		
		// count obstacles crossed during heuristic horizontally and vertically
		int obstacles = 0;
		if(i < Maze.HOME_COORDINATES[0]){
			for( int c = i; c < Maze.HOME_COORDINATES[0]; c++){
				if (Maze.obstacle(c,j)){
					obstacles++;
				}
			}
		} else{
			for(int c = i; c > Maze.HOME_COORDINATES[0]; c--){
				if (Maze.obstacle(c,j)){
					obstacles++;
				}
			}
		}
		if (j < Maze.HOME_COORDINATES[1]){
			for(int c = j; c < Maze.HOME_COORDINATES[1]; c++){
				if(Maze.obstacle(Maze.HOME_COORDINATES[0],c)){
					obstacles++;
				}
			}
		} else {
			for(int c = j; c > Maze.HOME_COORDINATES[1]; c--){
				if(Maze.obstacle(Maze.HOME_COORDINATES[0],c)){
					obstacles++;
				}
			}
		}
		
		return obstacles + Math.abs(vertical) + Math.abs(horizontal);
	}
	
	private static void findShortestPath(int num){
		// visit the smileys position and add it to the queue
		smileys[num].visit(smileys[num].getX(), smileys[num].getY());
		smileys[num].enqueue(new Square(smileys[num].getX(), smileys[num].getY(), 0, 0));
		
		
		while(!smileys[num].pqIsEmpty()){
			// remove the square with the smallest f(n)
			Square v = smileys[num].dequeue();
			// stop the loop once we found the home square
			if(v.getX() == Maze.HOME_COORDINATES[0] && v.getY() == Maze.HOME_COORDINATES[1]){
				break;
			}
			
			// check for a new square to add to the queue, left, right, down and up
			if(v.getX() > 0 && Maze.goodSmileyPos(v.getX() - 1, v.getY()) && smileys[num].visited(v.getX() - 1, v.getY()) == false){
				smileys[num].visit(v.getX() - 1, v.getY());
				smileys[num].replacePrev( new AbstractMap.SimpleEntry<Integer, Integer>(v.getX() - 1, v.getY()), new AbstractMap.SimpleEntry<Integer, Integer>(v.getX(), v.getY())); 
				smileys[num].enqueue(new Square(v.getX() - 1, v.getY(), calcHeuristic(v.getX() - 1, v.getY()), v.getCost() + 1));
			}
			if(v.getX() < Maze.getMazeLength() - 1 && Maze.goodSmileyPos(v.getX() + 1,v.getY()) && smileys[num].visited(v.getX() + 1, v.getY()) == false){
				smileys[num].visit(v.getX() + 1, v.getY());
				smileys[num].replacePrev( new AbstractMap.SimpleEntry<Integer, Integer>(v.getX() + 1, v.getY()), new AbstractMap.SimpleEntry<Integer, Integer>(v.getX(), v.getY())); 
				smileys[num].enqueue(new Square(v.getX() + 1, v.getY(), calcHeuristic(v.getX() + 1, v.getY()), v.getCost() + 1));
			}
			if(v.getY() > 0 && Maze.goodSmileyPos(v.getX(),v.getY() - 1) && smileys[num].visited(v.getX(), v.getY() - 1) == false){
				smileys[num].visit(v.getX(), v.getY() - 1);
				smileys[num].replacePrev( new AbstractMap.SimpleEntry<Integer, Integer>(v.getX(), v.getY() - 1), new AbstractMap.SimpleEntry<Integer, Integer>(v.getX(), v.getY())); 
				smileys[num].enqueue(new Square(v.getX(), v.getY() - 1, calcHeuristic(v.getX(), v.getY() - 1), v.getCost() + 1));
			}
			if(v.getY() < Maze.getMazeWidth() - 1 && Maze.goodSmileyPos(v.getX(),v.getY() + 1) && smileys[num].visited(v.getX(), v.getY() + 1) == false){
				smileys[num].visit(v.getX(), v.getY() + 1);
				smileys[num].replacePrev( new AbstractMap.SimpleEntry<Integer, Integer>(v.getX(), v.getY() + 1), new AbstractMap.SimpleEntry<Integer, Integer>(v.getX(), v.getY())); 
				smileys[num].enqueue(new Square(v.getX(), v.getY() + 1, calcHeuristic(v.getX(), v.getY() + 1), v.getCost() + 1));
			}
		}
		
		// compilate path of the smiley
		smileys[num].makePath();
		
	}
	
	// return the path of each smiley and the winner of the race
	public static String raceToString(){
		StringBuffer answer = new StringBuffer();
		if(winner != null){
			for(int i = 0; i < Smiley.NUM_SMILEY; i++){
				answer.append("Smiley " + (i + 1) + " " + smileys[i].toString()+ "\n\n");
			}
			answer.append("The winner of this race is smiley " + (winner + 1));
		} else{
			throw new IllegalStateException("The race has not been simulated");
		}
		
		return answer.toString();
	}
	
}
