package rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import model.Role;
import model.User;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Set;

public class UserSerializer extends StdSerializer<User> {


    public UserSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User user, JsonGenerator gen, SerializerProvider provider) throws IOException {

        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        gen.writeStartObject();

        if (user.getId() == null){

            gen.writeNullField("userID");
        }else {

            gen.writeNumberField("userID", user.getId());
        }

        gen.writeStringField("username", user.getUsername());
        gen.writeObjectField("roles", user.getRoles());
        gen.writeStringField("enabled", user.getEnabled().toString());
        gen.writeEndObject();
    }
}
