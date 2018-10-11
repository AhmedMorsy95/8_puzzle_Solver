package application;
	
import java.lang.reflect.Array;
import java.net.Inet4Address;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			
			GridPane grid = new GridPane();
			
			grid.setMinSize(400, 400);
			grid.setPadding(new Insets(3,3,3,3));
			grid.setAlignment(Pos.CENTER);
		
			ArrayList<Integer> cur = generate_random();
			
			ArrayList<Label> numbers =  modify_labels(cur);
			
		    grid.setVgap(5); 
		    grid.setHgap(5);
		      
		    int cnt = 0;
		    
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					grid.add(numbers.get(cnt++),j,i);
				}
			}
			
			Scene scene = new Scene(grid , 400, 400);
		
			primaryStage.setScene(scene);
			primaryStage.show();
			dfs(cur);
			
			print_path();
				
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Label> modify_labels(ArrayList<Integer> x){
		ArrayList<Label> numbers = new ArrayList<Label>();
		
		for(int i=0;i<9;i++){
			numbers.add(new Label());
			numbers.get(i).setMinSize(50, 50);
			if(x.get(i) == 0){
			      numbers.get(i).setText("_");
			}
			else
		      numbers.get(i).setText(Integer.toString(x.get(i)));
		}

		return numbers;
	}
	
	ArrayList<Integer> generate_random(){  /// generates an arraylist with numbers from 0 -> 8 in random positions
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
	
	Integer getNumber(ArrayList<Integer> x){ /// given a list of numbers returns the number composed of adding all the number in the list together
		Integer ret = 0;
		for(int i=0;i<x.size();i++){
		    ret *= 10;
		    ret += x.get(i);
		}
		return ret;
	}

	ArrayList<Integer> getNumberList(int x){ /// given a number concatenates each digit in it and adds them into the list
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
	
	HashSet<Integer> visited = new HashSet<Integer>();
	HashMap<Integer,Integer> parent = new HashMap<Integer,Integer>();
	
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
	void dfs(ArrayList<Integer> state){  /// dfs implementation 
		
		Stack<Integer> s = new Stack<Integer>(); 
		
		s.push(getNumber(state));
		visited.add(getNumber(state));
		
		while(!s.empty()){
    	  
    	  int cur = s.peek();
          s.pop();
    	
          if(cur == 12345678){
    		  System.out.print("DOOOOOOOOONE");
    		  break;
    	  }
    	  
  		state = getNumberList(cur);
  		
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
				
				if(visited.contains(getNumber(tmp))){
					continue;
				}
				
				s.push(getNumber(tmp));
				visited.add(getNumber(tmp));
	            parent.put(getNumber(tmp),cur); 			
			}
		}
      }
	}
	
	
	HashSet<Integer> visitedbfs = new HashSet<Integer>();
	void bfs(ArrayList<Integer> state) {
		Queue<Integer> q=new LinkedList<Integer>();
		q.add(getNumber(state));
		System.out.println(getNumber(state));
		visitedbfs.add(getNumber(state));
		while(!q.isEmpty()) {
			int ourstate=q.peek();
			q.poll();
			if(ourstate==12345678)
			{
				System.out.println("DONE");
				return ;
			}
			state=getNumberList(ourstate);
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
			    if(!visitedbfs.contains(getNumber(tmp)))
			    {
			    	q.add(getNumber(tmp));
			    	visitedbfs.add(getNumber(tmp));
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
			    if(!visitedbfs.contains(getNumber(tmp)))
			    {
			    	q.add(getNumber(tmp));
			    	visitedbfs.add(getNumber(tmp));
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
			    if(!visitedbfs.contains(getNumber(tmp)))
			    {
			    	q.add(getNumber(tmp));
			    	visitedbfs.add(getNumber(tmp));
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
			    if(!visitedbfs.contains(getNumber(tmp)));
			    {
			    	q.add(getNumber(tmp));
			    	visitedbfs.add(getNumber(tmp));
			    }
			}
 
		}
 
	}
	
	
	public static void main(String[] args) {
		launch(args);
	    
	}
}
