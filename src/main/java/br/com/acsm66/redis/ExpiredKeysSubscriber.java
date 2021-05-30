// Solution based on omarkad solution at https://github.com/omarkad2/quarkus-redis-example

package br.com.acsm66.redis;

import java.io.IOException;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.quarkus.redis.client.RedisClient;
import io.quarkus.redis.client.RedisClientName;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;

@ApplicationScoped
public class ExpiredKeysSubscriber {
  private static final Logger log = LoggerFactory.getLogger(ExpiredKeysSubscriber.class);

  @Inject
  @RedisClientName("expired-keys")
  RedisClient redisClient;

  @Inject
  Vertx vertx;

  void onStart(@Observes StartupEvent ev) throws IOException {
    // Define channel handlers
    vertx.eventBus().<String>consumer("io.vertx.redis.__keyevent@0__:expired").handler(msg -> {
      log.info("{}", msg.body());
    });

    // Subscribe to channel
    //this.redisClient.subscribe(List.of("expired-keys"));
    this.redisClient.psubscribe(List.of("__keyevent@0__:expired"));
  }

}
