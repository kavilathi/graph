package graph;

import java.util.HashMap;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Run extends Application {
	
	Pane containerPane ;
	HashMap<String,Element> allItem = new HashMap<>(); ;
    Element m;
    Element addedChild,lastChild;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		feedData();
		VBox root = new VBox();
	    containerPane = new Pane();
		TextArea searchTextArea = new TextArea();
		searchTextArea.setPrefHeight(20);
		Button searchButton = new Button("Search");
		searchButton.setOnAction(e->{
			for(Element m :allItem.values()) {
				if(m.name.equals(searchTextArea.getText())) {
					containerPane.getChildren().clear();
					this.m=m;
					rootLabel();
					getDescendent(this.m);	
				}
			}
		});
		root.getChildren().addAll(searchTextArea,searchButton,containerPane);
		Scene scene = new Scene(root,400,400);
		primaryStage.setScene(scene);
		primaryStage.show();			
	}
	

	 private Element getElement(String label) {
		 if(label==null) return null;
		 Element m = new Element(label); 
		 if(!allItem.containsKey(label)) {
			 allItem.put(label, m);
		 }
		 return m;
	 }
	 
	 private void feedData() {
		 Element mA = getElement("A.SOURCE");
		 Element mA1 = getElement("A.CHILD1");
		 mA.addChild(mA1);
		 Element mA11 = getElement("A.CHILD11");
		 mA1.addChild(mA11);
         Element mA2 = getElement("A.CHILD2");
         mA.addChild(mA2);
         Element mA3 = getElement("A.CHILD3");
         mA.addChild(mA3);	
         Element mA4 = getElement("A.CHILD4");
         mA.addChild(mA4);
         Element mA5 = getElement("A.CHILD5");
         mA.addChild(mA5);	
         Element mA51 = getElement("A.CHILD51");
         mA5.addChild(mA51);
         Element mA52 = getElement("A.CHILD52");
         mA5.addChild(mA52);	
/*         Element mA11 = getElement("A.CHILD1.CHILD1");
		 mA1.addChild(mA11);
         mA11.addParent(mA1);*/
//         Element mA111 = getElement("A.CHILD1.CHILD2");
//		 mA1.addChild(mA111);
//         mA111.addParent(mA1);

//        Element mA22 = getElement("A.CHILD2.CHILD1");
//         mA2.addChild(mA22);
//         mA22.addParent(mA2);
//        / Element mA23 = getElement("A.SECOND.SECOND.CHILD");
//         mA2.addChild(mA23);
//         mA23.addParent(mA2);

	 }
	 
	 
	 private void rootLabel() {
		containerPane.getChildren().clear();
		this.m.createLabel();
		this.m.getLabel().relocate(50, 20);
		containerPane.getChildren().add(this.m.getLabel());
	 }
	 

	 private void getDescendent(Element m) {			 
          for(Element child:m.childList) {
        	  child.createLabel();
        	  getCurrentChildPositionRelativeToParent(child);
        	  containerPane.getChildren().add(child.getLabel());
        	  addedChild=child;	  
        	  getDescendent(child);
          }

	 }


	 private void getCurrentChildPositionRelativeToParent(Element child) {	 

		   if(addedChild == null)
		   {
				child.getLabel().layoutXProperty().bind(this.m.getLabel().layoutXProperty().add(50));
				child.getLabel().layoutYProperty().bind(this.m.getLabel().layoutYProperty().add(30));	
		   }
		   else 
		   {
			   
			    if(addedChild.childList.size()>0) {
			    	child.getLabel().layoutXProperty().bind(addedChild.getLabel().layoutXProperty().add(50));
			    	child.getLabel().layoutYProperty().bind(addedChild.getLabel().layoutYProperty().add(30));
			    }else {
			    	child.getLabel().layoutXProperty().bind(addedChild.getLabel().layoutXProperty().subtract(50));
					child.getLabel().layoutYProperty().bind(addedChild.getLabel().layoutYProperty().add(30));
			    }
		   }	   
				
		}
	  
	 public static void main(String args[]){           
	      launch(args);      
	 } 
	 
}
