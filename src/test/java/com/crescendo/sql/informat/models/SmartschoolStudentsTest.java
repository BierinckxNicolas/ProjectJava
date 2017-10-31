package com.crescendo.sql.informat.models;

import com.crescendo.sql.helpers.AssertAnnotations;
import com.crescendo.sql.helpers.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

/**
 * Created by Gilles on 31/05/2017.
 */
public class SmartschoolStudentsTest {

        /*
    TEST ANNOTATIONS PRESENT
     */

    @Test
    public void AnnotationOnClassLevel() {
        // assert
        AssertAnnotations.assertType(
                SmartschoolStudents.class, Entity.class, Table.class);
    }

    @Test
    public void AnnotationOnFieldLevel() {
        // assert
        AssertAnnotations.assertField(SmartschoolStudents.class, "id", Column.class);
        AssertAnnotations.assertField(SmartschoolStudents.class, "lastName", Column.class);
        AssertAnnotations.assertField(SmartschoolStudents.class, "firstName", Column.class);
        AssertAnnotations.assertField(SmartschoolStudents.class, "stamNr", Column.class, Id.class);
        AssertAnnotations.assertField(SmartschoolStudents.class, "username", Column.class);
        AssertAnnotations.assertField(SmartschoolStudents.class, "email", Column.class);
        AssertAnnotations.assertField(SmartschoolStudents.class, "sex", Column.class);
        AssertAnnotations.assertField(SmartschoolStudents.class, "phonenumber", Column.class);
        AssertAnnotations.assertField(SmartschoolStudents.class, "country", Column.class);
        AssertAnnotations.assertField(SmartschoolStudents.class, "city", Column.class);
        AssertAnnotations.assertField(SmartschoolStudents.class, "street", Column.class);
        AssertAnnotations.assertField(SmartschoolStudents.class, "poBox", Column.class);
        AssertAnnotations.assertField(SmartschoolStudents.class, "postalCode", Column.class);
        AssertAnnotations.assertField(SmartschoolStudents.class, "houseNumber", Column.class);
    }

    @Test
    public void AnnotationOnMethodLevel() {
        // assert
        AssertAnnotations.assertMethod(
                SmartschoolStudents.class, "getId");
        AssertAnnotations.assertMethod(
                SmartschoolStudents.class, "getFirstName");
        AssertAnnotations.assertMethod(
                SmartschoolStudents.class, "getLastName");
        AssertAnnotations.assertMethod(
                SmartschoolStudents.class, "getStamNr");
        AssertAnnotations.assertMethod(
                SmartschoolStudents.class, "getUsername");
        AssertAnnotations.assertMethod(
                SmartschoolStudents.class, "getEmail");
        AssertAnnotations.assertMethod(
                SmartschoolStudents.class, "getSex");
        AssertAnnotations.assertMethod(
                SmartschoolStudents.class, "getPhonenumber");
        AssertAnnotations.assertMethod(
                SmartschoolStudents.class, "getCountry");
        AssertAnnotations.assertMethod(
                SmartschoolStudents.class, "getCity");
        AssertAnnotations.assertMethod(
                SmartschoolStudents.class, "getStreet");
        AssertAnnotations.assertMethod(
                SmartschoolStudents.class, "getPoBox");
        AssertAnnotations.assertMethod(
                SmartschoolStudents.class, "getPostalCode");
        AssertAnnotations.assertMethod(
                SmartschoolStudents.class, "getHouseNumber");
    }

    /*
   TEST IF PROPERTIES OF ANNOTATIONS CORRECT (like name,mappedBy,cascade, etc)
    */

    @Test
    public void EntityNameTest() {
        // setup
        Entity a
                = ReflectTool.getClassAnnotation(SmartschoolStudents.class, Entity.class);
        // assert
        Assert.assertEquals("", a.name());
    }

    @Test
    public void TableNameTest() {
        // setup
        Table t
                = ReflectTool.getClassAnnotation(SmartschoolStudents.class, Table.class);
        // assert
        Assert.assertEquals("informatssstudents", t.name());
        Assert.assertEquals("crescendo", t.schema());
    }

    @Test
    public void ColumnNameOnId() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                SmartschoolStudents.class, "id", Column.class);
        // assert
        Assert.assertEquals("id", c.name());
    }
    @Test
    public void ColumnNameOnLastName() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                SmartschoolStudents.class, "lastName", Column.class);
        // assert
        Assert.assertEquals("lastName", c.name());
    }

    @Test
    public void ColumnNameOnFirstName() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                SmartschoolStudents.class, "firstName", Column.class);
        // assert
        Assert.assertEquals("firstName", c.name());
    }

    @Test
    public void ColumnNameOnStamNr() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                SmartschoolStudents.class, "stamNr", Column.class);
        // assert
        Assert.assertEquals("stamNr", c.name());
    }

    @Test
    public void ColumnNameOnUsername() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                SmartschoolStudents.class, "username", Column.class);
        // assert
        Assert.assertEquals("username", c.name());
    }

    @Test
    public void ColumnNameOnEmail() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                SmartschoolStudents.class, "email", Column.class);
        // assert
        Assert.assertEquals("email", c.name());
    }

    @Test
    public void ColumnNameOnSex() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                SmartschoolStudents.class, "sex", Column.class);
        // assert
        Assert.assertEquals("sex", c.name());
    }

    @Test
    public void ColumnNameOnCountry() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Students.class, "country", Column.class);
        // assert
        Assert.assertEquals("country", c.name());
    }
    @Test
    public void ColumnNameOnCity() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Students.class, "city", Column.class);
        // assert
        Assert.assertEquals("city", c.name());
    }
    @Test
    public void ColumnNameOnStreet() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Students.class, "street", Column.class);
        // assert
        Assert.assertEquals("street", c.name());
    } @Test
    public void ColumnNameOnPoBox() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Students.class, "poBox", Column.class);
        // assert
        Assert.assertEquals("poBox", c.name());
    }
    @Test
    public void ColumnNameOnPostalCode() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Students.class, "postalCode", Column.class);
        // assert
        Assert.assertEquals("postalCode", c.name());
    }
    @Test
    public void ColumnNameOnHouseNumber() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Students.class, "houseNumber", Column.class);
        // assert
        Assert.assertEquals("houseNumber", c.name());
    }

    @Test
    public void ColumnNameOnPhoneNumber() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Students.class, "phonenumber", Column.class);
        // assert
        Assert.assertEquals("phonenumber", c.name());
    }


}