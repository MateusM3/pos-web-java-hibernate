package br.edu.guairaca.hibernate.view;

import java.util.List;

import br.edu.guairaca.hibernate.dao.CidadeDAO;
import br.edu.guairaca.hibernate.dao.EstadoDAO;
import br.edu.guairaca.hibernate.model.Cidade;
import br.edu.guairaca.hibernate.model.Estado;

public class CidadeView {
	/**
	public static void main(String[] args) {
		Estado e1 = EstadoDAO.getInstance().getById(1);
		listarCidadesPorEstado(e1);
	}
	public static void salvarCidade() {
		Estado e1 = EstadoDAO.getInstance().getById(14);
		Cidade c1 = new Cidade();
		c1.setEstado(e1);
		c1.setNome("Teste");
		CidadeDAO.getInstance().persist(c1);
	}
	
	public static void listarCidadesPorEstado() {
		List<Estado> listaEstado = EstadoDAO.getInstance().findAll();
		
		for (Estado e : listaEstado) {
			System.out.println(e.getNome());
			
			for(Cidade c : e.getCidades()) {
				System.out.println(" Cidades "+c.getNome());
			}
		}
	}
	
	public static void listarCidadesPorEstado(Estado estado) {
		List<Cidade> listaCidade = CidadeDAO.getInstance().buscarPorEstado(estado);
		for (Cidade c : listaCidade) {
			System.out.println(estado.getSigla()+ " - "+ c.getNome());
		}
	}
	**/
}
