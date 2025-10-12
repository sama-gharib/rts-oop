package fr.uvsq.amis;

/**
 * Performs line/column mapping from world coordinates on a tiling of regular hexagons.
 */
class HexagonBasis {
	/**
	 * The angle formed by two adjacent points in an hexagon.
	 */
	private static float TETA = (float) Math.PI / 3;
	private static Vector2D HORIZONTAL = new Vector2D(1.f, 0.f);
	private static Vector2D VERTICAL = new Vector2D(HexagonBasis.TETA * 2.f);

	/**
	 * Distance from any point of an hexagon to its center.
	*/
	private float radius;
	private float sideCenterDistance;
	private Matrix2 indicesToWorld;
	private Matrix2 worldToIndices;
	
	public HexagonBasis(float radius) {
		this.radius = radius;
		this.sideCenterDistance = this.radius * (float) Math.cos(TETA/2.f);
		this.indicesToWorld = new Matrix2(HORIZONTAL, VERTICAL).scale(sideCenterDistance * 2.f);
		this.worldToIndices = this.indicesToWorld.inverse();
	}

	public Vector2D getWorldPosition(Vector2D indices) {
		return this.indicesToWorld.transform(indices);
	}
	
	public Vector2D getIndices(Vector2D coordinates) {
		return this.worldToIndices.transform(coordinates).add(Vector2D.ONE.scale(0.5f));
	}

	public float getRadius() {
		return this.radius;
	}

	public float getColumnWidth() {
		return this.sideCenterDistance * 2.f;
	}

	public float getLineHeight() {
		return this.radius + (float) (Math.sin(TETA/2.f));
	}
}
