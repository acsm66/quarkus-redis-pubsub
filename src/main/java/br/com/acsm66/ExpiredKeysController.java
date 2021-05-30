package br.com.acsm66;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import io.quarkus.redis.client.RedisClient;

@Path("/publisher")
public class ExpiredKeysController {

  @Inject
  RedisClient redisClient;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  // @formatter:off
  public String publish(
      @QueryParam(value = "key") String key,
      @QueryParam(value = "value") String value
  ) throws NoSuchAlgorithmException {
  // formatter:on
    Integer expiration = SecureRandom.getInstanceStrong().nextInt(10);
    redisClient.setex(key, expiration.toString(), value);
    return "Chave/valor [" + key + "/" + value + "] registrado com expiracao de " + expiration + " segundos";
  }

}
