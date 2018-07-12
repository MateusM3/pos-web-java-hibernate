package br.edu.guairaca.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.edu.guairaca.hibernate.model.Estado;

@Transactional
public class EstadoDAO extends GenericDAO<Estado, Long> implements Serializable{
	@Inject 
	protected EstadoDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
	public List<Estado> buscarPorSigla(String sigla){
		StringBuffer hql = new StringBuffer();
		hql.append("select e from Estado e where e.sigla = :sigla");
		Query query = entityManager.createQuery(hql.toString());
		
		query.setParameter("sigla", sigla);
		return query.getResultList();		
	}
	
	@SuppressWarnings("unchecked")
	public List<Estado> buscarPorNomeESigla(String nome, String sigla) {
		if(nome != null && nome.isEmpty()) {
			nome = null;
		}
		
		if(sigla != null && sigla.isEmpty()) {
			sigla = null;
		}
		
		StringBuffer hql = new StringBuffer();
		hql.append("select e from Estado e where e.sigla = coalesce(:sigla, e.sigla) and e.nome = coalesce(:nome, e.nome)");

		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("sigla", sigla);
		query.setParameter("nome", nome);

		return query.getResultList();
	}

}
