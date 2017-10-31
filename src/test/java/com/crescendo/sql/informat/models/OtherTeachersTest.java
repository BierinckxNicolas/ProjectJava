package com.crescendo.sql.informat.models;

import com.crescendo.sql.helpers.AssertAnnotations;
import com.crescendo.sql.helpers.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

/**
 * Created by Gilles on 31/05/2017.
 */
public class OtherTeachersTest {

        /*
    TEST ANNOTATIONS PRESENT
     */

    @Test
    public void AnnotationOnClassLevel() {
        // assert
        AssertAnnotations.assertType(
                OtherTeachers.class, Entity.class, Table.class);
    }

    @Test
    public void AnnotationOnFieldLevel() {
        // assert
        AssertAnnotations.assertField(OtherTeachers.class, "id", Id.class, Column.class);
        AssertAnnotations.assertField(OtherTeachers.class, "lastName", Column.class);
        AssertAnnotations.assertField(OtherTeachers.class, "firstName", Column.class);
        AssertAnnotations.assertField(OtherTeachers.class, "courseId", Column.class);
    }

    @Test
    public void AnnotationOnMethodLevel() {
        // assert
        AssertAnnotations.assertMethod(
                OtherTeachers.class, "getId");
        AssertAnnotations.assertMethod(
                OtherTeachers.class, "getLastName");
        AssertAnnotations.assertMethod(
                OtherTeachers.class, "getFirstName");
        AssertAnnotations.assertMethod(
                OtherTeachers.class, "getCourseId");
    }

    /*
   TEST IF PROPERTIES OF ANNOTATIONS CORRECT (like name,mappedBy,cascade, etc)
    */

    @Test
    public void EntityNameTest() {
        // setup
        Entity a
                = ReflectTool.getClassAnnotation(OtherTeachers.class, Entity.class);
        // assert
        Assert.assertEquals("", a.name());
    }

    @Test
    public void TableNameTest() {
        // setup
        Table t
                = ReflectTool.getClassAnnotation(OtherTeachers.class, Table.class);
        // assert
        Assert.assertEquals("informatotherteachers", t.name());
        Assert.assertEquals("crescendo", t.schema());
    }

    @Test
    public void ColumnNameOnId() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                OtherTeachers.class, "id", Column.class);
        // assert
        Assert.assertEquals("id", c.name());
    }
    @Test
    public void ColumnNameOnLastName() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                OtherTeachers.class, "lastName", Column.class);
        // assert
        Assert.assertEquals("lastName", c.name());
    }

    @Test
    public void ColumnNameOnFirstName() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                OtherTeachers.class, "firstName", Column.class);
        // assert
        Assert.assertEquals("firstName", c.name());
    }

    @Test
    public void ColumnNameOnCourseId() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                OtherTeachers.class, "courseId", Column.class);
        // assert
        Assert.assertEquals("courseId", c.name());
    }


}