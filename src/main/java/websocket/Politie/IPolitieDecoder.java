/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.Politie;

import websocket.NAW.*;
import org.json.JSONException;

/**
 *
 * @author koenv
 */
public interface IPolitieDecoder {
    
    public String Decode(String object) throws JSONException;
    
}
