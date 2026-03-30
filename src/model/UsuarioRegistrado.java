package model;

/**
 * Subclase de Usuario que representa a un usuario ya registrado en el sistema.
 * Aplica HERENCIA — extiende Usuario agregando el campo fechaRegistro.
 * Aplica POLIMORFISMO — sobreescribe toString() con información adicional.
 */
public class UsuarioRegistrado extends Usuario {

    // Campo adicional que solo tiene un usuario registrado
    private String fechaRegistro;

    /**
     * Constructor que llama al constructor protegido de Usuario (super)
     * y agrega la fecha de registro propia de esta subclase.
     *
     * @param username      nombre de usuario
     * @param nombre        nombre real
     * @param apellido      apellido
     * @param telefono      número de teléfono
     * @param correo        correo electrónico
     * @param passwordHash  contraseña hasheada
     * @param fechaRegistro fecha en que se registró
     */
    public UsuarioRegistrado(String username, String nombre, String apellido,
                             String telefono, String correo,
                             String passwordHash, String fechaRegistro) {
        // Llama al constructor protegido de la clase padre
        super(username, nombre, apellido, telefono, correo, passwordHash);
        this.fechaRegistro = fechaRegistro;
    }

    // ── Getter y setter de fechaRegistro 
    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Polimorfismo — sobreescribe toString() de Usuario
     * agregando el tipo de usuario y su fecha de registro.
     */
    @Override
    public String toString() {
        return super.toString() + " (Registrado el: " + fechaRegistro + ")";
    }
}