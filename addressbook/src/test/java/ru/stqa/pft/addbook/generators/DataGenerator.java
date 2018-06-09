package ru.stqa.pft.addbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ru.stqa.pft.addbook.model.ContactData;
import ru.stqa.pft.addbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

  @Parameter(names = "-c", description="count items of list")
  public int count;
  @Parameter(names = "-f", description = "target file")
  public String file;
  @Parameter(names = "-t", description = "type of file: csv xml json")
  public String type;
  @Parameter(names = "-m", description = "model class: group or contact")
  public String model;


  public static void main(String[] args) throws IOException {

    DataGenerator generator = new DataGenerator();
    new JCommander().newBuilder().addObject(generator).build().parse(args);

    generator.run();

  }

  private void run() throws IOException {

    List<GroupData> groups = new ArrayList<>();
    List<ContactData> conts = new ArrayList<>();


    if (model.equals("group")) {
      groups = getRandomGroupProvider(count);
    } else if (model.equals("contact")) {
      conts = getRandomContactProvider(count);
    } else {
      System.out.print("unknown model class");
    }

    if (type.equals("excel")) {
      if (model.equals("group")) {
        writeGroupDataToExcel(groups, new File(file));
      } else {
        writeContactDataToExcel(conts, new File(file));
      }
    }else if (type.equals("csv")) {
      if (model.equals("group")) {
        writeGroupDataToCsv(groups, new File(file));
      } else {
        writeContactDataToCsv(conts, new File(file));
      }
    } else if (type.equals("xml")) {
      if (model.equals("group")) {
        writeGroupDataToXml(groups, new File(file));
      } else {
        writeContactDataToXml(conts, new File(file));
      }
    } else if (type.equals("json")) {
      if (model.equals("group")) {
        writeGroupDataToJson(groups, new File(file));
      } else {
        writeContactDataToJson(conts, new File(file));
      }
    } else {
      System.out.print("unknown type of file");
    }
  }


  private void writeGroupDataToExcel(List<GroupData> groups, File file) throws IOException {
    Workbook book = new HSSFWorkbook();
    Sheet sheet = book.createSheet();
    int i = 0;
    for (GroupData item: groups){
      Row row = sheet.createRow(i);
      Cell cell = row.createCell(0);
      cell.setCellValue(item.getName());
      Cell cellS = row.createCell(1);
      cellS.setCellValue(item.getHeader());
      Cell cellT = row.createCell(2);
      cellT.setCellValue(item.getFooter());
      i++;
    }
    book.write(new FileOutputStream(file));
    book.close();
  }

  private void writeContactDataToExcel(List<ContactData> conts, File file) throws IOException {
    Workbook book = new HSSFWorkbook();
    Sheet sheet = book.createSheet();
    int i = 0;
    for (ContactData item: conts){
      Row row = sheet.createRow(i);
      Cell cell = row.createCell(0);
      cell.setCellValue(item.getLastName());
      Cell cellS = row.createCell(1);
      cellS.setCellValue(item.getFirstName());
      Cell cellT = row.createCell(2);
      cellT.setCellValue(item.getNewGroup());
      i++;
    }
    book.write(new FileOutputStream(file));
    book.close();
  }

  private void writeGroupDataToJson(List<GroupData> groups, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(groups);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void writeContactDataToJson(List<ContactData> conts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(conts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void writeGroupDataToXml(List<GroupData> groups, File file) throws IOException {
    XStream xstream = new XStream();

    xstream.alias("groups", GroupData.class);
    xstream.aliasField("identificator", GroupData.class, "id");
    xstream.useAttributeFor(GroupData.class, "id");

    String xml = xstream.toXML(groups);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private void writeContactDataToXml(List<ContactData> conts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(conts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private void writeGroupDataToCsv(List<GroupData> groups, File file) throws IOException {
    try(Writer writer = new FileWriter(file);){
      for (GroupData item : groups) {
        writer.write(String.format("%s, %s, %s\n", item.getName(), item.getHeader(), item.getFooter()));
      }
    }
  }

  private void writeContactDataToCsv(List<ContactData> conts, File file) throws IOException {
    try(Writer writer = new FileWriter(file);) {
      for (ContactData item : conts) {
        writer.write(String.format("%s, %s, %s\n", item.getLastName(), item.getFirstName(), "z"));
      }
    }
  }


  private List<ContactData> getRandomContactProvider(int count) {
    List<ContactData> conts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      conts.add(new ContactData(
              getRandomString(15),
              getRandomString(15),
              "z"));
    }
    return conts;
  }

  private List<GroupData> getRandomGroupProvider(int count) {
    List<GroupData> groups = new ArrayList<GroupData>();
    for (int i = 0; i < count; i++) {
      groups.add(new GroupData(
              getRandomString(15),
              getRandomString(30),
              getRandomString(30)));
    }
    return groups;
  }

  public static Random rnd = new Random();

  private String getRandomString(int max) {
    int l = (int) (rnd.nextDouble() * max);
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < l; i++) {
      builder.append((char) ((int) 32 + rnd.nextDouble() * 223));
    }
    return builder.toString();
  }

}
