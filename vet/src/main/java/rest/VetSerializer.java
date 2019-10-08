package rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import model.Vet;
import model.VetSpeciality;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

public class VetSerializer extends StdSerializer<Vet> {


    protected VetSerializer(Class<Vet> t) {
        super(t);
    }

    @Override
    public void serialize(Vet vet, JsonGenerator gen, SerializerProvider provider) throws IOException {

        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        gen.writeStartObject();

        if (vet.getId() == null){

            throw new IOException("id number for vet is necessary!");
        }

        gen.writeNumberField("vetID", vet.getId());
        gen.writeStringField("vetFirstName", vet.getFirstName());
        gen.writeStringField("vetLastName", vet.getLastName());

        gen.writeArrayFieldStart(" vetSpecialties");
        getSpecialties(gen, vet.getSpecialties());
        gen.writeEndArray();
        gen.writeStringField("birthDay",formatter.format(vet.getBirthDay()));
        gen.writeEndObject();
    }

    private void getSpecialties(JsonGenerator generator, List<VetSpeciality> specialties) throws IOException {

        if (specialties == null){

            throw new IOException("specialties cannot be null");
        }

        generator.writeArrayFieldStart("specialties");

        for (VetSpeciality specialty : specialties) {

            generator.writeNumberField("id", specialty.getId());
            generator.writeStringField("specialty", specialty.getVetSpecialtyName());
        }

        generator.writeEndArray();
    }
}
