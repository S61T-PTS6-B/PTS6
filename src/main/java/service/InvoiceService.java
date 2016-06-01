/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.InvoiceDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.Invoice;
import model.NAW;

/**
 *
 * @author koenv
 */
@Stateless
public class InvoiceService implements IInvoiceService{
	@EJB
	InvoiceDAO ida;
	

	@Override
	public void createInvoice(Invoice i) {
		ida.createInvoice(i);
	}

	@Override
	public List<Invoice> getAllInvoices() {
		return ida.getAllInvoices();
	}

	@Override
	public List<Invoice> getInvoiceByNAW(NAW naw) {
		return ida.getInvoiceByNAW(naw);
	}

	@Override
	public void payInvoice(Long id) {
		ida.payInvoice(id);
	}

	@Override
	public List<Invoice> getPaidInvoicesByNAW(NAW naw) {
		return ida.getPaidInvoicesByNAW(naw);
	}
	
}
