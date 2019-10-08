package rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import model.Address;
import model.Client;
import model.Pet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class CustomClientDeserializer extends StdDeserializer<Client> {

    protected CustomClientDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Client deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {

        Client client = new Client();
        Address address;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        JsonNode pet_node = mapper.readTree("pets");
        JsonNode address_node = jsonNode.get("address");
        address = mapper.treeToValue(address_node, Address.class);
        Long id = jsonNode.get("id").asLong();
        String birth = jsonNode.get("birthDay").asText(null);

        if (!(client.getId() == null)){

            client.setId(id);
        }


        client.setFirstName(jsonNode.get("firstName").asText());
        client.setLastName(jsonNode.get("lastName").asText());
        client.setPersonalID(jsonNode.get("personalID").asInt());
        client.setBirthDay(LocalDate.parse(birth, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        client.setEmail(jsonNode.get("email").asText());
        client.setPhoneNumber(jsonNode.get("phoneNumber").asText());
        client.setAddress(address);
        List<Pet> pets = mapper.convertValue(pet_node.get("pet"), List.class);
        client.setPets(Collections.unmodifiableList(pets));
        return client;
    }
}
