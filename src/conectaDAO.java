
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    private static String bancoDados = "uc11";
    private static String url = "jdbc:mysql://localhost:3306/" + bancoDados;
    private static String user = "root";
    private static String password = "MmYySsQqLl_22";//ANTES DE ENVIAR/COMMITAR, APAGAR ESSA SENHA
    private static Connection con = null;
    private static PreparedStatement ps;
    private static ResultSet rs = null;
    private static String sql = null;
    private static boolean conectado;
        
    //Construtores
    public conectaDAO(String bancoDados, String url, String user, String password){
        this.bancoDados = bancoDados;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public conectaDAO() {
    }

    //Getters e Setters
    public static String getBancoDados() {
        return bancoDados;
    }

    public static void setBancoDados(String bancoDados) {
        conectaDAO.bancoDados = bancoDados;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        conectaDAO.url = url;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        conectaDAO.user = user;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        conectaDAO.password = password;
    }

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        conectaDAO.con = con;
    }

    public static PreparedStatement getPs() {
        return ps;
    }

    public static void setPs(PreparedStatement ps) {
        conectaDAO.ps = ps;
    }

    public static ResultSet getRs() {
        return rs;
    }

    public static void setRs(ResultSet rs) {
        conectaDAO.rs = rs;
    }

    public static String getSql() {
        return sql;
    }

    public static void setSql(String sql) {
        conectaDAO.sql = sql;
    }

    public static boolean isConectado() {
        return conectado;
    }

    public static void setConectado(boolean conectado) {
        conectaDAO.conectado = conectado;
    }

    
    
        public static Connection abrirConexao(){
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); //Carregando o Driver
                setCon(DriverManager.getConnection(getUrl(), getUser(), getPassword())); //Criando o objeto para conexão
                setConectado(true);
//                System.out.println("Driver JDBC carregado");
                return getCon();
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Driver JDC não encontrado" + cnfe.getMessage());
            } catch (SQLException sqle) {
                System.out.println("Erro na conexão com o banco de dados" + sqle.getMessage());
                setConectado(false);
                return null;
            }
            
            return null;
        }
    
        public static void fecharConexao(){
            try {
                if( getCon() != null){
                    getCon().close();
                }
            } catch (SQLException sqle) {
                //Deixo em branco para não exibir uma mensagem desnecessária para o usuário
            }
            
        }
    
}
