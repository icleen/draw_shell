package iain.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Observable;

import cs355.GUIFunctions;
import cs355.model.drawing.Line;
import cs355.model.drawing.Shape;
import cs355.model.drawing.Triangle;
import cs355.view.ViewRefresher;
import iain.model.Model;
import iain.model.ShapeSizer;

public class View implements ViewRefresher {
	
	private static final int BORDER_SIZE = 5;
	private static final int LINE_SIZE = 1;
	private static final int ORIGIN = 0;
	
	public View() {
		Model.SINGLETON.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		GUIFunctions.refresh();
	}

	@Override
	public void refreshView(Graphics2D g2d) {
		List<Shape> shapes = Model.SINGLETON.getShapes();
		Shape.SHAPE_TYPE type = null;
		AffineTransform objToWorld = null;
		for (Shape s : shapes) {
			type = s.getShapeType();
			int width = (int) s.getWidth(), height = (int) s.getHeight();
			objToWorld = new AffineTransform();
			objToWorld.translate(s.getCenter().x - (s.getWidth()/2), s.getCenter().y - (s.getHeight()/2));
			objToWorld.rotate(s.getRotation());
			g2d.setTransform(objToWorld);
			g2d.setColor(s.getColor());
			switch (type) {
			case circle:
				g2d.fillOval(ORIGIN, ORIGIN, width, height);
				if (s.shapeSelected()) {
					g2d.setColor(Color.pink);
					g2d.setStroke(new BasicStroke(BORDER_SIZE));
					g2d.drawOval(ORIGIN, ORIGIN, width, height);
				}
				break;
			case ellipse:
				g2d.fillOval(ORIGIN, ORIGIN, width, height);
				if (s.shapeSelected()) {
					g2d.setColor(Color.pink);
					g2d.setStroke(new BasicStroke(BORDER_SIZE));
					g2d.drawOval(ORIGIN, ORIGIN, width, height);
				}
				break;
			case line:
				g2d.setStroke(new BasicStroke(LINE_SIZE));
				Point2D.Double end = ((Line) s).getEnd();
				g2d.drawLine(ORIGIN, ORIGIN, (int) end.x, (int) end.y);
				break;
			case rectangle:
				g2d.fillRect(ORIGIN, ORIGIN, width, height);
				if (s.shapeSelected()) {
					g2d.setColor(Color.pink);
					g2d.setStroke(new BasicStroke(BORDER_SIZE));
					g2d.drawRect(ORIGIN, ORIGIN, width, height);
				}
				break;
			case square:
				g2d.fillRect(ORIGIN, ORIGIN, width, height);
				if (s.shapeSelected()) {
					g2d.setColor(Color.pink);
					g2d.setStroke(new BasicStroke(BORDER_SIZE));
					g2d.drawRect(ORIGIN, ORIGIN, width, height);
				}
				break;
			case triangle:
				int[] xPoints = ((Triangle) s).getXCoordinates();
				int[] yPoints = ((Triangle) s).getYCoordinates();
				g2d.fillPolygon(xPoints, yPoints, Model.TOTAL_TRIANGLE_POINTS);
				if (s.shapeSelected()) {
					g2d.setColor(Color.pink);
					g2d.setStroke(new BasicStroke(3));
					g2d.drawPolygon(xPoints, yPoints, Model.TOTAL_TRIANGLE_POINTS);
				}
				break;
			default:
				break;
			}
		}
	}

}
