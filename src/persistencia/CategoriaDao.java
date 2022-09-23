/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;





/**
 *
 * @author anderson.mazzotti
 */
public class CategoriaDao {
    
    
    public static boolean inserir(Categoria categoria){
            try {
        Connection conexao = Conexao.getConexao();
        String sql = "INSERT INTO categoria (nome, tipo)"
                    +"VALUES (?,?)";
                try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                    comando.setString(1, categoria.getNome());
                    comando.setString(2, String.valueOf(categoria.getTipo()));
                    comando.execute();
                }
        return true;
}   catch (SQLException e){
    return false;
}
    
    }
            
    public static boolean alterar(Categoria categoria) throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "UPDATE categoria SET" 
                    + "nome = ?,"
                    +"tipo = ?"
                    +"WHERE id =?";
        try (PreparedStatement comando = con.prepareStatement(sql)) {
            comando.setString(1, categoria.getNome());
            comando.setString(2, String.valueOf(categoria.getTipo()));
            comando.setInt(3, categoria.getId());
            
            int nrLinhas = comando.executeUpdate();
            
            return nrLinhas > 0;
            
        } catch (Exception e){
        return false;
        }
        
    }        
    
    public static boolean excluir(int id) throws SQLException{
    
        Connection con = Conexao.getConexao();
        String sql = "DELETE FROM categoria WHERE id =?";
        int nrLinhas;
        try (PreparedStatement comando = con.prepareStatement(sql)) {
            comando.setInt(1, id);
            nrLinhas = comando.executeUpdate();
        
        return nrLinhas > 0;
        
        }
        catch(Exception e){
        System.out.println(e.getMessage());
        return false;
                
        }
    
    
    
    }
    
    public static ArrayList<Categoria> listar() throws SQLException{
    ArrayList<Categoria> categorias = new ArrayList<Categoria>();
    try {
        Connection conexao = Conexao.getConexao();
        String sql = "SELECT * FROM categoria";
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(sql);
        while (resultado.next()){
        Categoria c = new Categoria();
        c.setId(resultado.getInt("id"));
        c.setNome(resultado.getString("nome"));
        c.setTipo(resultado.getString("tipo").charAt(0));
        categorias.add(c);
        }
        resultado.close();
        comando.close();    
    }   catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return categorias;
       
    }
       
    
    public static ArrayList<Categoria> listarPorTipo(char tipo) throws SQLException {
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        try {
        Connection conexao = Conexao.getConexao();
        String sql = "SELECT * FROM categoria WHERE tipo =?";
        PreparedStatement comando = conexao.prepareStatement(sql);
        comando.setString(1, String.valueOf(tipo));
        
        
        }
    
    } 
}


