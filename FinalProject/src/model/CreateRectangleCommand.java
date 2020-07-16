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
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;

public class CreateRectangleCommand implements ICommand{

	private PaintCanvasBase paintCanvas;
	private int width = 0;
	private int height = 0;
	
	public CreateRectangleCommand(PaintCanvasBase paintCanvas) {
		this.paintCanvas = paintCanvas;
		}
	@Override
	public void run(int x, int y, int pointX, int pointY) {
	    width  = (int) (x - pointX);
	    height =  (int) (y - pointY);
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(Color.GREEN);
        graphics2d.fillRect(pointX, pointY, (int)width, (int)height);
	}
}
