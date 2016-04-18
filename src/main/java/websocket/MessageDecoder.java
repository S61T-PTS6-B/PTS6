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

/**
 *
 * @author koenv
 */
class MessageDecoder implements Decoder.TextStream<NAW> {

    @Inject
    private NawDAO nd;
    
    @Override
    public void init(EndpointConfig config) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NAW decode(Reader reader) throws DecodeException, IOException {
        JsonReader jsonReader = Json.createReader(reader);
        JsonObject jsonObj = jsonReader.readObject();
        NAW test = null;
        String bsnReceive;
        bsnReceive = jsonObj.getString("bsn");
        test = nd.getNawByBsn(Integer.parseInt(bsnReceive));
        return test;
    }
    
}
