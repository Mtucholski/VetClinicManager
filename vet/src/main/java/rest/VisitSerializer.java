package rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import model.Client;
import model.Pet;
import model.Visit;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class VisitSerializer extends StdSerializer<Visit> {



    protected VisitSerializer(Class<Visit> t) {
        super(t);
    }

    @Override
    public void serialize(Visit visit, JsonGenerator gen, SerializerProvider provider) throws IOException {

        if (visit.getVisitDate() == null){

            throw new IOException("visit cannot be without date!!");
        }

        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        gen.writeStartObject();

        if (visit.getId() == null){

            gen.writeNullField("id");
        }else {

            gen.writeNumberField("visitID", visit.getId());
        }

        gen.writeStringField("visitDate", formatter.format(visit.getVisitDate()));
        gen.writeStringField("visitDescription", visit.getDescription());

        gen.writeObjectFieldStart("pet");
        Optional<Pet> pet = Optional.ofNullable(visit.getPet());

        if (pet.isPresent()){

            gen.writeStringField("petName", pet.get().getName());
            gen.writeStringField("birthDay", formatter.format(pet.get().getBirthDate()));
            gen.writeStringField("owner",pet.get().getClient().getFirstName()) ;
            gen.writeStringField("ownerLastName", pet.get().getClient().getLastName());
        }else {

            throw new IOException("pet cannot be null");
        }

        gen.writeEndObject();// pet
        gen.writeEndObject(); // visit
    }
}
