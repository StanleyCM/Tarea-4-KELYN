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

import controller.LoginController;
import model.Usuario;

/**
 * Vista del Login.
 * Solo maneja la interfaz gráfica — toda la lógica
 * va al LoginController, que a su vez llama al AuthService.
 *
 * CORRECCIONES:
 * - JFormattedTextField → JTextField (no necesitaba formato especial)
 * - El botón Registrarse ahora abre Register sin cerrar Login
 *   (así el usuario puede volver si cancela el registro)
 */
public class Login extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel         contentPane;
    private JTextField     txtUsuario;      // campo de texto simple para el username
    private JPasswordField txtContrasena;   // oculta caracteres automáticamente

    // El controller maneja la lógica — Login no habla con services ni DAO
    private final LoginController loginController = new LoginController();

    /** Punto de entrada para probar solo esta ventana */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /** Constructor — construye y configura todos los componentes */
    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 658, 390);

        // Panel principal con fondo gris claro y márgenes
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // ── Título ───────────────────────────────────────────────
        JLabel lblTitulo = new JLabel("Login");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitulo.setBounds(263, 12, 79, 50);
        contentPane.add(lblTitulo);

        // ── Campo: nombre de usuario ─────────────────────────────
        JLabel lblUsuario = new JLabel("Nombre de Usuario");
        lblUsuario.setBounds(221, 106, 181, 14);
        contentPane.add(lblUsuario);

        // JTextField simple — JFormattedTextField no aportaba nada aquí
        txtUsuario = new JTextField();
        txtUsuario.setBounds(221, 122, 167, 20);
        txtUsuario.setColumns(50);
        txtUsuario.setBackground(Color.WHITE);
        txtUsuario.setForeground(Color.BLACK);
        contentPane.add(txtUsuario);

        // ── Campo: contraseña (oculta con puntos) ────────────────
        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setBounds(221, 153, 181, 14);
        contentPane.add(lblContrasena);

        // JPasswordField oculta los caracteres mientras el usuario escribe
        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(221, 167, 165, 20);
        contentPane.add(txtContrasena);

        // ── Botón: Iniciar sesión ────────────────────────────────
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(243, 211, 124, 23);
        contentPane.add(btnLogin);

        // ── Enlace a registro ────────────────────────────────────
        JLabel lblNoTienesCuenta = new JLabel("¿No tienes una cuenta?");
        lblNoTienesCuenta.setBounds(241, 245, 135, 14);
        contentPane.add(lblNoTienesCuenta);

        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBounds(243, 264, 124, 23);
        contentPane.add(btnRegistrarse);

        // ── Acción: Iniciar sesión ───────────────────────────────
        btnLogin.addActionListener(e -> {
            String user = txtUsuario.getText();
            // getPassword() retorna char[] — se convierte a String para el controller
            String pass = new String(txtContrasena.getPassword());

            try {
                // El controller valida que no estén vacíos y llama al service
                Usuario u = loginController.login(user, pass);

                if (u != null) {
                    // Login exitoso: abrir Dashboard y cerrar Login
                    Dashboard dashboard = new Dashboard();
                    dashboard.setLocationRelativeTo(this);
                    dashboard.setVisible(true);
                    dashboard.refreshTable(); // carga la lista de usuarios al abrir
                    this.dispose();           // cierra la ventana de login
                } else {
                    // Credenciales incorrectas — usuario o contraseña no coinciden
                    JOptionPane.showMessageDialog(this,
                        "Usuario o contraseña incorrectos",
                        "Error de autenticación",
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (IllegalArgumentException ex) {
                // El controller lanzó esta excepción porque algún campo está vacío
                JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Campos requeridos",
                    JOptionPane.WARNING_MESSAGE);
            }
        });

        // ── Acción: Ir a Registro ────────────────────────────────
        btnRegistrarse.addActionListener(e -> {
            // Abre el registro. Login no se cierra aquí —
            // si el usuario cancela el registro, puede volver al login
            Register register = new Register();
            register.setLocationRelativeTo(this);
            register.setVisible(true);
        });
    }
}