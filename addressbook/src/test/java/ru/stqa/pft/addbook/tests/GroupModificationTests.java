package ru.stqa.pft.addbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class GroupModificationTests extends TestBaseAuth {

  @Test
  public void testModificationGroup(){

    GroupData group = new GroupData().setName("change");
    int index = 0;

    app.getGroupH().checkOneGroupExists(group);

    List<GroupData> before = app.getGroupH().getGroupList();
    app.getGroupH().modify(group, index);
    List<GroupData> after = app.getGroupH().getGroupList();


    int id = after.stream().mapToInt(GroupData::getId).max().getAsInt();
    group.setId(id);
    before.remove(index);
    before.add(group);


    before.sort(Comparator.comparingInt(GroupData::getId));
    after.sort(Comparator.comparingInt(GroupData::getId));

    Assert.assertEquals(after, before);
  }
}
