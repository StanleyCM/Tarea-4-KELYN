package controller;

import model.Usuario;
import services.IAuthService;
import services.AuthService;

public class LoginController {

    private final IAuthService authService;

    public LoginController() {
        this.authService = new AuthService();
    }

    /**
     * Metodos para llenar el login, y alerta cuando alguno de los datos no es ingresado
     */
    public Usuario login(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("debe ingresar su usuario y contraseña, si no está registrado debe registrarse");
        }
        return authService.login(username, password);
    }
}