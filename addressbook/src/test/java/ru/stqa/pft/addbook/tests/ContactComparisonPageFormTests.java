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

    assertThat(dataEditPage, equalTo(dataHomePage));
    assertThat(dataEditPage.getAddress(), equalTo(dataHomePage.getAddress()));
    assertThat(dataEditPage.getAllEmails(), equalTo(dataHomePage.getAllEmails()));
    assertThat(dataEditPage.getAllPhones(), equalTo(dataHomePage.getAllPhones()));
  }

}
