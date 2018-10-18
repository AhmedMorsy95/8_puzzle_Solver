package application;
 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
 
public class Main extends Application {
 
    ArrayList<String>s=new ArrayList<String>();
	ArrayList<Integer> cur = new ArrayList<Integer>();
	ArrayList<Button> output=new ArrayList<Button>();
	int it=0;
	String pathans ;
 
	@Override
	public void start(Stage primaryStage) {
		///BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
 
		try {
		BorderPane border = new BorderPane();	
		border.setMinSize(400, 400);
		border.setPadding(new Insets(3,3,3,3));
		GridPane grid=new GridPane();
		grid.setMinSize(200, 200);
		grid.setPadding(new Insets(3,3,3,3));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(5); 
	    grid.setHgap(5);
	    Button Next=new Button("Next");
	    Next.setTextFill(Color.BURLYWOOD);
	    Next.setStyle("-fx-border-color:white;-fx-background-color:black");
	    VBox gridd=new VBox();
	    gridd.setVisible(false);
	    gridd.getChildren().addAll(grid,Next);
		Label label1 = new Label("YourInput:");
		Button Enter=new Button("ENTER");
		Button BFSButton=new Button("Solve using BFS");
		Button DFSButton=new Button("Solve using DFS");
		Button AStar1Button=new Button("Solve using A* eclidean");
		Button AStar2Button=new Button("Solve using A* manthattan");
		TextField resultexpansion=new TextField();
		Label depth=new Label("Solution's depth :");
		Label cost=new Label("Solution's cost :");
		Label path=new Label("Solution's path :");
		Label expansion=new Label("Expansion Nodes :");
		Label time=new Label("Solution's Time :");
		TextField resultdepth=new TextField();
		TextField resultcost=new TextField();
		TextField resultpath=new TextField();
		TextField resulttime=new TextField();
		resultdepth.setMinSize(200,10);
		resultpath.setMinSize(200,10);
		resultcost.setMinSize(200,10);
		resulttime.setMinSize(200,10);
		resultexpansion.setMinSize(200,10);
		label1.setTextFill(Color.BURLYWOOD);
		time.setTextFill(Color.BURLYWOOD);
		cost.setTextFill(Color.BURLYWOOD);
		expansion.setTextFill(Color.BURLYWOOD);
		path.setTextFill(Color.BURLYWOOD);
		depth.setTextFill(Color.BURLYWOOD);
		label1.setStyle("-fx-bordern-color:Green;");
		TextField textField = new TextField ("  ");
		textField.setMinWidth(40);
 
 
		//Creating the mouse event handler 
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
			@Override 
		   public void handle(MouseEvent e) { 
				it=0;
			   s.clear();
			   output.clear();
			   cur.clear();
		       s.add(textField.getText());
		       set_Current();
		       for(int i=0;i<9;i++) {
		    	   output.add(new Button());
		    	   output.get(i).setMinSize(50, 50);
		    	   output.get(i).setStyle("-fx-border-color:black;-fx-background-color:BURLYWOOD");
		    	   if(cur.get(i) == 0){
					      output.get(i).setText("_");
					}
					else
				      output.get(i).setText(Integer.toString(cur.get(i)));
		    	   output.get(i).setTextFill(Color.BLACK);
 
		       }
		       int cnt=0;
		       for(int i=0;i<3;i++){
					for(int j=0;j<3;j++){
						grid.add(output.get(cnt++),j,i);
						////System.out.println("A");
					}
				}
		       grid.setVisible(true);
		       gridd.setVisible(true);
		   }  
		};
 
		EventHandler<MouseEvent> eventHandlerBFS = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) { 
 
				    BFS x = new BFS(new Node(Node.getNumber(cur)));
					x.bfs();
					resultpath.setText(x.save.path);
					resultcost.setText(Integer.toString(x.save.cost));
				    resultexpansion.setText(Integer.toString(x.save.expansion));
				    resultdepth.setText(Integer.toString(x.save.depth));
				    resulttime.setText(Long.toString(x.save.time) + " nano seconds");
				    pathans = x.save.path;
				    
				    ArrayList<String> send = new ArrayList<String>();
				    
                    send.add("result cost : " + Integer.toString(x.save.cost));
                    send.add("result expansion : " + Integer.toString(x.save.expansion));
                    send.add( "result depth : " + Integer.toString(x.save.depth) );
                    send.add( "result time :" + Long.toString(x.save.time) + " nano seconds" );
                    send.add("result path : " + x.save.path ) ;
			        try {
						print_file(send);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
 
			   }  
			};
		EventHandler<MouseEvent> eventHandlerDFS = new EventHandler<MouseEvent>() { 
				   @Override 
				   public void handle(MouseEvent e) { 
					   
					    DFS x = new DFS(new Node(Node.getNumber(cur)));
						x.dfs();
						resultpath.setText(x.save.path);
						resultcost.setText(Integer.toString(x.save.cost));
					    resultexpansion.setText(Integer.toString(x.save.expansion));
					    resultdepth.setText(Integer.toString(x.save.depth));
					    resulttime.setText(Long.toString(x.save.time) + " nano seconds");
					    pathans = x.save.path;	
					    ArrayList<String> send = new ArrayList<String>();
					    
	                    send.add("result cost : " + Integer.toString(x.save.cost));
	                    send.add("result expansion : " + Integer.toString(x.save.expansion));
	                    send.add( "result depth : " + Integer.toString(x.save.depth) );
	                    send.add( "result time :" + Long.toString(x.save.time) + " nano seconds" );
	                    send.add("result path : " + x.save.path ) ;
				        try {
							print_file(send);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				  }  
		};
		EventHandler<MouseEvent> NextHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) { 
				   int pos=0;
				   for(int i=0;i<9;i++) {
					  String ans= output.get(i).getText();
					  if(ans=="_") {
						  pos=i;
						  break;
					  }
				   }
				   if(it<pathans.length()) {
					  String tmp=output.get(pos).getText();
					  if(pathans.charAt(it)=='L') {
						 output.get(pos).setText(output.get(pos-1).getText());
						 output.get(pos-1).setText(tmp);
					  }
					  else if(pathans.charAt(it)=='R') {
						  output.get(pos).setText(output.get(pos+1).getText());
							 output.get(pos+1).setText(tmp);
					  }
					  else if(pathans.charAt(it)=='U') {
						  output.get(pos).setText(output.get(pos-3).getText());
							 output.get(pos-3).setText(tmp);
					  }
					  else
					  {
						  output.get(pos).setText(output.get(pos+3).getText());
							 output.get(pos+3).setText(tmp);
					  }
				   }
				   it++;
 
 
			  }  
	};
		EventHandler<MouseEvent> eventHandlerAStar1 = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) { 
				   
				    A_star_Euclidean x = new A_star_Euclidean(new Node(Node.getNumber(cur)));
					x.GO();
					resultpath.setText(x.save.path);
					resultcost.setText(Integer.toString(x.save.cost));
				    resultexpansion.setText(Integer.toString(x.save.expansion));
				    resultdepth.setText(Integer.toString(x.save.depth));
				    resulttime.setText(Long.toString(x.save.time) + " nano seconds");
				    pathans = x.save.path;
				    ArrayList<String> send = new ArrayList<String>();
				    
                    send.add("result cost : " + Integer.toString(x.save.cost));
                    send.add("result expansion : " + Integer.toString(x.save.expansion));
                    send.add( "result depth : " + Integer.toString(x.save.depth) );
                    send.add( "result time :" + Long.toString(x.save.time) + " nano seconds" );
                    send.add("result path : " + x.save.path ) ;
			        try {
						print_file(send);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			   }  
	};
	EventHandler<MouseEvent> eventHandlerAStarmanhattan = new EventHandler<MouseEvent>() { 
		   @Override 
		   public void handle(MouseEvent e) { 
 
			    A_star_Manhattan x = new A_star_Manhattan(new Node(Node.getNumber(cur)));
				x.GO();
				resultpath.setText(x.save.path);
				resultcost.setText(Integer.toString(x.save.cost));
			    resultexpansion.setText(Integer.toString(x.save.expansion));
			    resultdepth.setText(Integer.toString(x.save.depth));
			    resulttime.setText(Long.toString(x.save.time) + " nano seconds");
			    pathans = x.save.path;
			    
			    ArrayList<String> send = new ArrayList<String>();
			    
                send.add("result cost : " + Integer.toString(x.save.cost));
                send.add("result expansion : " + Integer.toString(x.save.expansion));
                send.add( "result depth : " + Integer.toString(x.save.depth) );
                send.add( "result time :" + Long.toString(x.save.time) + " nano seconds" );
                send.add("result path : " + x.save.path ) ;
		        try {
					print_file(send);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		  }  
};
 
		Enter.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
		Next.addEventFilter(MouseEvent.MOUSE_CLICKED, NextHandler);
		BFSButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerBFS);
		DFSButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerDFS);
		AStar1Button.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerAStar1);
		AStar2Button.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerAStarmanhattan);
		DFSButton.setMinWidth(200);
		BFSButton.setMinWidth(200);
		AStar1Button.setMinWidth(200);
		AStar2Button.setMinWidth(200);
		DFSButton.setTextFill(Color.BROWN);
		BFSButton.setTextFill(Color.BROWN);
		AStar1Button.setTextFill(Color.BROWN);
		AStar2Button.setTextFill(Color.BROWN);
		DFSButton.setStyle("-fx-border-color:black;-fx-background-color:black");
		BFSButton.setStyle("-fx-border-color:black;-fx-background-color:black");
		AStar1Button.setStyle("-fx-border-color:black;-fx-background-color:black");
		AStar2Button.setStyle("-fx-border-color:black;-fx-background-color:black");
		border.setPadding(new Insets(10,10,10, 10));
		HBox hb = new HBox();
		VBox vb =new VBox();
		VBox result1=new VBox();
		VBox result2=new VBox();
		HBox hb2=new HBox();
		result1.getChildren().addAll(expansion,depth,cost,path,time);
		result2.getChildren().addAll(resultexpansion,resultdepth,resultcost,resultpath,resulttime);
		hb.setSpacing(10);
		result1.setSpacing(30);
		result2.setSpacing(20);
		hb2.getChildren().addAll(result1,result2);
		hb.getChildren().addAll(label1, textField,Enter);
		vb.setCenterShape(true);
		vb.setSpacing(10);
		vb.getChildren().addAll(hb,BFSButton,DFSButton,AStar1Button,AStar2Button);
		border.setTop(vb);
		border.setRight(gridd);
	    border.setBottom(hb2);
	//	border.setRight(result);
	    grid.setStyle("-fx-border-color:black;-fx-background-color:maroon");
		border.setStyle("-fx-border-color:black;-fx-background-color:maroon");
	    Scene scene = new Scene(border , 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		//Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        //primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
       // primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void set_Current(){
	       cur = FromStringToList(s.get(0));
	}
	public void print_file(ArrayList<String>s) throws FileNotFoundException, UnsupportedEncodingException{
	      System.out.print("YES");
		   PrintWriter writer = new PrintWriter("info.txt", "UTF-8");
	       for(int i=0;i<s.size();i++)
	       writer.println(s.get(i));
	       writer.close();
	}
 
	public ArrayList<Integer> FromStringToList(String s){
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		String [] splitting= s.split(",");
		for(int i=0;i<9;i++) {
			numbers.add(Integer.parseInt(splitting[i]));
		}
		return numbers;
	}
 
	public static void main(String[] args) {
 
		launch(args);	    
	}
}
 


