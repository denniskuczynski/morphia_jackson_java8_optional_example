package morphia_jackson_java8_optional;

import morphia_jackson_java8_optional.model.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.MapperFeature;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.mapping.Mapper;

public class Example {

    public static void main(String[] args) {
        try {
            ObjectMapper mapper = createMapper();

            Book book1 = new Book("C Programming Language");
            Book book2 = new Book("C++ Programming Language", "4th edition");
            
            System.out.println();
            System.out.println("Example Mapping from Jackson:");
            System.out.println("---------------------------------");
            System.out.println("book1");
            System.out.println("---------------------------------");
            System.out.println(mapper.writeValueAsString(book1));
            System.out.println("book2");
            System.out.println("---------------------------------");
            System.out.println(mapper.writeValueAsString(book2));

            System.out.println("Example Mapping tp Jackson:");
            System.out.println("---------------------------------");
            System.out.println("book3");
            System.out.println("---------------------------------");
            System.out.println(mapper.readValue("{\"title\":\"Unit Testing in Java\"}", Book.class));

            Morphia morphia = new Morphia();
            morphia.mapPackage("model");
            morphia.getMapper().getConverters().addConverter(
                new OptionalConverter(morphia.getMapper().getConverters())
            );

            Book book4 = new Book("Efficient Ruby");
            Book book5 = new Book("Practical Javascript", "First Edition");

            System.out.println();
            System.out.println("Example Mapping from Morphia:");
            System.out.println("---------------------------------");
            System.out.println("book4");
            System.out.println("---------------------------------");
            System.out.println(morphia.toDBObject(book4));
            System.out.println("book5");
            System.out.println("---------------------------------");
            System.out.println(morphia.toDBObject(book5));
            Datastore ds = morphia.createDatastore(new MongoClient(), "morphia");
            ds.save(book4);
            ds.save(book5);
            book4 = ds.find(Book.class, "_id", book4.getId()).get();
            book5 = ds.find(Book.class, "_id", book5.getId()).get();

            System.out.println("---------------------------------");
            System.out.println("book4 (from DB)");
            System.out.println("---------------------------------");
            System.out.println(book4);
            System.out.println("book5 (from DB)");
            System.out.println("---------------------------------");
            System.out.println(book5);

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
        mapper.registerModule(new com.fasterxml.jackson.datatype.jdk8.Jdk8Module());
        return mapper;
    }
}
