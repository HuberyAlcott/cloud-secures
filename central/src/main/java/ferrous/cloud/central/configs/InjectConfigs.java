package ferrous.cloud.central.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Configuration
public class InjectConfigs {

  @Bean
  @ConditionalOnMissingBean()
  public ScheduledExecutorService scheduledExecutorService() {

    final var schedule =
        new ScheduledThreadPoolExecutor(
            20,
            runner -> {
              final var thread = new Thread(runner);
              thread.setName("schTask-" + thread.getId());
              return thread;
            },
            (runner, executor) -> {
              ThreadPoolExecutor.CallerRunsPolicy policy =
                  new ThreadPoolExecutor.CallerRunsPolicy();
              policy.rejectedExecution(runner, executor);
            });
    log.warn(
        "Initialized ScheduledThreadPoolExecutor @{}", Integer.toHexString(schedule.hashCode()));
    return schedule;
  }
}
