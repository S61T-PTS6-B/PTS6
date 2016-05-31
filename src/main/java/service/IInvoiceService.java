/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Stateless;
import model.Invoice;

/**
 *
 * @author koenv
 */
@Stateless
public interface IInvoiceService {

	public void createInvoice(Invoice i);
	
	public List<Invoice> getAllInvoices();
		
	public List<Invoice> getInvoiceByNAW();
}
