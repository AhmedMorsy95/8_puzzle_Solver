package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	HashSet<Integer> visitedbfs = new HashSet<Integer>();
	HashMap<Integer,Integer> depth = new HashMap<Integer,Integer>();
	HashMap<Integer,String> parent = new HashMap<Integer,String>();
    HashMap<Integer,Integer> parent_id = new HashMap<Integer,Integer>();
	   	
	Node source;

    Path_Info save = new Path_Info();
	
    BFS(Node a){
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

	boolean bfs() {
 	   long start_time = System.nanoTime();
		ArrayList<Integer> state = Node.getNumberList(source.id);
		
		Queue<Node> q=new LinkedList<Node>();
		
		q.add(source);
		visitedbfs.add(source.id);
        
		depth.put(source.id, 0);
		while(!q.isEmpty()) {
			
			Node ourstate=q.peek();
			
			save.depth = Math.max(save.depth, get_G(ourstate.id));
            

			//System.out.println(ourstate.id);
			q.poll();
			if(ourstate.id == 12345678)
			{
 			   save.expansion = visitedbfs.size() - q.size();
 			   save.cost = get_G(ourstate.id);
 			   save.path = get_Path();
			   save.time = System.nanoTime() - start_time;
				return true;
			}
			
			state=Node.getNumberList(ourstate.id);
			
			int row = 0 , col = 0;
			for(int i=0;i<state.size();i++){
				if(state.get(i) == 0){
					row = i/3;
					col = i%3;
				}
			}
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			for(int k=0;k<state.size();k++){
				tmp.add(state.get(k));
			}
			if(row+1<3) {
				int index=state.get(row*3 + 3 +col);
			    tmp.set(row*3+3+col,0);
			    tmp.set(row*3+col, index);
			    
			    if(!visitedbfs.contains(Node.getNumber(tmp)))
			    {
			    	Node new_node =new Node(Node.getNumber(tmp));
			    	q.add(new_node);
			    	visitedbfs.add(new_node.id);
		            parent.put(new_node.id,"D");
		            parent_id.put(new_node.id,ourstate.id);
					depth.put(new_node.id, get_G(ourstate.id)+1);
			    }
			  
			}
			if(row-1>=0) {
				tmp.clear();
				for(int k=0;k<state.size();k++){
					tmp.add(state.get(k));
				}
				int index=state.get((row-1)*3 +col);
			    tmp.set((row-1)*3+col,0);
			    tmp.set(row*3+col, index);
			    if(visitedbfs.contains(Node.getNumber(tmp)) == false)
			    {
			    	Node new_node =new Node(Node.getNumber(tmp));
			    	q.add(new_node);
			    	visitedbfs.add(new_node.id);
		            parent.put(new_node.id,"U");
		            parent_id.put(new_node.id,ourstate.id); 	
					depth.put(new_node.id, get_G(ourstate.id)+1);		
			    }
 
			}
			if(col+1<3) {
				tmp.clear();
				for(int k=0;k<state.size();k++){
					tmp.add(state.get(k));
				}
				int index=state.get(row*3 +col+1);
			    tmp.set(row*3+col+1,0);
			    tmp.set(row*3+col, index);
			    if(!visitedbfs.contains(Node.getNumber(tmp)))
			    {
			    	Node new_node =new Node(Node.getNumber(tmp));
			    	q.add(new_node);
			    	visitedbfs.add(new_node.id);
		            parent.put(new_node.id,"R");
		            parent_id.put(new_node.id,ourstate.id); 	
					depth.put(new_node.id, get_G(ourstate.id)+1);		
			    }
			}
			if(col-1>=0) {
				tmp.clear();
				for(int k=0;k<state.size();k++){
					tmp.add(state.get(k));
				}
				int index=state.get(row*3 +col-1);
			    tmp.set(row*3+col-1,0);
			    tmp.set(row*3+col, index);
			    if(!visitedbfs.contains(Node.getNumber(tmp)))
			    {
			    	Node new_node =new Node(Node.getNumber(tmp));
			    	q.add(new_node);
			    	visitedbfs.add(new_node.id);
		            parent.put(new_node.id,"L");
		            parent_id.put(new_node.id,ourstate.id); 	
					depth.put(new_node.id, get_G(ourstate.id)+1);		
			    }
			}
 
		}
     return false;
	}
}
