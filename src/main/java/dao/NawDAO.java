/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.NAW;

/**
 *
 * @author koenv
 */
public interface NawDAO {
    public void createNaw(NAW naw);
    
    public List<NAW> getAllNaws();

    public NAW getNawById(long id);
}
