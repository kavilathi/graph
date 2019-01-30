package graph;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Element {
	
	public String name="";
	public ArrayList<Element> childList = new ArrayList<>() ;
	public ArrayList<Element> parentList = new ArrayList<>();
	private Label label;
	private StackPane group ;
	private Rectangle rectangle;
	private DoubleProperty x=new SimpleDoubleProperty();
	private DoubleProperty y=new SimpleDoubleProperty();
	
	public Element(String label) {
		this.name=label;
	}
	
	public void addChild(Element m) {
		if(m!=null && !childList.contains(m)) {
			childList.add(m);
		}
	}
	public void addParent(Element m) {
		if(m!=null && !parentList.contains(m)) {
			parentList.add(m);
		}
	}
	
	public void printLo(String offset) {
		System.out.println(offset+name);
		for(Element m1:childList) {
			m1.printLo("  | ");
		} 
	}
	public void printUp(String offset) {
		System.out.println(offset+name);
		for(Element m1:parentList) {
			m1.printUp("  | ");
		}
	}
	public int printLo() {
		
		int c = childList.size();
		for(Element m1:childList) {
			c = c + m1.childList.size();
			m1.printLo();
		}
		return c;
	}
	
	
	
	
	public  void createLabel() {
		group = new StackPane();
		rectangle = new Rectangle();
		rectangle.setFill(Color.DARKGREY);
		Button b = new Button("+");
		label = new Label(name);
		rectangle.widthProperty().bind(group.widthProperty());
		rectangle.heightProperty().bind(group.heightProperty());
		label.setTextFill(Color.BLUE);
		group.setPrefHeight(20);
		group.getChildren().addAll(rectangle,label);
	}
	public StackPane getLabel() {
		return group;
	}

	public void setLayoutXProperty(double value) {
		this.x.set(value);
	}
	public DoubleProperty getLayoutXProperty() {
		return this.x;
	}
	
	public void setLayoutYProperty(double value) {
		this.y.set(value);
	}
	public DoubleProperty getLayoutYProperty() {
		return this.y;
	}
	
}
