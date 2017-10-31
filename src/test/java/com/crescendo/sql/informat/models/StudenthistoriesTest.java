package com.crescendo.sql.informat.models;

import com.crescendo.sql.helpers.AssertAnnotations;
import com.crescendo.sql.helpers.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

/**
 * Created by Gilles on 31/05/2017.
 */
public class StudenthistoriesTest {

     /*
    TEST ANNOTATIONS PRESENT
     */

    @Test
    public void AnnotationOnClassLevel() {
        // assert
        AssertAnnotations.assertType(
                Studenthistories.class, Entity.class, Table.class);
    }

    @Test
    public void AnnotationOnFieldLevel() {
        // assert
        AssertAnnotations.assertField(Studenthistories.class, "id", Id.class, Column.class);
        AssertAnnotations.assertField(Studenthistories.class, "schoolyear", Column.class);
        AssertAnnotations.assertField(Studenthistories.class, "result", Column.class);
        AssertAnnotations.assertField(Studenthistories.class, "studentId", Column.class);
        AssertAnnotations.assertField(Studenthistories.class, "courseId", Column.class);
    }

    @Test
    public void AnnotationOnMethodLevel() {
        // assert
        AssertAnnotations.assertMethod(
                Studenthistories.class, "getId");
        AssertAnnotations.assertMethod(
                Studenthistories.class, "getSchoolyear");
        AssertAnnotations.assertMethod(
                Studenthistories.class, "getResult");
        AssertAnnotations.assertMethod(
                Studenthistories.class, "getStudentId");
        AssertAnnotations.assertMethod(
                Studenthistories.class, "getCourseId");
    }

    /*
   TEST IF PROPERTIES OF ANNOTATIONS CORRECT (like name,mappedBy,cascade, etc)
    */

    @Test
    public void EntityNameTest() {
        // setup
        Entity a
                = ReflectTool.getClassAnnotation(Studenthistories.class, Entity.class);
        // assert
        Assert.assertEquals("", a.name());
    }

    @Test
    public void TableNameTest() {
        // setup
        Table t
                = ReflectTool.getClassAnnotation(Studenthistories.class, Table.class);
        // assert
        Assert.assertEquals("informatstudenthistories", t.name());
        Assert.assertEquals("crescendo", t.schema());
    }

    @Test
    public void ColumnNameOnId() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Studenthistories.class, "id", Column.class);
        // assert
        Assert.assertEquals("id", c.name());
    }
    @Test
    public void ColumnNameOnSchoolyear() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Studenthistories.class, "schoolyear", Column.class);
        // assert
        Assert.assertEquals("schoolyear", c.name());
    }

    @Test
    public void ColumnNameOnResult() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Studenthistories.class, "result", Column.class);
        // assert
        Assert.assertEquals("result", c.name());
    }

    @Test
    public void ColumnNameStudentId() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Studenthistories.class, "studentId", Column.class);
        // assert
        Assert.assertEquals("informatstudents_id", c.name());
    }

    @Test
    public void ColumnNameOnCourseId() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Studenthistories.class, "courseId", Column.class);
        // assert
        Assert.assertEquals("informatcourses_id", c.name());
    }
}