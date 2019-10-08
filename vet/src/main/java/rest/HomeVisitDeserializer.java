package rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import model.HomeVisit;
import model.Pet;
import model.Vet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeVisitDeserializer extends StdDeserializer<HomeVisit> {


    public HomeVisitDeserializer(Class<HomeVisit> t){

        super(t);
    }

    @Override
    public HomeVisit deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {

        HomeVisit homeVisit = new HomeVisit();
        Vet vet;
        Pet pet;
        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = parser.getCodec().readTree(parser);
        JsonNode vet_node = node.get("vet");
        JsonNode pet_node = node.get("pet");

        vet = mapper.treeToValue(vet_node, Vet.class);
        pet = mapper.treeToValue(pet_node, Pet.class);
        long id = node.get("id").asLong();
        String dateFroms = node.get("dateFrom").asText();
        String dateTos = node.get("dateTo").asText();



        if ((id ==0)){

            homeVisit.setId(id);
        }

        homeVisit.setVet(vet);
        homeVisit.setPet(pet);
        homeVisit.setDescription(node.get("description").asText());
        homeVisit.setDateFrom(LocalDateTime.parse(dateFroms, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        homeVisit.setDateTo(LocalDateTime.parse(dateTos, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));

        return homeVisit;
    }
}
