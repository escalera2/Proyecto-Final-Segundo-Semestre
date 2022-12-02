package com.escalerados.dao;

import com.escalerados.model.Cliente;
import com.escalerados.model.Coche;
import com.escalerados.model.Revision;
import com.escalerados.util.ConectorBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RevisionDao {
    private Connection connection;

    public RevisionDao() {
        connection = ConectorBD.getConnection();
    }

    public void addRevision (Revision revision){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement( "INSERT INTO revisiones (codigo, filtro, aceite, frenos, placa) VALUES (?, ?, ?, ?, ?)" );

            preparedStatement.setString(1, revision.getCodigo());
            preparedStatement.setString(2, revision.getFiltro());
            preparedStatement.setString(3, revision.getAceite());
            preparedStatement.setString(4, revision.getFrenos());
            preparedStatement.setString(5, revision.getCoche().getPlaca());

            preparedStatement.executeUpdate();

            System.out.println(revision + " registrada con exito:");
        } catch (SQLException e){
            System.out.println("Errore en el registo de revision: " + e.getMessage());

        }
    }

    public void updateRevision(Revision revision){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE revisiones SET codigo=?, filtro=?, aceite=?, frenos=?, placa=? WHERE codigo=?");

            preparedStatement.setString(1, revision.getCodigo());
            preparedStatement.setString(2, revision.getFiltro());
            preparedStatement.setString(3, revision.getAceite());
            preparedStatement.setString(4, revision.getFrenos());
            preparedStatement.setString(5,revision.getCoche().getPlaca());

            preparedStatement.setString(6, revision.getCodigo());
            preparedStatement.executeUpdate();

            System.out.println(revision + "editada");
        } catch (SQLException e) {
            System.out.println("Error al editar la revisión : " + e.getMessage());
        }
    }

    public void deleteRevision(String codigo){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM revisiones WHERE codigo=?"
            );
            preparedStatement.setString(1, codigo);
            preparedStatement.executeUpdate();

            System.out.println(codigo + " eliminada");
        } catch (SQLException e) {
            System.out.println("Error al borrar la revisión: " + e.getMessage());
        }
    }

    public List<Revision> getAllRevisiones() {
        List<Revision> revisiones = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM revisiones");
            while (resultSet.next()) {
                Revision revision = new Revision();
                revision.setCodigo(resultSet.getString("codigo"));
                revision.setFiltro(resultSet.getString("filtro"));
                revision.setAceite(resultSet.getString("aceite"));
                revision.setFrenos(resultSet.getString("frenos"));

                String placa = resultSet.getString("matricula");
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM coches WHERE matricula = ?"
                );

                preparedStatement.setString(1,placa);
                ResultSet resultSet1 = preparedStatement.executeQuery();
                while (resultSet1.next()){
                    Coche coche = new Coche();
                    coche.setPlaca(resultSet1.getString("placa"));
                    coche.setMarca(resultSet1.getString("marca"));
                    coche.setModelo(resultSet1.getString("modelo"));
                    coche.setColor(resultSet1.getString("color"));
                    coche.setPrecio(resultSet1.getDouble("precio"));

                    String nif = resultSet1.getString("nif");

                    PreparedStatement preparedStatement1 = connection.prepareStatement(
                            "SELECT * FROM clientes WHERE nif = ?"
                    );
                    preparedStatement1.setString(1,nif);
                    ResultSet resultSet2 = preparedStatement1.executeQuery();
                    while (resultSet2.next()){

                        Cliente cliente = new Cliente();
                        cliente.setNif(resultSet2.getString("nif"));
                        cliente.setNombre(resultSet2.getString("nombre"));
                        cliente.setCiudad(resultSet2.getString("ciudad"));
                        cliente.setDireccion(resultSet2.getString("direccion"));
                        cliente.setTelefono(resultSet2.getInt("telefono"));

                        coche.setCliente(cliente);
                    }
                    revision.setCoche(coche);
                }
                revisiones.add(revision);
            }
        } catch (SQLException e) {
            System.out.println("Error al Listar las revisiones: " + e.getMessage());
        }

        return revisiones;


    }

}
