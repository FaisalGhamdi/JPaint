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
        seconderyShapeColor =  appState.getActiveSecondaryColor();
        width  = (int) (x - pointX);
        height =  (int) (y - pointY);
        // used to help in converting String to Color
        StyleSheet s = new StyleSheet();
        Color primaryColor = s.stringToColor(primaryShapeColor.toString());
        Color secondaryColor = s.stringToColor(seconderyShapeColor.toString());

        int xMin = Math.min(x, pointX);
        int xMax = Math.max(x, pointX);
        int yMin = Math.min(y, pointY);
        int yMax = Math.max(y, pointY);
        width  = (int) (x - pointX);
        height =  (int) (y - pointY);
        Graphics2D graphics2d = paintCanvas.getGraphics2D();

        // user selection for shade type
        if (shadingType.toString().equals("OUTLINE")) {
            graphics2d.setColor(primaryColor);
            graphics2d.draw(new Ellipse2D.Double(xMin, yMin, width, height));
        } else if (shadingType.toString().equals("FILLED_IN")) {
            graphics2d.setColor(primaryColor);
            graphics2d.fill(new Ellipse2D.Double(xMin, yMin, width, height));
        } else if (shadingType.toString().equals("OUTLINE_AND_FILLED_IN")) {
            // filled in
            graphics2d.setColor(primaryColor);
            graphics2d.fill(new Ellipse2D.Double(xMin, yMin, width, height));
            // outline
            graphics2d.setColor(secondaryColor);
            graphics2d.draw(new Ellipse2D.Double(xMin, yMin, width, height));
        }

    }
}
