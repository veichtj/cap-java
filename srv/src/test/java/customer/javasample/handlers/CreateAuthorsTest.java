package customer.javasample.handlers;


import cds.gen.catalogservice.Author;
import cds.gen.catalogservice.CatalogService_;
import com.sap.cds.ql.Insert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("default")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CreateAuthorsTest {

    @Test
    public void createAuthorTest(){
        Author joe = Author.create();

        joe.setCity("Wegscheid");
        joe.setId("Joe1");

        Insert.into(CatalogService_.AUTHOR).entry(joe);


    }
}
