package morphia_jackson_java8_optional;

import org.mongodb.morphia.converters.DefaultConverters;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

import java.util.Optional;

public class OptionalConverter extends TypeConverter
{
  private DefaultConverters defaultConverters;
  
  public OptionalConverter(DefaultConverters defaultConverters)
  {
    super(Optional.class);
    this.defaultConverters = defaultConverters;
  }
  
  @Override
  public Object encode(Object value, MappedField mappedField)
  {
    if (value == null)
    {
      return null;
    }

    Optional optional = (Optional)value;
    if (optional.isPresent() == false)
    {
      return null;
    }
    
    return defaultConverters.encode(optional.get());
  }

  @Override
  public Object decode(Class type, Object fromDbObject, MappedField mappedField)
  {
    return Optional.ofNullable(fromDbObject);
  }
}
