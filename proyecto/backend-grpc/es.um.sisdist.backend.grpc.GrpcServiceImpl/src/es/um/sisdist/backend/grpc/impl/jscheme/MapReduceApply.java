package es.um.sisdist.backend.grpc.impl.jscheme;

import static jscheme.JScheme.list;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import jscheme.JScheme;
import jscheme.SchemePair;
import jsint.Pair;

/**
 * 
 * @author dsevilla
 *
 * La clase MappReduceApply recibe un entorno de ejecución JScheme, una función map
 * como una cadena de caracteres, una función reduce como una cadena de caracteres y produce 
 * un array resultado como un diccionario (Map) que mapea claves y valores. Para cada par
 * de entrada, hay que llamar a la función apply, que irá generando el mapa de shuffle.
 * Para realizar el map-reduce, hay que llamar a la función map-reduce().
 */
public class MapReduceApply 
{
	private String ssdd_map_function = null;
	private String ssdd_reduce_function = null;	

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
	
	private JScheme js = null;
	
	MapperApply ma;
	ReducerApply ra;
	Map<Object, List<Object>> shuffle_map;
	Map<Object, Object> result;
	
	private void _install_emit_map_reduce_functions()
	{
		// Mapper
		ma = new MapperApply(js, ssdd_map_function,
						p ->  // emit function
							{
								var l = shuffle_map.getOrDefault(p.first(), new LinkedList<>());
								l.add(p.second());
								shuffle_map.putIfAbsent(p.first(), l);
								return null;
							});
		// Reducer
		ra = new ReducerApply(js, ssdd_reduce_function);
	}
	
	public MapReduceApply(JScheme js, String scheme_map_function, String scheme_reduce_function) 
	{
		super();
		this.shuffle_map = new HashMap<>();
		this.result = new HashMap<>();
		this.ssdd_map_function = scheme_map_function;
		this.ssdd_reduce_function = scheme_reduce_function;
		this.js = js;
		_install_emit_map_reduce_functions();
	}

	public <T1, T2> void apply(T1 k, T2 v)
	{
		ma.apply(k, v);
	}

	public Map<Object, Object> map_reduce()
	{
		shuffle_map.entrySet().forEach(e -> 
		{
			Object res = ra.apply(e.getKey(), list_to_pair(e.getValue()));
			result.put(e.getKey(), res);
		});

		return result;
	}
	
	public String getScheme_ssdd_map_function() {
		return ssdd_map_function;
	}
	public void setScheme_ssdd_map_function(String scheme_map_function) {
		this.ssdd_map_function = scheme_map_function;
		_install_emit_map_reduce_functions();
	}

	public String getSsdd_reduce_function() {
		return ssdd_reduce_function;
	}

	public void setSsdd_reduce_function(String ssdd_reduce_function) {
		this.ssdd_reduce_function = ssdd_reduce_function;
		_install_emit_map_reduce_functions();
	}

	public JScheme getJs() {
		return js;
	}
}
