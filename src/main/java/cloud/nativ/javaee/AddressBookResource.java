package cloud.nativ.javaee;

import cloud.nativ.javaee.protobuf.ProtocolBufferMediaType;
import cloud.nativ.javaee.types.AddressBook;
import cloud.nativ.javaee.types.Person;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.logging.Level;

@Log
@ApplicationScoped
@Path("address-book")
@Consumes(ProtocolBufferMediaType.APPLICATION_PROTOBUF)
@Produces(ProtocolBufferMediaType.APPLICATION_PROTOBUF)
public class AddressBookResource {

    @POST

    public AddressBook getAddressBook(Person person) {
        LOGGER.log(Level.INFO, "Get AddressBook for Person {0}", person);
        return AddressBook.newBuilder().addPeople(person).build();
    }

}
