package ru.stqa.pft.addbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.ContSet;
import ru.stqa.pft.addbook.model.ContactData;

public class ContactDeletionTests extends TestBaseAuth {

  @Test
  public void testContactModification(){
    ContactData contact = new ContactData().setLastName("uuu").setFirstName("iii").setNewGroup("z");
    int index =0;

    app.getContactH().checkOneContactExists(contact, true);

    int beforeCount = app.getContactH().countContact();
    ContSet before = app.getContactH().getContactSetList();

    ContactData deletedContact = before.iterator().next();

    app.getContactH().remove(deletedContact);

    int afterCount = app.getContactH().countContact();
    ContSet after = app.getContactH().getContactSetList();

    MatcherAssert.assertThat(afterCount, CoreMatchers.equalTo(beforeCount-1));
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(deletedContact)));

  }
}
