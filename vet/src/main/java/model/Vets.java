package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * domain object for representing a list of vets
 */
@XmlRootElement
public class Vets {

    private List<Vet> vets;

    public List<Vet> getVets() {

        if (vets ==null){

            vets = new ArrayList<>();
        }
        return vets;
    }
}
