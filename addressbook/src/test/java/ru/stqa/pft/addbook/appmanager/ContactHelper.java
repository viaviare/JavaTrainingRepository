package ru.stqa.pft.addbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(ApplicationManager manager) {super(manager);}

  public void create(ContactData contact, boolean creation){
    manager.getNavigatorH().gotoHomePage();
    initNewContact();
    fillContactFields(contact, creation);
    submitContactData();
    returnHomePage();
  }

  public void modify(ContactData contact, int index, boolean creation){
    manager.getNavigatorH().gotoHomePage();
    selectContactItem(index);
    editContactItem(index);
    fillContactFields(contact, creation);
    updateContactData();
    returnHomePage();
  }

  public void remove(int index){
    manager.getNavigatorH().gotoHomePage();
    selectContactItem(index);
    deleteContactItem();
    manager.getNavigatorH().gotoHomePage();
  }




  public void fillContactFields(ContactData contactData, boolean creation) {
    type(By.cssSelector("input[name='lastname']"), contactData.getLastName());
    type(By.cssSelector("input[name='firstname']"), contactData.getFirstName());
    if (creation) {
      new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getNewGroup());
    } else{
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initNewContact() { click(By.linkText("add new"));  }

  public void submitContactData() { click(By.cssSelector("input[name='submit']"));}

  public void updateContactData() { click(By.cssSelector("input[name='update']"));}

  public void returnHomePage() { click(By.linkText("home page"));  }

  private void deleteContactItem() {
    click(By.cssSelector("input[value='Delete']"));
    driver.switchTo().alert().accept();
  }

  private void selectContactItem(int index) {click(By.xpath("(//input[@name='selected[]'])[" + (index+1) + "]"));}

  private void editContactItem(int index) {click(By.xpath("//tr[@name='entry'][" + (index+1) + "]/td[8]"));}

  public void checkOneContactExists(ContactData contact, boolean creation) {
    if(! isElementPresent(By.cssSelector("input[name='selected[]']"))){
      create(contact, true);
    }
  }
}
