package ru.stqa.pft.addbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.ContSet;
import ru.stqa.pft.addbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBaseAuth {

  @Test
  public void testContactModification(){
    ContactData contact = new ContactData().setLastName("uuu").setFirstName("iii").setNewGroup("z");
    int index =0;

    app.getContactH().checkOneContactExists(contact, true);

    ContSet before = app.getDbH().contacts();

    ContactData deletedContact = before.iterator().next();

    app.getContactH().remove(deletedContact);

    int afterCount = app.getContactH().countContact();
    assertThat(afterCount, equalTo(before.size()-1));

    ContSet after = app.getDbH().contacts();

    assertThat(after, equalTo(before.without(deletedContact)));

    verifyContactListInUI();

  }
}
