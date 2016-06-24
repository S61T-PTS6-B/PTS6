/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Transfer;

/**
 *
 * @author koenv
 */
public class TransferDAO implements ITransferDAO {

	@PersistenceContext(unitName = "com.PTS6B_RekeningAdministratieOverheid_war_1.0-SNAPSHOTPU")
	EntityManager em;

	@Override
	public void createTransfer(Transfer t) {
		em.persist(t);
	}

}
