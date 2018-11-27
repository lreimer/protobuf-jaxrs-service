package cloud.nativ.javaee.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.java.Log;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;

/**
 * A JAX-RS exception mapper for invalid requests due to InvalidProtocolBufferException.
 */
@Log
@Provider
public class InvalidProtocolBufferExceptionMapper implements ExceptionMapper<InvalidProtocolBufferException> {
    @Override
    public Response toResponse(InvalidProtocolBufferException e) {
        LOGGER.log(Level.FINE, "Unable to process Protobuf message.", e);
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
