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
class MapReduceWordCountTest 
{
	static JScheme js;
	
	public static void main(String[] args)
	{
		try {
			setUpBeforeClass();
		} catch (Exception e) {
			e.printStackTrace();
		}
		new MapReduceWordCountTest().test();
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
		List<SchemePair> values = Arrays.asList(
				list(1,"abc def ghi"),
				list(2,"abc def thi"),
				list(3,"abc def xhi"),
				list(4,"abc def rhi"),
				list(5,"abc def jhi")
				); 
		
		// Mapper
		MapReduceApply mar = new MapReduceApply(js,
				
				// Función map
				"(import \"java.lang.String\")"
				+ "(define (ssdd-map k v)"
				+ " (display k)"
				+ " (display \": \")"
				+ " (display v)"
				+ " (display \"\\n\")"
				+ " (for-each (lambda (w)"
				+ "				(emit (list w 1)))"
				+ "   (vector->list (.split v \" \"))))",
				
				// Función reduce
				"(define (ssdd-reduce k l)" +
				" (apply + l))");
		
		values.stream().forEach(p -> mar.apply(p.first(), p.second()));

		Map<Object, Object> result = mar.map_reduce();

		// Aplicar el mismo procesamiento en la lista java 
		var result_java = values.stream().flatMap(p -> 
			Arrays.asList(((String)p.second()).split(" ")).stream())
				.map(w -> list(w,1))
				.collect(
						groupingBy(SchemePair::first,
								reducing(0,
										 p -> (Integer)p.second(), // mapping
										 (p1,p2) -> (Integer)p1 + (Integer)p2))
						);
		
		result_java.entrySet().forEach(e -> {
			Object v = result.get(e.getKey());
			assertNotNull(v);
			assertEquals(e.getValue(), v);
		});
		
		System.out.println("Done");
	}
}
