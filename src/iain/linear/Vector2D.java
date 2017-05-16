package iain.linear;

import java.awt.geom.Point2D;

public class Vector2D extends Vector {
	
	private static final int DIMENSION = 2;
	
	public Vector2D() {
		super(DIMENSION);
	}
	
	public Vector2D(double x, double y) {
		super(DIMENSION);
		values[0] = x;
		values[1] = y;
	}
	
	public Vector2D(Point2D.Double pt) {
		super(DIMENSION);
		values[0] = pt.x;
		values[1] = pt.y;
	}

	public void setValues(double x, double y) {
		values[0] = x;
		values[1] = y;
	}
	
}
