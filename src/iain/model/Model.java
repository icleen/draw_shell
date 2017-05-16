package iain.model;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import cs355.model.drawing.CS355Drawing;
import cs355.model.drawing.Shape;

public class Model extends CS355Drawing {
	
	public static final int TOTAL_TRIANGLE_POINTS = 3;
	private static final double SELECT_TOLERANCE = 4;
	
	private List<Shape> shapes;
	private static final int BACK_INDEX = 0;
	
	public static final Model SINGLETON = new Model();
	
	private Model() {
		shapes = new ArrayList<>();
	}

	@Override
	public Shape getShape(int index) {
		if (index < 0 || index > shapes.size()) {
			throw new IndexOutOfBoundsException("index was out of range");
		}
		return shapes.get(index);
	}

	@Override
	public int addShape(Shape s) {
		shapes.add(s);
		this.setChanged();
		this.notifyObservers();
		return shapes.size() - 1;
	}
	
	public void setShape(int index, Shape s) {
		shapes.set(index, s);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void deleteShape(int index) {
		shapes.remove(index);
		this.setChanged();
	}

	@Override
	public void moveToFront(int index) {
		if (index == shapes.size() - 1) {
			return;
		}else if (index < 0 || index > shapes.size()) {
			throw new IndexOutOfBoundsException("index was out of range");
		}
		Shape s = shapes.get(index);
		shapes.remove(index);
		shapes.add(s);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void movetoBack(int index) {
		if (index == 0) {
			return;
		}else if (index < 0 || index > shapes.size()) {
			throw new IndexOutOfBoundsException("index was out of range");
		}
		Shape s = shapes.get(index);
		shapes.remove(index);
		shapes.add(BACK_INDEX, s);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void moveForward(int index) {
		if (index == shapes.size() - 1) {
			return;
		}else if (index < 0 || index > shapes.size()) {
			throw new IndexOutOfBoundsException("index was out of range");
		}
		Shape s = shapes.get(index);
		shapes.remove(index);
		shapes.add(index + 1, s);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void moveBackward(int index) {
		if (index == 0) {
			return;
		}else if (index < 0 || index > shapes.size()) {
			throw new IndexOutOfBoundsException("index was out of range");
		}
		Shape s = shapes.get(index);
		shapes.remove(index);
		shapes.add(index - 1, s);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public List<Shape> getShapes() {
		return shapes;
	}

	@Override
	public List<Shape> getShapesReversed() {
		List<Shape> reversed = new ArrayList<>();
		for (Shape s : shapes) {
			reversed.add(BACK_INDEX, s);
		}
		return reversed;
	}

	@Override
	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
		this.setChanged();
		this.notifyObservers();
	}
	
	public Shape selectShape(Point2D.Double point) {
		Point2D.Double objCoord = null;
		AffineTransform worldToObj = null;
		Shape selected = null;
		boolean isFound = false;
		Shape s;
		for (int i = 0; i < shapes.size(); i++) {
			s = shapes.get(i);
			s.setIndex(i);
			objCoord = new Point2D.Double(0, 0);
			worldToObj = new AffineTransform();
			worldToObj.rotate(s.getRotation() * -1);
			worldToObj.translate(s.getCenter().x * -1, s.getCenter().y * -1);
			worldToObj.transform(point, objCoord);
			
			if (s.pointInShape(objCoord, SELECT_TOLERANCE) && !isFound) {
				System.out.println("found");
				selected = s;
				isFound = true;
			}
		}
		this.setChanged();
		this.notifyObservers();
		return selected;
	}

}








