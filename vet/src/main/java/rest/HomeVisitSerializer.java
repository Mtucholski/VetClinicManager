package rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import model.HomeVisit;
import model.Pet;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

public class HomeVisitSerializer extends StdSerializer<HomeVisit> {


    protected HomeVisitSerializer(Class<HomeVisit> t) {
        super(t);
    }

    @Override
    public void serialize(HomeVisit homeVisit, JsonGenerator gen, SerializerProvider provider) throws IOException {

        if ((homeVisit == null) || (homeVisit.getPet() == null)){

            throw new IOException("Can't serialize home visit object!. Some necessary values are null");
        }

        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        gen.writeStartObject();

        if (homeVisit.getId() == null){

            gen.writeNullField("id");
        }else{

            gen.writeNumberField("id", homeVisit.getId());
        }

        gen.writeStringField("vetFirstName", homeVisit.getVet().getFirstName());
        gen.writeStringField("vetLastName", homeVisit.getVet().getLastName());
        gen.writeStringField("visitDescription", homeVisit.getDescription());

        gen.writeObjectFieldStart("pet");
        Pet pet  = homeVisit.getPet();

        gen.writeStringField("ownerFistName", pet.getClient().getFirstName());
        gen.writeStringField("ownerLastName", pet.getClient().getLastName());
        gen.writeStringField("petName", pet.getName());
        gen.writeStringField("birthday", formatter.format(pet.getBirthDate()));
        gen.writeStringField("dateFrom", formatter.format(homeVisit.getDateFrom()));
        gen.writeStringField("dateTo", formatter.format(homeVisit.getDateTo()));
        gen.writeEndObject();//pet
        gen.writeEndObject();// homeVisit
    }
}
