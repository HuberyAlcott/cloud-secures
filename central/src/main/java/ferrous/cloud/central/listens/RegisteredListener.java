package ferrous.cloud.central.listens;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDateTime;

/**
 * @author marcus
 */
@Slf4j
public class RegisteredListener {

  @EventListener
  public void listen(EurekaInstanceRegisteredEvent event) {
    final var instance = event.getInstanceInfo();
    final var appName = instance.getAppName();
    final var appPort = instance.getPort();
    log.warn("Instance [ {}:{} ] registered at [ {} ]", appName, appPort, LocalDateTime.now());
  }
}
