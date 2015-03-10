//Cameron Johnson
//MatrixTest.java
//Pa3
//id: 1384841
//Computer Science 101
import java.util.Random;

	public class MatrixTest{
		   public static  void main(String[] args){
		      int i, j, n=100000;
		      Matrix A = new Matrix(n);
		      Matrix B = new Matrix(n);
// 		This code can be used to generate completely random matrices
		      Random rand = new Random();
		      for( i = 0;i<22;i++){
		    	  A.changeEntry(rand.nextInt(20)+1, rand.nextInt(20)+1, rand.nextInt(50)+5 );
		      }
		      for( j = 0;j<22;j++){
		    	  B.changeEntry(rand.nextInt(20)+1, rand.nextInt(20)+1, rand.nextInt(50)+5 );
		      }
		      A.changeEntry(1,1,1); B.changeEntry(1,1,1);
		      A.changeEntry(1,2,2); B.changeEntry(1,2,0);
		      A.changeEntry(1,3,3); B.changeEntry(1,3,1);
		      A.changeEntry(2,1,4); B.changeEntry(2,1,0);
		      A.changeEntry(2,2,5); B.changeEntry(2,2,1);
		      A.changeEntry(2,3,6); B.changeEntry(2,3,0);
		      A.changeEntry(3,1,7); B.changeEntry(3,1,1);
		      A.changeEntry(3,2,8); B.changeEntry(3,2,1);
		      A.changeEntry(3,3,9); B.changeEntry(3,3,1);
			  
		      System.out.println("=================== A ==============");
		      System.out.println(A.getNNZ());
		      System.out.println(A);

		      System.out.println("=================== B ==============");
		      System.out.println(B.getNNZ());
		      System.out.println(B);

		      System.out.println("====================== A x 1.5 = C =====================");
		      Matrix C = A.scalarMult(1.5);
		      System.out.println(C.getNNZ());
		      System.out.println(C);

		      System.out.println("====================== A + B = D =====================");
		      Matrix D = A.add(B);
		      System.out.println(D.getNNZ());
		      System.out.println(D);

		      System.out.println("====================== A - B = E =====================");
		      Matrix E = A.sub(B);
		      System.out.println(E.getNNZ());
		      System.out.println(E);

		     
		      
		      System.out.println("====================== A x B = G =====================");
		      Matrix G = A.mult(B);
		      System.out.println(G.getNNZ());
		      System.out.println(G);
		      
		      System.out.println("================ transpose B ===================");
		      Matrix F = B.transpose();
		      System.out.println(D.getNNZ());
		      System.out.println(D);
		       
		      System.out.println("====================== Copy A = H =====================");
		      Matrix H = A.copy();
		      System.out.println(H.getNNZ());
		      System.out.println(H);
		      
		      System.out.println("====================== A Equals H,B,C,D,E,A  =====================");
		      System.out.println(A.equals(H));
		      System.out.println(A.equals(B));
		      System.out.println(A.equals(C));
		      System.out.println(A.equals(D));
		      System.out.println(A.equals(F));
		      System.out.println(A.equals(A));

		      A.makeZero();
		      System.out.println(A.getNNZ());
		      System.out.println(A);
		   }
	}
		   
