package services;

import java.util.List;

import model.Usuario;

public interface IUsuarioService {
    Usuario login(String username, String password);
    boolean registrarUsuario(String username, String nombre, String apellido, String telefono, String correo, String password);
    List<Usuario> listarTodos();
    boolean actualizarUsuario(Usuario usuario);
    boolean eliminarUsuario(int id);
}
