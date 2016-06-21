/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Cordon;

/**
 *
 * @author koenv
 */
public interface ICordonService {
	public void createCordon(Cordon c);
	
	public List<Cordon> getAllCordons();
	
	public Cordon getCordonByName(String name);
	
	public void changeRate(Double rate, Cordon c);
}
