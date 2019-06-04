package DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
public class Conexion {
    private String cadenaconexion="jdbc:postgresql://localhost:5432/cinetecj";
    private String user="jonalnb";
    private String pass="fsd74126";
    private String driver= "org.postgresql.Driver";
    private  Connection connection;

    public Conexion(){
        try {
            Class.forName(driver);
            connection= DriverManager.getConnection(cadenaconexion,user,pass);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public Connection getConnection(){
        return connection;
    }
}
