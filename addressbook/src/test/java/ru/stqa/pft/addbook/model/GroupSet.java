package ru.stqa.pft.addbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class GroupSet extends ForwardingSet<GroupData> {

  private Set<GroupData> delegate;

  @Override
  protected Set<GroupData> delegate() {
    return null;
  }

  public GroupSet (){this.delegate = new HashSet<>();}

  public GroupSet (GroupSet groupSet){
    this.delegate = new HashSet<GroupData>(groupSet.delegate);
  }

  public GroupSet (Collection<GroupData> groupSet){
    this.delegate = new HashSet<GroupData>(groupSet);
  }
}
