package customer.javasample.handlers;

import cds.gen.catalogservice.Book;
import cds.gen.catalogservice.CatalogService_;
import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.ServiceName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@ServiceName(CatalogService_.CDS_NAME)
@Slf4j
public class CatalogServiceHandler implements EventHandler {


	@Autowired
	@Qualifier(CatalogService_.CDS_NAME)
	CqnService catalogService;


	@After(event = CdsService.EVENT_READ)
	public void discountBooks(Stream<Book> books) {
		books.filter(b -> b.getTitle() != null && b.getStock() != null)
		.filter(b -> b.getStock() > 200)
		.forEach(b -> b.setTitle(b.getTitle() + " (discounted)"));
	}

}