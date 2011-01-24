package se.xebia.xke

import scala.io.Source
import scala.util.logging.Logged
import java.io.File

case class Contact(firstName: String, midleName: String, lastName: String, phoneNumber: String) {

  def changePhone(phone: String): Contact = {
    Contact(firstName, midleName, lastName, phone)
  }
}

case class AddressBook(contacts: List[Contact]) extends Logged {

  val numberOfContacts = contacts.size

  def add(contact: Contact) =
    AddressBook(contacts ::: contact :: Nil)

  def remove(contact: Contact) =
    AddressBook(contacts.filterNot(_ == contact))

  def get(contact: Contact): Option[Contact] =
    contacts.find { c: Contact => c == contact }
}

case class AddressBookSaver(file: File) extends Logged {

  def load: AddressBook = {
    val contacts = Source.fromFile(file).getLines().map { line =>
      val linePattern = "([A-Za-z]+),([A-Za-z]*),([A-Za-z]+),([0-9]+)".r
      line match {
        case linePattern(l, m, f, p) => Contact(f, m, l, p)
        case _ => Contact("", "", "", "")
      }
    }.toList
    log("Address book contains: " + contacts)
    AddressBook(contacts.toList)
  }
}
