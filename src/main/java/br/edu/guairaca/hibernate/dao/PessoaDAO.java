package br.edu.guairaca.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.edu.guairaca.hibernate.model.Pessoa;

@Transactional
public class PessoaDAO extends GenericDAO<Pessoa, Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	protected PessoaDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> buscarPorNome(String nome) {
		StringBuffer hql = new StringBuffer();
		hql.append("select p from Pessoa p where 1 = 1 ");

		if (nome != null && !nome.isEmpty()) {
			hql.append("and p.nome like :nomePessoa");
		}

		Query query = entityManager.createQuery(hql.toString());

		if (nome != null && !nome.isEmpty()) {
			query.setParameter("nomePessoa", "%" + nome + "%");
		}

		return query.getResultList();
	}
}
