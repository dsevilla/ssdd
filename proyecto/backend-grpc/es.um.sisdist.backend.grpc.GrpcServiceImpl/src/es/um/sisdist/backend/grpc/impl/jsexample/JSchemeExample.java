/**
 * 
 */
package es.um.sisdist.backend.grpc.impl.jsexample;

import jscheme.JScheme;
import jscheme.SchemePair;

import static jscheme.JScheme.*;

import java.io.FileNotFoundException;

import jsint.Procedure;

/**
 * @author dsevilla
 *
 */
public class JSchemeExample 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		JScheme js = new JScheme();
		js.load("(define (countdown x)\n"
					+ "  (define (loop x acc)\n"
					+ "    (if (= x 0)\n"
					+ "      acc\n"
					+ "      (loop (- x 1) (+ acc x))))\n"
					+ "  (loop x 0))\n"
					+ "\n"
					+ "(define (retlength l) (length l))\n"
					+ ";;; config variables\n"
					+ "(define msg \"Hello from JScheme!\")\n"
					+ ";; tail calls are optimized as required\n"
					+ "(define answer (countdown 42))\n"
					);
		// load useful code
		js.load("(define (filter f lst)\n"
				+ "  (define (iter lst result)\n"
				+ "    (cond\n"
				+ "      ((null? lst) (reverse result))\n"
				+ "      ((f (car lst)) (iter (cdr lst)\n"
				+ "                           (cons (car lst) result)))\n"
				+ "      (else (iter (cdr lst)\n"
				+ "                  result))))\n"
				+ "  (iter lst '()))\n"
				+ "(define (reduce fn list init)\n"
				+ "  (if (null? list) init"
				+ "      (fn (car list)"
				+ "          (reduce fn (cdr list) init))))"
				+ "(define (map f L)"
				+ "  (if (null? L)"
				+ "    ()\n"
				+ "  (cons (f (car L))"
				+ "    (map f (cdr L)))))"
				);

		System.out.println("Message: '" + js.eval("msg") + "'");

		// Values
		int answer = (Integer) js.eval("answer");

		System.out.println("Answer: " + answer);
		
		// Function calls
		System.out.println(js.call("countdown", 42));
		System.out.println("Length: " + js.call("retlength", list(1,2)));
		Procedure p = new Procedure() 
		{	
			private static final long serialVersionUID = 6988405761033921572L;

			@Override
			public Object apply(Object[] arg0) 
			{
				SchemePair arg = (SchemePair)((SchemePair)arg0[0]).first();
				System.out.println("Key: " + arg.first() 
					+ ". Value: " + arg.second()
				);
				return null;
			}
		};
		p.setName("emit");
		js.setGlobalValue("a" , 2);
		js.setGlobalValue("emit", p);

		System.out.println("Emit '(1 2): " + js.call("emit", list(1,2)));
		
		js.readEvalPrintLoop();
	}
}
