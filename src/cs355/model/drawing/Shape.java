package cs355.model.drawing;

import java.awt.Color;

/**
 * This is the base class for all of your shapes.
 * Make sure they all extend this class.
 */
public abstract class Shape {

	public static enum SHAPE_TYPE {
		none, circle, ellipse, line, rectangle, square, triangle
	};
	
	// The color of this shape.
	protected Color color;
	protected SHAPE_TYPE shapeType = SHAPE_TYPE.none;
	protected int index;

	/**
	 * Basic constructor that sets the field.
	 * @param color the color for this new shape.
	 */
	public Shape(Color color) {
		this.color = color;
	}

	/**
	 * Getter for this shape's color.
	 * @return the color of this shape.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Setter for this shape's color
	 * @param color the new color for the shape.
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Getter for the shape type (ie circle, square, triangle, etc.)
	 * @return the type of this shape
	 */
	public SHAPE_TYPE getShapeType() {
		return shapeType;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
}
