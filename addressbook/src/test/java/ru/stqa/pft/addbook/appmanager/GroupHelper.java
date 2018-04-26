package ru.stqa.pft.addbook.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.addbook.model.GroupData;

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

  public void deleteGroup() {
    click(By.cssSelector("input[name='delete']"));
  }


  public void checkOneGroupExists(GroupData group) {
    if(! isElementPresent(By.cssSelector("input[name='selected[]']"))) {
      create(group);
    }
  }
}