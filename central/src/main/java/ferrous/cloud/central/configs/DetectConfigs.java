package ferrous.cloud.central.configs;

import ferrous.cloud.central.listens.RegisteredListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/** 注册发现配置 */
@Slf4j
@Configuration
@EnableEurekaServer
@Import({RegisteredListener.class})
public class DetectConfigs {}
