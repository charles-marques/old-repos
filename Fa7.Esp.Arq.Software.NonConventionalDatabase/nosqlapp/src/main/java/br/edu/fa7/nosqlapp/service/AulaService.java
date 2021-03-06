package br.edu.fa7.nosqlapp.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.fa7.nosqlapp.dao.AulaDAO;
import br.edu.fa7.nosqlapp.modelo.Aula;

@Stateless
public class AulaService {

	@Inject
	private AulaDAO dao;
	
	public void adiciona(Aula aula) {
		dao.adiciona(aula);
	}
	
	public Aula busca(Long id) {
		return dao.busca(id);
	}
	
	public Aula remove(Long id) {
		return dao.remove(id);
	}

	public Aula atualiza(Aula aula) {
		return dao.atualiza(aula);
	}
}
