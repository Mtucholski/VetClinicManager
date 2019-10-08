package rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import model.Address;

import java.io.IOException;
import java.time.LocalDate;

public class AddressDeserializer extends StdDeserializer<Address> {

    public AddressDeserializer() {
        this(null);
    }

    public AddressDeserializer(Class<Address> t){

        super(t);
    }

    @Override
    public Address deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        JsonNode node = parser.getCodec().readTree(parser);
        Address address = new Address();
        long id = node.get("id").asLong();
        String city = node.get("city").asText();
        String street = node.get("street").asText();
        String homeNumber = node.get("homeNumber").asText();

        if (!(id == 0)){

            address.setId(id);
        }

        address.setCity(city);
        address.setStreet(street);
        address.setHomeNumber(homeNumber);
        return address;
    }
}
