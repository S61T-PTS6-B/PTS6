/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Properties;
import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author koenv
 */
@Dependent
@Named("KweetReader")
public class KweetReader extends AbstractItemReader  {
    private CheckPoint checkpoint;
    private JSONArray jsonarr;
    
    @Inject
    private JobContext jobContext; 
    
    @Override
    public void open(Serializable checkpoint) throws Exception {
        if(checkpoint == null)
        {
            checkpoint = new CheckPoint();
        }
        else {
            checkpoint = (CheckPoint) checkpoint;
        }
        String fileName = jobContext.getProperties().getProperty("input_file");
        JSONParser parser = new JSONParser();
        JSONObject jsonobj = (JSONObject) parser.parse(new FileReader(fileName));
        jsonarr = (JSONArray) jsonobj.get("Tweets");
    }
    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object readItem() throws Exception {
        if ((int)checkpoint.getLineNum() < jsonarr.size()) {
        Object currenttweet = jsonarr.get((int)checkpoint.getLineNum());
        checkpoint.increase();
        return currenttweet;
        }
        return null;
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return checkpoint;
    }
    
}
