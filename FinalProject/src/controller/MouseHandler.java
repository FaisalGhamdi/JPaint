package controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.*;
import view.interfaces.*;

import java.awt.Point;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.persistence.CreateTriangleCommand;

public class MouseHandler extends MouseAdapter {
	public Point startPoint;
	public Point endPoint;
	private PaintCanvasBase paintCanvas;
	ShapeType shapeType;
	ShapeShadingType shadingType;
	ShapeColor primaryShapeColor;
	ShapeColor seconderyShapeColor;
	IApplicationState appState;
	ICommand command;
	public MouseHandler(IApplicationState appState, PaintCanvasBase paintCanvas) {
		this.paintCanvas = paintCanvas;
		this.appState =  appState;	
	}
   @Override
   public void mousePressed (MouseEvent e) {
		startPoint = new Point(e.getX(), e.getY());
   }
   @Override
   public void mouseReleased (MouseEvent e) {
		// get user selection for shape
	    shapeType = appState.getActiveShapeType();
	    shadingType = appState.getActiveShapeShadingType();
	    primaryShapeColor = appState.getActivePrimaryColor();
	    seconderyShapeColor = appState.getActiveSecondaryColor();

	    // test
	   		//System.out.print(shadingType);
	   //
	    // check user selection for shape and fill/draw
	    if(shapeType.toString().equals("RECTANGLE")) {
	       // Instantiate Command
	 	   command = new CreateRectangleCommand(paintCanvas);
		   command.run(e.getX(), e.getY(), (int)startPoint.getX(), (int)startPoint.getY(), appState);
       } else if  (shapeType.toString().equals("TRIANGLE")) {
			// Instantiate Command
			command = new CreateTriangleCommand(paintCanvas);
			command.run(e.getX(), e.getY(), (int)startPoint.getX(), (int)startPoint.getY(), appState);
	   } else if  (shapeType.toString().equals("ELLIPSE")) {
			// Instantiate Command
			command = new CreateEllipseCommand(paintCanvas);
			command.run(e.getX(), e.getY(), (int) startPoint.getX(), (int) startPoint.getY(), appState);
		} else {
			System.out.print("You have ran out of shapes!");
		}
   }
}
