package com.crescendo.sql.informat.models;

import com.crescendo.sql.helpers.AssertAnnotations;
import com.crescendo.sql.helpers.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

/**
 * Created by Gilles on 31/05/2017.
 */
public class CoursesTest {

    /*
    TEST ANNOTATIONS PRESENT
     */

    @Test
    public void AnnotationOnClassLevel() {
        // assert
        AssertAnnotations.assertType(
                Courses.class, Entity.class, Table.class);
    }

    @Test
    public void AnnotationOnFieldLevel() {
        // assert
        AssertAnnotations.assertField(Courses.class, "id", Id.class, Column.class);
        AssertAnnotations.assertField(Courses.class, "nrOfRegistrations", Column.class);
        AssertAnnotations.assertField(Courses.class, "maxRegistrations", Column.class);
        AssertAnnotations.assertField(Courses.class, "endDate", Column.class);
        AssertAnnotations.assertField(Courses.class, "semester", Column.class);
        AssertAnnotations.assertField(Courses.class, "thirdPartyRef", Column.class);
        AssertAnnotations.assertField(Courses.class, "modulesSet", ManyToMany.class);
    }

    @Test
    public void AnnotationOnMethodLevel() {
        // assert
        AssertAnnotations.assertMethod(
                Courses.class, "getId");
        AssertAnnotations.assertMethod(
                Courses.class, "getName");
        AssertAnnotations.assertMethod(
                Courses.class, "getNrOfRegistrations");
        AssertAnnotations.assertMethod(
                Courses.class, "getStartDate");
        AssertAnnotations.assertMethod(
                Courses.class, "getEndDate");
        AssertAnnotations.assertMethod(
                Courses.class, "getSemester");
        AssertAnnotations.assertMethod(
                Courses.class, "getThirdPartyRef");
        AssertAnnotations.assertMethod(
                Courses.class, "getMainTeachers");
        AssertAnnotations.assertMethod(
                Courses.class, "getDepartments");
        AssertAnnotations.assertMethod(
                Courses.class, "getLocations");
        AssertAnnotations.assertMethod(
                Courses.class, "getModulesSet");
        AssertAnnotations.assertMethod(
                Courses.class, "getStudiegebied");

    }

    /*
    TEST IF PROPERTIES OF ANNOTATIONS CORRECT (like name,mappedBy,cascade, etc)
     */

    @Test
    public void EntityNameTest() {
        // setup
        Entity a
                = ReflectTool.getClassAnnotation(Courses.class, Entity.class);
        // assert
        Assert.assertEquals("", a.name());
    }

    @Test
    public void TableNameTest() {
        // setup
        Table t
                = ReflectTool.getClassAnnotation(Courses.class, Table.class);
        // assert
        Assert.assertEquals("informatcourses", t.name());
        Assert.assertEquals("crescendo", t.schema());
    }

    @Test
    public void ColumnNameOnId() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Courses.class, "id", Column.class);
        // assert
        Assert.assertEquals("id", c.name());
    }

    @Test
    public void ColumnNameOnName() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Courses.class, "name", Column.class);
        // assert
        Assert.assertEquals("name", c.name());
    }

    @Test
    public void ColumnNameOnNrOfRegistrations() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Courses.class, "nrOfRegistrations", Column.class);
        // assert
        Assert.assertEquals("nrOfRegistrations", c.name());
    }
    @Test
    public void ColumnNameOnMaxRegistrations() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Courses.class, "maxRegistrations", Column.class);
        // assert
        Assert.assertEquals("maxRegistrations", c.name());
    }

    @Test
    public void ColumnNameOnStartDate() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Courses.class, "startDate", Column.class);
        // assert
        Assert.assertEquals("startDate", c.name());
    }

    @Test
    public void ColumnNameOnEndDate() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Courses.class, "endDate", Column.class);
        // assert
        Assert.assertEquals("endDate", c.name());
    }

    @Test
    public void ColumnNameOnSemester() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Courses.class, "semester", Column.class);
        // assert
        Assert.assertEquals("semester", c.name());
    }


    @Test
    public void ColumnNameOnThirdPartyRef() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Courses.class, "thirdPartyRef", Column.class);
        // assert
        Assert.assertEquals("thirdPartyRef", c.name());
    }

    @Test
    public void ColumnRelationOnMainTeacher() {
        // setup
        ManyToOne a
                = ReflectTool.getFieldAnnotation(
                Courses.class, "mainTeachers", ManyToOne.class);
        // assert
    }



}