package ru.stqa.pft.addbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.ContSet;
import ru.stqa.pft.addbook.model.ContactData;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBaseAuth {

  @Test (dataProvider = "getContactProvider")
  public void testContactCreation(ContactData contact){


    ContSet before = app.getContactH().getContactSetList();

    app.getContactH().create(contact, true);

    int afterCount = app.getContactH().countContact();
    assertThat(afterCount,equalTo(before.size()+1));

    ContSet after = app.getContactH().getContactSetList();

    assertThat(after,equalTo(before.withAdded(contact.setId
            (after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId()))));
  }

  @DataProvider
  public static Iterator<Object[]> getContactProvider(){
    List<Object[]> contacts = new ArrayList<Object[]>();
    for(int i=0; i<3; i++){
      contacts.add(new Object[]{new ContactData(
              getRandomString(15),
              getRandomString(15),
              "z")});
    }
    return contacts.iterator();
  }

  public static Random rnd = new Random();

  private static String getRandomString(int max) {
    int l = (int)(rnd.nextDouble() * max);
    StringBuilder builder = new StringBuilder();
    for(int i=0; i<l;i++){
      builder.append((char)(32 + rnd.nextDouble()*223));
    }
    return builder.toString();
  }
}
