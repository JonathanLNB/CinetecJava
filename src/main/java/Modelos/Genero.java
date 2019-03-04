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
@XmlRootElement(name = "Genero")
public class Genero {
    private int idGenero;
    private String genero;
    private String query;

    @XmlElement(required = true)
    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    @XmlElement(required = true)
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void insert() {
        query = "INSERT INTO genero(genero) VALUES ('" + genero + "')";
        executeQuery(query, true);
    }

    public List<Genero> mostrarTodas() {
        query = "SELECT * FROM genero";
        return executeQuery(query, false);
    }

    public List<Genero> buscarId(int idPelicula) {
        query = "SELECT genero FROM genero join peliculagenero using(idgenero) WHERE  idpelicula = p.idpelicula";
        return executeQuery(query, false);
    }

    public List<Genero> buscar(String busqueda) {
        query = "SELECT * FROM genero WHERE upper(genero) like upper('%" + busqueda + "%') ";
        return executeQuery(query, false);
    }

    public void update() {
        query = "UPDATE genero SET genero = '" + genero + "' WHERE idgenero = " + idGenero;
        executeQuery(query, true);
    }

    public void delete() {
        query = "DELETE FROM genero WHERE idgenero = " + idGenero;
        executeQuery(query, true);
    }

    private List<Genero> executeQuery(String query, boolean update) {
        ArrayList<Genero> generos = null;
        Conexion conexion = new Conexion();
        Statement statement;
        Genero genero;
        Connection connection = conexion.getConnection();
        try {
            statement = connection.createStatement();
            if (update) {
                genero = new Genero();
                statement.executeUpdate(query);
            } else {
                generos = new ArrayList<Genero>();
                ResultSet resultSet;
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    genero = new Genero();
                    genero.setIdGenero(resultSet.getInt("idgenero"));
                    genero.setGenero(resultSet.getString("genero"));
                    generos.add(genero);
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return generos;
        }
    }
}
