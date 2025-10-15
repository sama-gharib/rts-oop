package fr.uvsq.amis;

/**
 * Performs line/column mapping from world coordinates on a tiling of regular hexagons.
 */
class HexagonBasis {
	/**
	 * The angle formed by two adjacent points in an hexagon.
	 */
	private static float TETA = (float) Math.PI / 3;
	/**
	 * The horizontal vector in our indexing basis.
	 */
	private static Vector2D HORIZONTAL = new Vector2D(1.f, 0.f);
	/**
	 * The vertical vector in our indexing basis.
	 */
	private static Vector2D VERTICAL = new Vector2D(HexagonBasis.TETA * 2.f);

	/**
	 * Distance from any point of an hexagon to its center.
	*/
	private float radius;
	/**
	 * Distance any side to the center.
	 */
	private float sideCenterDistance;
	/**
	 * Transformation matrix to apply to indices.
	 */
	private Matrix2 indicesToWorld;
	/**
	 * Transformation matrix to apply to world coordinates.
	 */
	private Matrix2 worldToIndices;

	/**
	 * Constructs an indexing basis for a tiling of hexagon.
	 * @param radius the radius of every hexagon in tiling.
	 */
	public HexagonBasis(float radius) {
		this.radius = radius;
		this.sideCenterDistance = this.radius * (float) Math.cos(TETA/2.f);
		this.indicesToWorld = new Matrix2(HORIZONTAL, VERTICAL).scale(sideCenterDistance * 2.f);
		this.worldToIndices = this.indicesToWorld.inverse();
	}

	/**
	 * Transforms grid indices in pixel coordinates.
	 */
	public Vector2D getWorldPosition(Vector2D indices) {
		return this.indicesToWorld.transform(indices);
	}

	/**
	 * Transforms pixel coordinates in grid indices.
	 */
	public Vector2D getIndices(Vector2D coordinates) {
		return this.worldToIndices
			.transform(coordinates)
			.add(Vector2D.ONE.scale(0.5f)); // Offsets of (.5, .5) to account for affine transformation
	}

	public float getRadius() {
		return this.radius;
	}

	/**
	 * Distance between to hexagon centers side-by-side. 
	 */
	public float getColumnWidth() {
		return this.sideCenterDistance * 2.f;
	}

	/**
	 * Distance on the y axis between to centers of hexagons.
	 */
	public float getLineHeight() {
		return this.radius + (float) (Math.sin(TETA/2.f));
	}
}
