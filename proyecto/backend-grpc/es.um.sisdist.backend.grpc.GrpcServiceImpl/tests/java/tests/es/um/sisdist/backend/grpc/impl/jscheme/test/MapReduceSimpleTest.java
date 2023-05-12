/**
 * 
 */
package es.um.sisdist.backend.grpc.impl.jscheme.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.um.sisdist.backend.grpc.impl.jscheme.JSchemeProvider;
import es.um.sisdist.backend.grpc.impl.jscheme.MapReduceApply;

import static jscheme.JScheme.*;
import jscheme.JScheme;
import jscheme.SchemePair;

/**
 * @author dsevilla
 *
 */
class MapReduceSimpleTest 
{
	static JScheme js;
	
	public static void main(String[] args)
	{
		try {
			setUpBeforeClass();
		} catch (Exception e) {
			e.printStackTrace();
		}
		new MapReduceSimpleTest().test();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		js =  JSchemeProvider.js();
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
		// Mapper
		MapReduceApply mar = new MapReduceApply(js, 
				"(define (ssdd-map k v)"  // Función identidad
				+ " (emit (list k v)))",
				
				// Función reduce
				"(define (ssdd-reduce k l)"
				+ " (apply + l))");
		
		List<SchemePair> values = Arrays.asList(
				list(1,1),
				list(1,3),
				list(1,3),
				list(2,3),
				list(2,3)
				); 
		
		values.stream().forEach(p -> mar.apply(p.first(), p.second()));
		
		// 1 -> (1 3 3) -> 7
		// 2 -> (3 3) -> 6

		// Reducer
		Map<Object, Object> result = mar.map_reduce();

		// Aplicar el mismo procesamiento en la lista java 
		var result_java = values.stream().collect(
						groupingBy(SchemePair::first,
								reducing(0,
										 p -> (Integer)p.second(), // mapping
										 (p1,p2) -> {
											 return (Integer)p1 + (Integer)p2;
										 }))
						);
		
		result_java.entrySet().forEach(e -> {
			Object p = result.get(e.getKey());
			assertNotNull(p);
			assertEquals(e.getValue(), p);
		});
		
		System.out.println("Done");
	}
}
