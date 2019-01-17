package my.lottery.infrastructure.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class WinningCheckingRouteBuilder extends RouteBuilder{

    private String fromEndPoint;

    @Override
    public void configure() {
        //from(fromEndPoint).routeId()
    }
}
