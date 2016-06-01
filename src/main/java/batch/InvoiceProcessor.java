package batch;

import calculations.LocationsCalculator;
import model.Invoice;
import model.Location;
import static java.lang.System.out;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import model.CarTracker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import service.CarTrackerService;
import javax.batch.api.chunk.ItemProcessor;

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
@Named("InvoiceProcessor")
public class InvoiceProcessor implements ItemProcessor {
	
	@EJB
	CarTrackerService cts;
	
	public InvoiceProcessor() {
	}

	@Override
	public Object processItem(Object obj) throws Exception {
		JSONObject containerobj = (JSONObject) obj;
		JSONArray locArray = (JSONArray) containerobj.get("locations");
		List<Location> locList = new ArrayList<>();

		for (int i = 0; i < locArray.size(); i++) {
			JSONObject location = (JSONObject) locArray.get(i);
			double latitude = (double) location.get("lat");
			double longitude = (double) location.get("long");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse((String) location.get("date"));
			Location newLocation = new Location(latitude, longitude, date);
			locList.add(newLocation);
		}
		LocationsCalculator lc = new LocationsCalculator();
		CarTracker car = cts.getCarTrackerByLicensePlate((String) containerobj.get("licenseplate"));
		Invoice newInvoice = lc.getInvoice(locList, car);

		return newInvoice;
	}
}