/*package application;
	
import java.awt.Button;
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
		    
			///   120345678       
			ArrayList<Integer> cur = (Node.getNumberList(864213570));
			
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
			
			DFS go = new DFS(new Node(Node.getNumber(cur)));
		    go.dfs();
		    System.out.print("expansion : " + go.save.expansion + "\nSearch Depth : " + go.save.depth + "\nCost : " + go.save.cost + "\nHere is the path : " + go.save.path );	
		    
			System.out.print("\n\n");
					
			BFS go2 = new BFS(new Node(Node.getNumber(cur)));
			go2.bfs();
            System.out.print("expansion : " + go2.save.expansion + "\nSearch Depth : " + go2.save.depth + "\nCost : " + go2.save.cost + "\nHere is the path : " + go2.save.path );	
		    
			System.out.print("\n\n");
			
			A_star_Euclidean go3 = new A_star_Euclidean(new Node(Node.getNumber(cur)));
			go3.GO();
			System.out.print("expansion : " + go3.save.expansion + "\nSearch Depth : " + go3.save.depth + "\nCost : " + go3.save.cost + "\nHere is the path : " + go3.save.path );	
		    
			System.out.print("\n\n");
			
			A_star_Manhattan go4 = new A_star_Manhattan(new Node(Node.getNumber(cur)));
			go4.GO();
			System.out.print("expansion : " + go4.save.expansion + "\nSearch Depth : " + go4.save.depth + "\nCost : " + go4.save.cost + "\nHere is the path : " + go4.save.path );	
		   
			
		
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
*/