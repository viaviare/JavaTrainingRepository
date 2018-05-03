package ru.stqa.pft.addbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class ContSet extends ForwardingSet<ContactData>{

  private Set<ContactData> delegate;

  @Override
  protected Set<ContactData> delegate() {
    return delegate;
  }

  public ContSet() {
    this.delegate = new HashSet<ContactData>();
  }

  public ContSet (ContSet contSet){
    this.delegate = new HashSet<ContactData>(contSet.delegate);
  }

  public ContSet withAdded(ContactData contact){
    delegate.add(contact);
    return this;
  }

  public ContSet without(ContactData contact){
    delegate.remove(contact);
    return this;
  }

  public ContSet change(ContactData delCont, ContactData addCont){
    without(delCont);
    withAdded(addCont);
    return this;
  }

}
