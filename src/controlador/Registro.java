
package controlador;

import bd.Conexion;
import java.util.Date;
import modelo.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Profe Hernán
 * @version 17-11-2021
 */
public class Registro {
    
    public boolean agregar(Libro libro){
        
        Date date;
        try {
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();
            
            date = libro.getPublicacion();
            
            String query = "INSERT INTO libro(titulo,autor,publicacion,precio,disponible) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setDate(3, new java.sql.Date(date.getTime()));
            stmt.setInt(4, libro.getPrecio());
            stmt.setBoolean(5, libro.isDisponible());
            
            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error SQL al agregar Libro: " + e.getMessage());
            return false;
        } catch(Exception e){
            System.out.println("Error al agregar libro (EXCEPTION): " + e.getMessage());
            return false;
        }
    }
    
    
    public boolean actualizar(Libro libro){
        
        try {
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();
                        
            String query = "UPDATE libro set titulo = ?,autor = ?, publicacion = ?,precio = ?,disponible = ? WHERE idlibro = ?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setDate(3, new java.sql.Date(libro.getPublicacion().getTime()));
            stmt.setInt(4, libro.getPrecio());
            stmt.setBoolean(5, libro.isDisponible());
            stmt.setInt(6, libro.getIdLibro());
            
            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error SQL al agregar Libro: " + e.getMessage());
            return false;
        } catch(Exception e){
            System.out.println("Error al agregar libro (EXCEPTION): " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminar(int idLibro){
        try {
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();
                        
            String query = "DELETE FROM libro WHERE idlibro = ?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1, idLibro);
            
            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error SQL al agregar Libro: " + e.getMessage());
            return false;
        } catch(Exception e){
            System.out.println("Error al agregar libro (EXCEPTION): " + e.getMessage());
            return false;
        }
    }
    
    public Libro buscarPorId(int idLibro){
        
        Libro libro = new Libro();
        
        try {
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();
                        
            String query = "SELECT idlibro, titulo,autor,publicacion,precio,disponible FROM libro WHERE idlibro = ?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1, idLibro);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                libro.setIdLibro(rs.getInt("idlibro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setPublicacion(rs.getDate("publicacion"));
                libro.setPrecio(rs.getInt("precio"));
                libro.setDisponible(rs.getBoolean("disponible"));
                
            }
            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            System.out.println("Error SQL al agregar Libro: " + e.getMessage());
            
        } catch(Exception e){
            System.out.println("Error al agregar libro (EXCEPTION): " + e.getMessage());
            
        }
        return libro;
        
    }
    
    public List<Libro> buscarTodos(){
        
        List<Libro> lista = new ArrayList<>();
        
        
        try {
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();
                        
            String query = "SELECT idlibro, titulo,autor,publicacion,precio,disponible FROM libro order by titulo";
            PreparedStatement stmt = cnx.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Libro libro = new Libro();
                libro.setIdLibro(rs.getInt("idlibro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setPublicacion(rs.getDate("publicacion"));
                libro.setPrecio(rs.getInt("precio"));
                libro.setDisponible(rs.getBoolean("disponible"));
                
                lista.add(libro);
                
            }
            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            System.out.println("Error SQL al agregar Libro: " + e.getMessage());
            
        } catch(Exception e){
            System.out.println("Error al agregar libro (EXCEPTION): " + e.getMessage());
            
        }
        return lista;
        
    }
    
    
}
