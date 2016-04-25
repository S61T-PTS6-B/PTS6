/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.CarOwner;

import javax.inject.Inject;
import model.CarOwner;
import model.CarTracker;
import org.json.JSONException;
import org.json.JSONObject;
import service.ICarOwnerService;
import service.ICarTrackerService;
import service.INAWService;

/**
 *
 * @author koenv
 */
public class CarOwnerDecoder implements ICarOwnerDecoder {

    @Inject
    private ICarOwnerService cos;

    @Inject
    private INAWService ns;

    CarOwnerEncoder encode;

    public CarOwnerDecoder() {

    }

    @Override
    public String Decode(String object) throws JSONException {
        encode = new CarOwnerEncoder();
        System.out.println("Decoder");
        JSONObject jsonObj = new JSONObject(object);
        CarOwner co = null;
        String id;
        id = jsonObj.getString("id");
        co = cos.getCarOwnerByNawId(ns.getNAWByBsn(Integer.parseInt(id)));
        return encode.Encode(co);
    }
}

