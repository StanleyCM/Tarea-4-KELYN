package dao;

import java.util.List;
import model.Usuario;

/**
 * Interfaz (contrato) del DAO de usuarios.
 * Define QUÉ operaciones existen, sin decir CÓMO se implementan.
 * El Service depende de esta interfaz, nunca de UsuarioDao directamente.
 * Eso permite polimorfismo: cambiar la implementación sin tocar el Service.
 */
public interface IUsuarioDao {

    // ── Registro e inserción 

    /** Inserta un nuevo usuario en la BD */
    boolean registrar(Usuario usuario);

    /** Alias de registrar() — mantiene compatibilidad */
    boolean crear(Usuario usuario);

    // ── Autenticación

    /** Retorna el Usuario si username+passwordHash coinciden, null si no */
    Usuario login(String username, String passwordHash);

    // ── Consultas 

    /** Retorna todos los usuarios para mostrar en el Dashboard */
    List<Usuario> listarTodos();

    // ── CRUD

    /** Actualiza nombre, apellido, teléfono y correo */
    boolean actualizar(Usuario usuario);

    /** Elimina un usuario por su id */
    boolean eliminar(int id);

    // ── Validaciones previas al registro 

    /** true si ese username ya existe en la BD */
    boolean existeUsername(String username);

    /** true si ese correo ya existe en la BD */
    boolean existeCorreo(String correo);
}