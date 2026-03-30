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
        setBounds(100, 100, 660, 360);

        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // ── Título ───────────────────────────────────────────────
        JLabel lblTitulo = new JLabel("Usuarios Registrados");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitulo.setBounds(160, 10, 400, 35);
        contentPane.add(lblTitulo);

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
        scrollPane.setBounds(29, 56, 580, 185);
        contentPane.add(scrollPane);

        // ── Botones ──────────────────────────────────────────────
        JButton btnCrear = new JButton("Nuevo");
        btnCrear.setBounds(61, 265, 116, 28);
        contentPane.add(btnCrear);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(199, 265, 111, 28);
        contentPane.add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(328, 265, 116, 28);
        contentPane.add(btnEliminar);

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(460, 265, 120, 28);
        contentPane.add(btnCerrarSesion);

        // ── Acción: Nuevo ────────────────────────────────────────
        // Abre el formulario de registro pasando this como padre
        // para que al guardar, refreshTable() se llame automáticamente
        btnCrear.addActionListener(e -> {
            Register r = new Register(Dashboard.this);
            r.setLocationRelativeTo(Dashboard.this);
            r.setVisible(true);
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

        // ── Acción: Cerrar sesión ────────────────────────────────
        // Cierra el dashboard y vuelve a mostrar el login
        btnCerrarSesion.addActionListener(e -> {
            Login login = new Login();
            login.setLocationRelativeTo(this);
            login.setVisible(true);
            this.dispose(); // cierra solo el dashboard, no toda la app
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