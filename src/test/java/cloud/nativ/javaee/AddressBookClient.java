package cloud.nativ.javaee;

import cloud.nativ.javaee.protobuf.ProtocolBufferMediaType;
import cloud.nativ.javaee.protobuf.ProtocolBufferMessageBodyReader;
import cloud.nativ.javaee.protobuf.ProtocolBufferMessageBodyWriter;
import cloud.nativ.javaee.types.AddressBook;
import cloud.nativ.javaee.types.Person;
import cloud.nativ.javaee.types.Person.PhoneNumber;
import cloud.nativ.javaee.types.Person.PhoneType;
import lombok.extern.java.Log;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


/**
 * Simple JAX-RS test client for the AddressBook resource.
 */
@Log
public class AddressBookClient {
    public static void main(String[] args) {
        Client client = ClientBuilder.newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS)
                .register(ProtocolBufferMessageBodyReader.class).register(ProtocolBufferMessageBodyWriter.class)
                .build();

        Person person = Person.newBuilder()
                .setId(23577)
                .setName("M.-Leander Reimer")
                .setEmail("mario-leander.reimer@qaware.de")
                .addPhones(PhoneNumber.newBuilder().setNumber("+49 89 232315-121").setType(PhoneType.WORK).build())
                .build();

        Entity<Person> entity = Entity.entity(person, ProtocolBufferMediaType.APPLICATION_PROTOBUF_TYPE);

        WebTarget addressBookApi = client.target("http://localhost:8080").path("/api").path("/address-book");
        Response response = addressBookApi.request(ProtocolBufferMediaType.APPLICATION_PROTOBUF_TYPE).post(entity);

        LOGGER.log(Level.INFO, "Received JAX-RS response {0}", response);

        AddressBook addressBook = response.readEntity(AddressBook.class);

        LOGGER.log(Level.INFO, "Received AddressBook {0}", addressBook);
    }
}
