package customer.javasample.handlers;

import com.sap.cds.services.authorization.AuthorizationService;
import com.sap.cds.services.authorization.ServiceAccessEventContext;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.ServiceName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@ServiceName(AuthorizationService.DEFAULT_NAME)
@Slf4j
public class AuthorizationHandler implements EventHandler {

    @After(event = AuthorizationService.EVENT_SERVICE_ACCESS)
    public void afterServiceAuthorization(ServiceAccessEventContext context) {
        if (Boolean.FALSE.equals(context.getResult())) {
            log.warn("Service access to {} denied for user {}", context.getAccessServiceName(), context.getUserInfo().getName());
        }
    }

}
