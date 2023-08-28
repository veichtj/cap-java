package customer.javasample.handlers;

import cds.gen.catalogservice.ReserveBookContext;
import cds.gen.catalogservice.ReserveBookMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles("default")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ReserveBookHandlerTest {

	@Autowired
	private ReserveBookHandler handler;
	@SpyBean
	ChangeHistoryEventHandler eventHandler;

	@Test
	public void when_book_reserved_then_publish_event(){
		ReserveBookMessage msg = handler.reserveBook(ReserveBookContext.create());
		assertThat(msg.getAck()).isEqualTo("13A");
		verify(eventHandler, times(1)).handleBookReservedEvent(any());
	}

}
