/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.NAW;
import org.json.JSONException;
import org.json.JSONObject;
import service.INAWService;
import service.NAWService;

/**
 *
 * @author koenv
 */
@Stateless
public class NAWDecoder implements INAWDecoder {
    @Inject
    private INAWService ns;

    NAWEncoder encode;

    
    public NAWDecoder() {
    }

    @Override
    public String Decode(String object) throws JSONException {
        encode = new NAWEncoder();
        System.out.println("Decoder");
        JSONObject jsonObj = new JSONObject(object);
        NAW test = null;
        String bsnReceive;
        bsnReceive = jsonObj.getString("bsn");
        int bsnConverted = Integer.parseInt(bsnReceive);
        test = ns.getNAWByBsn(bsnConverted);
        return encode.Encode(test);
    }
}
