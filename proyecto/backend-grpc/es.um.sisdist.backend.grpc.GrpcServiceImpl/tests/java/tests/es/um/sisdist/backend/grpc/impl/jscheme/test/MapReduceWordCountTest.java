/**
 * 
 */
package es.um.sisdist.backend.grpc.impl.jscheme.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.um.sisdist.backend.grpc.impl.jscheme.JSchemeProvider;
import es.um.sisdist.backend.grpc.impl.jscheme.MapperApply;
import es.um.sisdist.backend.grpc.impl.jscheme.ReducerApply;

import static jscheme.JScheme.*;
import jscheme.JScheme;
import jscheme.SchemePair;
import jsint.Pair;

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

	static private SchemePair list_to_pair(List<Object> l)
	{
		if (l.isEmpty())
			return list();
		return list_to_pair_aux(l.iterator());
	}

	static private SchemePair list_to_pair_aux(Iterator<Object> it)
	{
		if (!it.hasNext())
			return list();
		Object next = it.next();
		if (!it.hasNext())
			return new Pair(next, list());
		else
			return new Pair(next, list_to_pair_aux(it));
	}
	
	@Test
	void test() 
	{		
		Map<Object, List<Object>> shuffle_map =
				new HashMap<>();

		Map<Object, Object> result = new HashMap<>();
		
		// Mapper
		MapperApply ma = new MapperApply(js, 
				"(import \"java.lang.String\")"
				+ "(define (ssdd_map k v)"
				+ " (display k)"
				+ " (display \": \")"
				+ " (display v)"
				+ " (display \"\\n\")"
				+ " (for-each (lambda (w)"
				+ "				(emit (list w 1)))"
				+ "   (vector->list (.split v \" \"))))",
				p ->  // emit function
					{
						System.out.println("Called: "
								+ p.first()
								+ ", "
								+ p.second());
						var l = shuffle_map.getOrDefault(p.first(), new LinkedList<>());
						l.add(p.second());
						shuffle_map.putIfAbsent(p.first(), l);
						return null;
					});
		
		List<SchemePair> values = Arrays.asList(
				list(1,"abc def ghi"),
				list(2,"abc def thi"),
				list(3,"abc def xhi"),
				list(4,"abc def rhi"),
				list(5,"abc def jhi")
				); 
		
		values.stream().forEach(p -> ma.apply(p.first(), p.second()));
		
		// Reducer
		ReducerApply ra = new ReducerApply(js,
				"(define (ssdd_reduce k l)" +
				" (reduce + l 0))");
		shuffle_map.entrySet().forEach(e -> 
			{
				Object res = ra.apply(e.getKey(), list_to_pair(e.getValue()));
				result.put(e.getKey(), res);
			});

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
