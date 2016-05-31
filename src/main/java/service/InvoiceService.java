/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.InvoiceDAO;
import java.util.List;
import javax.ejb.EJB;
import model.Invoice;

/**
 *
 * @author koenv
 */
public class InvoiceService implements IInvoiceService{
		@EJB
	InvoiceDAO ida;

	@Override
	public void createInvoice(Invoice i) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Invoice> getAllInvoices() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Invoice> getInvoiceByNAW() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
