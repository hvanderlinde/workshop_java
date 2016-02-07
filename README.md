##Employees SOAP Service

###Webservice server

```bash
$ cd <project root>
$ forge
$ soap-setup
$ crtl-d
```
Create a class `EmployeeSoapService` in `com.nedap.workshop.java.webservice` with 
 
* Class annotation `@WebService`
* Field of type `EmployeeService`, annotated with `@Inject`
* Method `getEmployees()` with
	* Annotation `@WebMethod`
	* Implementation that calls the findAll method on the injected EmployeeService
* Method `add(name)` with
	* Annotation `WebMethod(operationName = "add")`
	* Implementation that calls the save method on the injected EmployeeService

```bash
$ cd <project root>
$ mvn install
```

Open a browser and open <http://localhost:8080/workshop_java/EmployeeSoapService?wsdl>  
That should show the wsdl contents in the browser

###Webservice client

```bash
$ cd <project root>
$ forge
$ project-new --named "webservice-client" --topLevelPackage com.nedap.workshop --type jar
$ crtl-d
```
Open the newly created project in Intellij. Select "File" -> "Open..." and choose the pom.xml in the project-root of webservice-client.

Edit the file pom.xml
Within the tag `<build>` add the following snippet

```xml
<plugins>
    <plugin>
        <groupId>org.jvnet.jax-ws-commons</groupId>
        <artifactId>jaxws-maven-plugin</artifactId>
        <version>2.3</version>
        <executions>
            <execution>
                <goals>
                    <goal>wsimport</goal>
                </goals>
                <configuration>
                    <wsdlUrls>
                        <wsdlUrl>http://localhost:8080/workshop_java/EmployeeSoapService?wsdl</wsdlUrl>
                    </wsdlUrls>
                </configuration>
            </execution>
        </executions>
    </plugin>
</plugins>
```

Create a class `WebserviceClient` with

* Method `public static void main(String... args)`  
	* get an instance of the webservice client with `new EmployeeSoapServiceService().getEmployeeSoapServicePort();`
	* Create and print some employees with the instance of the webservice client

	
```bash
$ cd <project root>/webservice-client
$ mvn install
```

Run the created class as a standalone java application



