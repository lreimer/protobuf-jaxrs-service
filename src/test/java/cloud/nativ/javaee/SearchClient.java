package cloud.nativ.javaee;

import cloud.nativ.javaee.SearchProtos.SearchRequest;
import cloud.nativ.javaee.SearchProtos.SearchResponse;
import cloud.nativ.javaee.protobuf.ProtocolBufferMediaType;
import cloud.nativ.javaee.protobuf.ProtocolBufferMessageBodyReader;
import cloud.nativ.javaee.protobuf.ProtocolBufferMessageBodyWriter;
import lombok.extern.java.Log;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Simple JAX-RS test client for the Search resource.
 */
@Log
public class SearchClient {
    public static void main(String[] args) {
        Client client = ClientBuilder.newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS)
                .register(ProtocolBufferMessageBodyReader.class).register(ProtocolBufferMessageBodyWriter.class)
                .build();

        SearchRequest searchRequest = SearchRequest.newBuilder()
                .setQuery("QAware")
                .setResultPerPage(25)
                .setPageNumber(1)
                .build();

        Entity<SearchRequest> entity = Entity.entity(searchRequest, ProtocolBufferMediaType.APPLICATION_PROTOBUF_TYPE);

        WebTarget searchApi = client.target("http://localhost:8080").path("/api").path("/search");
        Response response = searchApi.request(ProtocolBufferMediaType.APPLICATION_PROTOBUF_TYPE).post(entity);

        LOGGER.log(Level.INFO, "Received JAX-RS response {0}", response);

        SearchResponse searchResponse = response.readEntity(SearchResponse.class);

        LOGGER.log(Level.INFO, "Received SearchResponse {0}", searchResponse);
    }
}
