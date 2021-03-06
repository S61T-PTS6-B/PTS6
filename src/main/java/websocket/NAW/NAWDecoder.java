/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.NAW;

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
		try {
			System.out.println(object);
			String email = jsonObj.getString("newmail");
			String telephone = jsonObj.getString("newphone");
			String register = jsonObj.getString("register");
			if (email.equals("")) {
				if (telephone.equals("")) {
					if (register.equals("")) {
						test = ns.getNAWByBsn(bsnConverted);
						return encode.Encode(test);
					} else {
						test = ns.getNAWByBsn(bsnConverted);
						NAW result = ns.changeMembership(test);
						return encode.Encode(result);
					}

				} else {
					String newphone = jsonObj.getString("newphone");
					System.out.println("Nieuwe telefoon: " + newphone);
					NAW result = ns.changePhone(test, newphone);
					return encode.Encode(result);
				}
			} else {
				String newmail = jsonObj.getString("newmail");
				System.out.println("Nieuwe email: " + newmail);
				NAW result = ns.changeMail(test, newmail);
				return encode.Encode(result);
			}
		} catch (JSONException e) {
			System.out.println("Er is geen goede JSON string gestuurd.");
			return null;
		}

	}
}
