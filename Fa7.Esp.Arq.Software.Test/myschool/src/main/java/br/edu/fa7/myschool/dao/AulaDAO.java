package br.edu.fa7.myschool.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.fa7.myschool.interceptador.LogInterceptador;
import br.edu.fa7.myschool.modelo.Aula;
import br.edu.fa7.myschool.modelo.Disciplina;
import br.edu.fa7.myschool.modelo.Topico;

@Stateless
//@TransactionManagement(TransactionManagementType.BEAN) // opcional
@Interceptors({LogInterceptador.class})
public class AulaDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static List<Aula> aulas = new ArrayList<Aula>();
	
	@PostConstruct
	void aposCriacao(){
		
//		try {
//			for (Aula aula : aulas) {
////				entityManager.persist(aula);
//				;;
//			}
//		} catch (Exception e) {
//			System.out.println("Aulas não persistidas");
//			e.printStackTrace();
//		}
		System.out.println("AulaDao foi criado");
	}

//	@Inject
//	private UserTransaction tx;
	
	private static Map<Long, Aula> banco = new HashMap<Long, Aula>();
//	private static AtomicLong contador = new AtomicLong(1);
	
	static {
		/* Assunto */
		List<String> assuntos = new ArrayList<String>();
		assuntos.add("Interpretação de Texto");
		assuntos.add("Multiplicação");
		assuntos.add("Isomeria");
		assuntos.add("Movimento Retilíneo Uniforme");
		assuntos.add("Complexo Golgiense");
		assuntos.add("Segunda Guerra Mundial");
		
		/* Tópicos */
		List<Topico> topicos = new ArrayList<Topico>();
		topicos.add(new Topico(null, "Inferência", "Inferência"));
		topicos.add(new Topico(null, "Propriedades da Operação", "Propriedades da Operação"));
		topicos.add(new Topico(null, "Afinidade Molecular", "Afinidade Molecular"));
		topicos.add(new Topico(null, "Iteração atômica", "Iteração atômica"));
		topicos.add(new Topico(null, "Excreção celular", "Fluxo de excreção celular"));
		topicos.add(new Topico(null, "Espionagem Industrial", "Os interesses empresariais"));
		
		// Provento:3.659,00
		// saldo	3.214,04
		//            500,00
		// Saldo at:2.714,04
		
		String aprender = "Aprender ";
		
		for (int i = 0; i < assuntos.size(); i++) {
			Set<Topico> list = new HashSet<Topico>();
			list.add(topicos.get(i));
			Aula aula = new Aula(new Integer(banco.size()).longValue(), assuntos.get(i), Disciplina.values()[i], aprender.concat(assuntos.get(i)), list);
//			aulas.add(new Aula(null, assuntos.get(i), Disciplina.values()[i], aprender.concat(assuntos.get(i)), list));
			banco.put(aula.getId(), aula);
		}
		
	}
	
	public void adiciona(Aula aula) {
//		long id = contador.incrementAndGet();
		aula.setId(new Integer(banco.size()).longValue());
		banco.put(aula.getId(), aula);
		
//		// Com EntityManager
//		entityManager.persist(aula);
	}
	
	public Aula busca(Long id) {
//		return entityManager.find(Aula.class, id);
		return banco.get(id);
	}
	
	public Aula remove(Long id) {
//		entityManager.remove(id);
		return banco.remove(id);
	}

	public Aula atualiza(Aula aula) {
//		entityManager.persist(aula);
//		entityManager.refresh(aula);
		return banco.put(aula.getId(), aula);
	}

	public static List<Aula> getAulas() {
		return aulas;
	}

	public static void setAulas(List<Aula> aulas) {
		AulaDAO.aulas = aulas;
	}
}
