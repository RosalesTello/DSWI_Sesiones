/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idat.app.dao;

import com.idat.app.entity.Persona;
import java.util.List;

/**
 *
 * @author IDAT
 */
public interface IPersonaDao {
    //insert delete update 

    public int operacionesEscritura(String indicador, Persona p);
    
    /// select
    public List<Persona>operacionesLectura(String indicador, Persona p);
    
}
