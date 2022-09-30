/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.pastor.persistencia;

import java.util.ArrayList;
import java.util.List;
import mx.itson.pastor.entidades.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author OSORIO 
 */
public class ClienteDAO { // DATA ACCCES OBJECT - DAO 
    
    public static List<Cliente> obtenerTodos(){
    
        List<Cliente> clientes = new ArrayList();
        try {
            Connection connection = Conexion.obtener();
            Statement statement = connection.createStatement(); //enunciado a la BD
            ResultSet resultSet = statement.executeQuery("SELECT id, nombre, direccion, telefono, email FROM cliente"); //Retornar varios registros, consultas de la tabla
            while(resultSet.next()){   //next - Interando los datos (todos los datos de la tabla)
                Cliente c = new Cliente();
                c.setId(resultSet.getInt(1));
                c.setNombre(resultSet.getString(2));
                c.setDireccion(resultSet.getString(3));
                c.setTelefono(resultSet.getString(4));
                c.setEmail(resultSet.getString(5));
                clientes.add(c);
                
            }
   
        } catch(Exception ex){
            System.err.print("Ocurrió un error: " + ex.getMessage());

        }
        return clientes;
             
    }
    
    public static boolean guardar(String nombre, String direccion, String telefono, String email){
        boolean resultado = false;
        try{
            Connection connection = Conexion.obtener();
            String consulta = "INSERT INTO cliente(nombre, direccion, telefono, email) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, nombre);
            statement.setString(2, direccion);
            statement.setString(3, telefono);
            statement.setString(4, email);
            statement.execute();
        
            resultado = statement.getUpdateCount()== 1; //Devuelve las filas afectadas en la consulta
         //   connection.close();
            
        } catch(Exception ex){
            System.err.print("Ocurrió un error: " + ex.getMessage());

        }
        return resultado;
    
    }
    //VALIDAR LOS DATOS Y QUE NO ESTEN REPETIDOS POR CORREO
    
    public static Cliente validarporCorreo(String nombre, String direccion, String telefono, String email){
        Cliente cliente = new Cliente();
        
        try {
            Connection connection = Conexion.obtener();
            String consulta = "SELECT id, nombre, direccion, telefono, email FROM cliente WHERE email LIKE ?";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, email);
            
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()){
            
                cliente.setId(resultSet.getInt(1));
                cliente.setNombre(resultSet.getString(2));
                cliente.setDireccion(resultSet.getString(3));
                cliente.setTelefono(resultSet.getString(4));
                cliente.setEmail(resultSet.getString(5)); 
            
            }
            
        }catch(Exception ex){
            System.err.println("Ocurrio un error..." + ex.getMessage());
        
        }
        return cliente;
    
    }
    
    
    
}
