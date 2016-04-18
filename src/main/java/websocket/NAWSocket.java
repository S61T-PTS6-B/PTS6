/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import model.NAW;

/**
 *
 * @author koenv
 */
@ServerEndpoint(value="/RekeningAdministratieSocket", decoders = {MessageDecoder.class}, encoders = {MessageEncoder.class})
public class NAWSocket {

    public NAWSocket() {
    }
    
    
    @OnMessage
    public NAW message(NAW naw, Session session){
        System.out.println("Hij is in message");
        try {
            session.getBasicRemote().sendObject(naw);
            System.out.println("Verstuur NAW");
        } catch (Exception ex)
        {
            System.out.println("Shit man");
            Logger.getLogger(NAWSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return naw;
    }
    
    @OnOpen 
    public void myOnOpen(Session session) {
        System.out.println("Hij is open " + session.toString());
        System.out.println(session.isOpen());
        
    }
    
    @OnError
    public void onError(Session session, Throwable thr) 
    {
        System.out.println(thr.toString());
    }
    
    
}
