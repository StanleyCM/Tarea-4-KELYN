package controller;

import services.UsuarioService;

public class RegisterController {

    private final UsuarioService usuarioService;

    public RegisterController() {
        this.usuarioService = new UsuarioService();
    }

    /**
     * metodos para el registro, y en cada caso su alerta de que los datos no fueron ingresados
     */
    public boolean register(String username, String nombre, String apellido, String telefono, String correo, String password, String confirmPassword) {
        if (username == null || username.isBlank()) throw new IllegalArgumentException("El campo 'nombre de usuario' es obligatorio");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El campo 'nombre' es obligatorio");
        if (apellido == null || apellido.isBlank()) throw new IllegalArgumentException("El campo 'apellido' es obligatorio");
        if (telefono == null || telefono.isBlank()) throw new IllegalArgumentException("El campo 'telefono' es obligatorio");
        if (correo == null || correo.isBlank()) throw new IllegalArgumentException("El campo 'correo' es obligatorio");
        if (password == null || password.isBlank()) throw new IllegalArgumentException("El campo 'contraseña' es obligatorio");
        if (confirmPassword == null || confirmPassword.isBlank()) throw new IllegalArgumentException("El campo 'confirmar contraseña' es obligatorio");
        if (!password.equals(confirmPassword)) throw new IllegalArgumentException("La contraseña y la confirmación no coinciden");

        return usuarioService.registrarUsuario(username, nombre, apellido, telefono, correo, password);
    }
}