package rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import model.Address;

import java.io.IOException;

public class CustomAddressSerializer extends StdSerializer<Address> {


    protected CustomAddressSerializer(Class<Address> t) {
        super(t);
    }

    @Override
    public void serialize(Address address, JsonGenerator gen, SerializerProvider provider) throws IOException {



         gen.writeStartObject();

         if (address.getId() == null){

             gen.writeNullField("id");
         }else {

             gen.writeNumberField("id", address.getId());
         }

         gen.writeStringField("city", address.getCity());
         gen.writeStringField("street", address.getStreet());
         gen.writeStringField("homeNumber", address.getHomeNumber());
         gen.writeEndObject();
    }
}
