/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * Garrett Custer
 * gsc535
 * 16475
 * Slip days used: <0>
 * Git URL: https://github.com/GarrettCodester/assignment3
 * Fall 2016
 */


package assignment3;
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
		ArrayList<String> words = new ArrayList<String>();
		words = new ArrayList<String>();
			while(words.size() < 2){	//allows user to input both words on same line OR one word each line
				String[] input = keyboard.nextLine().split("\\s+");  //splits input by whitespace
				if((input.length == 1) && (input[0].equals("/quit"))){  //checks for input "/quit"
					return new ArrayList<String>();
				}
				for (int i = 0; i < input.length; ++i){			//updates word ArrayList
					if (!(input[i].matches("^\\s*$"))){ words.add(input[i]);}
				}
			}
		return words;
	}
	
	/**
	 * Depth First Search
	 * @param String start - first word for ladder
	 * @param String end - second word for ladder
	 * @param Set<String> visitedSet - Set containing every word in dictionary that has already been passed
	 * @return ArrayList of Strings containing every word in ladder in reverse order
	 * if there is no word ladder between start and end, return empty ArrayList
	 */
	public static ArrayList<String> getWordLadderDFS(String start, String end) { //for initial call, no visitedSet
        return getWordLadderDFS(start, end, new HashSet<>());
    }

    public static ArrayList<String> getWordLadderDFS(String start, String end, Set<String> visitedSet) {
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
                if (result.size() > 0) { //if result.size() = 0, no ladder exists
                    result.add(0,start);
                    return result;
                }
            }
        }

        return new ArrayList<>();
    }
	
    /**
	 * Breadth First Search
	 * @param String start - first word for ladder
	 * @param String end - second word for ladder
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
	
	public static void printLadder(ArrayList<String> ladder) { 
	    printLadder(ladder, System.out);
	}

	private static void printLadder(ArrayList<String> ladder, PrintStream ps) {
        ladder.stream().map(String::toLowerCase).forEachOrdered(ps::println);
    }
	// TODO
	// Other private static methods here
}