/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    
    
    public void cadastrarProduto (ProdutosDTO produto){
            
        
        
        try{
            
        conn = new conectaDAO().connectDB();    
            
        String sql = "insert into produtos (nome, valor, status) values (?,?,?);";
            
        prep = conn.prepareStatement(sql);
        
        prep.setString(1, produto.getNome());
        prep.setString(2, produto.getValor().toString());
        prep.setString(3, produto.getStatus());
        
        prep.executeUpdate();
        
        prep.close();
        
        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        
        
        } catch (SQLException e){
            
            JOptionPane.showMessageDialog(null, "ERRO"+e.getMessage());
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
      
    }
    
    public void venderProduto (String id){
        
        try {
            
            conn = new conectaDAO().connectDB();
            
            String sql = "update produtos set status = ? where id_produto = ?;";
            
            prep = conn.prepareStatement(sql);
            
            prep.setString(1, "VENDIDO");
            prep.setString(2, id);
            
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro\n"+e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public ArrayList<ProdutosDTO> listarProdutos(){
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        try {
            
            
        
        String sql = "select id_produto, nome, valor, status from produtos;";
        conn = new conectaDAO().connectDB();
        
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();
        
        while (resultset.next()){
            int id = resultset.getInt("id_produto");
            String nome = resultset.getString("nome");
            String valor = resultset.getString("valor");
            String status = resultset.getString("status");

            ProdutosDTO produto = new ProdutosDTO(id, nome, valor, status);

            listagem.add(produto); // Adicione o objeto à lista
                                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro na listagem\n"+e.getMessage());
        } 

        
        return listagem;
    }
    
    public ArrayList<ProdutosDTO> listarVendas(){
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        try {
            
            
        
        String sql = "select id_produto, nome, valor, status from produtos where status = 'VENDIDO';";
        conn = new conectaDAO().connectDB();
        
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();
        
        while (resultset.next()){
            int id = resultset.getInt("id_produto");
            String nome = resultset.getString("nome");
            String valor = resultset.getString("valor");
            String status = resultset.getString("status");

            ProdutosDTO produto = new ProdutosDTO(id, nome, valor, status);

            listagem.add(produto); // Adicione o objeto à lista
                                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro na listagem\n"+e.getMessage());
        } 

        
        return listagem;
    }
    
        
}

