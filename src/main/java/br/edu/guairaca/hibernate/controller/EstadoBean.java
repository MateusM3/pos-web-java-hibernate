package br.edu.guairaca.hibernate.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.guairaca.hibernate.dao.EstadoDAO;
import br.edu.guairaca.hibernate.model.Estado;

@Named
@SessionScoped
public class EstadoBean extends AbstractController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoDAO dao;

	private Estado estado;

	private String nomeEstado;
	private String siglaEstado;

	private List<Estado> estados;

	public void pesquisar() {
		this.estados = dao.buscarPorNomeESigla(this.nomeEstado, this.siglaEstado);
	}

	public String incluir() {
		this.estado = new Estado();

		return "/paginas/estado/manterEstado.xhtml?faces-redirect=true";
	}

	public String cancelar() {
		this.estado = new Estado();

		pesquisar();
		return "/paginas/estado/pesquisarEstado.xhtml?faces-redirect=true";
	}

	public String alterar(Estado estado) {
		this.estado = dao.find(estado.getCodigo());
		return "/paginas/estado/manterEstado.xhtml?faces-redirect=true";
	}

	public void excluir(Estado estado) {
		this.estado = dao.find(estado.getCodigo());
		this.dao.remove(this.estado);
		this.estado = new Estado();
		pesquisar();
		mensagemInformacao("", "Estado Exclu�do com sucesso.");
	}

	public void salvar() {
		try {
			if (this.estado.getCodigo() == null) {
				this.dao.persist(this.estado);
				mensagemInformacao("", "Estado salvo com sucesso.");
				this.estado = new Estado();
			} else {
				this.dao.merge(this.estado);
				mensagemInformacao("", "Estado Alterado com sucesso.");
			}
		} catch (Exception e) {
			mensagemErroFatal(e, "Erro inesperado", "Erro ao cadastrar autor");
		}
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	public String getSiglaEstado() {
		return siglaEstado;
	}

	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public String iniciar() {
		this.estados = null;
		this.nomeEstado = null;
		this.siglaEstado = null;

		return "/paginas/estado/pesquisarEstado.xhtml?faces-redirect=true";
	}
}