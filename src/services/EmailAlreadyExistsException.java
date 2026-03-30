package services;

/**
 * Excepción lanzada cuando se intenta registrar un username que ya existe.
 * Es una RuntimeException para no obligar a declararla con throws en cada método.
 * El RegisterController la captura y muestra el mensaje al usuario.
 */
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}