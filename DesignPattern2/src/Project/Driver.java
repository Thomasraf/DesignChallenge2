package Project;

public class Driver {

	public static void main(String[] args) {
		//"com.mysql.jdbc.Driver","jdbc:mysql://112.211.95.65:3306/","superuser","arielariel0","swdespa"
		
		//jdbc:mysql://127.0.0.1:3306/
		
		//test adding for Git REEEE
		String URL = "jdbc:mysql://127.0.0.1:3306/";
		String USERNAME = "root";
		String PASSWORD = "arielariel0";
		String DATABASE = "swdespa";
		
		Database.getInstance().setConnection(URL, USERNAME, PASSWORD, DATABASE);
		
		selectAccount sa = new selectAccount();
		
	}

}
