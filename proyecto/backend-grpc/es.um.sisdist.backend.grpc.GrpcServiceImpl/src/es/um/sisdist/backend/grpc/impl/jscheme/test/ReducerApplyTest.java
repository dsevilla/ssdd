/**
 * 
 */
package es.um.sisdist.backend.grpc.impl.jscheme.test;

import static jscheme.JScheme.list;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.um.sisdist.backend.grpc.impl.jscheme.JSchemeProvider;
import es.um.sisdist.backend.grpc.impl.jscheme.ReducerApply;
import jscheme.JScheme;

/**
 * @author dsevilla
 *
 */
class ReducerApplyTest 
{
	static JScheme js;

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

	@Test
	void test() 
	{		
		ReducerApply ra = new ReducerApply(js, 
				"(define (ssdd_reduce v l)"
				+ " (reduce + l 0))");

		assertEquals(14, 
				ra.apply(3 , list(4,10)));
		
		assertEquals(21, 
				ra.apply(24, list(1,2,3, 4, 5, 6)));
	}
}
