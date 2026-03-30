package services;

/**
 * Excepción lanzada cuando se intenta registrar un correo que ya existe.
 * Separada de UserAlreadyExistsException para dar mensajes de error precisos.
 */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}