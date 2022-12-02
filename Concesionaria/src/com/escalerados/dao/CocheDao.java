package com.escalerados.dao;

import com.escalerados.model.Cliente;
import com.escalerados.model.Coche;
import com.escalerados.util.ConectorBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CocheDao {
    private Connection connection;

    public CocheDao() {
        connection = ConectorBD.getConnection();
    }

    public void addCoche (Coche coche){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement( "INSERT INTO coches (placa, marca, modelo, color, precio, nif) VALUES (?, ?, ?, ?, ?, ?)" );
            preparedStatement.setString(1,coche.getPlaca());
            preparedStatement.setString(2, coche.getMarca());
            preparedStatement.setString(3, coche.getModelo());
            preparedStatement.setString(4, coche.getColor());
            preparedStatement.setDouble(5, coche.getPrecio());
            preparedStatement.setString(6, coche.getCliente().getNif());
            preparedStatement.executeUpdate();

            System.out.println(coche + " creado");
        } catch (SQLException e) {
            System.out.println("Error al crear el coche: " + e.getMessage());
        }
    }

    public void updateCoche(Coche coche){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE coches SET Placa=?, marca=?, modelo=?, color=?, precio=?, nif=? WHERE placa=?");

            preparedStatement.setString(1, coche.getPlaca());
            preparedStatement.setString(2, coche.getMarca());
            preparedStatement.setString(3, coche.getModelo());
            preparedStatement.setString(4, coche.getColor());
            preparedStatement.setDouble(5,coche.getPrecio());
            preparedStatement.setString(6, coche.getCliente().getNif());

            preparedStatement.setString(7, coche.getPlaca());
            preparedStatement.executeUpdate();

            System.out.println(coche + " fue editado!");
        } catch (SQLException e) {
            System.out.println("Error al editar el coche: " + e.getMessage());
        }
    }

    public void deleteCoche(String placa){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM coches WHERE placa=?"
            );
            preparedStatement.setString(1, placa);
            preparedStatement.executeUpdate();

            System.out.println(placa + " eliminado");
        } catch (SQLException e) {
            System.out.println("Error al borrar el coche: " + e.getMessage());
        }
    }

    public List<Coche> getAllCoches() {
        List<Coche> coches = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM coches");

            while (resultSet.next()){
                Coche coche = new Coche();
                coche.setPlaca(resultSet.getString("placa"));
                coche.setMarca(resultSet.getString("marca"));
                coche.setModelo(resultSet.getString("modelo"));
                coche.setColor(resultSet.getString("color"));
                coche.setPrecio(resultSet.getDouble("precio"));

                String nif = resultSet.getString("nif");
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "Select * FROM clientes where nif = ?"
                );


                preparedStatement.setString(1,nif);
                ResultSet resultSet1 = preparedStatement.executeQuery();
                while (resultSet1.next()){
                    Cliente cliente = new Cliente();
                    cliente.setNif(resultSet1.getString("nif"));
                    cliente.setNombre(resultSet1.getString("nombre"));
                    cliente.setCiudad(resultSet1.getString("ciudad"));
                    cliente.setDireccion(resultSet1.getString("direccion"));
                    cliente.setTelefono(resultSet1.getInt("telefono"));

                    coche.setCliente(cliente);
                }
                coches.add(coche);
            }
        }catch (SQLException e){
            System.out.println("Error al listar los coches: "+ e.getMessage());
        }
        return coches;
    }
}
