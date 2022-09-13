import java.sql.*;

public class Statements {

    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student" , "Student123");
            myStmt = myConn.prepareStatement("select * from datasheet where zip_code = ? and company=?");

            myStmt.setDouble(1, 56120);
            myStmt.setString(2, "Hexaware");

            myRs = myStmt.executeQuery();

            display(myRs);

            System.out.println("\n\nReuse the prepared statement:  zip_code = 56120,  company = Hexaware");

            myStmt.setDouble(1, 56120);
            myStmt.setString(2, "Hexaware");

            myRs = myStmt.executeQuery();

            display(myRs);


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

    private static void display(ResultSet myRs) throws SQLException {
        while (myRs.next()) {
            String lastName = myRs.getString("last_name");
            String firstName = myRs.getString("first_name");
            double zip_code = myRs.getDouble("zip_code");
            String company = myRs.getString("company");

            System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, zip_code, company);
        }
    }
}
