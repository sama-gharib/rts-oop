package fr.uvsq.amis;

class Matrix2 {

	public static Matrix2 IDENTITY = new Matrix2(1.f, 0.f, 0.f, 1.f);

	private Vector2D i;
	private Vector2D j;

	public Matrix2(Vector2D i, Vector2D j) {
		this.i = i;
		this.j = j;
	}

	public Matrix2(float a, float b, float c, float d) {
		this.i = new Vector2D(a, c);
		this.j = new Vector2D(b, d);
	}

	public float det() {
		return this.i.getX() * this.j.getY() - this.i.getY() * this.j.getX();
	}

	public Matrix2 scale(float f) {
		return new Matrix2(
			this.i.scale(f),
			this.j.scale(f)
		);
	}

	public Matrix2 inverse() {
		return new Matrix2(
			this.j.getY(), -this.j.getX(),
			-this.i.getY(), this.i.getX()
		).scale(1.f / this.det());
	}

	public Vector2D transform(Vector2D v) {
		return new Vector2D(
			this.i.getX() * v.getX() + this.j.getX() * v.getY(),
			this.i.getY() * v.getX() + this.j.getY() * v.getY()
		);
	}

	@Override
	public String toString() {
		return String.format(
			"i = %s; j = %s",
			this.i,
			this.j
		);
	}
}
