/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.Politie;

import websocket.NAW.*;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import model.CarTracker;
import model.NAW;

/**
 *
 * @author koenv
 */
public class PolitieEncoder {

    public PolitieEncoder() {
    }
    
    
        public String Encode(NAW object, CarTracker ctobject){
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
		.add("id", ctobject.getId())
		.add("licenseplate", ctobject.getLicensePlate())
		.add("brand", ctobject.getBrandCar())
		.add("model", ctobject.getModelCar())
		.add("pricecategory", ctobject.getPriceCategory())
                .build();
        return json.toString();
    }
}
