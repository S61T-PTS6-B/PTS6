/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import model.NAW;

/**
 *
 * @author koenv
 */
class MessageEncoder implements Encoder.TextStream<NAW> {

    @Override
    public void encode(NAW object, Writer writer) throws EncodeException, IOException {
        System.out.print("Encoder: ");
        JsonObject json = Json.createObjectBuilder()
                .add("bsn", object.getBsn())
                .add("firstname", object.getFirstname())
                .add("lastname", object.getLastname())
                .add("address", object.getAddress())
                .add("number", object.getNumber())
                .add("zipcode", object.getZipcode())
                .add("city", object.getCity())
                .add("telephone", object.getTelephone())
                .add("email", object.getEmail())
                .build();
        try {
            JsonWriter jsonWriter = Json.createWriter(writer);
            jsonWriter.write(json);
        } catch (Exception e) {

        }
    }

    @Override
    public void init(EndpointConfig config) {
        
    }

    @Override
    public void destroy() {
        
    }

}
