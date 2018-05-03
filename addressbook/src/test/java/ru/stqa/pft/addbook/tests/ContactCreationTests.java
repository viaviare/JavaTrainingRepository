package ru.stqa.pft.addbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.ContSet;
import ru.stqa.pft.addbook.model.ContactData;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBaseAuth {

  @Test
  public void testContactCreation(){
    ContactData contact = new ContactData().setLastName("kjh").setFirstName("iop").setNewGroup("z");

    ContSet before = app.getContactH().getContactSetList();

    app.getContactH().create(contact, true);

    int afterCount = app.getContactH().countContact();
    assertThat(afterCount,equalTo(before.size()+1));

    ContSet after = app.getContactH().getContactSetList();

    assertThat(after,equalTo(before.withAdded(contact.setId
            (after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId()))));
  }
}
