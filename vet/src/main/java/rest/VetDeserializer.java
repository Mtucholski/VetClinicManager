package rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import model.Vet;
import model.VetSpeciality;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class VetDeserializer extends StdDeserializer<Vet> {


    public VetDeserializer(Class<Vet> vc) {
        super(vc);
    }

    @Override
    public Vet deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {

        JsonNode jsonNode = parser.getCodec().readTree(parser);
        JsonNode vetSpecialtyNode = jsonNode.get("vetSpecialties");
        ObjectMapper mapper = new ObjectMapper();
        Vet vet = new Vet();


        long id = jsonNode.get("id").asLong();
        String firstName = jsonNode.get("vetFirstName").asText();
        String lastName = jsonNode.get("vetLastName").asText();
        Set<VetSpeciality> specialities = mapper.convertValue(vetSpecialtyNode, Set.class);

        vet.setId(id);
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vet.setBirthDay(LocalDate.parse(jsonNode.get("birthDay").asText(),DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        vet.setSpecialties(specialities);
        return vet;
    }
}
