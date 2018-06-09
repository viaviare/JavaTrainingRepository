package ru.stqa.pft.addbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.ContSet;
import ru.stqa.pft.addbook.model.ContactData;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBaseAuth {


  @Test(dataProvider = "readContactDataFromExcel")
  public void testContactCreation(ContactData contact) {

    ContSet before = app.getContactH().getContactSetList();

    app.getContactH().create(contact, true);

    int afterCount = app.getContactH().countContact();
    assertThat(afterCount, equalTo(before.size() + 1));

    ContSet after = app.getContactH().getContactSetList();

    assertThat(after, equalTo(before.withAdded(contact.setId
            (after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId()))));
  }

  @DataProvider
  public static Iterator<Object[]> readContactDataFromExcel() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    HSSFWorkbook book = new HSSFWorkbook(new FileInputStream("src/test/resources/contacts.xls"));
    HSSFSheet sheet = book.getSheetAt(0);
    int rows = sheet.getPhysicalNumberOfRows();
    for (int i = 0; i < rows; i++) {
      HSSFRow row = sheet.getRow(i);
      list.add(new Object[]{new ContactData(
              row.getCell(0).getStringCellValue(),
              row.getCell(1).getStringCellValue(),
              row.getCell(2).getStringCellValue())});
    }
    book.close();
    return list.iterator();
  }


  public static Iterator<Object[]> readContactDataFromCsv() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] items = line.split(",");
      list.add(new Object[]{new ContactData(items[0], items[1], items[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @DataProvider
  public static Iterator<Object[]> readContactDataFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));) {
      String xml = reader.lines().collect(Collectors.joining());
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> list = (List<ContactData>) xstream.fromXML(xml);
      return list.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }


  public static Iterator<Object[]> readContactDataFromJson() throws FileNotFoundException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = reader.lines().collect(Collectors.joining());
    Gson gson = new Gson();
    List<ContactData> list = gson.fromJson(json, new TypeToken<List<ContactData>>() {
    }.getType());
    return list.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

  public static Iterator<Object[]> getContactProvider() {
    List<Object[]> contacts = new ArrayList<Object[]>();
    for (int i = 0; i < 3; i++) {
      contacts.add(new Object[]{new ContactData(
              getRandomString(15),
              getRandomString(15),
              "z")});
    }
    return contacts.iterator();
  }

  public static Random rnd = new Random();

  private static String getRandomString(int max) {
    int l = (int) (rnd.nextDouble() * max);
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < l; i++) {
      builder.append((char) (32 + rnd.nextDouble() * 223));
    }
    return builder.toString();
  }
}
