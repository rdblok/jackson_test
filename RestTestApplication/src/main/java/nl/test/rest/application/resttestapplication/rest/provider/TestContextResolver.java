package nl.test.rest.application.resttestapplication.rest.provider;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import nl.test.rest.application.resttestapplication.rest.object.ComplexObject;
import nl.test.rest.application.resttestapplication.rest.serializer.ComplexObjectDeserializer;

@Provider
public class TestContextResolver implements ContextResolver<ObjectMapper> {

    @Override
    public ObjectMapper getContext(Class<?> type) {
        final ObjectMapper result = new ObjectMapper();
        result.registerModule(new JodaModule());
        result.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false );
        SimpleModule module = new SimpleModule();
        module.addDeserializer(ComplexObject.class, new ComplexObjectDeserializer());
        result.registerModule(module);
        return result;
    }
    
}
