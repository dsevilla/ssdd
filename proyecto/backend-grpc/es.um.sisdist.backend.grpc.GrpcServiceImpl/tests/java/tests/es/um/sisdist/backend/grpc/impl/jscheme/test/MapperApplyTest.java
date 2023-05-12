/**
 * 
 */
package es.um.sisdist.backend.grpc.impl.jscheme.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.um.sisdist.backend.grpc.impl.jscheme.JSchemeProvider;
import es.um.sisdist.backend.grpc.impl.jscheme.MapperApply;
import jscheme.JScheme;

/**
 * @author dsevilla
 *
 */
class MapperApplyTest
{
	static JScheme js;

	static class Wrap {
		int v = 0;
		
		void inc() {++v;}
		int get() {return v;}
	};
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		js = JSchemeProvider.js();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test method 
	 */
	@Test
	void testApply_map_function()
	{
		final Wrap w = new Wrap();
		
		MapperApply ma = new MapperApply(js, 
				"(define (ssdd-map k v)"
				+ " (display k)"
				+ " (display \": \")"
				+ " (display v)"
				+ " (emit (list k v)))",
				p ->  // emit function
					{
						System.out.println("Called: "
								+ p.first()
								+ ", "
								+ p.second());
						w.inc();
						return null;
					});

		ma.apply(3, 4);
		assertEquals(w.get(), 1);
		
		ma.apply("a", "b");
		assertEquals(w.get(), 2);
	}
}
