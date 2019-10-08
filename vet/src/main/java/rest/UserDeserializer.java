package rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Role;
import model.User;

import java.io.IOException;
import java.util.Set;

public class UserDeserializer extends JsonDeserializer<User> {



    @Override
    public User deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        JsonNode jsonNode = parser.getCodec().readTree(parser);
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        JsonNode roles_node = jsonNode.get("roles");
        long id = jsonNode.get("id").asLong();
        String username = jsonNode.get("username").asText();
        Boolean enabled = jsonNode.get("enabled").asBoolean();
        Set<Role> roles = mapper.convertValue(roles_node, Set.class);

        if (!(id == 0)){

            user.setId(id);
        }

        user.setUsername(username);
        user.setRoles(roles);
        user.setEnabled(enabled);
        return user;
    }
}
