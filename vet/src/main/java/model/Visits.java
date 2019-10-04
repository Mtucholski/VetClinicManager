package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * domain object for representing a list of  visits
 */
@XmlRootElement
public class Visits {

    private List<Visit> visits;

    public List<Visit> getVisits(){

        if (visits == null || visits.isEmpty()){

            visits = new ArrayList<>();
        }

        return visits;
    }
}
