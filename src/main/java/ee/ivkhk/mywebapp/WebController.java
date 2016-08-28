/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ivkhk.mywebapp;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Random;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jvm
 */
@Named(value = "webController")
@SessionScoped
public class WebController implements Serializable {

    Integer randomNumber;
    Integer userNumber;
    String response;
    
    /**
     * Creates a new instance of WebController
     */
    public WebController() {
        Random randomGenerator = new Random();
        randomNumber= new Integer(randomGenerator.nextInt(10));
        System.out.println("Дюк задумал число:"+randomNumber);
    }
    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }

    public String getResponse() {
        if(userNumber != null && userNumber.compareTo(randomNumber)==0){
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();
            
            return "Ого! Угадал!";
        }
        return "<p>Сожалею, " + userNumber + " - число неправильное.</p>"
                + "<p>Попробуй еще...</p>";
    }
    
}
