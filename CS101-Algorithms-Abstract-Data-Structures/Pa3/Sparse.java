//Cameron Johnson
//Sparse.java
//Pa3
//id: 1384841
//Computer Science 101
import java.io.*;
import java.util.Scanner;
public class Sparse {
  public static  void main(String[] args) throws IOException{
	  Scanner input = null;
	  PrintWriter output = null;
	  String lines = null;
	  String[] token = null;
	  // checks for the correct amount of arguements
	  if( args.length<2){
	    throw new RuntimeException(" Sparse amount of information on command line");
	  }
	  input = new Scanner(new File(args[0]));
	  output = new PrintWriter(new FileWriter(args[1]));
	  // crucial variables for the program
		int length=0;
		int e1=0;
		int entry2=0;
		int row = 0;
		int column = 0;
		double val =0;
		if(input.hasNextLine()){
			lines = input.nextLine()+" ";
			token = lines.split("\\s+");
			length = Integer.parseInt(token[0]);
			e1 = Integer.parseInt(token[1]);
			entry2 = Integer.parseInt(token[2]);
		}
		
		//creates matrix
		Matrix A = new Matrix(length);
		Matrix B = new Matrix(length);
		
		/*matrix A initialising*/
		for( int i =0; i< e1; i++){
			if ( input.hasNextLine()){
				lines = input.nextLine();
				while ( lines.isEmpty() && input.hasNextLine()){
					lines = input.nextLine();
				}
				lines  += " ";
				token = lines.split("\\s+");
				row = Integer.parseInt(token[0]);
				column = Integer.parseInt(token[1]);
				val = Double.parseDouble(token[2]);
			}
			A.changeEntry(row,column,val);	
		} 
			
		/*matrix B initialising*/
		for ( int i =0; i<entry2; i++){
	  	if(input.hasNextLine()){
				lines = input.nextLine();
				while(lines.isEmpty() && input.hasNextLine()){
					lines = input.nextLine();
				}
				lines += "";
				token = lines.split("\\s+");
				row = Integer.parseInt(token[0]);
				column = Integer.parseInt(token[1]);
				val = Double.parseDouble(token[2]);
			}
			B.changeEntry(row,column,val);
		}
		
		/*prints to the output file in the order*/
		Matrix temp;
		output.println("A has "+A.getNNZ()+" non-zero entries:");
		output.println(A.toString());
	  if(A.getNNZ() != 0){
			output.println();
		}

		 output.println("B has "+B.getNNZ()+" non-zero entries:");
		 output.println(B.toString());
		 if(B.getNNZ() != 0){
	    output.println();
			}

		        output.println("(1.5)*A =");
			temp = A.scalarMult(1.5);
			output.println(temp.toString());
			if(temp.getNNZ() != 0){
				output.println();
			} 

			output.println("A+B =");
			temp = A.add(B);
			output.println(temp.toString());
			if(temp.getNNZ() != 0){
				output.println();
			}

			output.println("A+A =");
		  	Matrix copy = A.copy();
			temp = A.add(copy);
			output.println(temp.toString());
			if(temp.getNNZ() != 0){
				output.println();
			}

			output.println("B-A =");
			temp = B.sub(A);
			output.println(temp.toString());
			if(temp.getNNZ() != 0){
				output.println();
			} 

			output.println("A-A =");
			temp = A.sub(copy);
			output.println(temp.toString());
			if(temp.getNNZ() != 0){
				output.println();
			}

			output.println("Transpose(A) =");
			temp = A.transpose();
			output.println(temp.toString());
			if(temp.getNNZ() != 0){
				output.println();
			}

			output.println("A*B =");
			temp = A.mult(B);
			output.println(temp.toString());
			if(temp.getNNZ() != 0){
				output.println();
			}

			output.println("B*B =");
			copy = B.copy();
			temp = A.mult(copy);
			output.println(temp.toString());
			if(temp.getNNZ() != 0){
				output.println();
			}
	      
	    input.close();
		  output.close();

	}
}
