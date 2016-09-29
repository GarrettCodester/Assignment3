/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Fall 2016
 */


package assignment3;
import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		
		// TODO methods to read in words, output ladder
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
        ArrayList<String> result = new ArrayList<>();

        Matcher matcher;
        do {
            String line = keyboard.nextLine();
            Pattern pattern = Pattern.compile("(?s).*?(\\w+)\\s+(\\w+).*?");
            matcher = pattern.matcher(line);
        } while (!matcher.find());
        result.add(matcher.group(1));
        result.add(matcher.group(2));

        /*
        String first = keyboard.next();
        if (first.toLowerCase().equals("/quit"))
            System.exit(0);
        String second = keyboard.next();
        result.add(first);
        result.add(second);
        */
		return result;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code
		
		return null; // replace this line later with real return
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code
		
		return null; // replace this line later with real return
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
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
	
	public static void printLadder(ArrayList<String> ladder) { //if no PrintStream is specified
	    printLadder(ladder, System.out);
	}

	private static void printLadder(ArrayList<String> ladder, PrintStream ps) {
        ladder.stream().map(String::toLowerCase).forEachOrdered(ps::println); //prints each word with PrintStream
    }
	// TODO
	// Other private static methods here
}
