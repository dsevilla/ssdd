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
class MapReduceSimpleTest 
{
	static JScheme js;
	
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

		Map<Object, SchemePair> result = new HashMap<>();
		
		// Mapper
		MapperApply ma = new MapperApply(js, 
				"(define (ssdd_map p)"  // Función identidad
				+ " (display (first p))"
				+ " (display \": \")"
				+ " (display (second p))"
				+ " (display \"\\n\")"
				+ " (emit p))",
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
				list(1,1),
				list(1,3),
				list(1,3),
				list(2,3),
				list(2,3)
				); 
		
		values.stream().forEach(p -> ma.apply(p));
		
		// 1 -> (1 3 3) -> 7
		// 2 -> (3 3) -> 6
		
		// Reducer
		ReducerApply ra = new ReducerApply(js,
				"(define (ssdd_reduce v l)"
				+ " (reduce + l 0))");
		shuffle_map.entrySet().forEach(e -> 
			{
				Object res = ra.apply(e.getKey(), list_to_pair(e.getValue()));
				result.put(e.getKey(), list(e.getKey(), res));
			});

		// Aplicar el mismo procesamiento en la lista java 
		var result_java = values.stream().collect(
						groupingBy(SchemePair::first,
								reducing(list(null,null),
										 (p1,p2) -> {
											 if (p1.first() == null)
												 return p2;
											 return list(p1.first(),
													 (Integer)p1.second() + (Integer)p2.second());
										 }))
						);
		
		result_java.entrySet().forEach(e -> {
			SchemePair p = result.get(e.getKey());
			assertNotNull(p);
			assertEquals(e.getKey(), p.first());
			assertEquals(e.getValue().first(), p.first());
			assertEquals(e.getValue().second(), p.second());
		});
		
		System.out.println("Done");
	}
}
