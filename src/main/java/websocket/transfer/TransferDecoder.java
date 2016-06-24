/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.transfer;

import javax.inject.Inject;
import org.json.JSONException;
import service.ITransferService;

/**
 *
 * @author koenv
 */
public class TransferDecoder implements ITransferDecoder{
	
	@Inject
	private ITransferService ts;
	
	TransferEncoder encoder;

	public TransferDecoder() {
	}
	
	@Override
	public String Decode(String object) throws JSONException {
		String result = "";
		
		return encoder.Encode(result);
	}
}
