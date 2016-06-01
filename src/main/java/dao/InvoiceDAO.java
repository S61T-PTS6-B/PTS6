/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Invoice;
import model.NAW;

/**
 *
 * @author koenv
 */
public interface InvoiceDAO {

	public void createInvoice(Invoice i);

	public List<Invoice> getAllInvoices();

	public List<Invoice> getInvoiceByNAW(NAW naw);

	public void payInvoice(Long id);

	public List<Invoice> getPaidInvoicesByNAW(NAW naw);
	
}
