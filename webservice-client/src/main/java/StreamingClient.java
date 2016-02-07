import com.nedap.workshop.java.webservice.EmployeeSoapService;
import com.nedap.workshop.java.webservice.EmployeeSoapServiceService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * @author paul.vandenberg
 */
public class StreamingClient {

    public static void main(String... args) throws IOException {
        EmployeeSoapService employeeWebservice = new EmployeeSoapServiceService().getEmployeeSoapServicePort();
        IntStream.range(0, 1000).forEach(i -> employeeWebservice.add("emp " + i));

        Client client = ClientBuilder.newClient();
        String request = "http://localhost:8080/workshop_java/rest/employees/streaming";

        Future<String> stringFuture = client.target(request)
                .request(MediaType.APPLICATION_OCTET_STREAM)
                .async()
                .get(new InvocationCallback<String>() {
                    @Override
                    public void completed(String s) {
                        System.out.println(s);
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        System.err.println(throwable);
                    }
                });

        System.out.println("doing something else in the meantime");
    }

}
