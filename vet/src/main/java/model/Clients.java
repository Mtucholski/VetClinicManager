package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * domain object for representing a list of  clients
 * @author mateusztucholski
 */
@XmlRootElement
public class Clients {

    List<Client> clients;

    public List<Client> getClients(){

        if (clients == null || clients.isEmpty()){

            clients = new ArrayList<>();
        }

        return clients;
    }
}
