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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import java.awt.TextField;
import java.awt.Cursor;
import java.awt.Panel;

/**
 * Vista del Login.
 * Solo maneja la interfaz gráfica — toda la lógica
 * va al LoginController, que a su vez llama al AuthService.
 */
public class Login extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel         contentPane;
    private JTextField     txtUsuario;      // campo de texto simple para el username
    private JPasswordField txtContrasena;   // oculta caracteres automáticamente

    // El controller maneja la lógica — Login no habla con services ni DAO
    private final LoginController loginController = new LoginController();
    private final JPanel panel = new JPanel();

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
    	setResizable(false);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);

        // Panel principal con fondo gris claro y márgenes
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        panel.setBackground(new Color(255, 255, 255));
        panel.setForeground(Color.CYAN);
        panel.setBounds(0, 0, 784, 461);
        contentPane.add(panel);
        panel.setLayout(null);
        
        Panel panel_1_1 = new Panel();
        panel_1_1.setBackground(Color.BLACK);
        panel_1_1.setBounds(44, 271, 370, 2);
        panel.add(panel_1_1);
        
        Panel panel_1 = new Panel();
        panel_1.setBackground(Color.BLACK);
        panel_1.setBounds(44, 206, 370, 2);
        panel.add(panel_1);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(566, 38, 150, 150);
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Stanley\\Desktop\\Tarea4Kelyn\\Tarea4Kelyn\\bin\\images\\Owner_Blue_220x220 (1).png"));
        panel.add(lblNewLabel_1);
        
                //  Título 
                JLabel lblTitulo = new JLabel("Iniciar Sesión");
                lblTitulo.setBounds(101, 46, 227, 31);
                panel.add(lblTitulo);
                lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 28));
                
                        //  Campo: nombre de usuario 
                        JLabel lblUsuario = new JLabel("Nombre de Usuario");
                        lblUsuario.setFont(new Font("Rockwell", Font.PLAIN, 17));
                        lblUsuario.setBounds(44, 145, 204, 32);
                        panel.add(lblUsuario);
                        
                                //  Campo: contraseña (oculta con puntos)
                                JLabel lblContrasena = new JLabel("Contraseña");
                                lblContrasena.setFont(new Font("Rockwell", Font.PLAIN, 17));
                                lblContrasena.setBounds(44, 214, 204, 32);
                                panel.add(lblContrasena);
                                        
                                                // Enlace a registro 
                                                JLabel lblNoTienesCuenta = new JLabel("¿No tienes una cuenta?");
                                                lblNoTienesCuenta.setFont(new Font("Rockwell", Font.PLAIN, 14));
                                                lblNoTienesCuenta.setHorizontalAlignment(SwingConstants.CENTER);
                                                lblNoTienesCuenta.setBounds(150, 356, 178, 18);
                                                panel.add(lblNoTienesCuenta);
                                                                        
                                                                        JLabel lblNewLabel_2 = new JLabel("");
                                                                        lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Stanley\\Desktop\\Tarea4Kelyn\\Tarea4Kelyn\\bin\\images\\Group-1000011122 (1) (1).png"));
                                                                        lblNewLabel_2.setBounds(44, 38, 44, 39);
                                                                        panel.add(lblNewLabel_2);
                                                                        
                                                                        JLabel lblControlDeUsuarios = new JLabel("Administración");
                                                                        lblControlDeUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
                                                                        lblControlDeUsuarios.setForeground(new Color(255, 255, 255));
                                                                        lblControlDeUsuarios.setFont(new Font("Rockwell", Font.BOLD, 26));
                                                                        lblControlDeUsuarios.setBounds(541, 212, 198, 31);
                                                                        panel.add(lblControlDeUsuarios);
                                                                        
                                                                        JLabel lblDeRegistros = new JLabel("de Registros");
                                                                        lblDeRegistros.setHorizontalAlignment(SwingConstants.CENTER);
                                                                        lblDeRegistros.setForeground(Color.WHITE);
                                                                        lblDeRegistros.setFont(new Font("Rockwell", Font.BOLD, 26));
                                                                        lblDeRegistros.setBounds(548, 238, 178, 31);
                                                                        panel.add(lblDeRegistros);
                                                                        
                                                                        JLabel lblNewLabel = new JLabel("");
                                                                        lblNewLabel.setBounds(488, -17, 428, 512);
                                                                        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Stanley\\Desktop\\Tarea4Kelyn\\Tarea4Kelyn\\bin\\images\\Captura de pantalla 2026-03-30 222555.png"));
                                                                        panel.add(lblNewLabel);
                                                                        
                                                                                JButton btnRegistrarse = new JButton("Registrarme");
                                                                                btnRegistrarse.setForeground(Color.WHITE);
                                                                                btnRegistrarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                                                                btnRegistrarse.setFont(new Font("Rockwell", Font.PLAIN, 16));
                                                                                btnRegistrarse.setBackground(new Color(0, 134, 190));
                                                                                btnRegistrarse.setBounds(150, 377, 178, 39);
                                                                                panel.add(btnRegistrarse);
                                                                                
                                                                                        //  Botón: Iniciar sesión
                                                                                        JButton btnLogin = new JButton("Entrar");
                                                                                        btnLogin.setForeground(Color.WHITE);
                                                                                        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                                                                        btnLogin.setFont(new Font("Rockwell", Font.PLAIN, 16));
                                                                                        btnLogin.setBackground(new Color(0, 134, 190));
                                                                                        btnLogin.setBounds(150, 306, 178, 39);
                                                                                        panel.add(btnLogin);
                                                                                        
                                                                                                // JTextField simple — JFormattedTextField no aportaba nada aquí
                                                                                                txtUsuario = new JTextField();
                                                                                                txtUsuario.setBorder(null);
                                                                                                txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
                                                                                                txtUsuario.setBounds(44, 177, 370, 31);
                                                                                                panel.add(txtUsuario);
                                                                                                txtUsuario.setHorizontalAlignment(SwingConstants.LEFT);
                                                                                                txtUsuario.setBackground(Color.WHITE);
                                                                                                txtUsuario.setForeground(Color.BLACK);
                                                                                                
                                                                                                        // JPasswordField oculta los caracteres mientras el usuario escribe
                                                                                                        txtContrasena = new JPasswordField();
                                                                                                        txtContrasena.setBorder(null);
                                                                                                        txtContrasena.setFont(new Font("Tahoma", Font.PLAIN, 14));
                                                                                                        txtContrasena.setBounds(44, 242, 370, 31);
                                                                                                        panel.add(txtContrasena);
                                                                                        
                                                                                                //  Acción: Iniciar sesión 
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
                                                        
                                                                //  Acción: Ir a Registro 
                                                                btnRegistrarse.addActionListener(e -> {
                                                                    // Abre el registro. Login no se cierra aquí —
                                                                    // si el usuario cancela el registro, puede volver al login
                                                                    Register register = new Register();
                                                                    register.setLocationRelativeTo(this);
                                                                    register.setVisible(true);
                                                                });
    }
}