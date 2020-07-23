package model;
import java.awt.Graphics2D;
import model.interfaces.*;
import model.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import view.interfaces.*;
import model.ShapeType;
import model.CreateRectangleCommand;
import model.Rectangle;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import javax.swing.text.html.StyleSheet;
public class CreateRectangleCommand implements ICommand{

	private PaintCanvasBase paintCanvas;
	private int width = 0;
	private int height = 0;
	ShapeShadingType shadingType;
	ShapeColor primaryShapeColor;
	ShapeColor seconderyShapeColor;
	
	public CreateRectangleCommand(PaintCanvasBase paintCanvas) {
		this.paintCanvas = paintCanvas;
		}
	@Override
	public void run(int x, int y, int pointX, int pointY, IApplicationState appState) {


		// get user selection
		shadingType = appState.getActiveShapeShadingType();
		primaryShapeColor = appState.getActivePrimaryColor();
		seconderyShapeColor = appState.getActiveSecondaryColor();
	    width  = (int) (x - pointX);
	    height =  (int) (y - pointY);
        // used to help in converting String to Color
        StyleSheet s = new StyleSheet();
		Color primaryColor = s.stringToColor(primaryShapeColor.toString());
		Color secondaryColor = s.stringToColor(seconderyShapeColor.toString());
		// draw/fill
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(primaryColor);


		// user selection for shade type
		if (shadingType.toString().equals("OUTLINE")) {
			graphics2d.setColor(primaryColor);
			graphics2d.drawRect(pointX, pointY, (int)width, (int)height);
		} else if (shadingType.toString().equals("FILLED_IN")) {
			graphics2d.setColor(primaryColor);
			graphics2d.fillRect(pointX, pointY, (int)width, (int)height);
		} else if (shadingType.toString().equals("OUTLINE_AND_FILLED_IN")) {
			// filled in
			graphics2d.setColor(primaryColor);
			graphics2d.fillRect(pointX, pointY, (int)width, (int)height);
			// outline
			graphics2d.setColor(secondaryColor);
			graphics2d.drawRect(pointX, pointY, (int)width, (int)height);

		}
	}
}
