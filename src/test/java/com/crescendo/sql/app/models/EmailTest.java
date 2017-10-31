package com.crescendo.sql.app.models;

import com.crescendo.sql.helpers.AssertAnnotations;
import com.crescendo.sql.helpers.ReflectTool;

import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static org.junit.Assert.*;

/**
 * Created by Gilles on 31/05/2017.
 */
public class EmailTest {
  /*
    TEST ANNOTATIONS PRESENT
     */

    @Test
    public void AnnotationOnClassLevel() {
        // assert
        AssertAnnotations.assertType(
                Email.class, Entity.class, Table.class);
    }

    @Test
    public void AnnotationOnFieldLevel() {
        // assert
        AssertAnnotations.assertField(Email.class, "emailid", Id.class, Column.class, Min.class, Max.class, NotNull.class);
        AssertAnnotations.assertField(Email.class, "emailType", Column.class);
        AssertAnnotations.assertField(Email.class, "emailContent", Column.class, NotNull.class);
    }

    @Test
    public void AnnotationOnMethodLevel() {
        // assert
        AssertAnnotations.assertMethod(
                Email.class, "getEmailid");
        AssertAnnotations.assertMethod(
                Email.class, "getEmailType");
        AssertAnnotations.assertMethod(
                Email.class, "getEmailContent");
    }

    /*
   TEST IF PROPERTIES OF ANNOTATIONS CORRECT (like name,mappedBy,cascade, etc)
    */

    @Test
    public void EntityNameTest() {
        // setup
        Entity a
                = ReflectTool.getClassAnnotation(Email.class, Entity.class);
        // assert
        Assert.assertEquals("", a.name());
    }

    @Test
    public void TableNameTest() {
        // setup
        Table t
                = ReflectTool.getClassAnnotation(Email.class, Table.class);
        // assert
        Assert.assertEquals("emails", t.name());
        Assert.assertEquals("crescendo", t.schema());
    }

    @Test
    public void ColumnNameOnEmailId() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Email.class, "emailid", Column.class);
        Min c2
                = ReflectTool.getFieldAnnotation(
                Email.class, "emailid", Min.class);
        Max c3
                = ReflectTool.getFieldAnnotation(
                Email.class, "emailid", Max.class);
        // assert
        Assert.assertEquals("email_id", c.name());
        Assert.assertEquals(1, c2.value());
        Assert.assertEquals(3, c3.value());
    }

    @Test
    public void ColumnNameOnEmailType() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Email.class, "emailType", Column.class);
        // assert
        Assert.assertEquals("email_type", c.name());
    }

    @Test
    public void ColumnNameOnEmailContent() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Email.class, "emailContent", Column.class);
        // assert
        Assert.assertEquals("email_content", c.name());
        Assert.assertEquals("text", c.columnDefinition());
    }
}