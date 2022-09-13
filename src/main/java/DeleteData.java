import java.sql.*;

public class DeleteData {

    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String user = "student";
        String pass = "Student123";

        try {
            myConn = DriverManager.getConnection(dbUrl, user, pass);
            myStmt = myConn.createStatement();
            System.out.println("BEFORE THE DELETE...");
            displayEmployee(myConn, "Doe", "John");

            // DELETE the employee
            System.out.println("\nDELETING THE EMPLOYEE: Doe John\n");

            int rowsAffected = myStmt.executeUpdate(
                    "delete from datasheet " +
                            "where last_name='John' and first_name='Doe'");

            // Call helper method to display the employee's information
            System.out.println("AFTER THE DELETE...");
            displayEmployee(myConn, "Doe", "John");

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        finally {
            close(myConn, myStmt, myRs);
        }
    }

    private static void displayEmployee(Connection myConn, String firstName, String lastName) throws SQLException {
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // Prepare statement
            myStmt = myConn
                    .prepareStatement("select last_name, first_name, email from datasheet where last_name=? and first_name=?");

            myStmt.setString(1, lastName);
            myStmt.setString(2, firstName);

            // Execute SQL query
            myRs = myStmt.executeQuery();

            // Process result set
            boolean found = false;

            while (myRs.next()) {
                String theLastName = myRs.getString("last_name");
                String theFirstName = myRs.getString("first_name");
                String email = myRs.getString("email");

                System.out.printf("Found employee: %s %s, %s\n", theFirstName, theLastName, email);
                found=true;
            }

            if (!found) {
                System.out.println("Employee NOT FOUND: " + firstName + " " + lastName);
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            close(myStmt, myRs);
        }

    }

    private static void close(Connection myConn, Statement myStmt,
                              ResultSet myRs) throws SQLException {
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

    private static void close(Statement myStmt, ResultSet myRs)
            throws SQLException {

        close(null, myStmt, myRs);
    }
}
