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
@XmlRootElement(name = "Cliente")
public class Cliente {
    private int idCliente;
    private String cliente;
    private String nacimiento;
    private String email;
    private String numtel;
    private String query;

    @XmlElement(required = true)
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @XmlElement(required = true)
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    @XmlElement(required = true)
    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    @XmlElement(required = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(required = true)
    public String getNumTel() {
        return numtel;
    }

    public void setNumTel(String numtel) {
        this.numtel = numtel;
    }

    public void insert() {
        query = "INSERT INTO cliente(cliente, nacimiento, email, numtel) values('" + cliente + "','" + nacimiento + "','" + email + "','" + numtel + "')";
        executeQuery(query, true);
    }

    public List<Cliente> mostrarTodas() {
        query = "SELECT * FROM cliente";
        return executeQuery(query, false);
    }

    public List<Cliente> buscar(String buscar) {
        query = "SELECT idcliente, cliente, nacimiento, email, numtel FROM cliente WHERE cliente like '%" + buscar + "%' or email like '%" + buscar + "%' or numtel like '%" + buscar + "%'";
        return executeQuery(query, false);
    }

    public void update() {
        query = "UPDATE cliente SET cliente = '" + cliente + "', nacimiento = '" + nacimiento + "', email = '" + email + "', numtel = '" + numtel + "' WHERE idcliente = " + idCliente;
        executeQuery(query, true);
    }

    public void delete() {
        query = "DELETE FROM cliente WHERE idcliente = " + idCliente;
        executeQuery(query, true);
    }

    private List<Cliente> executeQuery(String query, boolean update) {
        ArrayList<Cliente> clientes = null;
        Conexion conexion = new Conexion();
        Statement statement;
        Cliente cliente;
        Connection connection = conexion.getConnection();
        try {
            statement = connection.createStatement();
            if (update) {
                cliente = new Cliente();
                statement.executeUpdate(query);
            } else {
                clientes = new ArrayList<Cliente>();
                ResultSet resultSet;
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    cliente = new Cliente();
                    cliente.setIdCliente(resultSet.getInt("idcliente"));
                    cliente.setCliente(resultSet.getString("cliente"));
                    cliente.setNacimiento(resultSet.getString("nacimiento"));
                    cliente.setEmail(resultSet.getString("email"));
                    cliente.setNumTel(resultSet.getString("numtel"));
                    clientes.add(cliente);
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return clientes;
        }
    }
}
