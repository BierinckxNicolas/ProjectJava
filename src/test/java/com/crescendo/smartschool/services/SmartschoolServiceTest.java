package com.crescendo.smartschool.services;

import com.crescendo.smartschool.models.*;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Gilles on 1-6-2017.
 */
public class SmartschoolServiceTest {

    @Test
    public void getAllAccounts() throws Exception {
        String code = "001";
        String recursive = "0";
        SmartschoolService service = new SmartschoolService();
        AccountList accounts = service.getAllAccounts(code,recursive);
        Assert.assertNotNull(accounts);
    }
/*
    @Test
    public void getAllAccounts2() throws Exception {
        String code = "001";
        String recursive = "0";
        SmartschoolService service = new SmartschoolService();
        AccountList accounts = service.getAllAccounts(code,recursive);
        Assert.assertEquals("testnaam",accounts.getAccount().get(1).getNaam());
    }
    */

    @Test
    public void getAllGroupsAndClasses() throws Exception {
        SmartschoolService service = new SmartschoolService();
        GroupList groups = service.getAllGroupsAndClasses();
        Assert.assertNotNull(groups);
    }


    @Test
    public void getUserDetailsById() throws Exception {
        String id = "";
        SmartschoolService service = new SmartschoolService();
        User user = service.getUserDetailsById(id);
        Assert.assertNotNull(user);
    }

    @Test
    public void getUserDetailsById2() throws Exception {
        String id = "";
        SmartschoolService service = new SmartschoolService();
        User user = service.getUserDetailsById(id);
        Assert.assertNotEquals("usernaam",user.getNaam());
    }

    @Test
    public void getClassList() throws Exception {
        SmartschoolService service = new SmartschoolService();
        ClassLists classList = service.getClassList();
        Assert.assertNotNull(classList);
    }
/*
    @Test
    public void getClassList2() throws Exception {
        SmartschoolService service = new SmartschoolService();
        ClassLists classList = service.getClassList();
        Assert.assertEquals("classcode", classList.getClasslist().get(1).getCode());
    }*/

    @Test
    public void getCourses() throws Exception {
        SmartschoolService service = new SmartschoolService();
        CourseList coursesList = service.getCourses();
        Assert.assertNotNull(coursesList);
    }


  /* @Test
    public void setAccountStatus() throws Exception {
        String id = "26006201972 G8";
        String status = "deactief";
        String code = "001";
        String recursive = "0";
        SmartschoolService service = new SmartschoolService();
        service.setAccountStatus(id,status);
        AccountList accountsList = service.getAllAccounts(code,recursive);
        Account account = new Account();
        for (Account acc : accountsList.getAccount()) {
            if (acc.getInternnummer() == id) account = acc;
        }
        Assert.assertEquals("deactief", account.getStatus());
    }*/

   /* @Test
    public void saveUser() throws Exception {
        String internnumber = "125369 g5";
        String username = "Test";
        String passwd1 = "passwd1";
        String passwd2 = "passwd2";
        String passwd3 = "passwd3";
        String name = "Johnny";
        String surname = "Test";
        String extranames = "";
        String initials = "";
        String sex = "man";
        String birthday = "25/06/1995";
        String birthplace = "Wilrijk";
        String birthcountry = "België";
        String address = "humbugstraat 47";
        String postalcode = "2000";
        String location = "Antwerpen";
        String country = "België";
        String email = "testmail@gmail.com";
        String mobilephone = "0456897858";
        String homephone = "03259478165";
        String fax = "";
        String prn = "";
        String stamboeknummer = "45896";
        String basisrol = "student";
        String untis = "";

        SmartschoolService service = new SmartschoolService();
        service.saveUser(internnumber, username, passwd1, passwd2, passwd3, name, surname, extranames, initials, sex, birthday,
                birthplace, birthcountry, address, postalcode, location, country, email, mobilephone, homephone, fax, prn,
                stamboeknummer, basisrol, untis);
        User user = service.getUserDetailsById(stamboeknummer);
        Assert.assertEquals(stamboeknummer,user.getStamboeknummer());
    }*/

   /* @Test
    public void delUser() throws Exception {
        String id = "1";
        SmartschoolService service = new SmartschoolService();
        service.delUser(id);
        User user = service.getUserDetailsById(id);
        Assert.assertNull(user);
    }
    */

    @Test
    public void now() throws Exception {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String date = SmartschoolService.now();
        Assert.assertEquals(sdf.format(cal.getTime()),date);
    }


}