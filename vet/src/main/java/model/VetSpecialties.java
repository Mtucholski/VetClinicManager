package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * list of vets specialties
 * @author mateusztucholski
 */
@XmlRootElement
public class VetSpecialties {

    private List<VetSpeciality> specialties;

    public List<VetSpeciality> getSpecialtiesAsList(){

        if (specialties == null){

            specialties = new ArrayList<>();
        }

        return  specialties;
    }
}
