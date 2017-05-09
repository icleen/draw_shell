package cs355.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;

import cs355.GUIFunctions;
import cs355.model.drawing.Circle;
import cs355.model.drawing.Ellipse;
import cs355.model.drawing.Line;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;
import cs355.model.drawing.Square;
import cs355.model.drawing.Triangle;
import iain.model.Model;
import iain.model.SaveStructure;
import iain.model.ShapeSizer;

public class Controller implements CS355Controller {
	
	private static Gson gson = new Gson();
	
	private enum STATES {
			none, circle, ellipse, line, rectangle, square, triangle
	};
	private STATES currentState;
	private Color currentColor;
	private int currentIndex;

	private List<Point2D.Double> trianglePoints;
	private Shape currentShape;
	private Point2D.Double start;
	
	public Controller() {
		currentState = STATES.none;
		currentColor = Color.WHITE;
//		GUIFunctions.changeSelectedColor(currentColor);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (currentState == STATES.triangle) {
			Point2D.Double p = new Point2D.Double();
			p.setLocation(arg0.getX(), arg0.getY());
			trianglePoints.add(p);
			if (trianglePoints.size() == Model.TRIANGLE_POINTS) {
				Shape triangle = new Triangle(currentColor, trianglePoints.get(0), trianglePoints.get(1), trianglePoints.get(2));
				Model.SINGLETON.addShape(triangle);
				trianglePoints.clear();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		start = new Point2D.Double();
		start.setLocation(arg0.getX(), arg0.getY());
		switch (currentState) {
		case circle:
			currentShape = new Circle(currentColor, start, 0);
			break;
		case ellipse:
			currentShape = new Ellipse(currentColor, start, 0, 0);
			break;
		case line:
			currentShape = new Line(currentColor, start, start);
			break;
		case rectangle:
			currentShape = new Rectangle(currentColor, start, 0, 0);
			break;
		case square:
			currentShape = new Square(currentColor, start, 0);
			break;
		default:
			break;
		}
		if (currentShape != null) {
			currentIndex = Model.SINGLETON.addShape(currentShape);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		start = null;
		currentShape = null;
		currentIndex = -1;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		Point2D.Double point = new Point2D.Double();
		point.setLocation(arg0.getX(), arg0.getY());
		switch (currentState) {
		case circle:
			ShapeSizer.inst().setCircle((Circle) currentShape, start, point);
			break;
		case ellipse:
			ShapeSizer.inst().setEllipse((Ellipse) currentShape, start, point);
			break;
		case line:
			((Line) currentShape).setEnd(point);
			break;
		case rectangle:
			ShapeSizer.inst().setRectangle((Rectangle) currentShape, start, point);
			break;
		case square:
			ShapeSizer.inst().setSquare((Square) currentShape, start, point);
			break;
		default:
			break;
		}
		if (currentShape != null) {
			Model.SINGLETON.deleteShape(currentIndex);
			currentIndex = Model.SINGLETON.addShape(currentShape);
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	@Override
	public void colorButtonHit(Color c) {
		currentColor = c;
		GUIFunctions.changeSelectedColor(c);
	}

	@Override
	public void lineButtonHit() {
		currentState = STATES.line;
	}

	@Override
	public void squareButtonHit() {
		currentState = STATES.square;
	}

	@Override
	public void rectangleButtonHit() {
		currentState = STATES.rectangle;
	}

	@Override
	public void circleButtonHit() {
		currentState = STATES.circle;
	}

	@Override
	public void ellipseButtonHit() {
		currentState = STATES.ellipse;
	}

	@Override
	public void triangleButtonHit() {
		currentState = STATES.triangle;
		trianglePoints = new ArrayList<>();
	}

	@Override
	public void selectButtonHit() {
		currentState = STATES.none;
	}

	@Override
	public void zoomInButtonHit() {
		currentState = STATES.none;
	}

	@Override
	public void zoomOutButtonHit() {
		currentState = STATES.none;
	}

	@Override
	public void hScrollbarChanged(int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void vScrollbarChanged(int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void openScene(File file) {
		// TODO Auto-generated method stub

	}

	@Override
	public void toggle3DModelDisplay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(Iterator<Integer> iterator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void openImage(File file) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveImage(File file) {
		// TODO Auto-generated method stub

	}

	@Override
	public void toggleBackgroundDisplay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveDrawing(File file) {
		SaveStructure save = new SaveStructure();
		save.fromModel();
		Writer writer = null;
		try {
			writer = new BufferedWriter( new FileWriter( file ) );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		gson.toJson(save, writer);
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void openDrawing(File file) {
		Reader reader = null;
		try {
			reader = new BufferedReader( new FileReader( file ) );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SaveStructure save = (SaveStructure) gson.fromJson(reader, SaveStructure.class);
		save.toModel();
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void doDeleteShape() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doEdgeDetection() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSharpen() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doMedianBlur() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doUniformBlur() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doGrayscale() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doChangeContrast(int contrastAmountNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doChangeBrightness(int brightnessAmountNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doMoveForward() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doMoveBackward() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSendToFront() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSendtoBack() {
		// TODO Auto-generated method stub

	}

}
