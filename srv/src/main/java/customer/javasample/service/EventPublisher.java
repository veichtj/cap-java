package customer.javasample.service;

import cds.gen.catalogservice.Book;
import cds.gen.catalogservice.BookReservedEvent;
import cds.gen.catalogservice.BookReservedEventContext;
import cds.gen.catalogservice.CatalogService_;
import com.sap.cds.services.cds.CqnService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {
    private final CqnService catService;

    public EventPublisher(@Qualifier(CatalogService_.CDS_NAME)CqnService catService) {
        this.catService = catService;
    }

    public void publishBookReservedEvent(Book book){
        BookReservedEvent bookReserved = BookReservedEvent.create();
        bookReserved.setBookId(book.getId());
        BookReservedEventContext reservedContext = BookReservedEventContext.create();
        reservedContext.setData(bookReserved);
        catService.emit(reservedContext);
    }
}
