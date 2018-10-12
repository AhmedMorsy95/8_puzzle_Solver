package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	HashSet<Integer> visitedbfs = new HashSet<Integer>();
	
	Node source;
	
	BFS(Node a){
		source = a;
	}
	void bfs() {
		ArrayList<Integer> state = Node.getNumberList(source.id);
		
		Queue<Node> q=new LinkedList<Node>();
		
		q.add(source);
		visitedbfs.add(source.id);
		
		while(!q.isEmpty()) {
			
			Node ourstate=q.peek();
			//System.out.println(ourstate.id);
			q.poll();
			if(ourstate.id == 12345678)
			{
				System.out.println("DOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONE");
				return ;
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
			    if(!visitedbfs.contains(Node.getNumber(tmp)))
			    {
			    	Node new_node =new Node(Node.getNumber(tmp));
			    	q.add(new_node);
			    	visitedbfs.add(new_node.id);
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
			    if(!visitedbfs.contains(Node.getNumber(tmp)));
			    {
			    	Node new_node =new Node(Node.getNumber(tmp));
			    	q.add(new_node);
			    	visitedbfs.add(new_node.id);
			    }
			}
 
		}
 
	}
}
