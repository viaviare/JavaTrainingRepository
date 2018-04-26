package ru.stqa.pft.addbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.GroupData;

public class GroupDeletionTests extends TestBaseAuth {

  @Test
  public void testDeletionGroup () {

    GroupData group = new GroupData("aa","zz", "ll");

    app.getGroupH().checkOneGroupExists(group);
    app.getGroupH().remove(0);
  }
}
