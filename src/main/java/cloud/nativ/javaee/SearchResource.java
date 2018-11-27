package cloud.nativ.javaee;

import cloud.nativ.javaee.protobuf.ProtocolBufferMediaType;
import cloud.nativ.javaee.types.SearchProtos.SearchRequest;
import cloud.nativ.javaee.types.SearchProtos.SearchResponse;
import lombok.extern.java.Log;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.logging.Level;

import static cloud.nativ.javaee.types.SearchProtos.Result;
import static org.eclipse.microprofile.metrics.MetricUnits.SECONDS;

@Log
@ApplicationScoped
@Path("search")
@Consumes(ProtocolBufferMediaType.APPLICATION_PROTOBUF)
@Produces(ProtocolBufferMediaType.APPLICATION_PROTOBUF)
public class SearchResource {

    @POST
    @Timed(name = "search", absolute = true, unit = SECONDS)
    public SearchResponse search(SearchRequest request) {
        LOGGER.log(Level.INFO, "Received SearchRequest {0}.", request);

        Result result = Result.newBuilder()
                .setTitle("QAware: Qualität und Agilität im Software Engineering")
                .setUrl("http://www.qaware.de")
                .addSnippets("Wir sind ein unabhängiges Beratungs- und Projekthaus für Softwaretechnik ...")
                .build();

        return SearchResponse.newBuilder().addResults(result).build();
    }
}
