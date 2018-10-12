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
		    
			ArrayList<Integer> cur = (Node.generate_random());
			
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
			
			System.out.println(Node.getNumber(cur));
			DFS go = new DFS(new Node(Node.getNumber(cur)));
			if(go.dfs()){
				System.out.println("DFS reached in " + go.get_path_length() + " steps");
			}
			else{
				System.out.println("this grid has no solution");
			}
			
			BFS go2 = new BFS(new Node(Node.getNumber(cur)));
			if(go2.bfs()){
				//go2.print_path();
				System.out.println("BFS reached in " + go2.get_path_length() + " steps");
			}
			
				
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
	
	public static void main(String[] args) {
		launch(args);
	    
	}
}
