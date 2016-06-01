package batch;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
@Named("InvoiceReader")
public class InvoiceReader implements javax.batch.api.chunk.ItemReader {

    private MyCheckpoint checkpoint;
    @Inject
    JobContext jobCtx;
    JSONArray jsonarr;

    public InvoiceReader() {
    }

    @Override
    public void open(Serializable ckpt) throws Exception {
        if (ckpt == null) {
            checkpoint = new MyCheckpoint();
        } else {
            checkpoint = (MyCheckpoint) ckpt;
        }

        JSONParser parser = new JSONParser();
        
        String month = jobCtx.getProperties().getProperty("month");
        String year = jobCtx.getProperties().getProperty("year");
        
        String send = "month=" + month + "&year=" + year;
        String encrp = AESencrp.encrypt(send);
        String url = "http://145.93.81.135:8080/VerplaatsingSysteem/Rest/carTrackers/allMonth?code=" + encrp;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        
        String result = AESencrp.decrypt(response.toString());
        
        jsonarr = (JSONArray) parser.parse(result);
    }

    @Override
    public void close() throws Exception {
    }

    @Override
    public Object readItem() throws Exception {
        if ((int)checkpoint.getLineNum() < jsonarr.size()) {
            Object row = jsonarr.get((int) checkpoint.getLineNum());
            checkpoint.increase();
            return row;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return checkpoint;
    }
}
