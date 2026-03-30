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
     * Attempts to login and returns the authenticated Usuario or null if credentials invalid.
     * Throws IllegalArgumentException if input is missing (so the view can show the required message).
     */
    public Usuario login(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("debe ingresar su usuario y contraseña, si no está registrado debe registrarse");
        }
        return authService.login(username, password);
    }
}