package br.edu.fa7.nosqlapp.interceptador;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LogInterceptador {

	@AroundInvoke
	public Object intercepta(InvocationContext context) throws Exception {

		long milis = System.currentTimeMillis();

		Object o = context.proceed();

		String metodo = context.getMethod().getName();
		String nomeClasse = context.getTarget().getClass().getSimpleName();

		System.out.println(nomeClasse + "," + metodo);
		System.out.println("Tempo gasto: " + (System.currentTimeMillis() - milis));

		return o;
	}

}
