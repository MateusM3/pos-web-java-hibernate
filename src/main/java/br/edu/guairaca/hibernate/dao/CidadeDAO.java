package br.edu.guairaca.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.edu.guairaca.hibernate.model.Cidade;
import br.edu.guairaca.hibernate.model.Estado;

@Transactional
public class CidadeDAO extends GenericDAO<Cidade, Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	protected CidadeDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> buscarPorEstado(Estado estado) {
		StringBuffer hql = new StringBuffer();
		hql.append("select c from Cidade c where c.estado.codigo = :codigoEstado");

		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("codigoEstado", estado.getCodigo());

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> buscarPorNomeEEstado(String nome, Estado estado) {
		StringBuffer hql = new StringBuffer();
		hql.append("select c from Cidade c where 1 = 1 ");

		if (estado != null) {
			hql.append("and c.estado.codigo = :codigoEstado ");
		}

		if (nome != null && !nome.isEmpty()) {
			hql.append("and c.nome = :nomeCidade");
		}

		Query query = entityManager.createQuery(hql.toString());
		if (estado != null) {
			query.setParameter("codigoEstado", estado.getCodigo());
		}

		if (nome != null && !nome.isEmpty()) {
			query.setParameter("nomeCidade", nome);
		}

		return query.getResultList();
	}
}
