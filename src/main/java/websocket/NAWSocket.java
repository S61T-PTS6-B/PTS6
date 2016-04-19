/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.json.JSONException;

/**
 *
 * @author koenv
 */
@ServerEndpoint(value = "/RekeningAdministratieSocket")
public class NAWSocket {

    private Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    @Inject
    IMessageDecoder decoder;

    public NAWSocket() {
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Server : open");
        sessions.add(session);
    }

    @OnMessage
    public String onMessage(String Message) throws JSONException {
        System.out.println("Receive from client: " + Message);
        String replyMessage = decoder.Decode(Message);
        System.out.println("send to client: " + replyMessage);
        return replyMessage;
    }
}
