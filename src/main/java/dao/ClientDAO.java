/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Client;
import model.Producte;
import util.Connexio;

/**
 *
 * @author alumne
 */
public class ClientDAO {
    // --- Sentències SQL Predefinides ---
    private static final String SQL_INSERT = 
        "INSERT INTO Clients (nom, correu) VALUES (?, ?)";
    private static final String SQL_SELECT = 
        "SELECT id, nom, correu FROM Clients";
    private static final String SQL_UPDATE = 
        "UPDATE Clients SET nom = ?, correu = ? WHERE id = ?";
    private static final String SQL_DELETE = 
        "DELETE FROM Clients WHERE id = ?";
    private static final String SQL_SELECT_BY_ID = 
        "SELECT id, nom, correu FROM Clients WHERE id = ?";

    // --------------------------------------------------------------------------
    // 1. INSERIR (CREATE)
    // --------------------------------------------------------------------------
    /**
     * Insereix un nou producte a la base de dades i actualitza l'ID de l'objecte.
     * @param cl L'objecte Producte a inserir.
     * @throws SQLException Si ocorre un error de JDBC.
     */
    public void inserir(Client cl) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null; // Per obtenir l'ID generat

        try {
            conn = Connexio.getConnection(); // Obtenim la connexió
            
            // Creem el PreparedStatement amb RETURN_GENERATED_KEYS
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS); 
            
            // Paràmetres:
            stmt.setString(1, cl.getNom());
            stmt.setString(2, cl.getCorreu());

            int filesAfectades = stmt.executeUpdate();

            // Si s'ha inserit correctament, obtenim l'ID generat
            if (filesAfectades > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    cl.setId(rs.getInt(1)); // Assignem l'ID generat a l'objecte
                }
            }
        } finally {
            // Tancar recursos en ordre invers
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    // --------------------------------------------------------------------------
    // 2. LLISTAR (READ ALL)
    // --------------------------------------------------------------------------
    /**
     * Retorna una llista de tots els clients de la base de dades.
     * @return Una llista d'objectes Client.
     * @throws SQLException Si ocorre un error de JDBC.
     */
    public List<Client> llistar() throws SQLException {
        List<Client> clients = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Connexio.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Mapeig de ResultSet a Objecte Producte
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String correu = rs.getString("correu");
                
                Client cl = new Client(id, nom, correu);
                clients.add(cl);
            }
        } finally {
            // Tancar recursos
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return clients;
    }
    
    // --------------------------------------------------------------------------
    // 3. ACTUALITZAR (UPDATE)
    // --------------------------------------------------------------------------
    /**
     * Actualitza un producte existent a la base de dades.
     * @param cl L'objecte Client amb les dades a actualitzar.
     * @throws SQLException Si ocorre un error de JDBC.
     */
    public void actualitzar(Client cl) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Connexio.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            // Paràmetres de SET (1 a 3):
            stmt.setString(1, cl.getNom());
            stmt.setString(2, cl.getCorreu());
            
            // Paràmetre de WHERE (4):
            stmt.setInt(4, cl.getId());

            stmt.executeUpdate();
            
        } finally {
            // Tancar recursos
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    // --------------------------------------------------------------------------
    // 4. ELIMINAR (DELETE)
    // --------------------------------------------------------------------------
    /**
     * Elimina un producte de la base de dades pel seu ID.
     * @param id L'ID del producte a eliminar.
     * @throws SQLException Si ocorre un error de JDBC.
     */
    public int eliminar(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;

        try {
            conn = Connexio.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);

            // Paràmetre de WHERE:
            stmt.setInt(1, id);

            result = stmt.executeUpdate();
            return result;
        } catch  (SQLException e){
            return result;
        } finally {
            // Tancar recursos
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    // --------------------------------------------------------------------------
    // MÈTODE ADDICIONAL: LLEGIR PER ID (READ ONE)
    // --------------------------------------------------------------------------
    /**
     * Cerca un producte a la base de dades pel seu ID.
     * @param id L'ID del producte a cercar.
     * @return L'objecte Producte trobat, o null si no existeix.
     * @throws SQLException Si ocorre un error de JDBC.
     */
    public Client obtenirPerId(int id) throws SQLException {
        Client cl = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Connexio.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String nom = rs.getString("nom");
                String correu = rs.getString("correu");
                
                cl = new Client(id, nom, correu);
            }
        } finally {
            // Tancar recursos
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return cl;
    }
}
