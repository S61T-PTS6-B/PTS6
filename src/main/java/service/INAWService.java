/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.NAW;

/**
 *
 * @author koenv
 */
public interface INAWService {

	void createNAW(NAW naw);

	List<NAW> getAllNaws();

	NAW getNAWByBsn(int bsn);

	NAW changeMail(NAW naw, String newmail);

	NAW changePhone(NAW naw, String newphone);

	NAW changeFirstname(NAW naw, String newfirstname);

	NAW changeLastname(NAW naw, String newlastname);

	NAW changeAddress(NAW naw, String newstreet);

	NAW changeHouseNumber(NAW naw, String newnumber);
	
	NAW changeZipcode(NAW naw, String newzipcode);
	
	NAW changeCity(NAW naw, String newcity);
	
	NAW changeTelephone(NAW naw, String newtelephone);
	
	NAW changeEmail(NAW naw, String newmail);
	
	NAW changeMembership(NAW naw);

}
