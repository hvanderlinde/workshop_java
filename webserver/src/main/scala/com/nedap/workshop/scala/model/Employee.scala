package com.nedap.workshop.scala.model

import java.io.Serializable
import javax.persistence._
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import scala.beans.BeanProperty

/**
  * @author paul.vandenberg
  */
@Entity class Employee(_name: String) extends Serializable {

  def this() = this(null)

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(updatable = false, nullable = false)
  @BeanProperty
  var id: Long = _

  @Version
  @BeanProperty
  var version: Int = _

  @NotNull
  @Size(min = 1, max = 10)
  @BeanProperty
  var name: String = _name

  override def toString: String = "Employee{" + "id=" + id + ", version=" + version + ", name='" + name + '\'' + '}'
}
