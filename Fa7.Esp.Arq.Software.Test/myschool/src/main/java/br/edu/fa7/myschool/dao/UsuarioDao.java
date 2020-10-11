package br.edu.fa7.myschool.dao;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.edu.fa7.myschool.modelo.Usuario;

@Stateless
@Startup
public class UsuarioDao {

	// private Banco banco = new Banco();
	@PersistenceContext
	private EntityManager manager;

	@PostConstruct
	void aposCriacao() {
		System.out.println("Usuário foi criado");
		manager.persist(new Usuario("admin", "pass"));
	}

	public Usuario buscaPeloLogin(String login) {
		// return this.banco.buscaPeloNome(login);
		TypedQuery<Usuario> query = manager.createQuery(
				"select u from Usuario u where u.login=:login ", Usuario.class);
		return query.setParameter("login", login).getSingleResult();
	}

}
