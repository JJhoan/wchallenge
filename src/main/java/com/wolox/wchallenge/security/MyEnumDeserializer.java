package com.wolox.wchallenge.security;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class MyEnumDeserializer extends StdSerializer {
    public MyEnumDeserializer() {
        super(ApplicationUserPermission.class);
    }

    public MyEnumDeserializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(Object o, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        ApplicationUserPermission applicationUserPermission = (ApplicationUserPermission) o;
        generator.writeStartObject();
        generator.writeFieldName("permission");
        generator.writeString(applicationUserPermission.name());
    }

}
