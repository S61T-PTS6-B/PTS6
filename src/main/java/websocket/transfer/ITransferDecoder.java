/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.transfer;

import org.json.JSONException;

/**
 *
 * @author koenv
 */
public interface ITransferDecoder {

	public String Decode(String object) throws JSONException;
	
}
