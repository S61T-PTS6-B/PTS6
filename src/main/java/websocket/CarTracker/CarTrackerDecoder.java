/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.CarTracker;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.CarTracker;
import model.NAW;
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
	NAW nawct = ns.getNAWByBsn(jsonObj.getInt("bsn"));
	List<CarTracker> CtListForJson;
	CtListForJson = new ArrayList<>();
	
	CtListForJson = cts.getCarTrackerById(nawct);
        return encode.Encode(CtListForJson);
    }
}
