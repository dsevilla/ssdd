/**
 * 
 */
package es.um.sisdist.backend.dao.utils;

import java.util.function.Supplier;

/**
 * @author dsevilla
 *
 */
public class Lazy 
{
	public static <Z> Supplier<Z> lazily(Supplier<Z> supplier) 
	{
	    return new Supplier<Z>()
	    {
	        Z value; // = null
	        @Override public Z get() 
	        {
	            if (value == null)
	                value = supplier.get();
	            return value;
	        }
	    };
	}
}
