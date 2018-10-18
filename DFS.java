package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class DFS {
	HashSet<Integer> visited = new HashSet<Integer>();

	HashMap<Integer,Integer> depth = new HashMap<Integer,Integer>();
	HashMap<Integer,String> parent = new HashMap<Integer,String>();
    HashMap<Integer,Integer> parent_id = new HashMap<Integer,Integer>();
    
	Node source;

	Path_Info save = new Path_Info();

	DFS(Node a){
		   source = a;
		   save.cost = 0;
	       save.depth = 0;
	       save.path = "";
	       save.expansion = 0;
	}
	
    int get_G(int id){
 	  if(depth.containsKey(id)) 
 	    return depth.get(id);
 	  return 0;
    }
    
    String get_Path(){
       String ret = "";  
 	   int cur = 12345678;
 	   while(parent.containsKey(cur)){
 	       ret += parent.get(cur);
 	       cur = parent_id.get(cur);
 	   }
        StringBuilder x = new StringBuilder(); 
        x.append(ret);
        x.reverse();
       return x.toString();
    }

	/// notes sometimes it doesnot reach a solution
	
	    boolean dfs(){  /// dfs implementation 
	    long start_time = System.nanoTime();
		
		Stack<Node> s = new Stack<Node>(); 
		
		s.push(source);
		visited.add(source.id);
	    depth.put(source.id, 0);
	    
		while(!s.empty()){
    	  
    	  Node cur = s.peek();
          s.pop();
          
          
        //  System.out.println(cur.id);
  		save.depth = Math.max(save.depth, get_G(cur.id));

          if(cur.id == 12345678){
			   save.expansion = visited.size() - s.size();
			   save.cost = get_G(cur.id);
			   save.path = get_Path();
			   save.time = System.nanoTime() - start_time;
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
				parent_id.put(new_node.id, cur.id);
				depth.put(new_node.id, get_G(cur.id)+1);
				    if(i == -1){
			    	  parent.put(new_node.id, "U");
				    }
				    if(i == 1){
			    	  parent.put(new_node.id, "U");
				    }
				    if(j == -1){
			    	  parent.put(new_node.id, "L");
			        }
			        if(j == 1){
			    	  parent.put(new_node.id, "R");
			        }
				
			}
		}
      }
		return false;
	}
}
