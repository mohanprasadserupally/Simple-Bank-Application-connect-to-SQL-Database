import java.sql.*;

public class BankDetails {

	private String AccountNumber;
	private double Balance;
	private String Name;
	private String Email;
	private String PhoneNumber;

	public BankDetails(String Number, double Balance, String Name, String Email, String PhoneNumber) {
		this.AccountNumber = Number;
		this.Balance = Balance; 
		this.Name = Name;
		this.Email = Email;
		this.PhoneNumber = PhoneNumber;
	}

	public void DepoistMoney(double DepositedMoney) {
		this.Balance += DepositedMoney;
		System.out.println("Deposit is Successful new Balance is" + this.Balance);
	}

	public void WithDraw(double WithDrawlMoney) {
		if (this.Balance - WithDrawlMoney < 0) {
			System.out.println("WithDraw UnSuccessful only" + this.Balance + "is left");
		} else {
			this.Balance -= WithDrawlMoney;
			System.out.println("WithDraw successful , Current Balace is" + this.Balance);
		}
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public double getBalance() {
		return Balance;
	}

	public void setBalance(double balance) {
		Balance = balance;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public static void main(String[] args) {
		String jdbcURL = "jdbc:postgresql://localhost:5432/Bank Details";
		String username = "postgres";
		String password = "root";

		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("Connection to PostgreSQL server");
			
			
			
			String createTableSQL = "CREATE TABLE IF NOT EXISTS bank_account "
					+ "(" + "AccountNumber BIGINT PRIMARY KEY, "
					+ "Name VARCHAR(255), " + "Balance DECIMAL(10, 2), " 
					+ "Email VARCHAR(255), "
					+ "PhoneNumber VARCHAR(20))";

			Statement createTableStatement = connection.createStatement();
			createTableStatement.execute(createTableSQL);
			createTableStatement.close();

			
			String insertDataSQL = "INSERT INTO bank_account (AccountNumber, Name, Balance,"
					+ " Email, PhoneNumber) "
					+ "VALUES ('123456', 'Mohan', '2500', 'mohan.sherla@gmail.com',"
					+ " '7865415')";

			Statement statement = connection.createStatement();
			int rows = statement.executeUpdate(insertDataSQL);
			if (rows > 0) {
				System.out.println("A new row has been inserted.");
			}

			connection.close();
		} catch (SQLException e) {
			System.out.println("Error in connecting to PostgreSQL server");
			e.printStackTrace();
		}
	}
}
