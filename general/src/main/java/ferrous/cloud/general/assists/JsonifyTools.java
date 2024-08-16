package ferrous.cloud.general.assists;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.jayway.jsonpath.JsonPath;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Marcus
 */
@Slf4j
@UtilityClass
public final class JsonifyTools {

  public static <T> String beanToJson(final T object) {
    final ObjectMapper mapper = new ObjectMapper();
    return beanToJson(object, mapper);
  }

  public static <T> String beanToJson(final T object, final ObjectMapper mapper) {
    try {
      Assert.notNull(object, "Target bean can't be null");
      Assert.notNull(mapper, "Target mapper can't be null");
      return mapper.writeValueAsString(object);
    } catch (JsonProcessingException error) {
      log.error(
          "'{}' can't convert to JSON string cause : {}",
          object.getClass().getName(),
          error.getMessage());
      if ((object instanceof Collection || object instanceof Array)) {
        return "[]";
      }
      return "{}";
    }
  }

  public static <T> String beanToSort(final T object) {
    final ObjectMapper mapper =
        new ObjectMapper().configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
    return beanToSort(object, mapper);
  }

  public static <T> String beanToSort(final T object, final ObjectMapper mapper) {
    mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
    try {
      Assert.notNull(object, "Target bean can't be null");
      Assert.notNull(mapper, "Target mapper can't be null");
      return mapper.writeValueAsString(object);
    } catch (JsonProcessingException error) {
      log.error("Occurred JsonProcessException cause : {}", error.getMessage());
      return "{}";
    }
  }

  public static <T> T jsonToBean(final String json, final Class<T> clazz) {
    final ObjectMapper mapper = new ObjectMapper();
    return jsonToBean(json, clazz, mapper);
  }

  public static <T> T jsonToBean(
      final String json, final Class<T> clazz, final ObjectMapper mapper) {
    try {
      Assert.hasText(json, "Target JSON string must have content");
      Assert.notNull(clazz, "Target class can't be null");
      Assert.notNull(mapper, "Target mapper can't be null");
      return mapper.readValue(json, clazz);
    } catch (JsonProcessingException error) {
      log.error(
          "Occurred error on '{}' to bean '{}' cause : {}",
          json,
          clazz.getName(),
          error.getMessage());
      return null;
    }
  }

  public static <O extends Collection<I>, I> List<I> jsonToList(
      final String json, final Class<O> outer, final Class<I> inner) {
    final ObjectMapper mapper = new ObjectMapper();
    return jsonToList(json, outer, inner, mapper);
  }

  public static <O extends Collection<I>, I> List<I> jsonToList(
      final String json, final Class<O> outer, final Class<I> inner, final ObjectMapper mapper) {
    CollectionType type = mapper.getTypeFactory().constructCollectionType(outer, inner);
    return jsonToList(json, type);
  }

  public static <T> List<T> jsonToList(final String json, final CollectionType type) {
    final ObjectMapper mapper = new ObjectMapper();
    return jsonToList(json, type, mapper);
  }

  public static <T> List<T> jsonToList(
      final String json, final CollectionType type, final ObjectMapper mapper) {
    try {
      Assert.hasText(json, "Target JSON string must have content");
      Assert.notNull(type, "CollectionType can't be null");
      Assert.notNull(mapper, "Target mapper can't be null");
      return mapper.readValue(json, type);
    } catch (JsonProcessingException error) {
      log.error(
          "Occurred error on '{}' to list '{}' cause : {}",
          json,
          type.toString(),
          error.getMessage());
      return new ArrayList<T>();
    }
  }

  public static String pathProp(final String json, final String prop) {
    final var read = JsonPath.read(json, prop);
    log.info("Parse {} from {} type is {}", prop, json, read.getClass().getName());
    return read;
  }

  public static String property(final String json, final String prop) {
    final ObjectMapper mapper = new ObjectMapper();
    return property(json, prop, mapper);
  }

  public static String property(final String json, final String prop, final ObjectMapper mapper) {
    try {
      return mapper.readTree(json).get(prop).toString();
    } catch (JsonProcessingException error) {
      log.error(
          "Occurred error on '{}' from JSON String {} cause : {}", prop, json, error.getMessage());
      return "";
    }
  }
}
