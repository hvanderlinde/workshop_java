##JavaServer Faces

###Model
Create a new Java class called `Employee` in the folder `src/main/java` in the package `com.nedap.workshop.java.model`  

The class should contain:  

* A field called `name` with getters and setters.  
* A constructor with a `name` parameter, which will set the name upon creation 

###Web page
Edit the file `index.xhtml` (created in step 1)  
Add `xmlns:f="http://xmlns.jcp.org/jsf/core"` as an attribute of the `html` tag  
Replace the `h:outputLabel` tag with  

```xml
<h:dataTable value="#{employeeBean.employees}" var="employee">
	<h:column>
		<f:facet name="Name"/>
		<h:outputText value="#{employee.name}"/>
	</h:column>
</h:dataTable>
```

IntelliJ might complain about missing beans and/or methods. That's correct, because we are missing the `EmployeeBean` with the `employees` property. Let's fix that.

###Managed Bean
Create a new Java class called `EmployeeBean` in the folder `src/main/java` in the package `com.nedap.workshop.java.controller`
The class should contain:

* A class annotation `@Named` and `@RequestScoped` (Uit de package `javax.enterprise.context`)
* A field called `employees` which is a List of Employee objects with a getter
* A public void method called `init` with a `@PostConstruct` annotation. That method should populate `employees` with a bunch of dummy employees.
