package services;

import model.Usuario;

/**
 * Contrato del servicio de autenticación.
 * LoginController depende de esta interfaz, no de AuthService directamente.
 * Permite polimorfismo: si mañana cambias la lógica de auth,
 * el controller no necesita modificarse.
 */
public interface IAuthService {

    Usuario login(String username, String password);
}