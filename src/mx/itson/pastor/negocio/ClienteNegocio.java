/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.pastor.negocio;

import mx.itson.pastor.persistencia.ClienteDAO;
import javax.swing.JOptionPane;
import mx.itson.pastor.entidades.Cliente;

/**
 *
 * @author OSORIO
 */
public class ClienteNegocio {
    //Verifica si no hay datos repetidos
    public static boolean guardar(String nombre, String direccion, String telefono, String email){
        boolean resultado = false;
        Cliente cliente = ClienteDAO.validarporCorreo(nombre, direccion, telefono, email);
        
        try{
            if (cliente.getId() != 0) {
                
                JOptionPane.showMessageDialog(null, "Este registro ya ha sido guardado", "Resgistro duplicado", JOptionPane.ERROR_MESSAGE);
   
            }else{
                resultado = ClienteDAO.guardar(nombre, direccion, telefono, email);
            }
            
        }catch (Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        
        }
         return resultado;    
        
    }
}
