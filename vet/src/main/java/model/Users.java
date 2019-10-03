package model;

import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * list of users for reports
 */
@XmlRootElement
public class Users {

    private List<User> users;

    public List<User> getUsers(){

        if (users == null){

            users = new ArrayList<>();
        }

        return  users;
    }
}
