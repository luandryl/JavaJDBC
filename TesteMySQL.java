public class TesteMySQL{
	public static void main(String[] args) {
			
			ConnectionMySQL c = new ConnectionMySQL();

			c.getConect();
			c.close();


		}	
}