/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CordonDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Cordon;

/**
 *
 * @author koenv
 */
@Stateless
public class CordonService implements ICordonService {

	@EJB
	CordonDAO cd;

	@Override
	public void createCordon(Cordon c) {
		cd.createCordon(c);
	}

	@Override
	public List<Cordon> getAllCordons() {
		return cd.getAllCordons();
	}

	@Override
	public Cordon getCordonByName(String name) {
		return cd.getCordonByName(name);
	}

	@Override
	public void changeRate(Double rate, Cordon c) {
		cd.changeRate(rate, c);
	}

}
