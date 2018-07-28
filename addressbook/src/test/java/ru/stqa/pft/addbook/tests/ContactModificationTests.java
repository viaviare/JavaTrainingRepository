package ru.stqa.pft.addbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.ContSet;
import ru.stqa.pft.addbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBaseAuth {

  @BeforeMethod
  public void ensurePreconditions(){
    ContactData tempContact = new ContactData().setLastName("mm").setFirstName("nn").setNewGroup("z");
    app.getContactH().checkOneContactExists(tempContact, true);
  }

  @Test
  public void testContactModification(){
    ContactData contact = new ContactData().setLastName("vcx").setFirstName("mnb");

    ContSet before = app.getDbH().contacts();

    ContactData modifiedContact = before.iterator().next();

    app.getContactH().modify(contact, modifiedContact, false);

    ContSet after = app.getDbH().contacts();
    assertThat(after.size(), equalTo(before.size()));

    assertThat(after, equalTo(before.change(modifiedContact, contact.setId(modifiedContact.getId()))));

    verifyContactListInUI();
  }

}
