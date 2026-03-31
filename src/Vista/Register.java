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
import java.awt.Panel;
import javax.swing.ImageIcon;
import java.awt.Cursor;

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
        setBounds(100, 100, 800, 500);

        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(67, 11, 44, 39);
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Stanley\\Desktop\\Tarea4Kelyn\\Tarea4Kelyn\\bin\\images\\Group-1000011122 (1) (1).png"));
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1_1 = new JLabel("");
        lblNewLabel_1_1.setBounds(634, 0, 150, 150);
        lblNewLabel_1_1.setIcon(new ImageIcon("C:\\Users\\Stanley\\Desktop\\Tarea4Kelyn\\Tarea4Kelyn\\bin\\images\\Owner_Blue_220x220 (1).png"));
        contentPane.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(634, -25, 150, 512);
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Stanley\\Desktop\\Tarea4Kelyn\\Tarea4Kelyn\\bin\\images\\Captura de pantalla 2026-03-30 222555.png"));
        contentPane.add(lblNewLabel_1);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 784, 461);
        contentPane.add(panel);
        panel.setLayout(null);
                
                Panel panel_1_1_3_1_1 = new Panel();
                panel_1_1_3_1_1.setBackground(Color.BLACK);
                panel_1_1_3_1_1.setBounds(363, 292, 227, 2);
                panel.add(panel_1_1_3_1_1);
                
                Panel panel_1_1_3_1 = new Panel();
                panel_1_1_3_1.setBackground(Color.BLACK);
                panel_1_1_3_1.setBounds(363, 220, 227, 2);
                panel.add(panel_1_1_3_1);
                
                Panel panel_1_1_2_1 = new Panel();
                panel_1_1_2_1.setBackground(Color.BLACK);
                panel_1_1_2_1.setBounds(80, 369, 227, 2);
                panel.add(panel_1_1_2_1);
                
                Panel panel_1_1_2 = new Panel();
                panel_1_1_2.setBackground(Color.BLACK);
                panel_1_1_2.setBounds(80, 292, 227, 2);
                panel.add(panel_1_1_2);
                
                Panel panel_1_1_1 = new Panel();
                panel_1_1_1.setBackground(Color.BLACK);
                panel_1_1_1.setBounds(80, 220, 227, 2);
                panel.add(panel_1_1_1);
        
                // ── Título ───────────────────────────────────────────────
                JLabel lblTitulo = new JLabel("Crear usuario");
                lblTitulo.setBounds(118, 21, 192, 34);
                panel.add(lblTitulo);
                lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 28));
                
                Panel panel_1_1 = new Panel();
                panel_1_1.setBounds(80, 144, 227, 2);
                panel.add(panel_1_1);
                panel_1_1.setBackground(Color.BLACK);
                
                        // ── Correo ───────────────────────────────────────────────
                        JLabel lblCorreo = new JLabel("Correo Electrónico");
                        lblCorreo.setFont(new Font("Rockwell", Font.PLAIN, 17));
                        lblCorreo.setBounds(363, 87, 183, 25);
                        panel.add(lblCorreo);
                                
                                        // ── Contraseña (oculta) ──────────────────────────────────
                                        JLabel lblContrasena = new JLabel("Contraseña");
                                        lblContrasena.setFont(new Font("Rockwell", Font.PLAIN, 17));
                                        lblContrasena.setBounds(363, 163, 117, 25);
                                        panel.add(lblContrasena);
                                        
                                                // JPasswordField oculta los caracteres mientras escribe
                                                txtContrasena = new JPasswordField();
                                                txtContrasena.setBorder(null);
                                                txtContrasena.setBounds(363, 188, 230, 34);
                                                panel.add(txtContrasena);
                                                
                                                        // ── Confirmar contraseña (oculta) ────────────────────────
                                                        JLabel lblConfirmar = new JLabel("Confirmar Contraseña");
                                                        lblConfirmar.setFont(new Font("Rockwell", Font.PLAIN, 17));
                                                        lblConfirmar.setBounds(363, 233, 183, 25);
                                                        panel.add(lblConfirmar);
                                                        
                                                                txtConfirmar = new JPasswordField();
                                                                txtConfirmar.setBorder(null);
                                                                txtConfirmar.setBounds(363, 260, 227, 34);
                                                                panel.add(txtConfirmar);
                                                                
                                                                        // ── Botón Registrarse ────────────────────────────────────
                                                                        JButton btnRegistrarse = new JButton("Registrarse");
                                                                        btnRegistrarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                                                        btnRegistrarse.setForeground(Color.WHITE);
                                                                        btnRegistrarse.setBackground(new Color(0, 134, 190));
                                                                        btnRegistrarse.setFont(new Font("Rockwell", Font.PLAIN, 16));
                                                                        btnRegistrarse.setBounds(238, 391, 183, 43);
                                                                        panel.add(btnRegistrarse);
                                                                        
                                                                                txtTelefono = new JTextField();
                                                                                txtTelefono.setBorder(null);
                                                                                txtTelefono.setBounds(80, 337, 230, 34);
                                                                                panel.add(txtTelefono);
                                                                                
                                                                                        // ── Teléfono ─────────────────────────────────────────────
                                                                                        JLabel lblTelefono = new JLabel("Número de Teléfono");
                                                                                        lblTelefono.setFont(new Font("Rockwell", Font.PLAIN, 17));
                                                                                        lblTelefono.setBounds(80, 312, 162, 21);
                                                                                        panel.add(lblTelefono);
                                                                                                
                                                                                                        // ── Nombre de usuario ────────────────────────────────────
                                                                                                        JLabel lblUsername = new JLabel("Nombre de Usuario");
                                                                                                        lblUsername.setFont(new Font("Rockwell", Font.PLAIN, 17));
                                                                                                        lblUsername.setBounds(80, 233, 153, 21);
                                                                                                        panel.add(lblUsername);
                                                                                                                        
                                                                                                                                txtApellido = new JTextField();
                                                                                                                                txtApellido.setBorder(null);
                                                                                                                                txtApellido.setBounds(80, 188, 230, 34);
                                                                                                                                panel.add(txtApellido);
                                                                                                                
                                                                                                                        txtUsername = new JTextField();
                                                                                                                        txtUsername.setBorder(null);
                                                                                                                        txtUsername.setBounds(80, 260, 230, 34);
                                                                                                                        panel.add(txtUsername);
                                                                                                                
                                                                                                                        // ── Apellido ─────────────────────────────────────────────
                                                                                                                        JLabel lblApellido = new JLabel("Apellido");
                                                                                                                        lblApellido.setBounds(80, 157, 142, 22);
                                                                                                                        panel.add(lblApellido);
                                                                                                                        lblApellido.setFont(new Font("Rockwell", Font.PLAIN, 17));
                                                                                                                        
                                                                                                                                txtNombre = new JTextField();
                                                                                                                                txtNombre.setBorder(null);
                                                                                                                                txtNombre.setBounds(80, 112, 230, 34);
                                                                                                                                panel.add(txtNombre);
                                                                                                                                
                                                                                                                                JLabel lblUsuario = new JLabel("Nombre de Usuario");
                                                                                                                                lblUsuario.setBounds(80, 83, 204, 32);
                                                                                                                                panel.add(lblUsuario);
                                                                                                                                lblUsuario.setFont(new Font("Rockwell", Font.PLAIN, 17));
                                                                                                                                
                                                                                                                                Panel panel_1_1_3 = new Panel();
                                                                                                                                panel_1_1_3.setBackground(Color.BLACK);
                                                                                                                                panel_1_1_3.setBounds(363, 144, 227, 2);
                                                                                                                                panel.add(panel_1_1_3);
                                                                                                                                
                                                                                                                                        txtCorreo = new JTextField();
                                                                                                                                        txtCorreo.setBorder(null);
                                                                                                                                        txtCorreo.setBounds(363, 112, 230, 34);
                                                                                                                                        panel.add(txtCorreo);

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