package ru.stqa.pft.addbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addbook.model.ContSet;
import ru.stqa.pft.addbook.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(ApplicationManager manager) {super(manager);}

  public void create(ContactData contact, boolean creation){
    manager.getNavigatorH().gotoHomePage();
    initNewContact();
    fillContactFields(contact, creation);
    submitContactData();
    returnHomePage();
  }

  public void modify(ContactData contact, ContactData modifiedContact, boolean creation){
    manager.getNavigatorH().gotoHomePage();
    selectContactIdItem(modifiedContact.getId());
    editContactIdItem(modifiedContact.getId());
    fillContactFields(contact, creation);
    updateContactData();
    returnHomePage();
  }

  public void remove(ContactData contact) {
    manager.getNavigatorH().gotoHomePage();
    selectContactIdItem(contact.getId());
    deleteContactItem();
    manager.getNavigatorH().gotoHomePage();
  }

  public ContSet getContactSetList() {
    ContSet contacts = new ContSet();
    List<WebElement> elements = driver.findElements(By.name("entry"));
    for (WebElement item : elements) {
      String lastName = item.findElement(By.xpath("./td[2]")).getText();
      String firstName = item.findElement(By.xpath("./td[3]")).getText();
      int id = Integer.parseInt(item.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData(id, lastName, firstName ));
    }
    return contacts;
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

  private void selectContactIdItem(int id) {
    click(By.xpath("//input[@name='selected[]' and @value =" + (id) + "]"));
  }

  private void editContactItem(int index) {click(By.xpath("//tr[@name='entry'][" + (index+1) + "]/td[8]"));}

  private void editContactIdItem(int id) {
    click(By.xpath("//input[@name='selected[]' and @value =" + (id) + "]/ancestor::tr/td[8]"));
  }


  public void checkOneContactExists(ContactData contact, boolean creation) {
    manager.getNavigatorH().gotoHomePage();
    if(! isElementPresent(By.cssSelector("input[name='selected[]']"))){
      create(contact, true);
    }
  }

  public int countContact() {
    return driver.findElements(By.name("entry")).size();
  }


}
