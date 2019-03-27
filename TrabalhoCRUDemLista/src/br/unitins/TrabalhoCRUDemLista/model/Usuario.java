package br.unitins.TrabalhoCRUDemLista.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends DefaultEntity {

	private String nome;
	private String login;
	private String senha;
	private Perfil perfil;

	private List<Telefone> listaTelefone;

	public Usuario() {

	}

	public Usuario(Integer id, String nome, String login, String senha, Perfil perfil, List<Telefone> listaTelefone) {
		super();
		setId(id);
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
		this.listaTelefone = listaTelefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Telefone> getListaTelefone() {
		if (listaTelefone == null) {
			listaTelefone = new ArrayList<Telefone>();
		}
		return listaTelefone;
	}

	public void setListaTelefone(List<Telefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}

}