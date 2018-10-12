package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Node {
	/// the integer indicates the state of the node
     Integer id ;
     Node(){
    	 id = getNumber(generate_random());
     }
     Node(int a){
    	 id = a;
     }
 	static ArrayList<Integer> generate_random(){  /// generates an arraylist with numbers from 0 -> 8 in random positions
		ArrayList<Integer> x = new ArrayList<Integer>();
		
		for(int i=0;i<9;i++){
		     x.add(i);	
		}
		
		for(int i=0;i<9;i++){
			Random r = new Random() ;
			Integer cur = Math.abs(r.nextInt()) % 9;
		
			Integer tmp = x.get(i);
			
			x.set(i, x.get(cur));
			x.set(cur, tmp);
		}
		if(x.get(0) == 0){
			Integer tmp = x.get(1);
			x.set(1, 0);
			x.set(0, tmp);		
		}
		
		return x;
	}
	
	static Integer getNumber(ArrayList<Integer> x){ /// given a list of numbers returns the number composed of adding all the number in the list together
		Integer ret = 0;
		for(int i=0;i<x.size();i++){
		    ret *= 10;
		    ret += x.get(i);
		}
		return ret;
	}

	static ArrayList<Integer> getNumberList(int x){ /// given a number concatenates each digit in it and adds them into the list
		ArrayList<Integer> ret = new ArrayList<Integer>();
		
		while(x != 0){
			ret.add(x%10);
			x/=10;
		}
		
		if(ret.size() < 9)
			ret.add(0);
		Collections.reverse(ret);
		
		return ret;
	}
     
}
