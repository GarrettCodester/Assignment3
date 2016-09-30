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
    private static Set<String> visitedSet;

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
            ArrayList<String> ladder = getWordLadderDFS(startEnd.get(0), startEnd.get(1));
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
		letters = new ArrayList<>();
		for (int i=(int)'A'; i <= (int)'Z'; i++)
			letters.add((char)i);
        dict = makeDictionary();
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
        visitedSet = new HashSet<>();
        return DFSHelperFunction(start, end);
    }

	/**
	 * Depth First Search
	 * @param start - first word for ladder
	 * @param end - second word for ladder
	 * @return ArrayList of Strings containing every word in ladder in reverse order
	 * if there is no word ladder between start and end, return empty ArrayList
	 */
    private static ArrayList<String> DFSHelperFunction(String start, String end) {
        // check for end of ladder
        if (start.equals(end)) {
            ArrayList<String> result = new ArrayList<>();
            result.add(end);
            return result;
        }
        // mark this word as visited
        visitedSet.add(start);
        // optimization: first try to swap to the correct characters in "end"
        for (int i=0; i<start.length(); i++) {
            String newString = start.substring(0,i) + end.charAt(i) + start.substring(i+1);
            if (visitedSet.contains(newString) || !dict.contains(newString))	//is the word valid?
                continue;
            ArrayList<String> result = DFSHelperFunction(newString, end); // recurse
            if (result.size() > 0) { //if result.size() > 0, bubble up (we've found end)
                result.add(0,start);
                return result;
            }
        }
        // otherwise, fall back on checking the other combinations
        for (int i=0; i<start.length(); i++) {
            // otherwise, traverse normally
            for (char c : letters) {
                String newString = start.substring(0,i) + c + start.substring(i+1);
                if (visitedSet.contains(newString) || !dict.contains(newString))	//is the word valid?
                    continue;
                ArrayList<String> result = DFSHelperFunction(newString, end); // recurse
                if (result.size() > 0) { //if result.size() > 0, bubble up (we've found end)
                    result.add(0,start);
                    return result;
                }
            }
        }
        // if we're here, we didn't find a ladder
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
        // initialize the queue for processing words and the set for marking them as visited
        // we're saving the entire ladder in the queue so that we have a running history of words,
        // which obviates the need for backtracking.
        Queue<ArrayList<String>> queue = new LinkedList<>();
        Set<String> visitedSet = new HashSet<>();
        ArrayList<String> initialList = new ArrayList<>();
        initialList.add(start);
        queue.add(initialList);
        // process the queue, adding "children" to the end as we go along
        ArrayList<String> currentLadder;
        while (!queue.isEmpty()) {
            currentLadder = queue.poll(); // pull first ladder, removing from the queue
            String last = currentLadder.get(currentLadder.size()-1); // we only care about the most recent element in the ladder
            // have we found the end yet?
            if (last.equals(end))
                return currentLadder;
            // if not, mark the set as visited
            visitedSet.add(last);
            // add all possible (valid) children to the queue
            for (int i=0; i<start.length(); i++) {
                for (char c : letters) {
                    String newString = last.substring(0, i) + c + last.substring(i + 1);
                    if (!visitedSet.contains(newString) && dict.contains(newString)) {
                        // creating a copy of the ladder to prevent scope/reference issues
                        ArrayList<String> ladderCopy = new ArrayList<>(currentLadder);
                        ladderCopy.add(newString);
                        queue.add(ladderCopy);
                    }
                }
            }
        }
        // if we're here, there was no valid ladder (ran out of children to process)
        return new ArrayList<>();
	}

	public static Set<String> makeDictionary () {
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
	    // self-explanatory stream: convert to lowercase and print
        ladder.stream().map(String::toLowerCase).forEachOrdered(ps::println);
    }
}