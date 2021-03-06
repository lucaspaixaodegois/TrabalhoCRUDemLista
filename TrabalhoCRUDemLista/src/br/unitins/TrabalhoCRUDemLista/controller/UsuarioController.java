package br.unitins.TrabalhoCRUDemLista.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.unitins.TrabalhoCRUDemLista.application.Util;
import br.unitins.TrabalhoCRUDemLista.model.Perfil;
import br.unitins.TrabalhoCRUDemLista.model.Telefone;
import br.unitins.TrabalhoCRUDemLista.model.Usuario;

@Named
@ApplicationScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = -398646171892892491L;

	private Usuario usuario;
	private Telefone telefone;

	private List<Usuario> listaUsuario;
	private List<Telefone> listaTelefone;

	public List<Telefone> getListaTelefone() {
		return listaTelefone;
	}

	public void setListaTelefone(List<Telefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}

	public Telefone getTelefone() {
		if (telefone == null)
			telefone = new Telefone();
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
			listaTelefone = new ArrayList<Telefone>();
			listaTelefone.add(new Telefone("63", "90239432"));
			listaUsuario.add(new Usuario(1, "Joao", "joao", "123", Perfil.FUNCIONARIO, listaTelefone));
			listaTelefone = null;
		}
		return listaUsuario;
	}

	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void editar(Usuario usuario) {
		setUsuario((Usuario) usuario.getClone());
	}

	public void incluir() {
		// validacao de senha no servidor
		if (getUsuario().getSenha() == null || getUsuario().getSenha().trim().equals("")) {
			Util.addMessageError("A senha deve ser informada.");
			return;
		}
		if ((getTelefone().getDdd() == null || getTelefone().getDdd().trim().equals(""))
				|| (getTelefone().getTelefone() == null || getTelefone().getTelefone().trim().equals(""))) {
			getUsuario().getListaTelefone().add(new Telefone(telefone.getDdd(), telefone.getTelefone()));
		}
		getListaUsuario().add(getUsuario());
		limpar();
		listaTelefone = null;
		telefone = null;

	}

	public void alterar() {
		// validacao de senha no servidor
		if (getUsuario().getSenha() == null || getUsuario().getSenha().trim().equals("")) {
			Util.addMessageError("A senha deve ser informada.");
			return;
		}

		// obtendo o indice (posicao da lista)
		int index = getListaUsuario().indexOf(getUsuario());
		System.out.println(getUsuario().getId());
		if (index != -1) {
			// alterando a posicao da lista com um novo usuario
			getListaUsuario().set(index, getUsuario());
			limpar();
		}

	}

	public void excluir() {
		int index = getListaUsuario().indexOf(getUsuario());
		getListaUsuario().remove(index);
		if (index != -1)
			limpar();
	}

	public void limpar() {
		usuario = null;
		telefone = null;
	}

	public void incluirTelefone() {
		if ((getTelefone().getDdd() == null)
				|| (getTelefone().getTelefone() == null)) {
			return;
		}
		int index = getListaUsuario().indexOf(getUsuario());
		getListaUsuario().set(index, getUsuario());

		getUsuario().getListaTelefone().add(new Telefone(telefone.getDdd(), telefone.getTelefone()));
		if (index != -1) {
			// alterando a posicao da lista com um novo usuario
			getListaUsuario().set(index, getUsuario());
			limpar();
		}
		listaTelefone = null;
		telefone = null;
	}
}