package br.edu.guairaca.hibernate.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.guairaca.hibernate.dao.CidadeDAO;
import br.edu.guairaca.hibernate.dao.EstadoDAO;
import br.edu.guairaca.hibernate.model.Cidade;
import br.edu.guairaca.hibernate.model.Estado;

@Named
@SessionScoped
public class CidadeBean extends AbstractController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CidadeDAO dao;
	@Inject
	private EstadoDAO estadoDAO;

	private Cidade cidade;

	private List<Cidade> cidades;

	private List<Estado> estados;

	private String nomeCidade;
	private Estado estadoSelecionadoPesquisa;

	@PostConstruct
	public void init() {
		this.estados = this.estadoDAO.findAll();
	}

	public void pesquisar() {
		this.cidades = dao.buscarPorNomeEEstado(this.nomeCidade, this.estadoSelecionadoPesquisa);
	}

	public String incluir() {
		this.cidade = new Cidade();
		return "/paginas/cidade/manterCidade.xhtml?faces-redirect=true";
	}

	public String cancelar() {
		this.cidade = new Cidade();
		pesquisar();
		return "/paginas/cidade/pesquisarCidade.xhtml?faces-redirect=true";
	}

	public String alterar(Cidade cidade) {
		this.cidade = dao.find(cidade.getCodigo());
		return "/paginas/cidade/manterCidade.xhtml?faces-redirect=true";
	}

	public void excluir(Cidade cidade) {
		this.cidade = dao.find(cidade.getCodigo());
		this.dao.remove(this.cidade);
		this.cidade = new Cidade();
		pesquisar();
		mensagemInformacao("", "Cidade Exclu�do com sucesso.");
	}

	public void salvar() {
		try {
			if (this.cidade.getCodigo() == null) {
				this.dao.persist(this.cidade);
				mensagemInformacao("", "Cidade salvo com sucesso.");
				this.cidade = new Cidade();
			} else {
				this.dao.merge(this.cidade);
				mensagemInformacao("", "Cidade Alterado com sucesso.");
			}
		} catch (Exception e) {
			mensagemErroFatal(e, "Erro inesperado", "Erro ao cadastrar autor");
		}
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstadoSelecionadoPesquisa() {
		return estadoSelecionadoPesquisa;
	}

	public void setEstadoSelecionadoPesquisa(Estado estadoSelecionadoPesquisa) {
		this.estadoSelecionadoPesquisa = estadoSelecionadoPesquisa;
	}

	public String iniciar() {
		this.cidades = null;
		this.nomeCidade = null;
		this.estadoSelecionadoPesquisa = null;
		this.estados = this.estadoDAO.findAll();

		return "/paginas/cidade/pesquisarCidade.xhtml?faces-redirect=true";
	}
}