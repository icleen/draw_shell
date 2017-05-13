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
	
	private static final int INIT_SIZE = 0;
	
	private Shape selected = null;
	
	private enum STATES {
			circle, ellipse, line, rectangle, square, triangle, select, zoomIn, zoomOut
	};
	private STATES currentState;
	private Color currentColor;
	private int currentIndex;

	private List<Point2D.Double> trianglePoints;
	private Shape currentShape;
	private Point2D.Double start;
	
	public Controller() {
		currentState = STATES.select;
		currentColor = Color.WHITE;
//		GUIFunctions.changeSelectedColor(currentColor);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (currentState == STATES.triangle) {
			Point2D.Double p = new Point2D.Double();
			p.setLocation(arg0.getX(), arg0.getY());
			trianglePoints.add(p);
			if (trianglePoints.size() == Model.TOTAL_TRIANGLE_POINTS) {
				Triangle triangle = ShapeSizer.inst().setTriangle(currentColor, trianglePoints);
				Model.SINGLETON.addShape(triangle);
				trianglePoints.clear();
			}
		}else if (currentState == STATES.select) {
			
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
			currentShape = new Circle(currentColor, start, INIT_SIZE);
			break;
		case ellipse:
			currentShape = new Ellipse(currentColor, start, INIT_SIZE, INIT_SIZE);
			break;
		case line:
			currentShape = new Line(currentColor, start, start);
			break;
		case rectangle:
			currentShape = new Rectangle(currentColor, start, INIT_SIZE, INIT_SIZE);
			break;
		case square:
			currentShape = new Square(currentColor, start, INIT_SIZE);
			break;
		case select:
			selected = Model.SINGLETON.selectShape(start);
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
		if (currentState != STATES.select && currentState != STATES.zoomIn 
				&& currentState != STATES.zoomOut) {
			currentShape.resetShape(start, point);
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
		currentState = STATES.select;
	}

	@Override
	public void zoomInButtonHit() {
		currentState = STATES.zoomIn;
	}

	@Override
	public void zoomOutButtonHit() {
		currentState = STATES.zoomOut;
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
		try {
			Writer writer = new BufferedWriter( new FileWriter( file ) );
			gson.toJson(save, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void openDrawing(File file) {
		Reader reader = null;
		try {
			reader = new BufferedReader( new FileReader( file ) );
			SaveStructure save = (SaveStructure) gson.fromJson(reader, SaveStructure.class);
			save.toModel();
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
