package model.persistence;
import model.ShapeColor;
import model.ShapeShadingType;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;
import javax.swing.text.html.StyleSheet;
import java.awt.*;

public class CreateTriangleCommand implements ICommand {

    private PaintCanvasBase paintCanvas;
    private int width = 0;
    private int height = 0;
    IApplicationState appState;
    Graphics2D graphics2d;
    ShapeShadingType shadingType;
    ShapeColor primaryShapeColor;
    ShapeColor seconderyShapeColor;

    public CreateTriangleCommand(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;

    }
    @Override
    public void run(int x, int y, int pointX, int pointY, IApplicationState appState) {

        // get user selection
        shadingType = appState.getActiveShapeShadingType();
        primaryShapeColor = appState.getActivePrimaryColor();
        width  = (int) (x - pointX);
        height =  (int) (y - pointY);
        // used to help in converting String to Color
        StyleSheet s = new StyleSheet();
        Color color = s.stringToColor(primaryShapeColor.toString());

        int xMin = Math.min(x, pointX);
        int xMax = Math.max(x, pointX);
        int yMin = Math.min(y, pointY);
        int yMax = Math.max(y, pointY);
        width  = (int) (x - pointX);
        height =  (int) (y - pointY);
        int triangleMidPoint = xMin + (width / 2);

        int[] xCoordinates = {xMin, triangleMidPoint, xMax};
        System.out.println("xMin: " + xMin + " triangleMidPoint: " + triangleMidPoint + " xMax: " + xMax);
        int[] yCoordinates = {yMin, xMax, yMin};
        System.out.println("yMin: " + yMin + " xMax: " + xMax + " yMin: " + yMin);
        graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(color);
        graphics2d.fillPolygon(xCoordinates, yCoordinates, 3);

    }
}
