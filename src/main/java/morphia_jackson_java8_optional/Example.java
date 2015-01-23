package morphia_jackson_java8_optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.MapperFeature;

import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.mapping.Mapper;

public class Example {

    public static void main(String[] args) {
        try {
            ObjectMapper mapper = createMapper();
        } catch(Throwable t) {
            System.out.println("Exception in Example");
            t.printStackTrace();
        }
    }

    public static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(
            MapperFeature.AUTO_DETECT_CREATORS,
            MapperFeature.AUTO_DETECT_GETTERS,
            MapperFeature.AUTO_DETECT_IS_GETTERS,
            MapperFeature.AUTO_DETECT_FIELDS
        );
        return mapper;
    }
}
