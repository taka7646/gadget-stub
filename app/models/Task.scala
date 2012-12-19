package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Task(id: Long, label: String)

object Task{
  val task = {
    get[Long]("id") ~
    get[String]("label") map{
      case id~label => Task(id, label)
    }
  }
  def all(): List[Task] = DB.withConnection { implicit c =>
    SQL("SELECT * FROM task").as(task *)
  }
  
  def create(label:String){
    DB.withConnection { implicit c =>
      SQL("INSERT INTO task(label) values({label})").on(
          'label -> label
          ).executeUpdate()
    }
  }
  
  def delete(id: Long){
    DB.withConnection{ implicit c =>
      SQL("DELETE FROM task where id = {id}").on(
          'id -> id
      ).executeUpdate()
    }
  }
}
