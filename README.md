# Morphia/Jackson Java 8 Optional Example

Morphia (mongoDB) and Jackson (JSON) serialization can both be leveraged to serialize/deserialize the new Java 8 Optional type.

For Jackson, you simply need to register the JDK8 module.
[https://github.com/FasterXML/jackson-datatype-jdk8](https://github.com/FasterXML/jackson-datatype-jdk8)

For Morphia, you need to register a custom converter for Optional.
[OptionalConverter](https://github.com/denniskuczynski/morphia_jackson_java8_optional_example/blob/master/src/main/java/morphia_jackson_java8_optional/OptionalConverter.java)

## Tips
* The raw data may still contain nulls or empty fields, so default values should be specified on the Java models for non-optional fields.

## Running the example
* mvn compile
* mvn exec:java -Dexec.mainClass="morphia_jackson_java8_optional.Example"
