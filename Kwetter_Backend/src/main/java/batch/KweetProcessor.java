/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch;

import dao.UserDao;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.batch.api.chunk.ItemProcessor;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import model.Kweet;
import model.KwetterUser;
import org.json.simple.JSONObject;

/**
 *
 * @author koenv
 */
@Dependent
@Named("KweetProcessor")
public class KweetProcessor implements ItemProcessor {
    
    @EJB 
    UserDao ud;
    
    @Override
    public Object processItem(Object item) throws Exception {
        JSONObject tweetobj = (JSONObject) item;
        Kweet tweetentity = new Kweet();
        tweetentity.setContent((String) tweetobj.get("tweet"));
        tweetentity.setPostedFrom((String) tweetobj.get("postedFrom"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);       
        String datestring = (String) tweetobj.get("postDate");
        Date tweetdate = sdf.parse(datestring);
        tweetentity.setPostingdate(tweetdate);
        String poster = (String) tweetobj.get("screenName");
        KwetterUser user = ud.GetUserByNickname(poster);
        if (user != null) {
            tweetentity.setPoster(user);
        }
        else {
            user = new KwetterUser();
            user.setNickname(poster);
            ud.create(user);
            tweetentity.setPoster(user);
        }       
        return tweetentity;
    }
    
}
