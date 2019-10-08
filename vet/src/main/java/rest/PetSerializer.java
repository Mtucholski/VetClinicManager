package rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import model.HomeVisit;
import model.Pet;
import model.Visit;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

public class PetSerializer extends StdSerializer<Pet> {

    private Format formatter = new SimpleDateFormat("dd-MM-yyyy");


    public PetSerializer(Class<Pet> t) {
        super(t);
    }

    @Override
    public void serialize(Pet pet, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();
        if (pet.getId() == null) {

            gen.writeNullField("id");
        } else {

            gen.writeNumberField("id", pet.getId());
        }

        gen.writeStringField("species", pet.getPetSpecies().getSpecies());
        gen.writeStringField("name", pet.getName());
        gen.writeStringField("ownerFirstName", pet.getClient().getFirstName());
        gen.writeStringField("ownerLastName", pet.getClient().getLastName());
        gen.writeStringField("birthDay", formatter.format(pet.getBirthDate()));

        gen.writeArrayFieldStart("visits");
        getVisitsAsArray(gen, pet.getVisits());
        gen.writeEndArray();

        gen.writeArrayFieldStart("homeVisit");
        getHomeVisitsAsArray(gen, pet.getHomeVisits());
        gen.writeEndArray();


    }

    private void getVisitsAsArray(JsonGenerator generator, List<Visit> visits) throws IOException {

        for (Visit visit : visits) {

            generator.writeStartObject(); //start

            if (visit.getId() == null) {

                generator.writeNullField("id");
            } else {

                generator.writeNumberField("visitId", visit.getId());
            }

            generator.writeStringField("petName", visit.getPet().getName());
            generator.writeStringField("visitDate", formatter.format(visit.getVisitDate()));
            generator.writeStringField("visitDescription", visit.getDescription());
            generator.writeEndObject();
        }
    }

    private void getHomeVisitsAsArray(JsonGenerator generator, List<HomeVisit> homeVisits) throws IOException {

        for (HomeVisit homeVisit : homeVisits) {

            generator.writeStartObject();

            if (homeVisit.getId() == null) {

                generator.writeNullField("id");
            } else {

                generator.writeNumberField("homeVisitId", homeVisit.getId());
            }

            generator.writeStringField("vetFirstName", homeVisit.getVet().getFirstName());
            generator.writeStringField("vetLastName", homeVisit.getVet().getLastName());
            generator.writeStringField("dateFrom", formatter.format(homeVisit.getDateFrom()));
            generator.writeStringField("dateTo", formatter.format(homeVisit.getDateTo()));
            generator.writeEndObject();
        }
    }
}
