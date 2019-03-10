/**
 * BoundingBox complete class for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 * update by Guang Yang
 */
package utilities;

import org.newdawn.slick.Image;
/**
 * This class is used to determine whether two sprites collides
 * @author yangguang
 *
 */
public class BoundingBox {
	private static final float FUZZ = 0.95f;
	private static final int SMALL_NUMBER = 3;
	private float left;
	private float top;
	private float width;
	private float height;
	private float x;
	private float y;
	/**
	 * Constructor of BoundingBox
	 * @param x x location of BoundingBox
	 * @param y y location of BoundingBox
	 * @param width width of BoundingBox
	 * @param height height of BoundingBox
	 */
	public BoundingBox(float x, float y, float width, float height) {
		setWidth(width);
		setHeight(height);
		this.x = x;
		this.y = y;
		setX(x);
		setY(y);
	}
	/**
	 * Constructor of BoundingBox
	 * @param img this image can determine the width and height of bounding box 
	 * @param x x location of BoundingBox
	 * @param y y location of BoundingBox
	 */
	public BoundingBox(Image img, float x, float y) {
		setWidth(img.getWidth());
		setHeight(img.getHeight());
		setX(x);
		setY(y);
	}
	/**
	 * Constructor of BoundingBox
	 * @param bb BoundingBox
	 */
	public BoundingBox(BoundingBox bb) {
		width = bb.width;
		height = bb.height;
		left = bb.left;
		top = bb.top;
	}

	/*
	 * Sets the x and y position at the centre of the bounding box.
	 */
	/**
	 * setter of x
	 * @param x
	 */
	public void setX(float x) {
		left = x - width / 2;
	}
	/**
	 * setter of y
	 * @param y
	 */
	public void setY(float y) {
		top = y - height / 2;
	}
	/**
	 * setter of width
	 * @param w
	 */
	public void setWidth(float w) {
		width = w * FUZZ;
	}
	/**
	 * setter of height
	 * @param h
	 */
	public void setHeight(float h) {
		height = h * FUZZ;
	}
	/**
	 * getter of x
	 * @return
	 */
	public float getX() {
		return x;
	}
	/**
	 * getter of y
	 * @return
	 */
	public float getY() {
		return y;
	}
	/**
	 * getter of left
	 * @return
	 */
	public float getLeft() {
		return left;
	}
	/**
	 * getter of top
	 * @return
	 */
	public float getTop() {
		return top;
	}
	/**
	 * getter of right border
	 * @return
	 */
	public float getRight() {
		return left + width;
	}
	/**
	 * getter of bottom border
	 * @return
	 */
	public float getBottom() {
		return top + height;
	}
	/**
	 * getter of width
	 * @return
	 */
	public float getWidth() {
		return width;
	}
	/**
	 * getter of height
	 * @return height
	 */
	public float getHeight() {
		return height;
	}
	/**
	 * This method is write for contact normal objects, like vehicles and water
	 * @param other Other bounding box this bounding box contact
	 * @return If contact, return true, else return false
	 */
	public boolean intersects(BoundingBox other) {
		return !(other.left > getRight()
			  || other.getRight() < left
			  || other.top > getBottom()
			  || other.getBottom() < top);
	}
	/**
	 * This method is write for left contact bulldozer
	 * @param other Other bounding box this bounding box contact
	 * @return if left contact, return true, else return false
	 */
	public boolean LeftIntersects(BoundingBox other) {
		return ((Math.abs(other.getRight() - left) < SMALL_NUMBER)
				&& (other.getTop() == top));
	}

	/**
	 * This method is write for contact with float objects like log and turtles
	 * @param other Other sprite which collides with 
	 * @return If contact, return true, else return false
	 */
	public boolean xIntersects(BoundingBox other) {
		return (Math.abs(other.getTop() - top) < SMALL_NUMBER &&
				Math.abs(other.getX() - x) < other.getWidth()/2);
	}
}
