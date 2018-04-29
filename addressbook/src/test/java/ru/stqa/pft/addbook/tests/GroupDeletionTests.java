package ru.stqa.pft.addbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBaseAuth {

  @Test
  public void testDeletionGroup () {

    GroupData group = new GroupData("xx","zz", "ll");
    int index = 0;
    app.getGroupH().checkOneGroupExists(group);

    int countBefore = app.getGroupH().countGroups();
    List<GroupData> before = app.getGroupH().getGroupList();

    app.getGroupH().remove(index);
    int countAfter = app.getGroupH().countGroups();
    List<GroupData> after = app.getGroupH().getGroupList();
    before.remove(index);

    Assert.assertEquals(countAfter, countBefore-1);
    Assert.assertEquals(before, after);
  }
}
