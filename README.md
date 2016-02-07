##Streaming REST Service
### Server

```bash
$ cd <project root>
$ forge
$ javaee-setup --javaEEVersion 7
$ cd webservice-client
$ javaee-setup --javaEEVersion 7
```
Edit `EmployeeRestService` and add a field with type `ManagedExecutorService`, annotated with `@Resource`

```java
@GET
@Path("streaming")
public void streams(@Suspended final AsyncResponse asyncResponse) {
    executor.submit(() -> {
        String employees = employeeService.findAll().stream().map((Employee::getName)).collect(Collectors.joining());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        asyncResponse.resume(employees);
    });
}
```	

###Client

```
$ cd <project root>/webservice-client
```

Edit file `pom.xml` and add the following dependency

```xml
<dependency>
  <groupId>org.jboss.resteasy</groupId>
  <artifactId>resteasy-client</artifactId>
  <version>3.0.2.Final</version>
</dependency>
```

Create a new java class `StreamingClient` with the following implementation

```java
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
```





