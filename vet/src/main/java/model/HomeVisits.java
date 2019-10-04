package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * domain object for representing a list of home visits
 * @author mateusztucholski
 */
@XmlRootElement
public class HomeVisits {

    private List<HomeVisit> homeVisits;

    public List<HomeVisit> getHomeVisits(){

        if (homeVisits == null || homeVisits.isEmpty()){

            homeVisits = new ArrayList<>();
        }

        return homeVisits;
    }
}
