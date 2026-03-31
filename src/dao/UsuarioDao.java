package dao;

import db.ConexionDB;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao implements IUsuarioDao {

    private final Connection con;

    public UsuarioDao() {
        this.con = ConexionDB.getInstance().getConexion();
    }

    // Registrar — usa los nombres de columna de tu tabla Clientes
    @Override
    public boolean registrar(Usuario u) {
        String sql = "INSERT INTO Clientes " +
                     "(Usuario, Nombre, Apellido, Telefono, Correo_Electronico, Contrasena) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getTelefono());
            ps.setString(5, u.getCorreo());
            ps.setString(6, u.getPasswordHash());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Login — busca por Usuario y Contrasena
    @Override
    public Usuario login(String username, String passwordHash) {
        String sql = "SELECT * FROM Clientes WHERE Usuario = ? AND Contrasena = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, passwordHash);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapear(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Listar todos ordenados por Nombre
    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Clientes ORDER BY Nombre";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Actualizar — usa Id_Cliente como identificador
    @Override
    public boolean actualizar(Usuario u) {
        String sql = "UPDATE Clientes SET Nombre=?, Apellido=?, Telefono=?, Correo_Electronico=? " +
                     "WHERE Id_Cliente=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getTelefono());
            ps.setString(4, u.getCorreo());
            ps.setInt   (5, u.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar por Id_Cliente
    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Clientes WHERE Id_Cliente = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Verifica si el Usuario ya existe
    @Override
    public boolean existeUsername(String username) {
        String sql = "SELECT 1 FROM Clientes WHERE Usuario = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Verifica si el Correo ya existe
    @Override
    public boolean existeCorreo(String correo) {
        String sql = "SELECT 1 FROM Clientes WHERE Correo_Electronico = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean crear(Usuario usuario) {
        return registrar(usuario);
    }

    // Mapea una fila de Clientes a un objeto Usuario
    // Id_Cliente -> id, Usuario -> username, Contrasena -> passwordHash
    private Usuario mapear(ResultSet rs) throws SQLException {
        return Usuario.builder()
                .id          (rs.getInt   ("Id_Cliente"))
                .username    (rs.getString("Usuario"))
                .nombre      (rs.getString("Nombre"))
                .apellido    (rs.getString("Apellido"))
                .telefono    (rs.getString("Telefono"))
                .correo      (rs.getString("Correo_Electronico"))
                .passwordHash(rs.getString("Contrasena"))
                .build();
    }
}