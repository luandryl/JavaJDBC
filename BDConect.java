import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BDConect{
	static Connection connection = "TESTE";
	static ResultSet result      = null;
	static Statement statement   = null;

	//Dados do SGBD
	static String  usuario = "postgres";
	static String  senha  = "postgres";
	static String url = "jdbc:postgresql://localhost:5432/BDCom"; // nome da fonte de dados
	

	public static void main(String args[]){
		getConect();
		//inSQL("insert into clientes(pk_cli, nome) values (77, 'CASOS')");
		//updSQL("update clientes set sex = 'M' where pk_cli = 700");
		//updSQL("update clientes set debito = 0 where pk_cli = 700");
		//updSQL("update clientes set salario = 0 where pk_cli = 700");
		setResult("select * from clientes");
	}
	
	//método para criar a conexão com SGBD
	public static void getConect(){
		try{
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url,usuario,senha);
			statement = connection.createStatement();
		}

		catch(ClassNotFoundException erro){
			System.out.println("\n\nNao encontrou o Driver do BD --> "+erro+"!");
                        		erro.printStackTrace();

		}
		catch(SQLException erro){
			System.out.println("\n\nProblemas na execução do SQL--> "+erro+"!");
			System.exit(0);
		}
	}


	//metodo para a execucao de uma inserção
	public static void inSQL(String sql){
		boolean boolIns = false;
		try{
			getConect();
			boolIns = statement.execute(sql);
		}
		catch(SQLException erro){
			System.out.println("\n\nErro de SQL BDConecta "+erro+"! -- de SQL em BDConecta");
		}
		close();
	}


	//metodo para a execucao de uma atualização
	public static void updSQL(String sql){
		int intUpd = 0;
		try{
			getConect();
			intUpd = statement.executeUpdate(sql);
		}
		catch(SQLException erro){
			System.out.println("Erro ao Atualizar! "+erro);
		}
		close();
	}


	//metodo para a execucao de uma consulta
	public static void setResult(String sql){
		ResultSet result = null;
		try{
			getConect();
			result = statement.executeQuery(sql);
			
		}	
		catch(SQLException erro){
			System.out.println("\n\nErro de SQL BDConecta "+erro+"! -- de SQL em BDConecta");
			System.exit(0);
		}
		try{
			while(result.next()){
				System.out.println("\nCPF...: "+ result.getInt("pk_cli"));
				System.out.print("Nome..: "+result.getString("nome"));

			}
		}
		catch(SQLException errSql){
			System.out.println("Error de SQL "+errSql+"! -- Erro na Consulta");
			System.exit(0);	
		}
    	}


	
	//metodo para encerrar a conexao
	public static void close(){
		
		try{
			if(!connection.isClosed()) connection.close();
		}
		catch(SQLException erro){
			System.out.println("\n\nErro ao desconectar a: "+url+"! -- Erro de Desconexao!");
						
		}
	}

}

