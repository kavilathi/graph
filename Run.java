package graph;

import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class Run extends Application {
	
	int[] y = {50};
	int childYOff = 30;
	Pane containerPane ;
	HashMap<String,Murex> allItem = new HashMap<>(); ;
	@Override
	public void start(Stage primaryStage) throws Exception {
		feedData();
		VBox root = new VBox();
	    containerPane = new Pane();
		TextArea searchTextArea = new TextArea();
		searchTextArea.setPrefHeight(20);
		Button searchButton = new Button("Search");
		searchButton.setOnAction(e->{
			for(Murex m :allItem.values()) {
				if(m.name.contains(searchTextArea.getText())) {
					containerPane.getChildren().clear();
					initXY();
					m.createView();
					m.getView().setLayoutX(setX());
					m.getView().setLayoutY(setY());
					containerPane.getChildren().add(m.getView());
					getDescendent(m);
				}
			}
		});
		root.getChildren().addAll(searchTextArea,searchButton,containerPane);
		Scene scene = new Scene(root,400,400);
		primaryStage.setScene(scene);
		primaryStage.show();
				
	}
	public void initXY() {
		 y[0] = 50;
	    childYOff = 30;
	}
	
	public int setX() {
		return 50;
	}
	public int setY() {
		return y[0];
	}
	
	 public static void main(String args[]){           
	      launch(args);      
	   } 

	 public Murex getMurex(String label) {
		 if(label==null) return null;
		 Murex m = new Murex(label);
		 if(!allItem.containsKey(label)) {
			 allItem.put(label, m);
		 }
		 return m;
	 }
	 
	 public void feedData() {
		 String[] A = {"A.FIRST.CHILD","A.SECOND.CHILD","A.THIRD.CHILD"};
		 Murex mA = getMurex("A.SOURCE");
		 for(String s:A) {
            Murex mA1 = getMurex(s);
            mA.addLowerItem(mA1);
            mA1.addUpperItem(mA);
		}
		 String[] B = {"B.FIRST.CHILD","B.SECOND.CHILD","B.THIRD.CHILD"};
		 Murex mB = getMurex("B.SOURCE");
		 for(String s:B) {
            Murex mB1 = getMurex(s);
            mB.addLowerItem(mB1);
            mB1.addUpperItem(mB);
		}	
		 
		 String[] C = {"C.FIRST.CHILD","C.SECOND.CHILD","C.THIRD.CHILD"};
		 Murex mC = getMurex("C.SOURCE");
		 for(String s:C) {
            Murex mC1 = getMurex(s);
            mC.addLowerItem(mC1);
            mC1.addUpperItem(mC);
		}
				 
	 }
	 
	 public void getDescendent(Murex m) {
		 for(Murex m1:m.loItem) {
			 m1.createView();
			 m1.getView().setLayoutX(setX()+20);
			 m1.getView().setLayoutY(setY()+childYOff);		 
			 System.out.println( m1.getView().getLayoutY());
			 Line l = new Line();
			 l.startXProperty().bind(m.getView().layoutXProperty());
			 l.endXProperty().bind(m1.getView().layoutXProperty());
			 containerPane.getChildren().addAll(m1.getView(),l);
			 childYOff += 30;
			 getDescendent(m1);
		 }
	 }
}
