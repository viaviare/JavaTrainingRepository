package ru.stqa.pft.addbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBaseAuth {

  @Test
  public void testModificationGroup(){

    GroupData group = new GroupData("change",null, null);
    int index = 0;

    app.getGroupH().checkOneGroupExists(group);

    List<GroupData> before = app.getGroupH().getGroupList();
    app.getGroupH().modify(group, index);
    List<GroupData> after = app.getGroupH().getGroupList();

    group.setId(before.get(index).getId());
    before.remove(index);
    before.add(group);

    before.sort(Comparator.comparingInt(GroupData::getId));
    after.sort(Comparator.comparingInt(GroupData::getId));

    Assert.assertEquals(after, before);
  }
}
