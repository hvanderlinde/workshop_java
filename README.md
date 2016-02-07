##Employees REST Service

###Jax-RS

```bash
$ cd <project root>
$ forge
$ rest-setup
```
Create a class `EmployeeRestService` in `com.nedap.workshop.java.webservice` with 
 
* Class annotation `@Path("employees")`
* Field of type `EmployeeService`, annotated with `@Inject`
* Method called `getEmployees()` with
	* Annotation `@GET`
	* Annotation `@Produces("application/json")`
	* Implementation that calls the findAll method on the injected EmployeeService

```bash
$ mvn clean install
```
	
Open <http://localhost:8080/workshop_java/faces/index.xhtml> and add some employees

Open <http://localhost:8080/workshop_java/rest/employees>  
Now the added employees should be shown in json format



