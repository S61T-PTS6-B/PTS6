/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.CarTracker;

import javax.json.Json;
import javax.json.JsonObject;
import model.CarTracker;
import model.NAW;

/**
 *
 * @author koenv
 */
public class CarTrackerEncoder {
        public CarTrackerEncoder() {
    }
    
    
        public String Encode(CarTracker object){
        System.out.print("Encoder: ");
        JsonObject json = Json.createObjectBuilder()
                .add("id", object.getId())
                .add("priceCategory", object.getPriceCategory())
                .add("licensePlate", object.getLicensePlate())
                .add("modelCar", object.getModelCar())
                .add("brandCar", object.getBrandCar())
                .add("websiteSubscription", object.isRekeningrijdersWebsite())
        .build();
        return json.toString();
    }
}
