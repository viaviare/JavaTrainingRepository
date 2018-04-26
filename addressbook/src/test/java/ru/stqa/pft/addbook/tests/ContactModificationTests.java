package ru.stqa.pft.addbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.ContactData;

public class ContactModificationTests extends TestBaseAuth {

  @Test
  public void testContactModification(){
    ContactData contact = new ContactData("xx", "ww", null);
    ContactData tempContact = new ContactData("mm", "nn", "z");

    app.getContactH().checkOneContactExists(tempContact, true);

    app.getContactH().modify(contact, 0, false);
  }

}
