package com.escalerados;

import com.escalerados.dao.ClienteDao;
import com.escalerados.dao.CocheDao;
import com.escalerados.dao.RevisionDao;
import com.escalerados.model.Cliente;
import com.escalerados.model.Coche;
import com.escalerados.model.Revision;
import com.escalerados.util.ConectorBD;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Cliente cliente = new Cliente("1451", "Carlos Perez", "Cochabamba", "Av. Ayacucho s/n",3324328);
        //Cliente  carlos = new Cliente("1456", "Juan Perez", "Cochabamba", "Av. Ayacucho s/n",4226678);
        //Cliente model = new Cliente("353453", "Yair Voris", "Cochabamba", "Calle san carlos s/n",009832);
        //Cliente vanis= new Cliente("372353", "Boris", "Oruro", "Calle caspia ", 874355);
        //Coche toyota = new Coche ("09441","toyota","BH98065","amarillo",308231,vanis);
        //Cliente laura= new Cliente("009832", "mery", "Cochabamba", "Calle Oruro ", 4200876);
        Cliente cris = new Cliente("0409199", "Juan luisa", "cochabamba", "Av.America #0865", 4762212);

        //Coche moster = new Coche ("09034SDF","Moster","67264","rojo",34503,laura);
        //Coche coral = new Coche("NBDJKS","BMW", "HWE324", "Negro", FEWFEWV, model);
        Coche mitsubishi = new Coche ("WE09234","mitshu","ASD56","negro",900873,cris);


        Revision numero3 = new Revision("444000","No sirve","Correcto","Cambiados",mitsubishi);





        ClienteDao clienteDao = new ClienteDao();
        CocheDao cocheDao = new CocheDao();
        RevisionDao revisionDao = new RevisionDao();




        //CREATE
        //clienteDao.addCliente(cliente);
        //clienteDao.addCliente(model);
        //clienteDao.addCliente(laura);
        clienteDao.addCliente(cris);

        //cocheDao.addCoche(coral);
        //cocheDao.addCoche(moster);
        cocheDao.addCoche(mitsubishi);









        //READ
        /*List<Cliente> clientes = clienteDao.getAllClientes();
        for (int i = 0; i < clientes.size(); i++){
           Cliente cliente = clientes.get(i);

           System.out.println(cliente);
        }
        */

        /*List<Coche> coches = cocheDao.getAllCoches();
        for (int i = 0; i < coches.size(); i++){
            Coche coche = coches.get(i);
            System.out.println(coche);
        }*/

        /*List<Revision> revisiones = revisionDao.getAllRevisiones();
        for (int i = 0 ; i < revisiones.size(); i++){
            Revision revision = revisiones.get(i);
            System.out.println(revision);
        }*/




        //UPDATE
        /*Cliente cliente = new Cliente("1421", "Fernando Torricos", "Cochabamba", "A.v 6 de agosto s/n",4444444);
        clienteDao.updateCliente(cliente);*/

        //Coche coche = new Coche("3289","Ferrari","BVE769","ROSA",87323,vanis);
        //cocheDao.updateCoche(coche);

        Revision revision = new Revision("444000", "buenos", "Correcto", "estables", mitsubishi);
        revisionDao.updateRevision(revision);


        //DELETE
        //clienteDao.deleteCliente("1456-1B");
        //clienteDao.deleteCliente("1452");

        //cocheDao.deleteCoche("BHG098");
        //cocheDao.deleteCoche("HD6KJ");
        //revisionDao.deleteRevision("234525");
        //revisionDao.deleteRevision("444000");



    }
}
