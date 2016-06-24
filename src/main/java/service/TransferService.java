/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.TransferDAO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Transfer;

/**
 *
 * @author koenv
 */
@Stateless
public class TransferService implements ITransferService{
	@EJB
	TransferDAO td;
	
	@Override
	public void createTransfer(Transfer t) {
		td.createTransfer(t);
	}
	
}
