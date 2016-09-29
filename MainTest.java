package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

public class MainTest {

	private boolean containsDuplicates(ArrayList<String> ladder) {
		// keep track of all the words we've passed so far
		HashSet<String> seen = new HashSet<>();
		// add each word to the set, returning true if we encounter one we've seen before
		for (String word : ladder) {
			if (seen.contains(word))
				return true;
			seen.add(word);
		}
		return false;
	}
	
	@Test
	public void Basic_BFS() {
		Main.initialize();
		ArrayList<String> ladder = Main.getWordLadderBFS("STONE", "MONEY");
		if(!(ladder.get(0).equals("STONE")) || !(ladder.get(ladder.size() - 1)).equals("MONEY")){
			fail("Incorrect ladder");
		}
		if (containsDuplicates(ladder))
			fail("Duplicate words in ladder");
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
		if (containsDuplicates(ladder))
			fail("Duplicate words in ladder");
		System.out.println("pass");
	}
	
	@Test
	public void Long_BFS() {
		Main.initialize();
		ArrayList<String> ladder = Main.getWordLadderBFS("DRILL", "HOUSE");
		if(!(ladder.get(0).equals("DRILL")) || !(ladder.get(ladder.size() - 1)).equals("HOUSE")){
			fail("Incorrect ladder");
		}
		if (containsDuplicates(ladder))
			fail("Duplicate words in ladder");
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
		if (containsDuplicates(ladder))
			fail("Duplicate words in ladder");
		System.out.println("pass");
	}
	
	@Test
	public void NoLadder_DFS() {
		Main.initialize();
		ArrayList<String> ladder = Main.getWordLadderDFS("HAPPY", "IDIOT");
		Main.printLadder(ladder);
		if(ladder.size() > 0){
			fail("Incorrect ladder");
		}
		if (containsDuplicates(ladder))
			fail("Duplicate words in ladder");
		System.out.println("pass");
	}
	
	@Test
	public void NoLadder_BFS() {
		Main.initialize();
		ArrayList<String> ladder = Main.getWordLadderBFS("HAPPY", "IDIOT");
		Main.printLadder(ladder);
		if(ladder.size() > 0){
			fail("Incorrect ladder");
		}
		if (containsDuplicates(ladder))
			fail("Duplicate words in ladder");
		System.out.println("pass");
	}
	
	@Test
	public void LongFlipped_BFS() {
		Main.initialize();
		ArrayList<String> ladder = Main.getWordLadderBFS("HOUSE", "DRILL");
		if(!(ladder.get(0).equals("HOUSE")) || !(ladder.get(ladder.size() - 1)).equals("DRILL")){
			fail("Incorrect ladder");
		}
		if (containsDuplicates(ladder))
			fail("Duplicate words in ladder");
		System.out.println("pass");
	}
	
	@Test
	public void LongFlipped_DFS() {
		Main.initialize();
		ArrayList<String> ladder = Main.getWordLadderDFS("HOUSE", "DRILL");
		if(!(ladder.get(0).equals("HOUSE")) || !(ladder.get(ladder.size() - 1)).equals("DRILL")){
			fail("Incorrect ladder");
		}
		Main.printLadder(ladder);
		if (containsDuplicates(ladder))
			fail("Duplicate words in ladder");
		System.out.println("pass");
	}
	
	@Test
	public void NoLadderFlipped_DFS() {
		Main.initialize();
		ArrayList<String> ladder = Main.getWordLadderDFS("IDIOT", "HAPPY");
		Main.printLadder(ladder);
		if(ladder.size() > 0){
			fail("Incorrect ladder");
		}
		if (containsDuplicates(ladder))
			fail("Duplicate words in ladder");
		System.out.println("pass");
	}
	
	@Test
	public void NoLadderFlipped_BFS() {
		Main.initialize();
		ArrayList<String> ladder = Main.getWordLadderBFS("IDIOT", "HAPPY");
		Main.printLadder(ladder);
		if(ladder.size() > 0){
			fail("Incorrect ladder");
		}
		if (containsDuplicates(ladder))
			fail("Duplicate words in ladder");
		System.out.println("pass");
	}
}
