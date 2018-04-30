package ru.stqa.pft.addbook.tests;

        import org.openqa.selenium.By;
        import org.testng.Assert;
        import org.testng.annotations.Test;
        import ru.stqa.pft.addbook.appmanager.ContactHelper;
        import ru.stqa.pft.addbook.model.ContactData;

        import java.util.Comparator;
        import java.util.List;

public class ContactCreationTests extends TestBaseAuth {

  @Test
  public void testContactCreation(){
    ContactData contact = new ContactData("ppp", "uuu", "z");

    int beforeCount = app.getContactH().countContact();
    List<ContactData> before = app.getContactH().getContactList();

    app.getContactH().create(contact, true);

    int afterCount = app.getContactH().countContact();
    List<ContactData> after = app.getContactH().getContactList();

    int highId = after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId();
    before.add(new ContactData(highId, contact.getLastName(),contact.getFirstName()));

    before.sort(Comparator.comparing(ContactData::getId));
    after.sort(Comparator.comparing(ContactData::getId));

    Assert.assertEquals(afterCount, beforeCount+1);
    Assert.assertEquals(after, before);
  }
}
