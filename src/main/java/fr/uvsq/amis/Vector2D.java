package fr.uvsq.amis;

import java.awt.geom.Point2D;

class Vector2D {
	public static Vector2D ZERO = new Vector2D(0.f, 0.f);
	public static Vector2D ONE  = new Vector2D(1.f, 1.f);

	private float x;
	private float y;

	// Constructors
	
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D(float angle) {
		this.x = (float) Math.cos(angle);
		this.y = (float) Math.sin(angle);
	}

	public Vector2D(Point2D p) {
		this.x = (float) p.getX();
		this.y = (float) p.getY();
	}

	public Vector2D() {
		this.x = 0.f;
		this.y = 0.f;
	}

	// Operators

	public Vector2D add(Vector2D v) {
		return new Vector2D(
			this.x + v.x,
			this.y + v.y
		);
	}

	public Vector2D sub(Vector2D v) {
		return this.add(v.scale(-1.f));
	}

	public Vector2D scale(float f) {
		return new Vector2D(
			this.x * f,
			this.y * f
		);
	}

	public float dot(Vector2D v) {
		return this.x * v.x + this.y * v.y;
	}

	public float getSquaredLength() {
		return this.x * this.x + this.y * this.y;
	}

	public float getLength() {
		return (float) Math.sqrt((double) this.getSquaredLength());
	}

	// Setters

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y)  {
		this.y = y;
	}

	// Getters

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public Vector2D asIndices() {
		return new Vector2D(
			(int) this.x,
			(int) this.y
		);
	}
	
	@Override
	public boolean equals(Object other) {
		if (! (other instanceof Vector2D) ) {
			return false;
		} else {
			Vector2D casted = (Vector2D) other;
			return this.x == casted.x && this.y == casted.y;
		}
	}
	
	@Override
	public String toString() {
		return String.format("(%f; %f)", this.x, this.y);
	}
}
