package ru.stqa.pft.addbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBaseAuth {

  @Test
  public void testDeletionGroup () {

    GroupData group = new GroupData("xx","zz", "ll");
    app.getGroupH().checkOneGroupExists(group);

    int countBefore = app.getGroupH().countGroups();
    Set<GroupData> before = app.getGroupH().getGroupSet();

    GroupData deletedGroup = before.iterator().next();
    app.getGroupH().remove(deletedGroup);

    int countAfter = app.getGroupH().countGroups();
    Set<GroupData> after = app.getGroupH().getGroupSet();

    before.remove(deletedGroup);

    Assert.assertEquals(countAfter, countBefore-1);
    Assert.assertEquals(before, after);
  }
}
