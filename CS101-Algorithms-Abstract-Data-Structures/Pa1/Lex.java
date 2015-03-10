/*-----------------------------------------------------
Cameron Johnson
Lex.java
Pa1
id: 1384841
Computer Science 101
*/-----------------------------------------------------
import java.io.*;
import static java.lang.System.*;
import java.util.Scanner;

public class Lex {
	
	public static void main (String[] args){
		// checks to see if there is correct input
		if(args.length != 2){
			System.err.println("We need more input!!!!");
			System.exit(1);
		}
		Scanner input = null;
		//checks to see if the file to read from is appropriate
		try{
		    input = new Scanner(new File(args[0]));
		} catch (IOException except){
		    System.err.println("Invalid filename");
		    exit(1);
		}
		PrintWriter writer = null;
		//checks to see if the file to write to is appropriate
		try {
		    writer = new PrintWriter(new FileWriter(args[1]));
		} catch (FileNotFoundException excep){
		    System.err.println("Could not create the file");
		    exit(1);
		} catch (IOException excep){
		    System.err.println("Could not create the file");
		    exit(1);
		}		
		int lines = 0;
		//counts the number of lines in the input file
		for(; input.hasNextLine(); input.nextLine()) lines++;		
		//reads the file again into input
		input = new Scanner(new File(args[0]));
		String[] array = copy (input, lines);
		//sorts the array lexicographically 
		List main = sort(array);	
		// iterates through sorted list and outputs each line onto an array
		for(main.moveTo(0); main.getIndex()>=0; main.moveNext()){
		    writer.println(array[main.getElement()]);
		}
		// closes the print writer and input file
		writer.close();	
		input.close();
	}
	// algorithm to sort the list lexicographically
	static List sort(String[] file){
		List main = new List();
		// appends the first item to the new list
		if(file.length > 0){
			main.append(0);	
		}
		// loops through input array
		for(int i = 1; i<file.length; i++){
			String word = file[i];
			int cursor = i-1;
			main.moveTo(cursor);
			// loops through each list and checks to see if the value is similar
			while(cursor>-1 && word.compareTo(file[main.getElement()])<1){
				cursor--;
				main.movePrev();
			}
			// if it makes it to the end of the list prepend it to the list, 
			// but if it isn't insert it after the current element
			if(main.getIndex() == -1){
				main.prepend(i);
			}else {
				main.insertAfter(i);
			}
		}
		// returns the finished list
		return main;
	}
	// function copies each line into an array of strings
	static String[] copy(Scanner file, int lines){		
		String[] array = new String[lines];
		for(int i = 0; file.hasNextLine(); i++){
		    array[i] = file.nextLine();
		}
		return array;
	}
}
