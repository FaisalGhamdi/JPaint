package model;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CreateEllipseCommand implements ICommand {
    private PaintCanvasBase paintCanvas;
    private int width = 0;
    private int height = 0;
    ShapeShadingType shadingType;
    ShapeColor primaryShapeColor;
    ShapeColor seconderyShapeColor;

    public CreateEllipseCommand(PaintCanvasBase paintCanvas) {
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
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(color);
        graphics2d.fill(new Ellipse2D.Double(xMin, yMin, width, height));
    }
}
