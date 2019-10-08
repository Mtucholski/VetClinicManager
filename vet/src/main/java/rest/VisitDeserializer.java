package rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import model.Pet;
import model.Visit;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VisitDeserializer extends StdDeserializer<Visit> {


    public VisitDeserializer(Class<Visit> vc) {
        super(vc);
    }

    @Override
    public Visit deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {

        JsonNode jsonNode = parser.getCodec().readTree(parser);
        ObjectMapper mapper = new ObjectMapper();
        Visit visit = new Visit();

        JsonNode pet_node = jsonNode.get("pet");
        Pet pet = mapper.treeToValue(pet_node, Pet.class);
        String description = jsonNode.get("visitDescription").asText();
        long id = jsonNode.get("id").asLong();

        if (!(id ==0)){

            visit.setId(id);
        }

        visit.setDescription(description);
        visit.setVisitDate(LocalDate.parse(jsonNode.get("visitDate").asText(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        visit.setPet(pet);
        return visit;
    }
}
