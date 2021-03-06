package br.edu.fa7.nosqlapp.bean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.edu.fa7.nosqlapp.modelo.Aula;
import br.edu.fa7.nosqlapp.service.AulaService;

@Model
public class AulaBean {
	
	@Inject
	private AulaService service;
	
	public void adiciona(Aula aula)  {
		service.adiciona(aula);
	}
	
	public Aula busca(Long id)  {
		return service.busca(id);
	}
	
	public Aula remove(Long id) {
		return service.remove(id);
	}
	
	public Aula atualiza(Aula aula) {
		return service.atualiza(aula);
	}
}
