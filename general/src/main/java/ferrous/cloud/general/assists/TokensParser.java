package ferrous.cloud.general.assists;

import ferrous.cloud.general.domains.LoginForm;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

/**
 * @author Marcus
 */
@Slf4j
@UtilityClass
public final class TokensParser {

  /**
   * 解析请求中的登录信息
   *
   * @param request 请求对象
   * @return 登录对象
   */
  public static Optional<LoginForm> parsedLogin(final HttpServletRequest request) {
    /* 获取请求中的用户名和密码参数 */
    final var username = request.getParameter("username");
    final var password = request.getParameter("password");

    final var contType = MediaType.parseMediaType(request.getContentType());

    if (contType.getType().equals("text")) {
      if (contType.getSubtype().equals("plain")) {
        return Optional.of(new LoginForm(username, password));
      }
    } else {
      return Optional.empty();
    }
    return Optional.empty();
  }

  public static String createToken(final String token) {
    return "";
  }

  public static String updateToken(final String token) {
    return "";
  }

  public static String parsedToken(final String token) {
    return "";
  }
}
