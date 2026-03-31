package services;

import dao.DaoFactory;
import dao.IUsuarioDao;
import model.Usuario;
import utils.HashUtil;

/**
 * Implementación de IAuthService.
 * Maneja la lógica de autenticación: hashea la contraseña
 * y delega la consulta al DAO.
 * El Service depende de la interfaz IUsuarioDao, no de UsuarioDao directamente.
 */
public class AuthService implements IAuthService {

    // Depende de la interfaz, no de la clase concreta (polimorfismo)
    private final IUsuarioDao usuarioDao;

    /**
     * Constructor: obtiene el DAO a través de la fábrica.
     * Nunca crea UsuarioDao directamente — respeta el patrón Factory.
     */
    public AuthService() {
        this.usuarioDao = DaoFactory.getUsuarioDao();
    }

    /**
     * Autentica un usuario comparando username y contraseña hasheada.
     *username nombre de usuario ingresado en el Login
     * password contraseña en texto plano (se hashea aquí antes de comparar)
     * objeto Usuario si las credenciales son válidas, null si no
     */
    @Override
    public Usuario login(String username, String password) {
        // Nunca se guarda ni compara contraseña en texto plano
        // Se hashea con SHA-256 antes de consultar la BD
        String hashed = HashUtil.sha256(password);
        return usuarioDao.login(username, hashed);
    }
}