package com.nedap.workshop.scala.service

import java.io.OutputStream
import javax.ejb.Stateless
import javax.json.Json
import javax.json.stream.JsonGenerator
import javax.persistence.{PersistenceContext, EntityManager}
import scala.collection.JavaConverters._

import com.nedap.workshop.scala.model.Employee

/**
  * @author paul.vandenberg
  */
@Stateless
class EmployeeService {

  @PersistenceContext
  var entityManager: EntityManager = null

  def save(employee: Employee) {
    entityManager.persist(employee)
  }

  def findAll: java.util.List[Employee] = entityManager.createQuery("select e from Employee e").getResultList.asScala
    .map{ case e: Employee => e}
    .asJava

  //todo write better implementation in scala
  def findAllStream(outputStream: OutputStream) {
    val employees: java.util.List[Employee] = findAll
    val jsonGenerator: JsonGenerator = Json.createGenerator(outputStream)
    jsonGenerator.writeStartObject
    var i: Int = 0
    for (employee: Employee <- employees) {
      jsonGenerator.write("name", employee.getName)
      if (i % 100 == 0) {
        jsonGenerator.flush()
        try {
          Thread.sleep(1000)
        }
        catch {
          case e: InterruptedException => {
            e.printStackTrace()
          }
        }
      }
      i += 1
    }
    jsonGenerator.writeEnd.close()
  }
}
