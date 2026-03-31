package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import controller.DashboardController;
import model.Usuario;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

/**
 * Vista principal del sistema — muestra todos los usuarios registrados.
 * Permite Crear, Actualizar, Eliminar y Cerrar Sesión.
 *
 * CORRECCIONES:
 * - Se eliminaron los JLabel duplicados encima de la tabla
 *   (JTable ya tiene su propio header con los nombres de columna)
 * - La columna ID queda oculta pero disponible para operaciones CRUD
 * - refreshTable() se llama después de cada operación exitosa
 *   para que los cambios se reflejen inmediatamente
 */
public class Dashboard extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel  contentPane;
    private JTable  tablaUsuarios;

    // El controller conecta esta vista con la capa de servicios
    private final DashboardController controller = new DashboardController();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Dashboard frame = new Dashboard();
                frame.setVisible(true);
                frame.refreshTable(); // carga los datos al iniciar
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Dashboard() {
        setTitle("Panel Principal - Usuarios Registrados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);

        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // ── Tabla ────────────────────────────────────────────────
        // Columna "ID" va al final y se oculta — se usa solo para CRUD
        tablaUsuarios = new JTable();
        tablaUsuarios.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        tablaUsuarios.setColumnSelectionAllowed(false); // selección por fila completa
        tablaUsuarios.setRowSelectionAllowed(true);
        tablaUsuarios.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Nombre", "Apellido", "Teléfono", "Correo Electrónico", "Usuario", "ID"}
        ) {
            // Ninguna celda es editable — editar se hace con el botón Actualizar
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        });

        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        scrollPane.setFont(new Font("Rockwell", Font.PLAIN, 16));
        scrollPane.setBorder(null);
        scrollPane.setBounds(63, 69, 642, 265);
        contentPane.add(scrollPane);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 784, 461);
        contentPane.add(panel);
        panel.setLayout(null);
        
                // ── Botones ──────────────────────────────────────────────
                JButton btnCrear = new JButton("Nuevo");
                btnCrear.setBackground(new Color(0, 134, 190));
                btnCrear.setForeground(Color.WHITE);
                btnCrear.setFont(new Font("Rockwell", Font.PLAIN, 16));
                btnCrear.setBounds(65, 374, 133, 35);
                panel.add(btnCrear);
                
                        JButton btnActualizar = new JButton("Actualizar");
                        btnActualizar.setFont(new Font("Rockwell", Font.PLAIN, 16));
                        btnActualizar.setForeground(Color.WHITE);
                        btnActualizar.setBackground(new Color(0, 134, 190));
                        btnActualizar.setBounds(226, 374, 138, 35);
                        panel.add(btnActualizar);
                        
                                JButton btnEliminar = new JButton("Eliminar");
                                btnEliminar.setBackground(new Color(0, 134, 190));
                                btnEliminar.setForeground(Color.WHITE);
                                btnEliminar.setFont(new Font("Rockwell", Font.PLAIN, 16));
                                btnEliminar.setBounds(396, 374, 138, 35);
                                panel.add(btnEliminar);
                                
                                        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
                                        btnCerrarSesion.setForeground(Color.WHITE);
                                        btnCerrarSesion.setFont(new Font("Rockwell", Font.PLAIN, 16));
                                        btnCerrarSesion.setBackground(new Color(0, 134, 190));
                                        btnCerrarSesion.setBounds(564, 374, 138, 35);
                                        panel.add(btnCerrarSesion);
                                        
                                                // ── Título ───────────────────────────────────────────────
                                                JLabel lblTitulo = new JLabel("Usuarios Registrados");
                                                lblTitulo.setBounds(99, 11, 400, 35);
                                                panel.add(lblTitulo);
                                                lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 26));
                                                
                                                JLabel lblNewLabel = new JLabel("");
                                                lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Stanley\\Desktop\\Tarea4Kelyn\\Tarea4Kelyn\\bin\\images\\Group-1000011122 (1) (1).png"));
                                                lblNewLabel.setBounds(42, 7, 44, 39);
                                                panel.add(lblNewLabel);
                                        
                                                // ── Acción: Cerrar sesión ────────────────────────────────
                                                // Cierra el dashboard y vuelve a mostrar el login
                                                btnCerrarSesion.addActionListener(e -> {
                                                    Login login = new Login();
                                                    login.setLocationRelativeTo(this);
                                                    login.setVisible(true);
                                                    this.dispose(); // cierra solo el dashboard, no toda la app
                                                });
                                
                                        // ── Acción: Eliminar ─────────────────────────────────────
                                        btnEliminar.addActionListener(e -> {
                                            int row = tablaUsuarios.getSelectedRow();
                                            if (row == -1) {
                                                JOptionPane.showMessageDialog(this,
                                                    "Seleccione un usuario de la tabla para eliminar",
                                                    "Sin selección", JOptionPane.WARNING_MESSAGE);
                                                return;
                                            }
                                
                                            DefaultTableModel model = (DefaultTableModel) tablaUsuarios.getModel();
                                            int id = Integer.parseInt(String.valueOf(model.getValueAt(row, 5)));
                                
                                            // Confirmación antes de eliminar — operación irreversible
                                            int confirm = JOptionPane.showConfirmDialog(this,
                                                "¿Está seguro que desea eliminar este usuario?",
                                                "Confirmar eliminación", JOptionPane.YES_NO_OPTION,
                                                JOptionPane.WARNING_MESSAGE);
                                
                                            if (confirm == JOptionPane.YES_OPTION) {
                                                boolean ok = controller.eliminarUsuario(id);
                                                if (ok) {
                                                    JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente");
                                                    refreshTable(); // refresca inmediatamente
                                                } else {
                                                    JOptionPane.showMessageDialog(this, "Error al eliminar", "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                                }
                                            }
                                        });
                        
                                // ── Acción: Actualizar ───────────────────────────────────
                                btnActualizar.addActionListener(e -> {
                                    int row = tablaUsuarios.getSelectedRow();
                                    if (row == -1) {
                                        JOptionPane.showMessageDialog(this,
                                            "Seleccione un usuario de la tabla para actualizar",
                                            "Sin selección", JOptionPane.WARNING_MESSAGE);
                                        return;
                                    }
                        
                                    // Obtiene el ID de la columna oculta (índice 5)
                                    DefaultTableModel model = (DefaultTableModel) tablaUsuarios.getModel();
                                    int id = Integer.parseInt(String.valueOf(model.getValueAt(row, 5)));
                        
                                    // Busca el objeto Usuario completo para pre-poblar los diálogos
                                    Usuario found = controller.listarUsuarios()
                                            .stream()
                                            .filter(u -> u.getId() == id)
                                            .findFirst()
                                            .orElse(null);
                        
                                    if (found == null) {
                                        JOptionPane.showMessageDialog(this, "Usuario no encontrado");
                                        return;
                                    }
                        
                                    // Pide los nuevos valores pre-poblando con los actuales
                                    String nuevoNombre    = JOptionPane.showInputDialog(this, "Nuevo nombre:",    found.getNombre());
                                    String nuevoApellido  = JOptionPane.showInputDialog(this, "Nuevo apellido:",  found.getApellido());
                                    String nuevoTelefono  = JOptionPane.showInputDialog(this, "Nuevo teléfono:",  found.getTelefono());
                                    String nuevoCorreo    = JOptionPane.showInputDialog(this, "Nuevo correo:",    found.getCorreo());
                        
                                    // Si el usuario canceló algún diálogo, los valores serán null
                                    if (nuevoNombre == null || nuevoNombre.isBlank()) {
                                        JOptionPane.showMessageDialog(this, "El nombre es obligatorio");
                                        return;
                                    }
                        
                                    // Construye el Usuario actualizado conservando id, username y password
                                    Usuario updated = Usuario.builder()
                                            .id(found.getId())
                                            .username(found.getUsername())
                                            .nombre(nuevoNombre)
                                            .apellido(nuevoApellido  != null ? nuevoApellido  : found.getApellido())
                                            .telefono(nuevoTelefono  != null ? nuevoTelefono  : found.getTelefono())
                                            .correo(nuevoCorreo      != null ? nuevoCorreo    : found.getCorreo())
                                            .passwordHash(found.getPasswordHash())
                                            .build();
                        
                                    boolean ok = controller.actualizarUsuario(updated);
                                    if (ok) {
                                        JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente");
                                        refreshTable(); // refresca inmediatamente
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Error al actualizar", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    }
                                });

        // ── Acción: Nuevo ────────────────────────────────────────
        // Abre el formulario de registro pasando this como padre
        // para que al guardar, refreshTable() se llame automáticamente
        btnCrear.addActionListener(e -> {
            Register r = new Register(Dashboard.this);
            r.setLocationRelativeTo(Dashboard.this);
            r.setVisible(true);
        });
    }

    /**
     * Recarga la tabla con los datos actuales de la BD.
     * Se llama después de cada operación de Crear, Actualizar o Eliminar
     * para que los cambios se reflejen en pantalla inmediatamente.
     */
    public void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) tablaUsuarios.getModel();
        model.setRowCount(0); // limpia todas las filas antes de recargar

        List<Usuario> lista = controller.listarUsuarios();
        for (Usuario u : lista) {
            model.addRow(new Object[]{
                u.getNombre(),
                u.getApellido(),
                u.getTelefono(),
                u.getCorreo(),
                u.getUsername(),
                u.getId()       // columna oculta — necesaria para CRUD
            });
        }

        // Oculta la columna ID visualmente pero la mantiene accesible
        // setWidth(0) + min/max=0 la hace invisible sin eliminarla
        if (tablaUsuarios.getColumnModel().getColumnCount() > 5) {
            tablaUsuarios.getColumnModel().getColumn(5).setMinWidth(0);
            tablaUsuarios.getColumnModel().getColumn(5).setMaxWidth(0);
            tablaUsuarios.getColumnModel().getColumn(5).setWidth(0);
        }
    }
}