package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import javafx.util.Pair;

public class A_star_Manhattan {
       Node source;
       
       /// first cost so far then the node
       Comparator<my_Pair> smalles_sort = new Comparator<my_Pair>() {

		@Override
		public int compare(my_Pair o1, my_Pair o2) {
			return o1.cost-o2.cost;
		}
	   };
       PriorityQueue <my_Pair> q = new PriorityQueue<my_Pair>(smalles_sort);
       
   	   HashSet<Integer> visited = new HashSet<Integer>();
   	   HashMap<Integer,Integer> depth = new HashMap<Integer,Integer>();
   	   HashMap<Integer,String> parent = new HashMap<Integer,String>();
   	   HashMap<Integer,Integer> parent_id = new HashMap<Integer,Integer>();
	   
   	   Path_Info save = new Path_Info();
       
   	   public A_star_Manhattan(Node a) {
 	       save.cost = 0;
 	       save.depth = 0;
 	       save.path = "";
 	       save.expansion = 0;
   		   source = a;
	   }
       int get_Heuristics(int id){
    	   ArrayList<Integer> cur = Node.getNumberList(id);
    	   int cost = 0;
    	   for(int i=0;i<cur.size();i++){
    		     int pos = cur.get(i);
    		     int x = pos/3 , y = pos%3;
    		     int x2 = i/3 , y2 = i%3;
    		     cost += Math.abs(x2-x)+ Math.abs(y2-y);
    	   }
    	   return cost;
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
       void GO(){
    	   long start_time = System.nanoTime();
    	   visited.add(source.id);
    	   q.add(new my_Pair(source.id,0));
    	   depth.put(source.id, 0);
    	   while(q.size() > 0){
    		   my_Pair cur = q.peek();
    		   q.poll();
    	  	   int current_node = cur.id;
    		   if(current_node == 12345678){
    			   save.expansion = visited.size() - q.size();
    			   save.cost = get_G(current_node);
    			   save.path = get_Path();
    			   save.time = System.nanoTime() - start_time;
    			   //System.out.print("A*_Euclidean reach with depth " + get_G(current_node));
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
    					q.add(new my_Pair(new_node.id,get_Heuristics(new_node.id) + get_G(current_node) +1));
    					save.depth = Math.max(save.depth, get_G(current_node)+1);
    				    parent_id.put(new_node.id, current_node);
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
    	   
       }
       
       
}
/*
1- A*
2- expansion
3- GUI
4-runtime
5-depth
6-path to goal*/