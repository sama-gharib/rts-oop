package fr.uvsq.amis;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;

public class HexagonBasisTest {
	private HexagonBasis basis;
	
	@Before
	public void initBasis() {
		this.basis = new HexagonBasis(100.f);
	}
	
	@Test
	public void worldToIndices() {
		Vector2D indices = this.basis.getIndices(
			new Vector2D(
				350.f,
				250.f
			)
		).asIndices();

		assertEquals(indices, new Vector2D(5.f, 3.f));
	}

	@Test
	public void indicesToWorld() {
		Vector2D world = this.basis.getWorldPosition(Vector2D.ZERO);

		assertEquals(world, Vector2D.ZERO);
	}
}
