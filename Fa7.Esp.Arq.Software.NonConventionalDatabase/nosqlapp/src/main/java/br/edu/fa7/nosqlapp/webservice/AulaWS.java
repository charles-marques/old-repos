package br.edu.fa7.nosqlapp.webservice;

import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.edu.fa7.nosqlapp.dao.AulaDAO;
import br.edu.fa7.nosqlapp.modelo.Aula;
import br.edu.fa7.nosqlapp.modelo.Topico;

/**
 * Classe de recurso para um servico REST para a enteidade {@link Aula}
 * @author charles.marques
 * 
 */
@WebService
@Stateless
public class AulaWS {
	
	@Inject
	AulaDAO dao;
	
	@WebResult(name="livros")
	public Set<Topico> getTopicosPelaAula(@WebParam(name="id_aula") Long idAula) {
		
		System.out.println("AulaWS: procurando topicos da aula: "+ idAula);
		
		return dao.busca(idAula).getTopicos();
	}

}
