package es.um.sisdist.backend.grpc.impl.jscheme;

import java.util.function.Function;

import jscheme.JScheme;
import jscheme.SchemePair;
import jsint.Procedure;

/**
 * 
 * @author dsevilla
 *
 * La clase MapperApply recibe un entorno de ejecución JScheme, una función map
 * como una cadena de caracteres y opcionalmente una función emit que se conecta 
 * con el intérprete JScheme para ser llamada cada vez que el código de la función 
 * map realice un emit. 
 */
public class MapperApply 
{
	private static Void nilfunc(SchemePair t) {return null;};
	
	private String scheme_map_function = null;
	private Function<SchemePair, Void> emit_function = MapperApply::nilfunc;
	private JScheme js = null;
	
	private void _install_emit_function()
	{
		Procedure p = new Procedure() {
			
			private static final long serialVersionUID = 6988405761033921572L;

			@Override
			public Object apply(Object[] arg0) 
			{
				SchemePair arg = (SchemePair)((SchemePair)arg0[0]).first();
				emit_function.apply(arg);
//				System.out.println("Key: " + arg.first() + ". Value: " +
//						arg.second());
				return null;
			}
		};
		p.setName("emit");
		js.setGlobalValue("emit", p);
	}
	
	public MapperApply(JScheme js, String scheme_map_function) 
	{
		super();
		this.scheme_map_function = scheme_map_function;
		this.js = js;

	}
	public MapperApply(JScheme js, String scheme_map_function, Function<SchemePair, Void> emit_function)
	{
		super();
		this.scheme_map_function = scheme_map_function;
		this.emit_function = emit_function;
		this.js = js;
		_install_emit_function();
	}

	public void apply_map_function(SchemePair p)
	{
		js.call("map", p);
	}

	public <T1, T2> void apply_map_function(T1 e1, T2 e2)
	{
		apply_map_function(JScheme.list(e1,e2));
	}
	
	public String getScheme_map_function() {
		return scheme_map_function;
	}
	public void setScheme_map_function(String scheme_map_function) {
		this.scheme_map_function = scheme_map_function;
	}
	public Function<SchemePair, Void> getEmit_function() {
		return emit_function;
	}
	public void setEmit_function(Function<SchemePair, Void> emit_function) {
		this.emit_function = emit_function;
		_install_emit_function();
	}
	public JScheme getJs() {
		return js;
	}
}
