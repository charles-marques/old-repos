package br.edu.fa7.nosqlapp.dao;

import java.util.Arrays;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.edu.fa7.nosqlapp.interceptador.LogInterceptador;
import br.edu.fa7.nosqlapp.modelo.Disciplina;

@Stateless
@Interceptors({LogInterceptador.class})
public class DisciplinaDAO {
	
	public Disciplina busca(Long id) {
		return Arrays.asList(Disciplina.values()).get(id.intValue());
	}
	
}
