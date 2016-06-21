/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SendQueue;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.Queue;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.NamingException;
import model.CarOwner;
import model.CarTracker;
import model.Invoice;
import model.NAW;
import service.CarOwnerService;
import service.CarTrackerService;
import service.ICarOwnerService;
import service.INAWService;
import service.NAWService;

/**
 *
 * @author koenv
 */
@Stateless
public class InvoiceQueueSend {

	@Inject
	INAWService ns;

	@Inject
	ICarOwnerService cos;

	// Sends a message to the InvoiceQueue, will be received by the RekeningRijders App
	public static void sendMessage(Invoice i, String bsn) throws NamingException, JMSException {
		ConnectionFactory connFactory = new ConnectionFactory();
		connFactory.setProperty(ConnectionConfiguration.imqAddressList, "mq://145.93.44.182:7676");

		Queue myQueue = new Queue("InvoiceQueue");

		try (Connection connection = connFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(myQueue)) {

			Message message = session.createTextMessage("new message");
			message.setLongProperty("id", i.getId());
			message.setStringProperty("bsn", bsn);
			message.setStringProperty("cartracker", i.getCar().getLicensePlate());
			message.setDoubleProperty("totalAmount", i.getTotalAmount());
			message.setIntProperty("month", i.getMonth());
			message.setIntProperty("year", i.getYear());
			message.setBooleanProperty("paid", i.isPaid());
			message.setStringProperty("URLToDownload", i.getURLToDownload());
			message.setDoubleProperty("totalDistance", i.getTotalDistance());
			producer.send(message);
			
			System.out.println("Message sent." + message.toString());
		}
	}
}
