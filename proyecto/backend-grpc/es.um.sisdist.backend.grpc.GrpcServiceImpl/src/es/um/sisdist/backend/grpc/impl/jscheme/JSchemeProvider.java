/**
 * 
 */
package es.um.sisdist.backend.grpc.impl.jscheme;

import jscheme.JScheme;

/**
 * @author dsevilla
 *
 */
public class JSchemeProvider 
{
	public static JScheme js()
	{
		JScheme js_instance = new JScheme();
		// Useful code
		js_instance.load(
				  "(define (filter f lst)\n"
				+ "  (define (iter lst result)\n"
				+ "    (cond\n"
				+ "      ((null? lst) (reverse result))\n"
				+ "      ((f (car lst)) (iter (cdr lst)\n"
				+ "                           (cons (car lst) result)))\n"
				+ "      (else (iter (cdr lst)\n"
				+ "                  result))))\n"
				+ "  (iter lst '()))\n"
				+ "(define (reduce fn list init)"
				+ "  (if (null? list) init"
				+ "      (fn (car list)"
				+ "          (reduce fn (cdr list) init))))"
				+ "(define (map f L)"
				+ "  (if (null? L)"
				+ "    ()\n"
				+ "  (cons (f (car L))"
				+ "    (map f (cdr L)))))"
				);
		return js_instance;
	}
}
