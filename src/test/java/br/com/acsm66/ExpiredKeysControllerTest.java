package br.com.acsm66;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class ExpiredKeysControllerTest {

  @Test
  public void testHelloEndpoint() {
    // @formatter:off
    given()
    .queryParam("key", "chave")
    .queryParam("value", "valor")
    .when()
    .get("/publisher")
    .then()
    .statusCode(200)
    .body(containsString("Chave/valor [chave/valor] registrado com expiracao de"));
    // @formatter:on
  }

}
