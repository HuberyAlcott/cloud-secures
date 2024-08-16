package ferrous.cloud.general.assists;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ferrous.cloud.general.assists.JsonifyTools.beanToJson;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
public final class ResultWriter {

  private ResultWriter() {
  }

  public static <T> boolean writeJson(final T result, final HttpServletRequest request, final HttpServletResponse response) {
    final var target = request.getRequestURI();

    try (final var stream = response.getOutputStream()) {
      final var json = beanToJson(result);
      response.setContentType(APPLICATION_JSON.getType());
      stream.write(json.getBytes(UTF_8));
      log.warn("'{}' json write done {}", target, json);
      return true;
    } catch (IOException error) {
      log.error("'{}' write fail cause {}", target, error.getMessage());
      return false;
    }
  }
}
