package morphia_jackson_java8_optional;

import org.mongodb.morphia.converters.DefaultConverters;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

import java.util.Optional;

// Originally seen in https://github.com/mongodb/morphia/issues/600 via darbie
public class OptionalConverter extends TypeConverter {
  private DefaultConverters defaultConverters;
  
  public OptionalConverter(DefaultConverters defaultConverters) {
    super(Optional.class);
    this.defaultConverters = defaultConverters;
  }
  
  @Override
  public Object encode(Object value, MappedField mappedField) {
    if (value == null) {
      return null;
    }

    Optional optional = (Optional)value;
    return optional.map(defaultConverters::encode).orElse(null);
  }

  @Override
  public Object decode(Class type, Object fromDbObject, MappedField mappedField) {
    return Optional.ofNullable(fromDbObject);
  }
}
