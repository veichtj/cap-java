package customer.javasample.handlers;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {
                "server.port=9999",
                "management.server.port=9042"
        })
@ActiveProfiles("default")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ServiceAuthenticationIntTest {
    @MockBean
    AuthorizationHandler authorizationHandler;
    @BeforeAll
    static void setup() {
        RestAssured.port = 9999;
    }

    @Test
    void when_read_author_then_service_auth_should_be_called(){
        //when calling GET Author
        given().auth().basic("admin", "admin").when().get("odata/v4/CatalogService/Author")
                .then().statusCode(org.springframework.http.HttpStatus.OK.value()).extract().response().jsonPath();
        //Then service Authorization should be called once
        verify(authorizationHandler, times(1)).afterServiceAuthorization(any());
    }
}
