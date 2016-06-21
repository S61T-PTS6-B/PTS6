/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import service.IInvoiceService;

/**
 *
 * @author koenv
 */
@MessageDriven(mappedName = "PaidQueue", activationConfig = {
	@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class InvoicePaidBean implements MessageListener {

	@Inject
	IInvoiceService iis;

	public InvoicePaidBean() {
	}

	@Override
	public void onMessage(Message message) {
		System.out.println("Ik ontvang iets.");

		try {
			Long invoiceID;
			invoiceID = message.getLongProperty("id");
			if (message.getStringProperty("type").equals("paid")) {
				iis.payInvoice(invoiceID);
			} else if (message.getStringProperty("type").equals("unknown")) {
				iis.sendLetter(invoiceID);
			}

		} catch (JMSException ex) {
			Logger.getLogger(InvoicePaidBean.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
