package cs355.model.drawing;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Add your line code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Line extends Shape {

	// The ending point of the line.
	private Point2D.Double end;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param start the starting point.
	 * @param end the ending point.
	 */
	public Line(Color color, Point2D.Double start, Point2D.Double end) {

		// Initialize the superclass.
		super(color, start);
		this.type = Shape.SHAPE_TYPE.line;
		// Set the field.
		this.end = end;
	}

	/**
	 * Getter for this Line's ending point.
	 * @return the ending point as a Java point.
	 */
	public Point2D.Double getEnd() {
		return end;
	}

	/**
	 * Setter for this Line's ending point.
	 * @param end the new ending point for the Line.
	 */
	public void setEnd(Point2D.Double end) {
		this.end = end;
	}

	/**
	 * Add your code to do an intersection test
	 * here. You <i>will</i> need the tolerance.
	 * @param pt = the point to test against.
	 * @param tolerance = the allowable tolerance.
	 * @return true if pt is in the shape,
	 *		   false otherwise.
	 */
	@Override
	public boolean pointInShape(Point2D.Double pt, double tolerance) {
		double x = end.getX() - center.getX(), y = end.getY() - center.getY();
//		System.out.println("p0: " + center.x + "p1: " + center.y);
//		System.out.println("q0: " + pt.x + "q1: " + pt.y);
//		System.out.println("x: " + x + ", y: " + y);
		Point2D.Double d = new Point2D.Double(x, y);
		x *= x;
		y *= y;
		double length = Math.sqrt(x + y);
//		System.out.println("length: " + length);
		d.setLocation(d.x/length, d.y/length);
//		System.out.println("d: (" + d.x + ", " + d.y + ")");
		x = (pt.x - center.x) * d.x;
		y = (pt.y - center.y) * d.y;
		double t = x + y;
//		System.out.println("t: " + t);
		if (t > length || t < 0) {
			this.isSelected = false;
		}else {
			x = pt.x - (center.x + (t * d.x));
			y = pt.y - (center.y + (t * d.y));
			x *= x;
			y *= y;
			double distance = Math.sqrt(x + y);
//			System.out.println("distance: " + distance);
			if (distance <= tolerance) {
				this.isSelected = true;
			}else {
				this.isSelected = false;
			}
		}
		return this.isSelected;
	}

	@Override
	public void resetShape(Point2D.Double start, Point2D.Double end) {
		assert(start.getX() == this.center.getX());
		assert(start.getY() == this.center.getY());
		this.end = end;
	}

}
