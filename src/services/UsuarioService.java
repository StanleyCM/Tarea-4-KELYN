package services;

import java.util.List;

import dao.DaoFactory;
import dao.IUsuarioDao;
import model.Usuario;
import utils.HashUtil;

/**
 * Capa de lógica de negocio para operaciones de usuario.
 * Implementa IUsuarioService (contrato).
 * Nunca accede directamente a la BD — delega siempre al DAO.
 * Es la única clase que conoce las reglas de negocio
 * (validaciones, hashing, existencia de duplicados).
 */
public class UsuarioService implements IUsuarioService {

    // Depende de la interfaz para respetar el patrón Factory y polimorfismo
    private final IUsuarioDao usuarioDao;

    /**
     * Constructor: obtiene el DAO desde la fábrica.
     * Así si mañana cambia la implementación del DAO,
     * UsuarioService no necesita tocarse.
     */
    public UsuarioService() {
        this.usuarioDao = DaoFactory.getUsuarioDao();
    }

    /**
     * Autentica un usuario.
     * Hashea la contraseña antes de pasarla al DAO.
     * return Usuario si las credenciales son válidas, null si no
     */
    @Override
    public Usuario login(String username, String password) {
        if (username == null || username.isBlank() ||
            password == null || password.isBlank()) {
            return null; // el controller se encarga del mensaje de error
        }
        String hashed = HashUtil.sha256(password);
        return usuarioDao.login(username, hashed);
    }

    /**
     * Registra un usuario nuevo en el sistema.
     * Valida cada campo obligatorio individualmente para dar
     * mensajes de error precisos (requisito de la tarea).
     * Lanza excepciones específicas para username/correo duplicados.
     *
     * IllegalArgumentException      si algún campo está vacío
     * UserAlreadyExistsException    si el username ya existe
     * EmailAlreadyExistsException   si el correo ya existe
     */
    @Override
    public boolean registrarUsuario(String username, String nombre, String apellido,
                                    String telefono, String correo, String password) {
        // Validación campo por campo — así el mensaje indica exactamente cuál falta
        if (username == null || username.isBlank())
            throw new IllegalArgumentException("El campo 'nombre de usuario' es obligatorio");
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El campo 'nombre' es obligatorio");
        if (apellido == null || apellido.isBlank())
            throw new IllegalArgumentException("El campo 'apellido' es obligatorio");
        if (telefono == null || telefono.isBlank())
            throw new IllegalArgumentException("El campo 'teléfono' es obligatorio");
        if (correo == null || correo.isBlank())
            throw new IllegalArgumentException("El campo 'correo' es obligatorio");
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("El campo 'contraseña' es obligatorio");

        // Verifica duplicados antes de intentar insertar
        if (usuarioDao.existeUsername(username))
            throw new UserAlreadyExistsException("El nombre de usuario '" + username + "' ya está en uso");
        if (usuarioDao.existeCorreo(correo))
            throw new EmailAlreadyExistsException("El correo '" + correo + "' ya está registrado");

        // La contraseña NUNCA se guarda en texto plano — siempre se hashea
        String hashed = HashUtil.sha256(password);

        // Construye el objeto con el patrón Builder
        Usuario u = Usuario.builder()
                .username(username)
                .nombre(nombre)
                .apellido(apellido)
                .telefono(telefono)
                .correo(correo)
                .passwordHash(hashed)
                .build();

        return usuarioDao.crear(u);
    }

    /**
     * Retorna todos los usuarios para mostrar en el Dashboard.
     * El DAO los devuelve ordenados por nombre.
     */
    @Override
    public List<Usuario> listarTodos() {
        return usuarioDao.listarTodos();
    }

    /**
     * Actualiza los datos de un usuario existente.
     * usuario debe tener un id válido para identificar el registro
     * true si la actualización fue exitosa
     */
    @Override
    public boolean actualizarUsuario(Usuario usuario) {
        if (usuario == null) return false;
        return usuarioDao.actualizar(usuario);
    }

    /**
     * Elimina un usuario por su id.
     * @param id identificador único del usuario a eliminar
     */
    @Override
    public boolean eliminarUsuario(int id) {
        return usuarioDao.eliminar(id);
    }
}