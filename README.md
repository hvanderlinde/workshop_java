##Installation
###Install Java
Download and install java JDK 8  
<http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html>  
Set the environment variable `JAVA_HOME` to your jdk installation  

```bash
$ export JAVA_HOME=<your jdk8 installation>
```

### WildFly
Download and unzip Wildfly Application Server.  
<http://download.jboss.org/wildfly/10.0.0.Final/wildfly-10.0.0.Final.zip>  
Set the environment variable `JBOSS_HOME` to your Wildfly root folder. 
 
```bash
$ export JBOSS_HOME=<your Wildfly installation>
```

Start Wildfly in a separate shell:
```bash
$ $JBOSS_HOME/bin/standalone.sh
```

### Forge
Download an unzip JBoss Forge  
<http://forge.jboss.org/download>
##Project setup
###Generate project structure
Start Forge to generate a new project

```bash
$ forge
```
When the Forge console is started, create a new web project

```bash
$ project-new --named workshop_java --type war --topLevelPackage com.nedap.workshop
$ cdi-setup
$ faces-setup
```
Exit the Forge console (ctrl-D)

###Add maven wildfly plugin
Open the file `pom.xml` in a texteditor and add to following within the `<plugins>` tag

```xml
<plugin>
	<groupId>org.wildfly.plugins</groupId>
	<artifactId>wildfly-maven-plugin</artifactId>
	<version>1.1.0.Alpha5</version>
	<executions>
	  <execution>
	    <phase>install</phase>
	    <goals>
	      <goal>deploy</goal>
	    </goals>
	  </execution>
	</executions>
</plugin>
```

Create a file called `index.xhtml` in the folder `src/main/webapp` with the following content

```xml
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html">
    <h:outputLabel value="Hello, world"/>
</html>

```

```bash
$ cd <project root>
$ mvn install
```

open <http://localhost:8080/workshop_java/faces/index.xhtml> in your browser.  
You should now see a welcome page displaying "Hello World".
