package customer.javasample.handlers;

import cds.gen.catalogservice.Book;
import cds.gen.catalogservice.ReserveBookContext;
import cds.gen.catalogservice.ReserveBookMessage;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.persistence.PersistenceService;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;

@ActiveProfiles("default")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ReserveBookHandlerTest {

	@Autowired
	private ReserveBookHandler handler;

	private Book book = Book.create();


	@Test
	public void testReserveBook(){

		ReserveBookMessage msg = handler.reserveBook(ReserveBookContext.create());
		assertThat(msg.getAck()).isEqualTo("13A");
		ReserveBookContext ctx = EventContext.create(ReserveBookContext.class, null);


	}

}
