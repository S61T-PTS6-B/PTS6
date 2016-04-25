/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.CarOwner;

import org.json.JSONException;

/**
 *
 * @author koenv
 */
public interface ICarOwnerDecoder {
    
    public String Decode(String object) throws JSONException;
    
}