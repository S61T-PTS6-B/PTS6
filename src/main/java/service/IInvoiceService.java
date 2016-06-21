/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Stateless;
import model.Invoice;
import model.NAW;

/**
 *
 * @author koenv
 */
public interface IInvoiceService {

	public void createInvoice(Invoice i);
	
	public List<Invoice> getAllInvoices();
		
	public List<Invoice> getInvoiceByNAW(NAW naw);
	
	public void payInvoice(Long id);
	
	public List<Invoice> getPaidInvoicesByNAW(NAW naw);
	
	public void saveInvoice(Invoice in);

	public void sendLetter(Long invoiceID);
	
}
