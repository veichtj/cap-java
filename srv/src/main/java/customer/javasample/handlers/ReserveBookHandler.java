package customer.javasample.handlers;

import cds.gen.catalogservice.Book;
import cds.gen.catalogservice.CatalogService_;
import cds.gen.catalogservice.ReserveBookContext;
import cds.gen.catalogservice.ReserveBookMessage;
import com.sap.cds.Result;
import com.sap.cds.ql.Select;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;
import customer.javasample.service.EventPublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@ServiceName(CatalogService_.CDS_NAME)
@AllArgsConstructor
@Slf4j
public class ReserveBookHandler implements EventHandler {

    private PersistenceService db;
    private final EventPublisher eventPublisher;

    @On()
    public ReserveBookMessage reserveBook(ReserveBookContext ctx){
        Result result = db.run(Select.from(CatalogService_.BOOK).where(b -> b.isbn_format().eq("13A")));
        Book book = result.first(Book.class).orElseThrow(()-> new ServiceException("Could not find book"));
        ReserveBookMessage msg = ReserveBookMessage.create();
        msg.setAck(book.getIsbnFormat());
        ctx.setResult(msg);
        ctx.setCompleted();
        eventPublisher.publishBookReservedEvent(book);
        return msg;
    }

}
