package ru.stqa.pft.addbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBaseAuth {

  @Test
  public void testContactModification(){
    ContactData contact = new ContactData("ppp", "uuu", "z");
    int index =0;

    app.getContactH().checkOneContactExists(contact, true);

    int beforeCount = app.getContactH().countContact();
    List<ContactData> before = app.getContactH().getContactList();
    app.getContactH().remove(index);

    int afterCount = app.getContactH().countContact();
    List<ContactData> after = app.getContactH().getContactList();

    Assert.assertEquals(afterCount, beforeCount-1);

    before.remove(index);

    Assert.assertEquals(before, after);

  }
}
