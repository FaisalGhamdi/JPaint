package model.persistence;

import model.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class CreateTriangleCommand implements ICommand {

    private PaintCanvasBase paintCanvas;
    private int width = 0;
    private int height = 0;

    public CreateTriangleCommand(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
    }
    @Override
    public void run(int x, int y, int pointX, int pointY) {
        //TODO: draw/fill a triangle

        int xMin = Math.min(x, pointX);
        int xMax = Math.max(x, pointX);
        int yMin = Math.min(y, pointY);
        int yMax = Math.max(y, pointY);
        width  = (int) (x - pointX);
        height =  (int) (y - pointY);

        int triangleMidPoint = xMin + (width / 2);

        int[] xCoordinates = {xMin, triangleMidPoint, xMax};
        System.out.println("=====================");
        System.out.println("xMin: " + xMin + " triangleMidPoint: " + triangleMidPoint + " xMax: " + xMax);
        int[] yCoordinates = {yMin, xMax, yMin};
        System.out.println("yMin: " + yMin + " xMax: " + xMax + " yMin: " + yMin);
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(Color.GREEN);
        graphics2d.fillPolygon(xCoordinates, yCoordinates, 3);

    }
}
