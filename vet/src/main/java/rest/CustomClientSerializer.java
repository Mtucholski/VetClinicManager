package rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import model.*;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * class for serialization and deserialization json client objects
 */


public class CustomClientSerializer extends StdSerializer<Client> {

     private Format formatter = new SimpleDateFormat("dd/MM/yyyy");
    public CustomClientSerializer(){
        this(null);
    }

    protected CustomClientSerializer(Class<Client> t) {
        super(t);
    }

    @Override
    public void serialize(Client client, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();

        if (client.getId() == null){

            gen.writeNullField("id");
        }else {

            gen.writeNumberField("id", client.getId());
        }

        gen.writeStringField("firstName", client.getFirstName());
        gen.writeStringField("lastName", client.getLastName());
        gen.writeObjectField("birthDay", formatter.format(client.getBirthDay()));
        gen.writeNumberField("personalNumber", client.getPersonalID());
        gen.writeStringField("phoneNumber", client.getPhoneNumber());
        gen.writeObjectField("address", client.getAddress());
        gen.writeStringField("email", client.getEmail());
        gen.writeArrayFieldStart("pets");//writing pet array
        writePet(gen, client.getPets());
        //end pet array
        gen.writeEndArray();
        gen.writeEndObject();
    }

    private void writePet(JsonGenerator generator, List<Pet> pets) throws IOException {

        if (pets == null){

            throw new IOException("pets are null!!");
        }

        //pet
        generator.writeArrayFieldStart("pets");

        for (Pet pet : pets) {

            if (pet.getId() == null) {

                generator.writeNullField("id");
            } else {

                generator.writeNumberField("id", pet.getId());
            }

            //pet species
            PetSpecies petSpecies = pet.getPetSpecies();
            generator.writeStringField("species", pet.getPetSpecies().getSpecies());
            generator.writeStringField("petName", pet.getName());
            generator.writeEndObject();//end of writing pet species.
        }
    }
}
