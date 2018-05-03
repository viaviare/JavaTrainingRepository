package ru.stqa.pft.addbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.ContSet;
import ru.stqa.pft.addbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBaseAuth {

  @Test
  public void testContactModification(){
    ContactData contact = new ContactData().setLastName("vcx").setFirstName("mnb");
    ContactData tempContact = new ContactData().setLastName("mm").setFirstName("nn").setNewGroup("z");
    int index = 0;

    app.getContactH().checkOneContactExists(tempContact, true);

    int beforeCount = app.getContactH().countContact();
    ContSet before = app.getContactH().getContactSetList();

    ContactData modifiedContact = before.iterator().next();

    app.getContactH().modify(contact, modifiedContact, false);

    int afterCount = app.getContactH().countContact();
    ContSet after = app.getContactH().getContactSetList();

    assertThat(afterCount, equalTo(beforeCount));
    assertThat(after, equalTo(before.change(modifiedContact, contact.setId(modifiedContact.getId()))));

  }

}
