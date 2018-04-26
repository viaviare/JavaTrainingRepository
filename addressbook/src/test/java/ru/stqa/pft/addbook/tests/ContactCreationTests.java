package ru.stqa.pft.addbook.tests;

        import org.testng.annotations.Test;
        import ru.stqa.pft.addbook.appmanager.ContactHelper;
        import ru.stqa.pft.addbook.model.ContactData;

public class ContactCreationTests extends TestBaseAuth {

  @Test
  public void testContactCreation(){
    ContactData contact = new ContactData("ppp", "uuu", "z"){
    };
    app.getContactH().create(contact, true);
  }
}
