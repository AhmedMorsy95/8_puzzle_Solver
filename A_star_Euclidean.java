package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javafx.util.Pair;

public class A_star_Euclidean {
       Node source;
       public A_star_Euclidean(Node a) {
    	   source = a;
	   }
       /// first cost so far then the node
       HashSet<Pair<Integer, Integer> > set = new HashSet<Pair<Integer,Integer> >();
   	   HashSet<Integer> visited = new HashSet<Integer>();
   	   HashMap<Integer,Integer> depth = new HashMap<Integer,Integer>();
       
       int get_Heuristics(int id){
    	   ArrayList<Integer> cur = Node.getNumberList(id);
    	   int cost = 0;
    	   for(int i=0;i<cur.size();i++){
    		     int pos = cur.get(i);
    		     int x = pos/3 , y = pos%3;
    		     int x2 = i/3 , y2 = i%3;
    		     cost += (x2-x)*(x2-x) + (y2-y)*(y2-y);
    	   }
    	   return cost;
       }
       int get_G(int id){
    	    return depth.get(id);
       }
       void GO(){
    	   visited.add(source.id);
    	   set.add(new Pair<Integer, Integer>(0,source.id));
    	   depth.put(source.id, 0);
    	   while(set.size() > 0){
    		   Pair<Integer, Integer> cur = set.iterator().next();
    		   set.remove(cur);
    	  	   
    		   int current_node = cur.getValue();
    		   if(current_node == 12345678){
    			   System.out.print("A*_Euclidean reach with depth " + get_G(current_node));
    			   break;
    		   }
    		   ArrayList<Integer> state = Node.getNumberList(current_node);
    	  	   	
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
    					
    					visited.add(new_node.id);
    					depth.put(new_node.id, get_G(current_node) + 1);
    					/// get my heuristics + cost so far + 1
    					set.add(new Pair<Integer, Integer>(get_Heuristics(new_node.id) + get_G(current_node) +1, new_node.id));
    				}
    			}
    		   
    	   }
    	   
       }
       
       
}
