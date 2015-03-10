//Cameron Johnson
//Matrix.java
//Pa3
//id: 1384841
//Computer Science 101
public class Matrix {
	// private class Entry that's used only in this class
	private class Entry{
		int col;
		double num;
		// constructor
		Entry(int col , double num){
			this.col = col;
			this.num = num;
			
		}
		//checks if one Entry is eqaul to another
		public boolean equals(Object x){
			
			Entry temp = (Entry) x;
			if(temp.col != this.col || temp.num != this.num) return false;
			
			return true;
		}
		// Allows Entrys to be printed in a certain format
		public String toString(){
			return "("+ col +","+ num +")";
		}
	}
//private fields
	private int size;
	private int NNZ;
	private List rows[];


	// Constructor
	Matrix(int n){
		if(n>=1){
			this.size = n;
			this.rows = new List[n];
			this.NNZ = 0;
			
		}else{
			throw new RuntimeException("Can't construct matrix with size of 0");
		}
		
	} // Makes a new n x n zero Matrix. pre: n>=1
	// Access functions
	int getSize(){
		return this.size;
		// Returns n, the number of rows and columns of this Matrix
	}
	int getNNZ(){
		return this.NNZ;// Returns the number of non-zero entries in this Matrix
	}
	public boolean equals(Object x){
		boolean equalTo = true;
		Matrix entry = (Matrix) x;
		if(entry.getSize() != size) return false;
		for(int i= 0; i < size; i++){
			if(rows[i] == null && entry.rows[i] == null) continue;
			if(!(rows[i].equals(entry.rows[i]))){
				return false;
			}
		}
		return equalTo;// overrides Object's equals() method
	}
// Manipulation procedures	
	//makes all of matrix's values zero	
	void makeZero(){
		for(int i=0;i<size;i++){
			rows[i] = null;
		}
		this.size = 0;
		this.NNZ = 0;
	}
	Matrix copy(){
		Matrix clone = new Matrix(size);
		for(int j = 0;j<size;  j++){
			if(rows[j] == null){
				continue;
			}else{
				List copy = new List();
				List orig = rows[j];
				Entry e1;
				for(orig.moveTo(0);orig.getIndex()>=0;orig.moveNext()){ 
					if(orig.getElement() == null) continue;
					e1 = (Entry) orig.getElement();
					copy.append(new Entry(e1.col,e1.num));
					clone.NNZ++;
				}
				
				clone.rows[j] = copy;
			}
		}
		return clone;
		// returns a new Matrix having the same entries as this Matrix
	}
	// changes the entry on a matrix
	void changeEntry(int i, int j, double x){
		if(1<=i || i<=getSize() || 1<=j || j<=getSize()){
			Entry temp;	
			if(rows[i-1] == null){
					if(x !=0){
						rows[i-1] = new List();
						rows[i-1].append(new Entry(j,x));
						NNZ++;
						return;
					}
				}else{
					rows[i-1].moveTo(0);
					while(rows[i-1].getIndex() >= 0){
						 temp =(Entry) rows[i-1].getElement();
						if(temp.col==j){
							if(x==0){
								rows[i-1].delete();
								NNZ--;
								return;
							}else{
									temp.col = j;
									temp.num = x;
									return;	
							}
						}
						
						if(temp.col>j){
							if(x!=0){
								rows[i-1].insertBefore(new Entry(j,x));
								NNZ++;
								return;
							}
						}
						rows[i-1].moveNext();
					}
					if(rows[i-1].getIndex()<0){
						if(x!=0){
							rows[i-1].append(new Entry(j,x));
							NNZ++;
						}
					}
					
				}
			
			
		}
}
	// returns a new Matrix that is the scalar product of this Matrix with x
	Matrix scalarMult(double x){
		Matrix D = this.copy();
		Matrix matrixScaled = new Matrix(size);
		
		if(x == 0){
			return matrixScaled;
		}
		for(int i = 0;i<D.size; i++){
			if(D.rows[i] == null){
				continue;
			}else{
				List mult = new List();
				Entry scalar;
				List close = D.rows[i];
				for(close.moveTo(0);close.getIndex()>=0;close.moveNext()){
					scalar = (Entry)close.getElement();
					mult.append(new Entry(scalar.col, scalar.num *x));
					matrixScaled.NNZ++;
				}
				matrixScaled.rows[i] = mult;
			}
		}
		return matrixScaled;
	}
	 // returns a new Matrix that is the sum of this Matrix with M	 
	Matrix add(Matrix M){
		if( getSize() != M.getSize() ){ 
			throw new RuntimeException("add() error: unequal Matrix size");
		}
		if(this.equals(M)){
			return M.scalarMult(2);
		}
		Matrix sum = new Matrix(size);
		for(int i = 0; i<size;i++){
				if(rows[i] == null && M.rows[i] == null){
					continue;
				}else{
					List listSum = new List();
					Entry x;
					Entry y;
					List num1 = rows[i];
					List num2 = M.rows[i];
					if(num1 != null && num2 == null){
						 sum.rows[i]= num1;
					}else if(num1 == null && num2 != null){
						 sum.rows[i] = num2;
					}else{
						num1.moveTo(0);
						num2.moveTo(0);
						while(num1.getIndex()>= 0 && num2.getIndex()>=0){
							x = (Entry) num1.getElement();
							y = (Entry) num2.getElement();
							if(x.col == y.col){
								if(x.num + y.num != 0){
									listSum.append(new Entry(x.col, x.num + y.num));
									sum.NNZ++;
									num1.moveNext();
									num2.moveNext();
								}else{
									sum.NNZ--;
								}
							}else if(x.col < y.col){
								listSum.append(new Entry(x.col,x.num));
								sum.NNZ++;
								num1.moveNext();
							}else if(x.col > y.col){
								listSum.append(new Entry(y.col,y.num));
								sum.NNZ++;
								num2.moveNext();
							}
							
						}
						while(num1.getIndex()>=0){
							x = (Entry) num1.getElement();
							listSum.append(new Entry(x.col,x.num));
							sum.NNZ++;
							num1.moveNext();
						}
						while(num2.getIndex()>=0){
							y = (Entry) num2.getElement();
							listSum.append(new Entry(y.col,y.num));
							sum.NNZ++;
							num2.moveNext();
						}
					}
					sum.rows[i] = listSum;
				}
			}
		return sum;
	}

//	 // pre: getSize()==M.getSize()
//	 // returns a new Matrix that is the difference of this Matrix with M
	Matrix sub(Matrix M){
		if( getSize() != M.getSize() ){ 
			throw new RuntimeException("add() error: unequal Matrix size");
		}
		if(this.equals(M)){
			Matrix D = new Matrix(M.size);
			return D;
		}
		Matrix difference = new Matrix(size);
		for(int i = 0; i<size;i++){
				if(rows[i] == null && M.rows[i] == null){
					continue;
				}else{
					List listDif = new List();
					Entry x;
					Entry y;
					List num1 = rows[i];
					List num2 = M.rows[i];
					if(num1 != null && num2 == null){
						 difference.rows[i]= num1;
					}else if(num1 == null && num2 != null){
						 difference.rows[i] = num2;
					}else{
						num1.moveTo(0);
						num2.moveTo(0);
						while(num1.getIndex()>= 0 && num2.getIndex()>=0){
							x = (Entry) num1.getElement();
							y = (Entry) num2.getElement();
							if(x.col == y.col){
								if(x.num - y.num != 0){
									listDif.append(new Entry(x.col, x.num - y.num));
									difference.NNZ++;
									num1.moveNext();
									num2.moveNext();
								}else{
									num1.moveNext();
									num2.moveNext();
								}
							}else if(x.col < y.col){
								listDif.append(new Entry(x.col,x.num));
								difference.NNZ++;
								num1.moveNext();
							}else if(x.col > y.col){
								listDif.append(new Entry(y.col,y.num));
								difference.NNZ++;
								num2.moveNext();
							}
							
						}
						while(num1.getIndex()>=0){
							x = (Entry) num1.getElement();
							listDif.append(new Entry(x.col,x.num));
							difference.NNZ++;
							num1.moveNext();
						}
						while(num2.getIndex()>=0){
							y = (Entry) num2.getElement();
							listDif.append(new Entry(y.col,y.num));
							difference.NNZ++;
							num2.moveNext();
						}
					}
					difference.rows[i] = listDif;
				}
			}
		return difference;
	}

//	 // returns a new Matrix that is the transpose of this Matrix
	Matrix transpose(){
		Matrix trans = new Matrix(size);
		Entry tran;
		for(int i = 0; i<size;i++){
			if(rows[i] == null){
				continue;
			}else{
				rows[i].moveTo(0);
				while(rows[i].getIndex()>=0){
					tran = (Entry) rows[i].getElement();
					trans.changeEntry(tran.col,i+1,tran.num);
					rows[i].moveNext();
				}
			}
		}
		return trans;
	}
	  // returns a new Matrix that is the product of this Matrix with M
	Matrix mult(Matrix M){
		if(M.getSize() != getSize()){
			throw new RuntimeException("Function mult(); size is not same as original matrix");
		}
		Matrix A = new Matrix(size);
		Matrix B = M.transpose();
		for(int i = 0; i < size; i++){
			if(rows[i] == null){
				continue;
			}else{
				for(int j=0; j<B.size;j++){
					if(B.rows[i] == null){
						continue;
					}else{
						if(dot(rows[i],B.rows[j]) == 0){
							continue;
						}else{
							double product = dot(rows[i],B.rows[j]);
							A.changeEntry(i+1, j+1, product);
						}
					}
				}
			}
		}
		return A;
	}
	
	
	 // pre: getSize()==M.getSize()
	// returns the dot product between two lists
	private static double dot(List A, List B){
		Entry e1;
		Entry e2;
		double num = 0;
		if(B==null || A == null){
			return 0;
		}
		A.moveTo(0);
		B.moveTo(0);
		while(A.getIndex() >= 0 && B.getIndex() >= 0){
			e1 = (Entry) A.getElement();
			e2 = (Entry) B.getElement();
			if(e1.col < e2.col){
				A.moveNext();
			}else if(e1.col == e2.col){
				num += e1.num*e2.num;
				A.moveNext();
				B.moveNext();
			
			}else{
				B.moveNext();
			}
		}
		return num;
	}
	// A test function used to test if dot works
	public void dotTest(){
		 List Z = new List();
	     List Y = new List();
	     Y.append(new Entry(1,4));
	     Y.append(new Entry(1,2));
	     Y.append(new Entry(1,2));
	     Z.append(new Entry(1,2));
	     Z.append(new Entry(1,2));
	     Z.append(new Entry(1,2));
	     double product = dot(Z,Y);
	     System.out.println(product);
	}
	// overrides Object's toString() method
	public String toString(){
		String matrix = "";
		for(int i=0;i<this.size;i++){
			if(rows[i] != null){
				matrix += (i+1) + ": ";
				matrix += rows[i].toString();
				matrix += "\n";
			}
		}
		return matrix;
		
	}
}
