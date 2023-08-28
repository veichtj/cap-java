package customer.javasample.handlers;

import cds.gen.catalogservice.BookReservedEventContext;
import cds.gen.catalogservice.CatalogService_;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@ServiceName(CatalogService_.CDS_NAME)
@AllArgsConstructor
@Slf4j
public class ChangeHistoryEventHandler implements EventHandler {

    @On(event = BookReservedEventContext.CDS_NAME)
    public void handleBookReservedEvent(BookReservedEventContext event){
        log.debug("Got new book reserved event for book {}", event.getData().getBookId());
        //ToDo create ChangeHistory here out of event
    }
}
