import com.nedap.workshop.java.webservice.Employee;
import com.nedap.workshop.java.webservice.EmployeeSoapService;
import com.nedap.workshop.java.webservice.EmployeeSoapServiceService;

import java.util.stream.IntStream;

/**
 * @author paul.vandenberg
 */
public class WebserviceClient {
    public static void main(String[] args) {
        createEmployees();
        printEmployees();
    }

    private static void printEmployees() {
        EmployeeSoapService service = new EmployeeSoapServiceService().getEmployeeSoapServicePort();
        service.getEmployees().stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    private static void createEmployees() {
        EmployeeSoapService service = new EmployeeSoapServiceService().getEmployeeSoapServicePort();
        IntStream.range(0, 1000).forEach(i -> service.add("emp " + i));
    }
}
