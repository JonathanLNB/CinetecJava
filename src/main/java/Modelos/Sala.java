package Modelos;

import DataBase.Conexion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "Sala")
public class Sala {
    private int idSala;
    private String sala;
    private String query;

    @XmlElement(required = true)
    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    @XmlElement(required = true)
    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public void insert() {
        query = "INSERT INTO sala(sala) VALUES ('" + sala + "')";
        executeQuery(query, true);
    }

    public List<Sala> mostrarTodas() {
        query = "SELECT * FROM sala";
        return executeQuery(query, false);
    }

    public List<Sala> buscar(String busqueda) {
        query = "SELECT * FROM sala WHERE upper(sala) like upper('%" + busqueda + "%') ";
        return executeQuery(query, false);
    }

    public void update() {
        query = "UPDATE sala SET sala = '" + sala + "' WHERE idSala = " + idSala;
        executeQuery(query, true);
    }

    public void delete() {
        query = "DELETE FROM sala WHERE idSala = " + idSala;
        executeQuery(query, true);
    }

    private List<Sala> executeQuery(String query, boolean update) {
        ArrayList<Sala> Salaes = null;
        Conexion conexion = new Conexion();
        Statement statement;
        Sala sala;
        Connection connection = conexion.getConnection();
        try {
            statement = connection.createStatement();
            if (update) {
                sala = new Sala();
                statement.executeUpdate(query);
            } else {
                Salaes = new ArrayList<Sala>();
                ResultSet resultSet;
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    sala = new Sala();
                    sala.setIdSala(resultSet.getInt("idSala"));
                    sala.setSala(resultSet.getString("sala"));
                    Salaes.add(sala);
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return Salaes;
        }
    }
}


