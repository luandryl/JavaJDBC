import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


 
public class ConnectionMySQL {
    
    //Objetos de Conexão
    private static Connection connection = null;
    private static ResultSet result      = null;
    private static Statement statement   = null;

    //Dados do SGBD
    private static final String url = "jdbc:mysql://localhost";
    private static final String user = "root";
    private static final String password = "t1i2i5";

    //Metodo que realiza a conexão
    public void getConect(){
        try{
            Class.forName ("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
        }

        catch(ClassNotFoundException erro){
            System.out.println("\n\nDriver NOT FOUND --> "+erro+"!");
                                erro.printStackTrace();

        }
        
        catch(SQLException erro){
            System.out.println("\n\nSQL ERROL--> "+erro+"!");
            System.exit(0);
        }

    }
    
    //Metodo para encerrar a conexão
    public void close(){
        
        try{
            if(!connection.isClosed()) connection.close();
        }
        catch(SQLException erro){
            System.out.println("\n\nDisconecction ERRO "+url+"!");
                        
        }
    }



    

}
