package com.crescendo.sql.informat.models;

import com.crescendo.sql.helpers.AssertAnnotations;
import com.crescendo.sql.helpers.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

/**
 * Created by Gilles on 31/05/2017.
 */
public class StudentsTest {
 /*
    TEST ANNOTATIONS PRESENT
     */

    @Test
    public void AnnotationOnClassLevel() {
        // assert
        AssertAnnotations.assertType(
                Students.class, MappedSuperclass.class);
    }

    @Test
    public void AnnotationOnFieldLevel() {
        // assert
        AssertAnnotations.assertField(Students.class, "id", Id.class, Column.class);
        AssertAnnotations.assertField(Students.class, "firstName", Column.class);
        AssertAnnotations.assertField(Students.class, "lastName", Column.class);
        AssertAnnotations.assertField(Students.class, "stamNr", Column.class);
        AssertAnnotations.assertField(Students.class, "email", Column.class);
        AssertAnnotations.assertField(Students.class, "phonenumber", Column.class);
        AssertAnnotations.assertField(Students.class, "sex", Column.class);
        AssertAnnotations.assertField(Students.class, "country", Column.class);
        AssertAnnotations.assertField(Students.class, "city", Column.class);
        AssertAnnotations.assertField(Students.class, "street", Column.class);
        AssertAnnotations.assertField(Students.class, "poBox", Column.class);
        AssertAnnotations.assertField(Students.class, "postalCode", Column.class);
        AssertAnnotations.assertField(Students.class, "houseNumber", Column.class);
    }

    @Test
    public void AnnotationOnMethodLevel() {
        // assert
        AssertAnnotations.assertMethod(
                Students.class, "getId");
        AssertAnnotations.assertMethod(
                Students.class, "getLastName");
        AssertAnnotations.assertMethod(
                Students.class, "getFirstName");
        AssertAnnotations.assertMethod(
                Students.class, "getStamNr");
        AssertAnnotations.assertMethod(
                Students.class, "getEmail");
        AssertAnnotations.assertMethod(
                Students.class, "getPhonenumber");
        AssertAnnotations.assertMethod(
                Students.class, "getSex");
        AssertAnnotations.assertMethod(
                Students.class, "getCountry");
        AssertAnnotations.assertMethod(
                Students.class, "getCity");
        AssertAnnotations.assertMethod(
                Students.class, "getStreet");
        AssertAnnotations.assertMethod(
                Students.class, "getPoBox");
        AssertAnnotations.assertMethod(
                Students.class, "getPostalCode");
        AssertAnnotations.assertMethod(
                Students.class, "getHouseNumber");
    }

    /*
   TEST IF PROPERTIES OF ANNOTATIONS CORRECT (like name,mappedBy,cascade, etc)
    */


    @Test
    public void ColumnNameOnId() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Students.class, "id", Column.class);
        // assert
        Assert.assertEquals("id", c.name());
    }
    @Test
    public void ColumnNameOnLastName() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Students.class, "lastName", Column.class);
        // assert
        Assert.assertEquals("lastName", c.name());
    }

    @Test
    public void ColumnNameOnFirstName() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Students.class, "firstName", Column.class);
        // assert
        Assert.assertEquals("firstName", c.name());
    }

    @Test
    public void ColumnNameOnStamnr() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Students.class, "stamNr", Column.class);
        // assert
        Assert.assertEquals("stamNr", c.name());
    }

    @Test
    public void ColumnNameOnUsername() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Students.class, "username", Column.class);
        // assert
        Assert.assertEquals("username", c.name());
        Assert.assertEquals(93, c.length());
    }

    @Test
    public void ColumnNameOnEmail() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Students.class, "email", Column.class);
        // assert
        Assert.assertEquals("email", c.name());
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
    @Test
    public void ColumnNameOnSex() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Students.class, "sex", Column.class);
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




}