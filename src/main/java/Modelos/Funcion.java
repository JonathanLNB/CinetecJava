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
@XmlRootElement(name = "Funcion")
public class Funcion {
    private int idFuncion;
    private int idSala;
    private int idIdioma;
    private int idPelicula;
    private String fechahora;
    private String query;

    @XmlElement(required = true)
    public int getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(int idFuncion) {
        this.idFuncion = idFuncion;
    }

    @XmlElement(required = true)
    public int setIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    @XmlElement(required = true)
    public int getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }

    @XmlElement(required = true)
    public String getFechaHora() {
        return fechahora;
    }

    public void setFechaHora(String fechahora) {
        this.fechahora = fechahora;
    }

    @XmlElement(required = true)
    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public void insert() {
        query = "INSERT INTO Funcion(fechahora, idPelicula, idSala, idIdioma) VALUES ('" + fechahora + "', " + idPelicula + ", " + idSala + ", " + idIdioma + ")";
        executeQuery(query, true);
    }

    public List<Funcion> mostrarTodas() {
        query = "SELECT idpelicula, fechahora, idPelicula, idSala, clasificacion FROM Funcion p join clasificacion using(idIdioma)";
        return executeQuery(query, false);
    }

    public List<Funcion> buscar(String busqueda) {
        query = "SELECT idpelicula, fechahora, idPelicula, idSala, clasificacion FROM Funcion p join clasificacion using(idIdioma) WHERE fechahora like '%" + busqueda + "%'";
        return executeQuery(query, false);
    }

    public void update() {
        query = "update Funcion set idSala=" + idSala + ", idIdioma=" + idIdioma + ", idPelicula= " + idPelicula + " , fechahora= '" + fechahora + "' where idfuncion=" + idFuncion;
        executeQuery(query, true);
    }

    public void delete() {
        query = "delete from Funcion where idpelicula=" + idFuncion;
        executeQuery(query, true);
    }

    private List<Funcion> executeQuery(String query, boolean update) {
        ArrayList<Funcion> peliculas = null;
        Conexion conexion = new Conexion();
        Statement statement;
        Funcion pelicula;
        Connection connection = conexion.getConnection();
        try {
            statement = connection.createStatement();
            if (update) {
                pelicula = new Funcion();
                statement.executeUpdate(query);
            } else {
                peliculas = new ArrayList<Funcion>();
                ResultSet resultSet;
                Genero aux = new Genero();
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    pelicula = new Funcion();
                    pelicula.setIdFuncion(resultSet.getInt("idpelicula"));
                    pelicula.setFechaHora(resultSet.getString("fechahora"));
                    pelicula.setIdPelicula(resultSet.getInt("idPelicula"));
                    pelicula.setIdSala(resultSet.getInt("idSala"));
                    pelicula.setIdIdioma(resultSet.getInt("idIdioma"));
                    peliculas.add(pelicula);
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return peliculas;
        }
    }

}
