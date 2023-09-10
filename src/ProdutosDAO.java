
/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
//import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    List<ProdutosDTO> listagem = new ArrayList<>();
    private String sql;
    
    public int cadastrarProduto (ProdutosDTO produto){
        
        //Iniciando a conexão com o banco de dados
        conectaDAO conectaDao = new conectaDAO();
        con = conectaDao.abrirConexao();
        
        //conn = new conectaDAO().connectDB();
        int status = 0;//Se for 1 significa inserido com sucesso, 0 erro na inserção
            try {
                sql = "INSERT INTO produtos (nome, valor, status) "
                        + "VALUES (?,?,?);";
                ps = con.prepareStatement(sql);
                ps.setString(1, produto.getNome());
                ps.setString(2, String.valueOf(produto.getValor()));
                ps.setString(3, produto.getStatus());
                status = ps.executeUpdate(); 
                System.out.println("Dados inseridos com sucesso!");
                return status;//Retorna 1 se for bem sucedido
                
            } catch (SQLException sqle) {
                System.out.println("Erro ao inserir os dados " + sqle.getMessage());
                return status;
            } finally {
                //Finalizando a conexao com o banco de dados
                conectaDao.fecharConexao();
            }
    }
    
    public List<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

