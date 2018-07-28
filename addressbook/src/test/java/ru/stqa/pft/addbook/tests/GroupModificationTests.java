package ru.stqa.pft.addbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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

    GroupData tempGroup = new GroupData().setName("t");
    app.getGroupH().checkOneGroupExists(tempGroup);

    List<GroupData> before = app.getGroupH().getGroupList();
    int id = before.stream().mapToInt(GroupData::getId).max().getAsInt();
    GroupData group = new GroupData().setName("change").setId(id);
    app.getGroupH().modify(group);
    List<GroupData> after = app.getGroupH().getGroupList();

    //before.remove(before.indexOf(group));
   // before.add(group);

    before.set(before.indexOf(group), group);

    System.out.print("before " +  before.size() + " after " + after.size());

    before.sort(Comparator.comparingInt(GroupData::getId));
    after.sort(Comparator.comparingInt(GroupData::getId));

    Assert.assertEquals(after, before);
  }
}
