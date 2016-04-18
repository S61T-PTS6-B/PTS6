/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import dao.NawDAO;
import java.io.IOException;
import java.io.Reader;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import model.NAW;
import service.INAWService;

/**
 *
 * @author koenv
 */
class MessageDecoder implements Decoder.TextStream<NAW> {

    @Inject
    INAWService ns;
    
    @Override
    public void init(EndpointConfig config) {
        System.out.println("Init");
    }

    @Override
    public void destroy() {
        
    }

    @Override
    public NAW decode(Reader reader) throws DecodeException, IOException {
        System.out.println("Decoder");
        JsonReader jsonReader = Json.createReader(reader);
        JsonObject jsonObj = jsonReader.readObject();
        NAW test = null;
        String bsnReceive;
        bsnReceive = jsonObj.getString("bsn");
        test = ns.getNAWByBsn(Integer.parseInt(bsnReceive));
        return test;
    }
    
}
