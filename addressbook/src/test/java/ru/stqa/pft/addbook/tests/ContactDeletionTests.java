package ru.stqa.pft.addbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.ContactData;

public class ContactDeletionTests extends TestBaseAuth {

  @Test
  public void testContactModification(){
    ContactData contact = new ContactData("ppp", "uuu", "z");

    app.getContactH().checkOneContactExists(contact, true);

    app.getContactH().remove( 0);
  }
}
