package ru.stqa.pft.addbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBaseAuth {

  @Test
  public void testCreationGroup() {

    GroupData group = new GroupData("aa", "zz", "ll");

    List<GroupData> before = app.getGroupH().getGroupList();
    app.getGroupH().create(group);
    List<GroupData> after = app.getGroupH().getGroupList();


    group.setId(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
  }

}
