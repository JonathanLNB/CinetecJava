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
@XmlRootElement(name = "Idioma")
public class Idioma {
    private int idIdioma;
    private String idioma;
    private String query;

    @XmlElement(required = true)
    public int getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }

    @XmlElement(required = true)
    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void insert() {
        query = "INSERT INTO idioma(idioma) VALUES ('" + idioma + "')";
        executeQuery(query, true);
    }

    public List<Idioma> mostrarTodas() {
        query = "SELECT * FROM idioma";
        return executeQuery(query, false);
    }

    public List<Idioma> buscar(String busqueda) {
        query = "SELECT * FROM idioma WHERE upper(idioma) like upper('%" + busqueda + "%') ";
        return executeQuery(query, false);
    }

    public void update() {
        query = "UPDATE idioma SET idioma = '" + idioma + "' WHERE idIdioma = " + idIdioma;
        executeQuery(query, true);
    }

    public void delete() {
        query = "DELETE FROM idioma WHERE idIdioma = " + idIdioma;
        executeQuery(query, true);
    }

    private List<Idioma> executeQuery(String query, boolean update) {
        ArrayList<Idioma> idiomas = null;
        Conexion conexion = new Conexion();
        Statement statement;
        Idioma idioma;
        Connection connection = conexion.getConnection();
        try {
            statement = connection.createStatement();
            if (update) {
                idioma = new Idioma();
                statement.executeUpdate(query);
            } else {
                idiomas = new ArrayList<Idioma>();
                ResultSet resultSet;
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    idioma = new Idioma();
                    idioma.setIdIdioma(resultSet.getInt("idIdioma"));
                    idioma.setIdioma(resultSet.getString("idioma"));
                    idiomas.add(idioma);
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return idiomas;
        }
    }
}


