package ru.stqa.pft.addbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactComparisonDetailsFormTests extends TestBaseAuth {

  @Test
  public void testContactComparisonDetailsForm(){
    int index = 0;
    ContactData dataEditPage = app.getContactH().getContDataFromEditPage(index);
    ContactData dataDetailsPage = app.getContactH().getContDataFromViewPage(index);

    assertThat(dataEditPage.getAllInfa(), equalTo(dataDetailsPage.getAllInfa()));


  }
}
