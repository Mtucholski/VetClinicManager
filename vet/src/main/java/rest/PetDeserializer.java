package rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import model.Client;
import model.Pet;
import model.PetSpecies;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PetDeserializer extends StdDeserializer<Pet> {

 public PetDeserializer(Class<Pet> vc) {
        super(vc);
    }

    protected PetDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected PetDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public Pet deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {


        Pet pet = new Pet();
        Client client;
        PetSpecies petSpecies;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = parser.getCodec().readTree(parser);

        JsonNode client_node = node.get("client");
        JsonNode petSpeciesNode = node.get("species");
        client = mapper.treeToValue(client_node, Client.class);
        petSpecies = mapper.treeToValue(petSpeciesNode, PetSpecies.class);
        long petID = node.get("id").asLong();
        String name = node.get("name").asText(null);
        String birthDayStr = node.get("birthDay").asText();

        if (!(pet.getId() == 0)){

            pet.setId(petID);
        }

        pet.setName(name);
        pet.setBirthDate(LocalDate.parse(birthDayStr, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        pet.setClient(client);
        pet.setPetSpecies(petSpecies);

        return pet;
    }
}
