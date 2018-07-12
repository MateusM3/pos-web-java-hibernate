package br.edu.guairaca.hibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PESSOA")
public class Pessoa {

	@Id
	@GeneratedValue
	@Column(name = "CD_PESSOA", nullable = false)
	private Long codigo;
	@Column(name = "NM_PESSOA", nullable = false, length = 100)
	private String nome;
	@Column(name = "DT_NASCIMENTO", nullable = false)
	private Date dataNascimento;
	@Column(name = "DS_CPF", nullable = false, length = 15)
	private String cpf;
	@ManyToOne
	@JoinColumn(name = "CD_CIDADE", nullable = false)
	private Cidade naturalidade;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Cidade getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(Cidade naturalidade) {
		this.naturalidade = naturalidade;
	}

}
