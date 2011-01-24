package se.xebia.xke

import org.scalatest.Spec
import org.scalatest.matchers.MustMatchers

import java.io.File
import scala.util.logging.ConsoleLogger

class AddressBookApplicationSamples extends Spec with MustMatchers {
  describe("loading csv file") { 
    it("should contain 3 entries, including contact with firstname Mark") {
      val addressBookSaver =
        new AddressBookSaver(new File("./src/main/resources/contacts.csv")) with ConsoleLogger
      val addressBook = addressBookSaver.load
      addressBook.numberOfContacts must be (3)
      val mark = Contact("Mark", "van", "Holsteijn", "0622374114")
      addressBook.get(mark).size must be (1)
    }
  }
}