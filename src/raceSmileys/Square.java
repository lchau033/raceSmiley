package raceSmileys;

/*
 * Assignment 3
 * CSI4106
 * Lia Chauvel
 * 6770728
 * 
 * my version of an entry to compare the functions of each squares according to their cost and to keep their coordinates
 */


public class Square implements Comparable<Square>{
	
	// square coordinates
	private Integer x;
	private Integer y;
	
	// square function components
	private Integer heuristic;
	private Integer cost;
	
	public Square(Integer x, Integer y, Integer heuristic,Integer cost){
		if(!Maze.mazeCreated()){
			throw new IllegalStateException("There can be no squares without a maze");
		}
		
		if(x < 0 || y < 0 || x > Maze.getMazeLength() || y > Maze.getMazeWidth() || heuristic < 0 || cost < 0){
			throw new IllegalArgumentException();
		}
		
		this.x = x;
		this.y = y;
		this.heuristic = heuristic;
		this.cost = cost;
	}
	
	public Integer getX(){
		return this.x;
	}
	
	public Integer getY(){
		return this.y;
	}
	
	public Integer getCost(){
		return this.cost;
	}

	public Integer getHeuristic(){
		return this.heuristic;
	}
	
	@Override
	// compare according to f(n)= cost + heuristic
	public int compareTo(Square temp) {
		if(temp == null){
			throw new NullPointerException("Can't compare null values");
		}
		
		if(this.cost + this.heuristic < temp.getCost() + temp.getHeuristic()){
			return -1;
		}
		else if(this.cost + this.heuristic == temp.getCost() + temp.getHeuristic()){
			return 0;
		}
		else
			return 1;
	}
	
	public String toString(){
		StringBuffer answer = new StringBuffer();
		
		answer.append("Coordinates : (" + this.getX() + ", " + this.getY() + ") ");
		answer.append(", Cost: " + this.getCost());
		answer.append(" , Heuristic: " + this.getHeuristic());
		
		return answer.toString();
	}
}
