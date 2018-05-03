package ru.stqa.pft.addbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase{

  public GroupHelper(ApplicationManager manager) {super(manager);}

  public void create(GroupData group) {
    manager.getNavigatorH().gotoGroupPage();
    initNewGroupItem();
    fillGroupFields(group);
    submitGroupData();
    returnToGroupPage();
  }

  public void modify(GroupData group, int index) {
    manager.getNavigatorH().gotoGroupPage();
    selectGroupItem(index);
    editGroupItem();
    fillGroupFields(group);
    updateGroupData();
    returnToGroupPage();
  }

  public void remove(int index) {
    manager.getNavigatorH().gotoGroupPage();
    selectGroupItem(index);
    deleteGroup();
    returnToGroupPage();
  }

  public void remove(GroupData group) {
    manager.getNavigatorH().gotoGroupPage();
    selectGroupIdItem(group.getId());
    deleteGroup();
    returnToGroupPage();
  }


  public void initNewGroupItem() { click(By.cssSelector("input[name='new']"));  }

  public void fillGroupFields(GroupData groupData) {
    type(By.cssSelector("input[name='group_name']"), groupData.getName());
    type(By.cssSelector("textarea[name='group_header']"), groupData.getHeader());
    type(By.cssSelector("textarea[name='group_footer']"), groupData.getFooter());
  }

  public void submitGroupData() { click(By.cssSelector("input[name='submit']"));  }

  private void updateGroupData() {click(By.cssSelector("input[name='update']"));}


  public void returnToGroupPage() { click(By.linkText("group page"));  }

  public void editGroupItem() { click(By.cssSelector("input[name='edit']"));}




  public void selectGroupItem(int index) {
    click(By.xpath("(//input[@name='selected[]'])[" + (index + 1) + "]"));
  }
  public void selectGroupIdItem(int id) {
    click(By.xpath("//input[@name='selected[]' and @value = '" + id + "']"));

  }

  public void deleteGroup() {
    click(By.cssSelector("input[name='delete']"));
  }


  public void checkOneGroupExists(GroupData group) {
    manager.getNavigatorH().gotoGroupPage();
    if(! isElementPresent(By.cssSelector("input[name='selected[]']"))) {
      create(group);
    }
  }

  public int idSelectedGroup(int index){
    manager.getNavigatorH().gotoGroupPage();
    return Integer.parseInt(
            driver.findElement(By.xpath("(//input[@name='selected[]'])[" + (index + 1) + "]"))
                    .getAttribute("value"));
  }

  public int countGroups() {
    manager.getNavigatorH().gotoGroupPage();
    return driver.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> getGroupList() {
    manager.getNavigatorH().gotoGroupPage();
    List<GroupData> groups = new ArrayList<>();
    List<WebElement> elements = driver.findElements(By.className("group"));
    for(WebElement item : elements){
      int id = Integer.parseInt(item.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData(id, item.getText(),null, null));
    }
    return groups;
  }

  public Set<GroupData> getGroupSet() {
    manager.getNavigatorH().gotoGroupPage();
    Set<GroupData> groups = new HashSet<GroupData>();
    List<WebElement> elements = driver.findElements(By.className("group"));
    for(WebElement item : elements){
      int id = Integer.parseInt(item.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().setId(id).setName(item.getText()));
    }
    return groups;
  }


}
