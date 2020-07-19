package controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.CreateEllipseCommand;
import view.interfaces.*;
import model.ShapeType;
import model.CreateRectangleCommand;
import java.awt.Point;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.persistence.CreateTriangleCommand;

public class MouseHandler extends MouseAdapter {
	public Point startPoint;
	public Point endPoint;
	private PaintCanvasBase paintCanvas;
	ShapeType shapeType;
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
	    // check user selection for shape and fill/draw
	    if(shapeType.toString().equals("RECTANGLE")) {
	       // Instantiate Command
	 	   command = new CreateRectangleCommand(paintCanvas);
		   command.run(e.getX(), e.getY(), (int)startPoint.getX(), (int)startPoint.getY());
       } else if  (shapeType.toString().equals("TRIANGLE")) {
			// Instantiate Command
			command = new CreateTriangleCommand(paintCanvas);
			command.run(e.getX(), e.getY(), (int)startPoint.getX(), (int)startPoint.getY());
	   } else if  (shapeType.toString().equals("ELLIPSE")) {
			// Instantiate Command
			command = new CreateEllipseCommand(paintCanvas);
			command.run(e.getX(), e.getY(), (int) startPoint.getX(), (int) startPoint.getY());
		} else {
			System.out.print("You have ran out of shapes!");
		}
   }
}
