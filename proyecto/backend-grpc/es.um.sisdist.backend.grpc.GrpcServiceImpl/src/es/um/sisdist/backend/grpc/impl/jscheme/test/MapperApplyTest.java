/**
 * 
 */
package es.um.sisdist.backend.grpc.impl.jscheme.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jscheme.JScheme;

/**
 * @author dsevilla
 *
 */
class MapperApplyTest
{

	static JScheme js;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		js = new JScheme();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test method for {@link es.um.sisdist.backend.grpc.impl.jscheme.MapperApply#apply_map_function(jscheme.SchemePair)}.
	 */
	@Test
	void testApply_map_functionSchemePair()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link es.um.sisdist.backend.grpc.impl.jscheme.MapperApply#apply_map_function(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	void testApply_map_functionT1T2() 
	{
		fail("Not yet implemented"); // TODO
	}

}
