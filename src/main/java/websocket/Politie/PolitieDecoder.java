/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.Politie;

import websocket.NAW.*;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.CarOwner;
import model.CarTracker;
import model.NAW;
import org.json.JSONException;
import org.json.JSONObject;
import service.ICarOwnerService;
import service.ICarTrackerService;
import service.INAWService;
import service.NAWService;

/**
 *
 * @author koenv
 */
@Stateless
public class PolitieDecoder implements IPolitieDecoder {

	@Inject
	private INAWService ns;

	@Inject
	private ICarTrackerService cts;

	@Inject
	private ICarOwnerService cos;

	PolitieEncoder encode;

	public PolitieDecoder() {
	}

	@Override
	public String Decode(String object) throws JSONException {
		encode = new PolitieEncoder();
		System.out.println("Decoder");
		JSONObject jsonObj = new JSONObject(object);
		NAW test = null;
		CarOwner temp = null;
		CarTracker testct = null;
		String licenseplateReceive;
		licenseplateReceive = jsonObj.getString("licenseplate");
		testct = cts.getCarTrackerByLicensePlate(licenseplateReceive);
		temp = cos.getCarOwnerByCarTracker(testct);
		test = temp.getNawid();
		return encode.Encode(test, testct);

	}
}
