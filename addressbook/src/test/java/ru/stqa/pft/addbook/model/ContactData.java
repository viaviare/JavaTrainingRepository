package ru.stqa.pft.addbook.model;

import static jdk.nashorn.internal.objects.NativeString.trim;

public class ContactData {

  private int id = Integer.MAX_VALUE;
  private String firstName;
  private String lastName;
  private String address;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String email;
  private String email2;
  private String email3;
  private String allPhones;
  private String allEmails;


  private String newGroup;

  public ContactData(){}

  public ContactData(String lastName, String firstName, String newGroup){
    this.lastName = lastName;
    this.firstName = firstName;
    this.newGroup = newGroup;
  }

  public ContactData(int id, String lastName, String firstName){

    this.id = id;
    this.lastName = lastName;
    this.firstName = firstName;
  }

  public String getFirstName() {
    return firstName;
  }
  public ContactData setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }
  public ContactData setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getAddress() {
    return address;
  }
  public ContactData setAddress(String address) {
    this.address = address;
    return this;
  }

  public String getHomePhone() {
    return homePhone;
  }
  public ContactData setHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }
  public ContactData setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public String getWorkPhone() {
    return workPhone;
  }
  public ContactData setWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public String getEmail() {
    return email;
  }
  public ContactData setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getEmail2() {
    return email2;
  }
  public ContactData setEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }
  public ContactData setEmail3(String email3) {
    this.email3 = email3;
    return this;
  }



  public String getAllEmails() {
    if (allEmails !=null){
      return allEmails;
    }
    allEmails = trim(clearValue(getEmail()) + clearValue(getEmail2()) + clearValue(getEmail3()));
    return allEmails;
  }

  public String clearValue(String text) {
    if (text==null || text.equals("")){
      return "";
    }
    text = text.replaceAll("[-() ]", "");
    return trim(text) + "\n";
  }

  public ContactData setAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAllPhones() {
    if (allPhones != null)
    {return allPhones;}
    allPhones = trim(clearValue(getHomePhone()) + clearValue(getMobilePhone()) + clearValue(getWorkPhone()));
    return allPhones;
  }

  public void setAllPhones(String allPhones) {
    this.allPhones = allPhones;
  }

  public String getNewGroup() { return newGroup;}
  public ContactData setNewGroup(String newGroup) {
    this.newGroup = newGroup;
    return this;
  }


  public int getId() {
    return id;
  }
  public ContactData setId(int id) {
    this.id = id;
    return this;
  }


  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

}
