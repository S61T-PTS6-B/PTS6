/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch;
import dao.KweetDao;
import java.io.Serializable;
import java.util.List;
import javax.batch.api.chunk.ItemWriter;
import javax.batch.runtime.context.JobContext;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import model.Kweet;
/**
 *
 * @author Gijs
 */
@Dependent
@Named("KweetWriter")
public class KweetWriter implements ItemWriter {
    @EJB
    private KweetDao kd;
    @Inject JobContext jobContext;
    
    @Override
    public void open(Serializable checkpoint) throws Exception {
        
    }
    @Override
    public void close() throws Exception {
        
    }
    @Override
    public void writeItems(List<Object> items) throws Exception {
        for (Object item : items) {
            Kweet k = (Kweet) item;
            service.PostTweet(t);
        }
    }
    @Override
    public Serializable checkpointInfo() throws Exception {
        return new MyCheckpoint();
    }
    
}
