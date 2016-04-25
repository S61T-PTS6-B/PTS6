/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import model.NAW;

/**
 *
 * @author koenv
 */
public class NAWEncoder {

    public NAWEncoder() {
    }
    
    
        public String Encode(NAW object){
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
        return json.toString();
    }
}
