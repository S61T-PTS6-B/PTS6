package batch;
 
import SendQueue.InvoiceQueueSend;
import model.Invoice;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.List;
import javax.batch.runtime.context.JobContext;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import pdf.PdfBuilder;
import service.CarOwnerService;
import service.ICarOwnerService;
import service.IInvoiceService;
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Max
 */
@Dependent
@Named("InvoiceWriter")
public class InvoiceWriter implements javax.batch.api.chunk.ItemWriter {
 
    @Inject
    private JobContext jobCtx;
   
    @EJB
    private IInvoiceService iis;
    
    @Inject 
    private ICarOwnerService cos;
 
    @Override
    public void open(Serializable ckpt) throws Exception {
        //opent niks
    }
 
    @Override
    public void writeItems(List<Object> items) throws Exception {
        for (Object i : items) {
            Invoice invoice = (Invoice) i;
            PdfBuilder pdfb = new PdfBuilder();
            String urltodownload = pdfb.createPdf(invoice);
            invoice.setURLToDownload(urltodownload);
            iis.createInvoice(invoice);
	    InvoiceQueueSend.sendMessage(invoice, String.valueOf(cos.getCarOwnerByCarTracker(invoice.getCar()).getNawid().getBsn()));
        }
    }
 
    @Override
    public void close() throws Exception {
        //sluit niks
    }
 
    @Override
    public Serializable checkpointInfo() throws Exception {
        return new MyCheckpoint();
    }
}