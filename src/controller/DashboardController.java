package controller;

import services.UsuarioService;

import java.util.List;

import model.Usuario;

public class DashboardController {

    private final UsuarioService usuarioService;

    public DashboardController() {
        this.usuarioService = new UsuarioService();
    }

    public List<Usuario> listarUsuarios() {
        return usuarioService.listarTodos();
    }

    public boolean actualizarUsuario(Usuario u) {
        return usuarioService.actualizarUsuario(u);
    }

    public boolean eliminarUsuario(int id) {
        return usuarioService.eliminarUsuario(id);
    }


    public void logout() {
    }
}