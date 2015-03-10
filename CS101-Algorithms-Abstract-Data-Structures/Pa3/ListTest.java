//Cameron Johnson
//ListTest.java
//Pa3
//id: 1384841
//Computer Science 101
public class ListTest {
public static void main(String[] args){ 
		
		List L = new List();
		System.out.println(L.length());
		System.out.println(L.getIndex());
	
		L.append((int)9);
		System.out.println(L);
		System.out.println(L.getElement());
		L.append((int) 8);
		
		System.out.println(L.length());
		System.out.println("L"+L);
		System.out.println(L.front()); 
		System.out.println(L.back());
		System.out.println(L.getElement());
		System.out.println("index" + L.getIndex());
		
		//L.insertBefore((int)1);
		L.insertAfter((int) 2);

		System.out.println(L);
		System.out.println(L.getElement());
                System.out.println("index" + L.getIndex());
		
		L.moveTo(3);
		System.out.println(L.getElement());
		System.out.println("index"+L.getIndex());
		
		L.moveTo(0);
		System.out.println(L.getElement());
		System.out.println("index"+L.getIndex());
	
	}
}
