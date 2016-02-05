##Saving employees

###Java Persistence API

```bash
$ cd <project root>
$ forge
$ jpa-setup
$ ejb-setup
```

Edit the class `Employee`  

* A class annotation `@Entity`
* Add a constructor without parameters and no implementation
* Add an id field of type Long that acts as the primary key. See <http://www.objectdb.com/java/jpa/entity/id#Automatic_Primary_Key_> for more information
* Add a version field of type Integer, annotated with `@version`
* create getters for version and id

###Enterprise JavaBeans
Create a Java class `com.nedap.workshop.service.EmployeeService` with

* A class annotation `@Stateless`
* A Field of type `EntityManager` annotated with `@PersistenceContext`
* A `save` method with an Employee parameter which calls `persist()` on the injected EntityManager
* A `findAll` method which retrieves all Employees by running a query, created with the EntityManager. See <https://docs.oracle.com/javaee/7/tutorial/persistence-querylanguage002.htm#BNBRG> for more information

###Web

Edit the class `EmployeeBean`

* Remove the init method
* Remove the field `employees`
* Add a field of type `EmployeeService` annotated with `@Inject`
* Add a field of type `Employee` annotated with `@Inject` and create a getter for this field
* Replace the implementation of `getEmployees` with a call to the `findAll()` method on the injected EmployeeService
* Add a `save()` method that calls the `save(employee)` on the injected EmployeeService

Edit the page index.xhtml

* add a `h:form` tag after the datatable tag
* within the form create a `h:inputText` tag with 
	* a value that points to the employee name via the EmployeeBean
* within the form create a `h:commandButton` tag with
	* an action attribute that calls the save method on the EmployeeBean.
	* a value attribute "save"


