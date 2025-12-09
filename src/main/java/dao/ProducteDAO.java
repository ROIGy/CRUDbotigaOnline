package dao;

import java.sql.*;
import util.Connexio; // Assumim que aquesta classe proporciona la connexió
import model.Producte; // Classe creada anteriorment
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO per gestionar l'accés a dades de la taula 'Productes'.
 */
public class ProducteDAO {

    
    // --- Sentències SQL Predefinides ---
    private static final String SQL_INSERT = 
        "INSERT INTO Productes (nom, preu, estoc) VALUES (?, ?, ?)";
    private static final String SQL_SELECT = 
        "SELECT id, nom, preu, estoc FROM Productes";
    private static final String SQL_UPDATE = 
        "UPDATE Productes SET nom = ?, preu = ?, estoc = ? WHERE id = ?";
    private static final String SQL_DELETE = 
        "DELETE FROM Productes WHERE id = ?";
    private static final String SQL_SELECT_BY_ID = 
        "SELECT id, nom, preu, estoc FROM Productes WHERE id = ?";

    // --------------------------------------------------------------------------
    // 1. INSERIR (CREATE)
    // --------------------------------------------------------------------------
    /**
     * Insereix un nou producte a la base de dades i actualitza l'ID de l'objecte.
     * @param p L'objecte Producte a inserir.
     * @throws SQLException Si ocorre un error de JDBC.
     */
    public void inserir(Producte p) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null; // Per obtenir l'ID generat

        try {
            conn = Connexio.getConnection(); // Obtenim la connexió
            
            // Creem el PreparedStatement amb RETURN_GENERATED_KEYS
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS); 
            
            // Paràmetres:
            stmt.setString(1, p.getNom());
            stmt.setDouble(2, p.getPreu());
            stmt.setInt(3, p.getEstoc());

            int filesAfectades = stmt.executeUpdate();

            // Si s'ha inserit correctament, obtenim l'ID generat
            if (filesAfectades > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    p.setId(rs.getInt(1)); // Assignem l'ID generat a l'objecte
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
     * Retorna una llista de tots els productes de la base de dades.
     * @return Una llista d'objectes Producte.
     * @throws SQLException Si ocorre un error de JDBC.
     */
    public List<Producte> llistar() throws SQLException {
        List<Producte> productes = new ArrayList<>();
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
                double preu = rs.getDouble("preu");
                int estoc = rs.getInt("estoc");
                
                Producte producte = new Producte(id, nom, preu, estoc);
                productes.add(producte);
            }
        } finally {
            // Tancar recursos
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return productes;
    }
    
    // --------------------------------------------------------------------------
    // 3. ACTUALITZAR (UPDATE)
    // --------------------------------------------------------------------------
    /**
     * Actualitza un producte existent a la base de dades.
     * @param p L'objecte Producte amb les dades a actualitzar.
     * @throws SQLException Si ocorre un error de JDBC.
     */
    public void actualitzar(Producte p) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Connexio.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            // Paràmetres de SET (1 a 3):
            stmt.setString(1, p.getNom());
            stmt.setDouble(2, p.getPreu());
            stmt.setInt(3, p.getEstoc());
            
            // Paràmetre de WHERE (4):
            stmt.setInt(4, p.getId());

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
    public void eliminar(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Connexio.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            
            // Paràmetre de WHERE:
            stmt.setInt(1, id);

            stmt.executeUpdate();
            
        } finally {
            // Tancar recursos
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
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
    public Producte obtenirPerId(int id) throws SQLException {
        Producte producte = null;
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
                double preu = rs.getDouble("preu");
                int estoc = rs.getInt("estoc");
                
                producte = new Producte(id, nom, preu, estoc);
            }
        } finally {
            // Tancar recursos
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return producte;
    }
}