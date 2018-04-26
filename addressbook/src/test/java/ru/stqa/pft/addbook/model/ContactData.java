package ru.stqa.pft.addbook.model;

public class ContactData {


  private String firstName;
  private String lastName;
  private String address;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String email;
  private String email2;
  private String email3;
  private String newGroup;

  public ContactData(String lastName, String firstName, String newGroup){

    this.lastName = lastName;
    this.firstName = firstName;
    this.newGroup = newGroup;
  }

  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }

  public String getHomePhone() {
    return homePhone;
  }
  public void setHomePhone(String homePhone) {
    this.homePhone = homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }
  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }
  public void setWorkPhone(String workPhone) {
    this.workPhone = workPhone;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail2() {
    return email2;
  }
  public void setEmail2(String email2) {
    this.email2 = email2;
  }

  public String getEmail3() {
    return email3;
  }
  public void setEmail3(String email3) {
    this.email3 = email3;
  }

  public String getNewGroup() { return newGroup;}
  public void setNewGroup(String newGroup) {
    this.newGroup = newGroup;
  }
}
