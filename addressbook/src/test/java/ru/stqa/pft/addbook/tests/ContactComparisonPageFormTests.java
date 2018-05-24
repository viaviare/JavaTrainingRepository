package ru.stqa.pft.addbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactComparisonPageFormTests extends TestBaseAuth{

  @Test
  public void testContactComparisonPageForm(){
    int index = 0;
    ContactData dataHomePage = app.getContactH().getContDataFromHomePage(index);
    ContactData dataEditPage = app.getContactH().getContDataFromEditPage(index);

    assertThat(dataHomePage, equalTo(dataEditPage));
    assertThat(dataHomePage.getAddress(), equalTo(dataEditPage.getAddress()));
    assertThat(dataHomePage.getAllEmails(), equalTo(dataEditPage.getAllEmails()));
    assertThat(dataHomePage.getAllPhones(), equalTo(dataEditPage.getAllPhones()));
  }

}
