package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class DFS {
	HashSet<Integer> visited = new HashSet<Integer>();
	HashMap<Integer,Integer> parent = new HashMap<Integer,Integer>();
	
	Node source;
	DFS(Node a){
		source = a;
	}
	/// notes sometimes it doesnot reach a solution
	
	void print_path(){
		int cur = 12345678;
		ArrayList<Integer> x = new ArrayList<Integer>();
		while(parent.containsKey(cur)){
			x.add(cur);
			cur = parent.get(cur);
		}
		x.add(cur);
		Collections.reverse(x);
		for(int i=0;i<x.size();i++)
			 System.out.println(x.get(i));
	}
	int get_path_length(){
		int cur = 12345678;
		ArrayList<Integer> x = new ArrayList<Integer>();
		while(parent.containsKey(cur)){
			x.add(cur);
			cur = parent.get(cur);
		}
		x.add(cur);
		return x.size()-1;
	}
	    boolean dfs(){  /// dfs implementation 
		
		Stack<Node> s = new Stack<Node>(); 
		
		s.push(source);
		visited.add(source.id);
		
		while(!s.empty()){
    	  
    	  Node cur = s.peek();
          s.pop();
          
        //  System.out.println(cur.id);
    	
          if(cur.id == 12345678){
    		  System.out.println("DOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONE");
    		  return true;
    	  }
    	  
  		ArrayList<Integer> state = Node.getNumberList(cur.id);
  		
    	 /// find the zero index 
		int row = 0 , col = 0;
		for(int i=0;i<state.size();i++){
			if(state.get(i) == 0){
				row = i/3;
				col = i%3;
			}
		}
		
		/// visit neighbours
		for(int i=-1;i<2;i++){
			for(int j=-1;j<2;j++){
				int a = row+i;
				int b = col+j;
				
				if(Math.abs(i) == Math.abs(j)){
					continue;
				}
				
				if(a<0 || b<0 || a==3 || b==3)continue;
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				
				for(int k=0;k<state.size();k++){
					tmp.add(state.get(k));
				}
				
				int swap = state.get(a*3+b);
				tmp.set(row*3+col, swap);
				tmp.set(a*3+b, 0);
				
				Node new_node = new Node(Node.getNumber(tmp));
				if(visited.contains(new_node.id)){
					continue;
				}
				
				s.push(new_node);
				visited.add(new_node.id);
	            parent.put(new_node.id,cur.id); 			
			}
		}
      }
		return false;
	}
}
