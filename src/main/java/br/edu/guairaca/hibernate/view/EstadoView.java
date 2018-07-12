package br.edu.guairaca.hibernate.view;

import java.util.List;

import br.edu.guairaca.hibernate.dao.EstadoDAO;
import br.edu.guairaca.hibernate.model.Estado;

public class EstadoView {
	public static void main(String[] args) {
		/**
		Estado estado = new Estado();
		estado.setNome("bahia");
		estado.setSigla("bh");
		EstadoDAO.getInstance().persist(estado);
//		
//		Estado estadoAlterado = EstadoDAO.getInstance().getById(2);
//		estadoAlterado.setNome("Batata");
//		EstadoDAO.getInstance().merge(estadoAlterado);
//		
//		Estado estadoExcluido = EstadoDAO.getInstance().getById(2);
//		EstadoDAO.getInstance().remove(estadoAlterado);
		
//		List<Estado> lista = EstadoDAO.getInstance().findAll();
		List<Estado> lista = EstadoDAO.getInstance().buscarPorSigla("PR");
		for(Estado e : lista) {
			System.out.println(e.getCodigo());
			System.out.println(e.getNome());
			System.out.println(e.getSigla());
		}
		
		**/	
		
	}
}
