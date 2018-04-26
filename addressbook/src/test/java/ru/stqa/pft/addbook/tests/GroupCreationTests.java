package ru.stqa.pft.addbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.GroupData;

public class GroupCreationTests extends TestBaseAuth{

  @Test
  public void testCreationGroup(){

      GroupData group = new GroupData("aa","zz", "ll");

      app.getGroupH().create(group);
  }

}
