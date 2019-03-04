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
@XmlRootElement(name = "Pelicula")
public class Pelicula {
    private int idPelicula;
    private String titulo, sinopsis;
    private int duracion;
    private int idclasificacion;
    private String query;
    private List<Genero> generos;

    @XmlElement(required = true)
    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    @XmlElement(required = true)
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @XmlElement(required = true)
    public int getIdclasificacion() {
        return idclasificacion;
    }

    public void setIdclasificacion(int idclasificacion) {
        this.idclasificacion = idclasificacion;
    }

    @XmlElement(required = true)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @XmlElement(required = true)
    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    @XmlElement(required = true)
    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void insert() {
        query = "INSERT INTO pelicula(titulo, sinopsis, duracion, idclasificacion) VALUES ('" + titulo + "', '" + sinopsis + "', " + duracion + ", " + idclasificacion + ")";
        executeQuery(query, true);
        insertGeneros();
    }

    public void insertGeneros() {
        for (Genero aux : generos)
            query = "INSERT INTO peliculagenero VALUES (" + idPelicula + ", " + aux.getIdGenero() + ")";
        executeQuery(query, true);
    }

    public List<Pelicula> mostrarTodas() {
        query = "SELECT idpelicula, titulo, sinopsis, duracion, clasificacion FROM pelicula p join clasificacion using(idclasificacion)";
        return executeQuery(query, false);
    }

    public List<Pelicula> buscar(String busqueda) {
        query = "SELECT idpelicula, titulo, sinopsis, duracion, clasificacion FROM pelicula p join clasificacion using(idclasificacion) WHERE titulo like '%" + busqueda + "%'";
        return executeQuery(query, false);
    }

    public void update() {
        query = "update pelicula set duracion=" + duracion + ", idclasificacion=" + idclasificacion + ", sinopsis= '" + sinopsis + "' , titulo= '" + titulo + "' where idpelicula=" + idPelicula;
        executeQuery(query, true);
    }

    public void delete() {
        query = "delete from pelicula where idpelicula=" + idPelicula;
        executeQuery(query, true);
    }

    private List<Pelicula> executeQuery(String query, boolean update) {
        ArrayList<Pelicula> peliculas = null;
        Conexion conexion = new Conexion();
        Statement statement;
        Pelicula pelicula;
        Connection connection = conexion.getConnection();
        try {
            statement = connection.createStatement();
            if (update) {
                pelicula = new Pelicula();
                statement.executeUpdate(query);
            } else {
                peliculas = new ArrayList<Pelicula>();
                ResultSet resultSet;
                Genero aux = new Genero();
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    pelicula = new Pelicula();
                    pelicula.setIdPelicula(resultSet.getInt("idpelicula"));
                    pelicula.setTitulo(resultSet.getString("titulo"));
                    pelicula.setSinopsis(resultSet.getString("sinopsis"));
                    pelicula.setDuracion(resultSet.getInt("duracion"));
                    pelicula.setIdclasificacion(resultSet.getInt("idclasificacion"));
                    pelicula.setGeneros(aux.buscarId(idPelicula));
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
