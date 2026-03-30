package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.RegisterController;
import services.EmailAlreadyExistsException;
import services.UserAlreadyExistsException;

/**
 * Vista de Registro de usuarios.
 * Permite a nuevos usuarios crear una cuenta ingresando su información.
 *

 */
public class Register extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel         contentPane;

    // ── Campos del formulario ────────────────────────────────────
    private JTextField     txtNombre;
    private JTextField     txtApellido;
    private JTextField     txtUsername;
    private JTextField     txtTelefono;     
    private JTextField     txtCorreo;        
    private JPasswordField txtContrasena;
    private JPasswordField txtConfirmar;

    // Controller que maneja validaciones y llamada al service
    private final RegisterController registerController = new RegisterController();

    // Referencia al Dashboard padre (si se abre desde ahí)
    // Si viene del Login, será null
    private Dashboard parentDashboard;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new Register().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /** Constructor sin padre — se usa cuando viene desde el Login */
    public Register() {
        this(null);
    }

    /**
     * Constructor con referencia al Dashboard padre.
     * Si parent != null, al registrar exitosamente refresca la tabla.
     * @param parent Dashboard que abrió esta ventana (puede ser null)
     */
    public Register(Dashboard parent) {
        this.parentDashboard = parent;

        setTitle("Registro de Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // no cierra toda la app
        setBounds(100, 100, 400, 480);

        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // ── Título ───────────────────────────────────────────────
        JLabel lblTitulo = new JLabel("Registro");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitulo.setBounds(130, 11, 150, 40);
        contentPane.add(lblTitulo);

        // ── Nombre ───────────────────────────────────────────────
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(80, 65, 120, 14);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(80, 82, 220, 22);
        contentPane.add(txtNombre);

        // ── Apellido ─────────────────────────────────────────────
        JLabel lblApellido = new JLabel("Apellido");
        lblApellido.setBounds(80, 112, 120, 14);
        contentPane.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(80, 129, 220, 22);
        contentPane.add(txtApellido);

        // ── Nombre de usuario ────────────────────────────────────
        JLabel lblUsername = new JLabel("Nombre de Usuario");
        lblUsername.setBounds(80, 159, 150, 14);
        contentPane.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(80, 176, 220, 22);
        contentPane.add(txtUsername);

        // ── Teléfono ─────────────────────────────────────────────
        JLabel lblTelefono = new JLabel("Número de Teléfono");
        lblTelefono.setBounds(80, 206, 150, 14);
        contentPane.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(80, 223, 220, 22);
        contentPane.add(txtTelefono);

        // ── Correo ───────────────────────────────────────────────
        JLabel lblCorreo = new JLabel("Correo Electrónico");
        lblCorreo.setBounds(80, 253, 150, 14);
        contentPane.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(80, 270, 220, 22);
        contentPane.add(txtCorreo);

        // ── Contraseña (oculta) ──────────────────────────────────
        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setBounds(80, 300, 100, 14);
        contentPane.add(lblContrasena);

        // JPasswordField oculta los caracteres mientras escribe
        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(80, 317, 220, 22);
        contentPane.add(txtContrasena);

        // ── Confirmar contraseña (oculta) ────────────────────────
        JLabel lblConfirmar = new JLabel("Confirmar Contraseña");
        lblConfirmar.setBounds(80, 347, 150, 14);
        contentPane.add(lblConfirmar);

        txtConfirmar = new JPasswordField();
        txtConfirmar.setBounds(80, 364, 220, 22);
        contentPane.add(txtConfirmar);

        // ── Botón Registrarse ────────────────────────────────────
        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBounds(130, 400, 130, 28);
        contentPane.add(btnRegistrarse);

        // ── Acción del botón ─────────────────────────────────────
        btnRegistrarse.addActionListener(e -> {
            // Leer todos los campos
            String nombre   = txtNombre.getText();
            String apellido = txtApellido.getText();
            String username = txtUsername.getText();
            String telefono = txtTelefono.getText();
            String correo   = txtCorreo.getText();
            // getPassword() retorna char[] para no dejar la contraseña en el heap
            String password = new String(txtContrasena.getPassword());
            String confirm  = new String(txtConfirmar.getPassword());

            try {
                // El controller valida campos vacíos y coincidencia de contraseñas
                // El service valida duplicados de username/correo
                boolean ok = registerController.register(
                    username, nombre, apellido, telefono, correo, password, confirm
                );

                if (ok) {
                    JOptionPane.showMessageDialog(this,
                        "Registro exitoso. Ya puedes iniciar sesión.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                    // Si se abrió desde el Dashboard, refrescar la tabla
                    if (parentDashboard != null) {
                        parentDashboard.refreshTable();
                    }

                    this.dispose(); // cierra solo esta ventana
                }

            } catch (IllegalArgumentException ex) {
                // Campo vacío o contraseñas no coinciden
                JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Campo requerido",
                    JOptionPane.WARNING_MESSAGE);

            } catch (UserAlreadyExistsException ex) {
                // Username duplicado
                JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Usuario ya existe",
                    JOptionPane.WARNING_MESSAGE);

            } catch (EmailAlreadyExistsException ex) {
                // Correo duplicado
                JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Correo ya registrado",
                    JOptionPane.WARNING_MESSAGE);

            } catch (Exception ex) {
                // Error inesperado — se muestra para facilitar depuración
                JOptionPane.showMessageDialog(this,
                    "Error inesperado: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}