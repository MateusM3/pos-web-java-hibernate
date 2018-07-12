package br.edu.guairaca.hibernate.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.guairaca.hibernate.dao.CidadeDAO;
import br.edu.guairaca.hibernate.dao.EstadoDAO;
import br.edu.guairaca.hibernate.dao.PessoaDAO;
import br.edu.guairaca.hibernate.model.Cidade;
import br.edu.guairaca.hibernate.model.Estado;
import br.edu.guairaca.hibernate.model.Pessoa;

@Named
@SessionScoped
public class PessoaBean extends AbstractController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CidadeDAO cidadeDAO;
	@Inject
	private EstadoDAO estadoDAO;
	@Inject
	private PessoaDAO pessoaDAO;

	private Pessoa pessoa;

	private List<Estado> estados;
	private List<Cidade> cidades;
	private List<Pessoa> pessoas;

	private String nomePessoaPesquisa;

	private Estado estadoManutencao;

	public void pesquisar() {
		this.pessoas = this.pessoaDAO.buscarPorNome(this.nomePessoaPesquisa);
	}

	public String incluir() {
		this.pessoa = new Pessoa();
		this.estados = this.estadoDAO.findAll();
		this.estadoManutencao = null;
		this.cidades = null;
		return "/paginas/pessoa/manterPessoa.xhtml?faces-redirect=true";
	}

	public String cancelar() {
		this.pessoa = new Pessoa();
		pesquisar();
		return "/paginas/pessoa/pesquisarPessoa.xhtml?faces-redirect=true";
	}

	public String alterar(Pessoa pessoa) {
		this.pessoa = pessoaDAO.find(pessoa.getCodigo());
		this.estados = this.estadoDAO.findAll();
		this.estadoManutencao = this.estadoDAO.find(this.pessoa.getNaturalidade().getEstado().getCodigo());
		atualizarCidade();
		return "/paginas/pessoa/manterPessoa.xhtml?faces-redirect=true";
	}

	public void excluir(Pessoa pessoa) {
		this.pessoa = pessoaDAO.find(pessoa.getCodigo());
		this.pessoaDAO.remove(this.pessoa);
		this.pessoa = new Pessoa();
		pesquisar();
		mensagemInformacao("", "Pessoa Excluída com sucesso.");
	}

	public void salvar() {
		try {
			if (this.pessoa.getCodigo() == null) {
				this.pessoaDAO.persist(this.pessoa);
				mensagemInformacao("", "Pessoa salva com sucesso.");
				this.pessoa = new Pessoa();
				this.estadoManutencao = null;
			} else {
				this.pessoaDAO.merge(this.pessoa);
				mensagemInformacao("", "Pessoa Alterada com sucesso.");
			}
		} catch (Exception e) {
			mensagemErroFatal(e, "Erro inesperado", "Erro ao cadastrar autor");
		}
	}

	public String iniciar() {
		this.pessoas = null;

		return "/paginas/pessoa/pesquisarPessoa.xhtml?faces-redirect=true";
	}

	public void atualizarCidade() {
		if (this.estadoManutencao == null) {
			this.cidades = null;
		} else {
			this.cidades = this.cidadeDAO.buscarPorEstado(estadoManutencao);
		}
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public String getNomePessoaPesquisa() {
		return nomePessoaPesquisa;
	}

	public void setNomePessoaPesquisa(String nomePessoaPesquisa) {
		this.nomePessoaPesquisa = nomePessoaPesquisa;
	}

	public Estado getEstadoManutencao() {
		return estadoManutencao;
	}

	public void setEstadoManutencao(Estado estadoManutencao) {
		this.estadoManutencao = estadoManutencao;
	}

}