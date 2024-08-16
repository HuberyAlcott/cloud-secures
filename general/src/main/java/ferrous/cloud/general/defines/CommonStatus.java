package ferrous.cloud.general.defines;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonStatus {
  /* */
  NEED_LOGIN("_000_001", "需要登录"),
  /* */
  NEED_AUTH("_000_002", "权限不够"),
  /* */
  CSRF_FAIL("_000_003", "CSRF错误"),
  /* */
  CSRF_MISS("_000_004", "缺少CSRF token"),
  /* */
  CSRF_INVALID("_000_005", "CSRF无效"),
  /* */
  LOGIN_FAIL("_000_006", "登录失败"),
  /* */
  LOGIN_DONE("_000_007", "登录成功"),
  /* */
  LOGOUT_DONE("_000_008", "退出成功"),
  /* */
  LOGIN_INVALID("_000_009", "登录失效"),
  ;
  private final String code;

  private final String note;
}
