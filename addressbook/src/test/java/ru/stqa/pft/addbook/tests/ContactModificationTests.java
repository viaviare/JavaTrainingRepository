package ru.stqa.pft.addbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBaseAuth {

  @Test
  public void testContactModification(){
    ContactData contact = new ContactData("xx", "ww", null);
    ContactData tempContact = new ContactData("mm", "nn", "z");
    int index = 0;

    app.getContactH().checkOneContactExists(tempContact, true);

    int beforeCount = app.getContactH().countContact();
    List<ContactData> before = app.getContactH().getContactList();

    app.getContactH().modify(contact, index, false);

    int afterCount = app.getContactH().countContact();
    List<ContactData> after = app.getContactH().getContactList();

    int id = before.get(index).getId();
    ContactData contactId  = new ContactData(id, contact.getLastName(),contact.getFirstName());
    before.remove(index);
    before.add(contactId);

    before.sort(Comparator.comparing(ContactData::getId));
    after.sort(Comparator.comparing(ContactData::getId));

    Assert.assertEquals(afterCount, beforeCount);
    Assert.assertEquals(after, before);
  }

}
