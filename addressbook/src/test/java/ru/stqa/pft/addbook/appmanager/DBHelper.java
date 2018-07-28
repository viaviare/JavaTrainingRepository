package ru.stqa.pft.addbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.Test;
import ru.stqa.pft.addbook.model.ContSet;
import ru.stqa.pft.addbook.model.ContactData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.stqa.pft.addbook.model.GroupData;
import ru.stqa.pft.addbook.model.GroupSet;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {
  Logger logger = LoggerFactory.getLogger(DBHelper.class);
  private final SessionFactory sessionFactory;

  public DBHelper() {

    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();
      sessionFactory = new MetadataSources( registry )
              .buildMetadata()
              .buildSessionFactory();
  }

  public ContSet contacts(){
    logger.error("");
    Session session= sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery( "from ContactData where deprecated='0000-00-00 00:00:00'" ).list();
    session.getTransaction().commit();
    session.close();
    return new ContSet(result);
  }

  public GroupSet groups(){
    logger.error("");
    Session session= sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery( "from GroupData" ).list();
    session.getTransaction().commit();
    session.close();
    return new GroupSet(result);
  }

  public ArrayList<GroupData> groupsL(){
    logger.error("");
    Session session= sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery( "from GroupData" ).list();
    session.getTransaction().commit();
    session.close();
    return new ArrayList<GroupData>(result);
  }
}
