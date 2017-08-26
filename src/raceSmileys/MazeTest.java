package raceSmileys;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.Map.Entry;
import java.util.Stack;

import junit.framework.Assert;

import org.junit.Test;

public class MazeTest {

	@Test
	public void testMazes() {
		this.testWithMaze1();
		this.testWithMaze2();
	}
	
	public void testWithMaze1(){
		Smiley[] smileys = new Smiley[Smiley.NUM_SMILEY];
		Integer winner = 2;
		
		// smiley 1
		Stack<Entry<Integer, Integer>> path1 = new Stack<Entry<Integer,Integer>>(); 
		path1.push(new AbstractMap.SimpleEntry<Integer,Integer>(3,3));
		path1.push(new AbstractMap.SimpleEntry<Integer,Integer>(2,3));
		path1.push(new AbstractMap.SimpleEntry<Integer,Integer>(1,3));
		path1.push(new AbstractMap.SimpleEntry<Integer,Integer>(1,2));
		path1.push(new AbstractMap.SimpleEntry<Integer,Integer>(0,2));
		path1.push(new AbstractMap.SimpleEntry<Integer,Integer>(0,1));
		path1.push(new AbstractMap.SimpleEntry<Integer,Integer>(0,0));
		
		//smiley 2
		Stack<Entry<Integer, Integer>> path2 = new Stack<Entry<Integer,Integer>>(); 
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(3,3));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,3));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,4));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,5));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(5,5));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(6,5));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(6,4));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(6,3));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(6,2));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(6,1));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(6,0));
		
		//smiley 3
		Stack<Entry<Integer, Integer>> path3 = new Stack<Entry<Integer,Integer>>(); 
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(3,3));
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,3));
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,4));
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,5));
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(3,5));
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(2,5));
		
		//smiley 4
		Stack<Entry<Integer, Integer>> path4 = new Stack<Entry<Integer,Integer>>(); 
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(3,3));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,3));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,4));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,5));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(3,5));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(2,5));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(1,5));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(1,6));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(0,6));
		
		Maze.createMaze(1);
		Maze.setSmileyAt(0, 0);
		Maze.setSmileyAt(6, 0);
		Maze.setSmileyAt(2, 5);
		Maze.setSmileyAt(0, 6);
		Race.simulateRace();
		smileys = Maze.getSmileys();
		
		Assert.assertEquals(path1, getSmileyPath(smileys[0]));
		Assert.assertEquals(path2, getSmileyPath(smileys[1]));
		Assert.assertEquals(path3, getSmileyPath(smileys[2]));
		Assert.assertEquals(path4, getSmileyPath(smileys[3]));
		Assert.assertEquals(winner, getWinner());
		
		Maze.resetMaze();
	}
	
	
	public void testWithMaze2(){
		Smiley[] smileys = new Smiley[Smiley.NUM_SMILEY];
		Integer winner = 0;
		
		// smiley 1
		Stack<Entry<Integer, Integer>> path1 = new Stack<Entry<Integer,Integer>>(); 
		path1.push(new AbstractMap.SimpleEntry<Integer,Integer>(3,3));
		path1.push(new AbstractMap.SimpleEntry<Integer,Integer>(2,3));
		path1.push(new AbstractMap.SimpleEntry<Integer,Integer>(1,3));
		path1.push(new AbstractMap.SimpleEntry<Integer,Integer>(1,2));
		path1.push(new AbstractMap.SimpleEntry<Integer,Integer>(0,2));
		path1.push(new AbstractMap.SimpleEntry<Integer,Integer>(0,1));
		path1.push(new AbstractMap.SimpleEntry<Integer,Integer>(0,0));
		
		//smiley 2
		Stack<Entry<Integer, Integer>> path2 = new Stack<Entry<Integer,Integer>>(); 
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(3,3));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,3));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,4));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,5));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(3,5));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(2,5));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(1,5));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(1,6));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(0,6));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(0,7));
		path2.push(new AbstractMap.SimpleEntry<Integer,Integer>(0,8));
		
		//smiley 3
		Stack<Entry<Integer, Integer>> path3 = new Stack<Entry<Integer,Integer>>(); 
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(3,3));
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,3));
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,4));
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,5));
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(5,5));
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(6,5));
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(6,4));
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(6,3));
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(6,2));
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(6,1));
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(6,0));
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(7,0));
		path3.push(new AbstractMap.SimpleEntry<Integer,Integer>(8,0));
		
		//smiley 4
		Stack<Entry<Integer, Integer>> path4 = new Stack<Entry<Integer,Integer>>(); 
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(3,3));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,3));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,4));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(4,5));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(5,5));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(6,5));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(7,5));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(7,6));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(8,6));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(8,7));
		path4.push(new AbstractMap.SimpleEntry<Integer,Integer>(8,8));
		
		Maze.createMaze(2);
		Maze.setSmileyAt(0, 0);
		Maze.setSmileyAt(0, 8);
		Maze.setSmileyAt(8, 0);
		Maze.setSmileyAt(8, 8);
		Race.simulateRace();
		smileys = Maze.getSmileys();
		
		Assert.assertEquals(path1, getSmileyPath(smileys[0]));
		Assert.assertEquals(path2, getSmileyPath(smileys[1]));
		Assert.assertEquals(path3, getSmileyPath(smileys[2]));
		Assert.assertEquals(path4, getSmileyPath(smileys[3]));
		Assert.assertEquals(winner, getWinner());
		
		Maze.resetMaze();
	}
	
	public Stack<Entry<Integer,Integer>> getSmileyPath(Smiley smiley){
		Field f = null;
		Stack<Entry<Integer,Integer>> positions = null;
		try {
			f = smiley.getClass().getDeclaredField("positions");
			f.setAccessible(true);
			positions =(Stack<Entry<Integer,Integer>>)f.get(smiley);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return positions;
	}
	
	public Integer getWinner(){
		Field f = null;
		Integer winner = 0;
		
		try {
			f = Race.class.getDeclaredField("winner");
			f.setAccessible(true);
			winner = (Integer) f.get(Race.class);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return winner;
	}

}
