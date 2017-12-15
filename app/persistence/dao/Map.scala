package persistence.dao

import org.mongodb.scala._

trait Map[T] {
  def to(item: T): Document
  def from(document: Document): T
}
