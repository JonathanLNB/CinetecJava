package Modelos;

import DataBase.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "Clasificacion")
public class Clasificacion {
    private int idClasificacion;
    private String clasificacion;
    private String query;

    @XmlElement(required = true)
    public int getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(int idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    @XmlElement(required = true)
    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void insert() {
        query = "INSERT INTO clasificacion(clasificacion) VALUES ('" + clasificacion + "')";
        executeQuery(query, true);
    }

    public List<Clasificacion> mostrarTodas() {
        query = "SELECT * FROM clasificacion";
        return executeQuery(query, false);
    }

    public List<Clasificacion> buscar(String busqueda) {
        query = "SELECT * FROM clasificacion WHERE upper(clasificacion) like upper('%" + busqueda + "%') ";
        return executeQuery(query, false);
    }

    public void update() {
        query = "UPDATE clasificacion SET clasificacion = '" + clasificacion + "' WHERE idclasificacion = " + idClasificacion;
        executeQuery(query, true);
    }

    public void delete() {
        query = "DELETE FROM clasificacion WHERE idclasificacion = " + idClasificacion;
        executeQuery(query, true);
    }

    private List<Clasificacion> executeQuery(String query, boolean update) {
        ArrayList<Clasificacion> clasificaciones = null;
        Conexion conexion = new Conexion();
        Statement statement;
        Clasificacion clasificacion;
        Connection connection = conexion.getConnection();
        try {
            statement = connection.createStatement();
            if (update) {
                clasificacion = new Clasificacion();
                statement.executeUpdate(query);
            } else {
                clasificaciones = new ArrayList<Clasificacion>();
                ResultSet resultSet;
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    clasificacion = new Clasificacion();
                    clasificacion.setIdClasificacion(resultSet.getInt("idclasificacion"));
                    clasificacion.setClasificacion(resultSet.getString("clasificacion"));
                    clasificaciones.add(clasificacion);
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return clasificaciones;
        }
    }
}