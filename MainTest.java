package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MainTest {
	
	@Test
	public void Basic_BFS() {
		Main.initialize();
		ArrayList<String> ladder = Main.getWordLadderBFS("STONE", "MONEY");
		if(!(ladder.get(0).equals("STONE")) || !(ladder.get(ladder.size() - 1)).equals("MONEY")){
			fail("Incorrect ladder");
		}
		for (int i = 0; i < ladder.size(); i++){
			for (int j = 0; j < ladder.size(); j++){
				if((ladder.get(i) == ladder.get(j)) && (i != j)){
					fail("Duplicate words in ladder");
					return;
				}
			}
		}
		System.out.println("pass");
	}
	
	@Test
	public void Basic_DFS() {
		Main.initialize();
		ArrayList<String> ladder = Main.getWordLadderDFS("STONE", "MONEY");
		if(!(ladder.get(0).equals("STONE")) || !(ladder.get(ladder.size() - 1)).equals("MONEY")){
			fail("Incorrect ladder");
		}
		Main.printLadder(ladder);
		for (int i = 0; i < ladder.size(); i++){
			for (int j = 0; j < ladder.size(); j++){
				if((ladder.get(i) == ladder.get(j)) && (i != j)){
					fail("Duplicate words in ladder");
					return;
				}
			}
		}
		System.out.println("pass");
	}
	
	@Test
	public void Long_BFS() {
		Main.initialize();
		ArrayList<String> ladder = Main.getWordLadderBFS("DRILL", "HOUSE");
		if(!(ladder.get(0).equals("DRILL")) || !(ladder.get(ladder.size() - 1)).equals("HOUSE")){
			fail("Incorrect ladder");
		}
		for (int i = 0; i < ladder.size(); i++){
			for (int j = 0; j < ladder.size(); j++){
				if((ladder.get(i) == ladder.get(j)) && (i != j)){
					fail("Duplicate words in ladder");
					return;
				}
			}
		}
		System.out.println("pass");
	}
	
	@Test
	public void Long_DFS() {
		Main.initialize();
		ArrayList<String> ladder = Main.getWordLadderDFS("DRILL", "HOUSE");
		if(!(ladder.get(0).equals("DRILL")) || !(ladder.get(ladder.size() - 1)).equals("HOUSE")){
			fail("Incorrect ladder");
		}
		Main.printLadder(ladder);
		for (int i = 0; i < ladder.size(); i++){
			for (int j = 0; j < ladder.size(); j++){
				if((ladder.get(i) == ladder.get(j)) && (i != j)){
					fail("Duplicate words in ladder");
					return;
				}
			}
		}
		System.out.println("pass");
	}
)
