/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch;
 
import calculations.LocationsCalculator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.batch.api.chunk.ItemProcessor;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import model.CarTracker;
import model.Invoice;
import model.Location;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import service.ICarTrackerService;
 
 
/**
 *
 * @author koenv
 */
@Dependent
@Named("InvoiceProcessor")
public class InvoiceProcessor implements ItemProcessor {
 
    @EJB
    private ICarTrackerService cts;
    @EJB
    LocationsCalculator lc;
 
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
            DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.US);
            Date date = format.parse((String) location.get("date"));
            Location newLocation = new Location(latitude, longitude, date);
            locList.add(newLocation);
        }
        CarTracker car = cts.getCarTrackerByLicensePlate((String) containerobj.get("licenseplate"));
        Invoice newInvoice = lc.getInvoice(locList, car);
                newInvoice.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
                newInvoice.setYear(Calendar.getInstance().get(Calendar.YEAR));
 
        return newInvoice;
    }
 
}