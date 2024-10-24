/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idat.app.daoImpl;

import com.idat.app.dao.IPersonaDao;
import com.idat.app.entity.Persona;
import com.idat.app.repository.ConexionMysql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IDAT
 */
//implementa desde como si fuera el repositorio
public class PersonaDaoImpl implements IPersonaDao {

    @Override
    public int operacionesEscritura(String indicador, Persona p) {

        ConexionMysql connection = new ConexionMysql();//el nombre  de la conexion
        Connection con = null;
        CallableStatement cst = null;
        int procesar = -1;
        String procedimiento = "{call usp_persona:crud (?,?,?,?)}";

        try {

            con = connection.conectar();
            cst = con.prepareCall(procedimiento);
            cst.setString(1, indicador);
            cst.setInt(2, p.getCodigo());//de   persona 
            cst.setString(3, p.getNombre());
            cst.setString(4, p.getApellido());

            ///procesar = cst.executeUpdate();

        } catch (Exception ex) {
            System.out.println("OperacionEsccritura = Error" + ex.getMessage());
        } finally {

            try {

                if (cst != null) {
                    cst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.print("error" + ex.getMessage());
            }

        }
        return procesar; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public List<Persona> operacionesLectura(String indicador, Persona p) {

        ConexionMysql connection = new ConexionMysql();
        List<Persona> lista = new ArrayList<>();
        Connection con = null;
        CallableStatement cst = null;
        ResultSet rs = null;
        String procedimiento = "{call usp_persona_crud(?,?,?,?)}"; // Corrección en el formato del nombre del procedimiento
        //es otra forma de pedir
        try {
            // Establecer la conexión a la base de datos
            con = connection.conectar();
            // Preparar la llamada al procedimiento almacenado
            cst = con.prepareCall(procedimiento);
            cst.setString(1, indicador);
            cst.setInt(2, p.getCodigo());
            cst.setString(3, p.getNombre());
            cst.setString(4, p.getNombre());
            rs = cst.executeQuery();
            Persona objPersona;

            while (rs.next()) {
                objPersona = new Persona();
                objPersona.setCodigo(rs.getInt(1));
                objPersona.setNombre(rs.getString(2));
                objPersona.setApellido(rs.getString(3));
                lista.add(objPersona);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getSQLState());
            System.out.println("operacionesEscritura - Error : " + ex.getMessage()); //Para mostrar algun error 
            System.out.println("Código de error: " + ex.getErrorCode());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cst != null) {
                    cst.close();
                }
                if (cst != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.print("Error : " + ex.getMessage());
            }
        }
        return lista; //To change body of generated methods, choose Tools | Templates.
    }

}
