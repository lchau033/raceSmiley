package raceSmileys;

import java.util.AbstractMap;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/*
 * Assignment 3
 * CSI4106
 * Lia Chauvel
 * 6770728
 * 
 * Smiley class that compiles the path each smiley can follow in order to get home
 */

public class Smiley {
	// number of Smileys in the maze
	public static final int NUM_SMILEY = 4;
	
	// starting coordinates of the smiley
	private int startX;
	private int startY;
	
	// length of the path to get home
	private int pathLength;
	
	// path to get home
	private Stack<Entry<Integer, Integer>> positions;
	
	// priority queue for the smiley to reach home
	private PriorityQueue<Square> pq;
	
	
	// visited map for the smiley's race calculations
	private Map<Entry<Integer,Integer>, Boolean> visited = new HashMap<Entry<Integer,Integer>, Boolean>();
	
	// previous map for the smiley's path making
	private Map<Entry<Integer,Integer>, Entry<Integer,Integer>> prev = new HashMap<Entry<Integer, Integer>, Entry<Integer, Integer>>();
	
	// value to check if the smiley has a path to get home
	private boolean hasPath;
	
	public Smiley(int startX, int startY){
		if(!Maze.mazeCreated()){
			throw new IllegalStateException("There can't be smileys without a maze");
		}
		
		if(startX < 0 || startY < 0 || startX > Maze.getMazeLength() || startY > Maze.getMazeWidth() ){
			throw new IllegalArgumentException();
		}
		
		this.startX = startX;
		this.startY = startY;
		
		// initialize maps
		for(int i = 0; i < Maze.getMazeLength(); i++){
			for(int j = 0; j < Maze.getMazeWidth(); j++){
				visited.put( new AbstractMap.SimpleEntry<Integer, Integer>(i, j), false);
				prev.put(new AbstractMap.SimpleEntry<Integer, Integer>(i, j), null);
			}
		}
		
		this.hasPath = false;
	}
	
	// method to enqueue a square to the smiley's priority queue
	public void enqueue(Square s){
		// initialize queue for the first enqueue
		if(this.pq == null){
			this.pq = new PriorityQueue<Square>();
		}
		this.pq.add(s);
	}
	
	// method to dequeue a square from the priority queue
	public Square dequeue(){
		if(this.pq.isEmpty()){
			throw new IllegalStateException("The smiley's priority queue is empty");
		}
		return this.pq.remove();
	}
	
	// isEmpty method for the smiley's priority queue
	public boolean pqIsEmpty(){
		return this.pq.isEmpty();
	}
	
	/* I could've called exceptions if the coordinates given weren't in the map for these methods but maps have their own exceptions so 
	 * I didn't think it was worth it */
	
	// visit a square in the map
	public void visit(int i, int j){
		this.visited.replace(new AbstractMap.SimpleEntry<Integer, Integer>(i, j), true);
	}
	
	// find out if a square has been visited
	public boolean visited(int i, int j){
		return this.visited.get(new AbstractMap.SimpleEntry<Integer, Integer>(i, j));
	}
	
	// replace a square's previous square
	public void replacePrev(Entry<Integer, Integer> key, Entry<Integer, Integer> newValue){
		this.prev.replace(key, newValue);
	}
	
	// method to compile the path of squares needed for smiley to get home using A*
	public void makePath(){
		Entry<Integer, Integer> v = new AbstractMap.SimpleEntry<Integer, Integer>(Maze.HOME_COORDINATES[0], Maze.HOME_COORDINATES[1]);
		Entry<Integer, Integer> i = new AbstractMap.SimpleEntry<Integer, Integer>(Maze.HOME_COORDINATES[0], Maze.HOME_COORDINATES[1]);
		this.positions = new Stack<Entry<Integer, Integer>>();
		this.positions.push(v);
		boolean beginning = false;
		
		while(!beginning){
			i = prev.get(v);
			if(i == null){
				throw new IllegalStateException("The shortest path hasn't been found");
			}
			
			positions.push(i);
			v = i;
			if( i.getKey() == this.startX && i.getValue() == this.startY){
				beginning = true;
			}
		}
		this.pathLength = this.positions.size();
		this.hasPath = true;
	}

	public int getX(){
		return startX;
	}
	
	public int getY(){
		return startY;
	}
	
	public int getPathLength(){
		if(!this.hasPath){
			throw new IllegalStateException("The path of the smiley hasn't been compiled");
		}
		return this.pathLength;
	}
	
	// gives the path of the smiley
	public String toString(){
		StringBuffer answer = new StringBuffer();
		if(this.hasPath){
			answer.append("can go home with a path length of " + (this.pathLength - 1) + " using the following path: \n");
			while(!this.positions.isEmpty()){
				Entry<Integer, Integer> v = this.positions.pop();
				if(this.positions.isEmpty()){
					answer.append("("+ v.getKey() + ", " + v.getValue() + ")");
				} else
					answer.append("("+ v.getKey() + ", " + v.getValue() + "), ");
			}
		} else {
			answer.append("Coordinates: (" + this.getX() + ", " + this.getY() + ")");
		}
		return answer.toString();
	}
}
