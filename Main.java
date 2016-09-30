/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Grant Uy
 * gau84
 * 16480
 * Garrett Custer
 * gsc535
 * 16475
 * Slip days used: 0
 * Git URL: https://github.com/GarrettCodester/assignment3
 * Fall 2016
 */

package assignment3;
import java.io.*;
import java.util.*;

public class Main {
	
	private static List<Character> letters;
    private static Set<String> dict;
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
        initialize();
        while (true) {
            ArrayList<String> startEnd = parse(kb);
            ArrayList<String> ladder = getWordLadderDFS(startEnd.get(0).toUpperCase(), startEnd.get(1).toUpperCase());
            if (ladder.isEmpty()) {
                System.out.printf("no word ladder can be found between %s and %s.\n",
                        startEnd.get(0).toLowerCase(), startEnd.get(1).toLowerCase());
            } else {
                System.out.printf("a %d-rung word ladder exists between %s and %s.\n",
                        ladder.size()-2, startEnd.get(0).toLowerCase(), startEnd.get(1).toLowerCase());
                printLadder(ladder, ps);
            }
        }
	}
	
	public static void initialize() {
		letters = new ArrayList<>();		//reset letter list
		for (int i=(int)'A'; i <= (int)'Z'; i++ ) {
			letters.add((char)i);
		}
        dict = makeDictionary();			//reset dictionary
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> words = new ArrayList<>();
		while (words.size() < 2) {	//allows user to input both words on same line OR one word each line
            String rawInput = keyboard.nextLine().toUpperCase();
			String[] input = rawInput.split("\\s+");  //splits input by whitespace
			if (rawInput.contains("/QUIT"))  //checks for input "/quit"
				System.exit(0);
			for (int i = 0; i < input.length; ++i)			//updates word ArrayList
				if (!input[i].matches("^\\s*$"))
				    words.add(input[i]);
		}
		return words;
	}

	public static ArrayList<String> getWordLadderDFS(String start, String end) { //for initial call, no visitedSet
        return getWordLadderDFS(start, end, new HashSet<>());
    }

	/**
	 * Depth First Search
	 * @param start - first word for ladder
	 * @param end - second word for ladder
	 * @param visitedSet - Set containing every word in dictionary that has already been passed
	 * @return ArrayList of Strings containing every word in ladder in reverse order
	 * if there is no word ladder between start and end, return empty ArrayList
	 */
    private static ArrayList<String> getWordLadderDFS(String start, String end, Set<String> visitedSet) {
        visitedSet.add(start);

        if (start.equals(end)) {	//check for end of ladder
            ArrayList<String> result = new ArrayList<>();
            result.add(end);
            return result;
        }

        for (int i=0; i<start.length(); i++) {
            for (char c : letters) {
                String newString = start.substring(0,i) + c + start.substring(i+1); //changes word by 1 letter
                if (visitedSet.contains(newString))	//has the word been visited already?
                    continue;
                if (!dict.contains(newString))		//is the word in the dictionary?
                    continue;
                ArrayList<String> result = getWordLadderDFS(newString, end, visitedSet); //repeat with new String
                if (result.size() > 0) { //if result.size() > 0, bubble up (we've found end)
                    result.add(0,start);
                    return result;
                }
            }
        }

        return new ArrayList<>();
    }
	
    /**
	 * Breadth First Search
	 * @param start - first word for ladder
	 * @param end - second word for ladder
	 * @return ArrayList of Strings containing every word in ladder in reverse order
	 * if there is no word ladder between start and end, return empty ArrayList
	 */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
        Queue<ArrayList<String>> q = new LinkedList<>();
        Set<String> visitedSet = new HashSet<>();
        visitedSet.add(start);
        ArrayList<String> initList = new ArrayList<>();
        initList.add(start);
        q.add(initList);

        ArrayList<String> cur;
        while (!q.isEmpty()) {
            cur = q.poll();
            String last = cur.get(cur.size()-1);
            if (last.equals(end))
                return cur;
            visitedSet.add(last);
            for (int i=0; i<start.length(); i++) {
                for (char c : letters) {
                    String newString = last.substring(0, i) + c + last.substring(i + 1);
                    if (!visitedSet.contains(newString) && dict.contains(newString)) {
                        ArrayList<String> copy = new ArrayList<>(cur);
                        copy.add(newString);
                        q.add(copy);
                    }
                }
            }
        }
        return new ArrayList<>();
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	public static void printLadder(ArrayList<String> ladder) { 
	    printLadder(ladder, System.out);
	}

	private static void printLadder(ArrayList<String> ladder, PrintStream ps) {
        ladder.stream().map(String::toLowerCase).forEachOrdered(ps::println);
    }
}