package ru.stqa.pft.addbook.model;

import java.lang.ref.SoftReference;
import java.util.Objects;

public class GroupData {

  private String name;
  private String header;
  private String footer;
  private int id;

  public GroupData (String name, String header, String footer) {
    //this.id = 0;  this.id = null;
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public GroupData (int id, String name, String header, String footer) {
    this.id = id;
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getHeader() {
    return header;
  }
  public void setHeader(String header) {
    this.header = header;
  }

  public String getFooter() {
    return footer;
  }
  public void setFooter(String footer) {
    this.footer = footer;
  }

  public int getId() {return id;}
  public void setId(int id) {  this.id = id;  }





  @Override
  public String toString() {
    return "GroupData{" +
            "name='" + name + '\'' +
            ", id='" + id + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (id != groupData.id) return false;
    return name != null ? name.equals(groupData.name) : groupData.name == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + id;
    return result;
  }
}
