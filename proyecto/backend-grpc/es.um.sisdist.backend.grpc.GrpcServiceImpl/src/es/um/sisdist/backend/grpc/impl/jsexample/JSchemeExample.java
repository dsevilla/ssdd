/**
 * 
 */
package es.um.sisdist.backend.grpc.impl.jsexample;

import jscheme.JScheme;
import jscheme.SchemePair;
import jscheme.SchemeProcedure;
import jsint.Procedure;

/**
 * @author dsevilla
 *
 */
public class JSchemeExample 
{
	public static void main(String[] args)
	{
		JScheme js = null;
		js = new JScheme();
		js.load("(define (countdown x)\n"
					+ "  (define (loop x acc)\n"
					+ "    (if (= x 0)\n"
					+ "      acc\n"
					+ "      (loop (- x 1) (+ acc x))))\n"
					+ "  (loop x 0))\n"
					+ "\n"
					+ ";;; config variables\n"
					+ "(define msg \"Hello from JScheme!\")\n"
					+ ";; tail calls are optimized as required\n"
					+ "(define answer (countdown 42))\n"
					);
		System.out.println("Message: '" + js.eval("msg") + "'");

		// Values
		int answer = (Integer) js.eval("answer");

		System.out.println("Answer: " + answer);
		
		// Function calls
		System.out.println(js.call("countdown", 42));
		Procedure p = new Procedure() {
			
			private static final long serialVersionUID = 6988405761033921572L;

			@Override
			public Object apply(Object[] arg0) 
			{
				System.out.println("xxxxxxxxxxxxxxxxxxxx");
				return null;
			}
		};
		js.setGlobalValue("a" , 2);
		js.setGlobalValue("emit", p);

		js.readEvalPrintLoop();
	}
}
