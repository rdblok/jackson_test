package nl.test.rest.application.resttestapplication.rest.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import nl.test.rest.application.resttestapplication.rest.generic.Namespace;
import nl.test.rest.application.resttestapplication.rest.object.ComplexObject;

public class ComplexObjectDeserializer extends StdDeserializer<ComplexObject> {

    public ComplexObjectDeserializer(Class<ComplexObject> vc) {
        super(vc);
    }

    public ComplexObjectDeserializer() {
        super(ComplexObject.class);
    }

    private static final long serialVersionUID = 1L;

    @Override
    public ComplexObject deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        String uriAsString = jp.getText();
        if (ComplexObject.isValidUri(uriAsString) || ComplexObject.isValidCurie(uriAsString)) {
            return new ComplexObject(uriAsString);
        } else {
            return new ComplexObject(Namespace.INSTANCE, uriAsString);
        }
    }

}
