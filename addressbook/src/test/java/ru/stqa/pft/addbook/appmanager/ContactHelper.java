package ru.stqa.pft.addbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addbook.model.ContSet;
import ru.stqa.pft.addbook.model.ContactData;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(ApplicationManager manager) {
    super(manager);
  }

  public void create(ContactData contact, boolean creation) {
    manager.getNavigatorH().gotoHomePage();
    initNewContact();
    fillContactFields(contact, creation);
    submitContactData();
    returnHomePage();
  }

  public void modify(ContactData contact, ContactData modifiedContact, boolean creation) {
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

  private ContSet contSetCache;

  public ContSet getContactSetList() {
    if (contSetCache != null) {
      return new ContSet(contSetCache);
    }
      ContSet contSetCache = new ContSet();
      List<WebElement> elements = driver.findElements(By.name("entry"));
      for (WebElement item : elements) {
        String lastName = item.findElement(By.xpath("./td[2]")).getText();
        String firstName = item.findElement(By.xpath("./td[3]")).getText();
        int id = Integer.parseInt(item.findElement(By.tagName("input")).getAttribute("value"));
        contSetCache.add(new ContactData(id, lastName, firstName));
      }
      return new ContSet(contSetCache);
  }

  public void fillContactFields(ContactData contactData, boolean creation) {
    type(By.cssSelector("input[name='lastname']"), contactData.getLastName());
    type(By.cssSelector("input[name='firstname']"), contactData.getFirstName());
    if (creation) {
      new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getNewGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initNewContact() {
    click(By.linkText("add new"));
  }

  public void submitContactData() {
    click(By.cssSelector("input[name='submit']"));
    contSetCache = null;
  }

  public void updateContactData() {
    click(By.cssSelector("input[name='update']"));
    contSetCache = null;
  }

  private void deleteContactItem() {
    click(By.cssSelector("input[value='Delete']"));
    driver.switchTo().alert().accept();
    contSetCache = null;
  }


  public void returnHomePage() {
    click(By.linkText("home page"));
  }

  private void selectContactIdItem(int id) {
    click(By.xpath(String.format("//input[@name='selected[]' and @value ='%s']", id)));
  }

  private void editContactIdItem(int id) {
    click(By.xpath("//input[@name='selected[]' and @value =" + (id) + "]/ancestor::tr/td[8]/a"));
  }

  public void checkOneContactExists(ContactData contact, boolean creation) {
    manager.getNavigatorH().gotoHomePage();
    if (!isElementPresent(By.cssSelector("input[name='selected[]']"))) {
      create(contact, true);
    }
  }

  public int countContact() {
    return driver.findElements(By.name("entry")).size();
  }


}
