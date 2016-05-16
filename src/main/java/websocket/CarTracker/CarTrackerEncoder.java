/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.CarTracker;

import java.io.Console;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
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

	public String Encode(List<CarTracker> object) {
		System.out.print("Encoder: ");
		JsonArrayBuilder jsonar;
		jsonar = Json.createArrayBuilder();

		for (CarTracker ct : object) {
			JsonObject json = Json.createObjectBuilder()
				.add("id", ct.getId())
				.add("priceCategory", ct.getPriceCategory())
				.add("licensePlate", ct.getLicensePlate())
				.add("modelCar", ct.getModelCar())
				.add("brandCar", ct.getBrandCar())
				.add("websiteSubscription", ct.isRekeningrijdersWebsite())
				.build();
			jsonar.add(json);
		}

		
		String deliver = jsonar.build().toString();
		return deliver;
	}
}
