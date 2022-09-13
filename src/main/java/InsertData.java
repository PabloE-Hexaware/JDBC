import java.sql.*;

public class InsertData {

    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String user = "student";
        String pass = "Student123";

        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection(dbUrl, user, pass);

            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Insert a new employee
            System.out.println("Inserting a new employee to database\n");

            int rowsAffected = myStmt.executeUpdate(
                    "INSERT INTO `datasheet` (`id`,`first_name`,`last_name`,`email`, `password`, `company`, `address`, `city`, `zip_code`, `mobile_phone`) " +
                            "VALUES (4,'Sam','Sam','susan.queue@foo.com', 'Susan123','Hexaware','Mexico','CDMX',56120, 55436785)");


            // 4. Verify this by getting a list of employees
            myRs = myStmt.executeQuery("select * from datasheet order by last_name");

            // 5. Process the result set
            while (myRs.next()) {
                System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
    }
}
