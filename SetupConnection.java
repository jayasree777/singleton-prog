import java.sql.*;
import java.util.Scanner;

public class SetupConnection{
    private Connection con;
    private Statement st;
    private ResultSet results;
    private Boolean connected = false;

    private static SetupConnection setconn;

    private SetupConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Setup Successful.");

            Scanner scan  = new Scanner(System.in);

            System.out.println("Please Enter your mySQL password to connect to database: ");
            String pass = scan.nextLine();

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/OLCustomers?serverTimezone=UTC", "root", pass);
            System.out.println("Database Connection Successful.");
            connected = true;

            st = con.createStatement();
        }
        catch(Exception e){
            connected = false;
            System.out.println(e);
        }
    }

    public static SetupConnection getInstance(){
        if(setconn==null){
            setconn=new SetupConnection();
        }
        return setconn;
    }

    public void getSomething(){
        System.out.println("Database connected with singleton pattern");
    }

    public static void main(String[] args) {
        SetupConnection st=SetupConnection.getInstance();
        st.getSomething();
    }

}

   /* public ResultSet writeQuery(String query){
        if(connected){
            try{
                    results = st.executeQuery(query);
                    System.out.println("Query Execution Successful.");
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        return results;
    }

    public void updateQuery(String query){
        if(connected){
            try{
                    st.executeUpdate(query);
                    System.out.println("Query Update Successful.");
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }

    public void closeConnection(){
        try{
            if(connected){
                connected = false;
                con.close();
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public Boolean checkConnection(){
        return connected;
    }

}
*/