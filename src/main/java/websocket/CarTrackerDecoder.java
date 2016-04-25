/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import javax.ejb.Stateless;
import javax.inject.Inject;
import model.CarTracker;
import org.json.JSONException;
import org.json.JSONObject;
import service.ICarTrackerService;
import service.INAWService;

/**
 *
 * @author koenv
 */
@Stateless
public class CarTrackerDecoder implements ICarTrackerDecoder{

    @Inject
    private ICarTrackerService cts;
    
    @Inject
    private INAWService ns;

    CarTrackerEncoder encode;

    public CarTrackerDecoder() {
        
    }
    
    @Override
    public String Decode(String object) throws JSONException {
        encode = new CarTrackerEncoder();
        System.out.println("Decoder");
        JSONObject jsonObj = new JSONObject(object);
        CarTracker ct = null;
        String id;
        id = jsonObj.getString("id");
        ct = cts.getSingleCarTrackerById(Long.parseLong(id));
        return encode.Encode(ct);
    }
}
