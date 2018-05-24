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


  public ContactData getContDataFromEditPage(int index){
    manager.getNavigatorH().gotoHomePage();
    editContactItem(index);
    ContactData contact = new ContactData();
    contact.setLastName(getTextValue("lastname"))
            .setFirstName(getTextValue("firstname"))
            .setAddress(getTextValue("address"))
            .setHomePhone(getTextValue("home"))
            .setMobilePhone(getTextValue("mobile"))
            .setWorkPhone(getTextValue("work"))
            .setEmail(getTextValue("email"))
            .setEmail2(getTextValue("email2"))
            .setEmail3(getTextValue("email3"));

    return contact;
  }

  private String getTextValue(String name) {
    return driver.findElement(By.name(name)).getAttribute("value");
  }

  public ContactData getContDataFromHomePage(int index){
    manager.getNavigatorH().gotoHomePage();
    selectContactItem(index);
    ContactData contact = new ContactData();
    List<WebElement> infaRow = driver.findElements(By.xpath("//tr[@name='entry'][" + (index+1) + "]/td"));
    contact.setLastName(infaRow.get(1).getText())
            .setFirstName(infaRow.get(2).getText())
            .setAddress(infaRow.get(3).getText())
            .setAllEmails(infaRow.get(4).getText())
            .setAllPhones(infaRow.get(5).getText());

    return contact;
  }

  public ContactData getContDataFromViewPage(int index){
    manager.getNavigatorH().gotoHomePage();
    viewContactItem(index);
    ContactData contact = new ContactData();
    contact.setAllInfa(driver.findElement(By.cssSelector("div#content")).getText());
    return contact;
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

  public void selectContactIdItem(int id) {
    click(By.xpath(String.format("//input[@name='selected[]' and @value ='%s']", id)));
  }

  public void selectContactItem(int index) {
    click(By.xpath(("//input[@name='selected[]']["+(index+1)+"]")));
  }

  public void editContactIdItem(int id) {
    click(By.xpath("//input[@name='selected[]' and @value =" + (id) + "]/ancestor::tr/td[8]/a"));
  }

  public void editContactItem(int index) {
    click(By.xpath(String.format("//input[@name='selected[]']['%s']/ancestor::tr/td[8]/a", index)));
  }

  public void viewContactItem(int index) {
    click(By.xpath(String.format("//input[@name='selected[]']['%s']/ancestor::tr/td[7]/a", index)));
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
