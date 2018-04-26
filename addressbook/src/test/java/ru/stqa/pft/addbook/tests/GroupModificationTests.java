package ru.stqa.pft.addbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.GroupData;

public class GroupModificationTests extends TestBaseAuth {

  @Test
  public void testModificationGroup(){

    GroupData group = new GroupData("change",null, null);

    app.getGroupH().checkOneGroupExists(group);
    app.getGroupH().modify(group, 0);
  }
}
