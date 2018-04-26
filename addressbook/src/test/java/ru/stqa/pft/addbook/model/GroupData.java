package ru.stqa.pft.addbook.model;

public class GroupData {

  private String name;
  private String header;
  private String footer;



  public GroupData (String name, String header, String footer) {
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
}
