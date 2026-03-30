package model;

/**
 * Clase base que representa un usuario del sistema.
 * Aplica encapsulamiento (atributos privados, solo getters)
 * y el patrón Builder para construir instancias de forma segura.
 *
 * Es la clase padre de UsuarioRegistrado (herencia).
 */
public class Usuario {

    // ── Atributos encapsulados 
    // Todos privados — se acceden solo mediante getters
    private int    id;
    private String username;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String passwordHash;

    /**
     * Constructor privado usado por el Builder.
     * Nadie puede hacer new Usuario() directamente desde fuera.
     */
    private Usuario(Builder b) {
        this.id           = b.id;
        this.username     = b.username;
        this.nombre       = b.nombre;
        this.apellido     = b.apellido;
        this.telefono     = b.telefono;
        this.correo       = b.correo;
        this.passwordHash = b.passwordHash;
    }

    /**
     * Constructor protegido — permite que las subclases (UsuarioRegistrado)
     * puedan llamar super() sin romper el encapsulamiento del Builder.
     */
    protected Usuario(String username, String nombre, String apellido,
                      String telefono, String correo, String passwordHash) {
        this.username     = username;
        this.nombre       = nombre;
        this.apellido     = apellido;
        this.telefono     = telefono;
        this.correo       = correo;
        this.passwordHash = passwordHash;
    }

    //Getters
    public int    getId()           { return id; }
    public String getUsername()     { return username; }
    public String getNombre()       { return nombre; }
    public String getApellido()     { return apellido; }
    public String getTelefono()     { return telefono; }
    public String getCorreo()       { return correo; }
    public String getPasswordHash() { return passwordHash; }

    /**
     * Punto de entrada al patrón Builder.
 	 * Permite construir un Usuario paso a paso con validaciones.
     */
    public static Builder builder() { return new Builder(); }

    //  Patrón Builder 
    /**
     * Permite construir un Usuario paso a paso.
     * Cada método set retorna el mismo Builder (encadenamiento fluido).
     */
    public static class Builder {
        private int    id;
        private String username;
        private String nombre;
        private String apellido;
        private String telefono;
        private String correo;
        private String passwordHash;

        public Builder id(int id)               { this.id = id;               return this; }
        public Builder username(String u)       { this.username = u;          return this; }
        public Builder nombre(String n)         { this.nombre = n;            return this; }
        public Builder apellido(String a)       { this.apellido = a;          return this; }
        public Builder telefono(String t)       { this.telefono = t;          return this; }
        public Builder correo(String c)         { this.correo = c;            return this; }
        public Builder passwordHash(String p)   { this.passwordHash = p;      return this; }

        /**
         * Construye el objeto Usuario.
         * Valida que username no esté vacío antes de crear la instancia.
         */
        public Usuario build() {
            if (username == null || username.isBlank())
                throw new IllegalStateException("El username es obligatorio");
            return new Usuario(this);
        }
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (@" + username + ")";
    }
}