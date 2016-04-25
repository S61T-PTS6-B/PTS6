/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.CarOwner;

import javax.json.Json;
import javax.json.JsonObject;
import model.CarOwner;
import model.CarTracker;

/**
 *
 * @author koenv
 */
class CarOwnerEncoder {
    public CarOwnerEncoder() {
    }
    
    
        public String Encode(CarOwner object){
        System.out.print("Encoder: ");
        JsonObject json = Json.createObjectBuilder()
                .add("id", object.getId())
                .add("naw", object.getNawid().getBsn()) //RETURN BSN NUMMER
                .add("ct", object.getCarid().getLicensePlate()) //RETURN KENTEKEN
        .build();
        return json.toString();
    }
}
