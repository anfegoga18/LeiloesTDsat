
/**
 *
 * @author Andrés Felipe González García
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ProdutosDAO {
    
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
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
    
    public boolean contarProdutos(){//Saber se a tabela no banco de dados tem registros
        
        //Iniciando a conexão com o banco de dados
        conectaDAO conectaDao = new conectaDAO();
        con = conectaDao.abrirConexao();
            try {
                sql = "SELECT count(*) FROM produtos;";
                ps = con.prepareStatement(sql);
                ps.executeQuery();//OBS: para execuções que não sejam alterações, inserções ou eliminações deve ser usado executeQuerry ao invés do executeUpdate
                System.out.println("Contagem de elementos realizada");
                return true;
            } catch (SQLException sqle) {
                System.out.println("Houve um erro ao contar os registros na tabela --> " + sqle.getMessage());
                return false;
            } finally {
                //Finalizando a conexao com o banco de dados
                conectaDao.fecharConexao();
            }
        }
    
    public List<ProdutosDTO> listaDeProdutos(){
        
        List<ProdutosDTO> lista = new ArrayList<>();
        
        //Iniciando a conexão com o banco de dados
        conectaDAO conectaDao = new conectaDAO();
        con = conectaDao.abrirConexao();
        
        try{
            ProdutosDTO produto = new ProdutosDTO();
            sql = "SELECT * FROM produtos;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //Vai listar todos os produtos na tabela produtos
                if (rs.next()) {//Carrega os dados se encontrou um filme
                    produto.setId(rs.getInt("id"));
                    produto.setNome(rs.getString("nome"));
                    produto.setValor(rs.getInt("valor"));
                    produto.setStatus(rs.getString("status"));
                    
                    lista.add(produto);
                    return lista;
                } else {
                    System.out.println("Não foram achados produtos para listar");
                    return null;
                }
            } catch (SQLException sqle){
                System.out.println("Aconteceu um erro ao tentar consultar. " + sqle.getMessage());
                return null;
            } finally {
                //Finalizando a conexao com o banco de dados
                conectaDao.fecharConexao();
            }
    }
    
    public int venderProduto(JFrame view, int id){
    
    int status = 0;//Se for 1 significa inserido com sucesso, 0 erro na inserção
    
        if(contarProdutos()){
        
        //Iniciando a conexão com o banco de dados
        conectaDAO conectaDao = new conectaDAO();
        con = conectaDao.abrirConexao();
        
            try {
                sql = "UPDATE produtos "
                        + "SET status = ? "
                        + "WHERE id = ? ;";
                ps = con.prepareStatement(sql);
                ps.setString(1, "Vendido" );
                ps.setString(2, String.valueOf(id));
                status = ps.executeUpdate(); 
                System.out.println("Venda atualizada com sucesso!");
                return status;//Retorna 1 se for bem sucedido
                
            } catch (SQLException sqle) {
                System.out.println("Erro ao atualizar os dados " + sqle.getMessage());
                return status;
            } finally {
                //Finalizando a conexao com o banco de dados
                conectaDao.fecharConexao();
            }
        
        } else {
            JOptionPane.showMessageDialog(view, "Não há produtos para serem vendidos");
        }
        
        return status;
    }
    
}

