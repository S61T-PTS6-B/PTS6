/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.NawDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.NAW;

/**
 *
 * @author koenv
 */
@Stateless
public class NAWService implements INAWService {

	@EJB
	NawDAO nd;

	@Override
	public void createNAW(NAW naw) {
		nd.createNaw(naw);
	}

	@Override
	public List<NAW> getAllNaws() {
		return nd.getAllNaws();
	}

	@Override
	public NAW getNAWByBsn(int bsn) {
		return nd.getNawByBsn(bsn);
	}

	@Override
	public NAW changeMail(NAW naw, String newmail) {
		return nd.changeMail(naw, newmail);
	}

	@Override
	public NAW changePhone(NAW naw, String newphone) {
		return nd.changePhone(naw, newphone);
	}

	@Override
	public NAW changeFirstname(NAW naw, String newfirstname) {
		return nd.changeFirstname(naw, newfirstname);
	}

	@Override
	public NAW changeLastname(NAW naw, String newlastname) {
		return nd.changeLastname(naw, newlastname);
	}

	@Override
	public NAW changeAddress(NAW naw, String newstreet) {
		return nd.changeAddress(naw, newstreet);
	}

	@Override
	public NAW changeHouseNumber(NAW naw, String newnumber) {
		return nd.changeNumber(naw, newnumber);
	}

	@Override
	public NAW changeZipcode(NAW naw, String newzipcode) {
		return nd.changeZipcode(naw, newzipcode);
	}

	@Override
	public NAW changeCity(NAW naw, String newcity) {
		return nd.changeCity(naw, newcity);
	}

	@Override
	public NAW changeTelephone(NAW naw, String newtelephone) {
		return nd.changeTelephone(naw, newtelephone);
	}

	@Override
	public NAW changeEmail(NAW naw, String newmail) {
		return nd.changeEmail(naw, newmail);
	}

	@Override
	public NAW changeMembership(NAW naw) {
		return nd.changeMembership(naw);
	}

}
