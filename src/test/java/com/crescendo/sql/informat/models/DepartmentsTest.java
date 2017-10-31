package com.crescendo.sql.informat.models;

import com.crescendo.sql.helpers.AssertAnnotations;
import com.crescendo.sql.helpers.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

/**
 * Created by Gilles on 31/05/2017.
 */
public class DepartmentsTest {
   /*
    TEST ANNOTATIONS PRESENT
     */

    @Test
    public void AnnotationOnClassLevel() {
        // assert
        AssertAnnotations.assertType(
                Departments.class, Entity.class, Table.class);
    }

    @Test
    public void AnnotationOnFieldLevel() {
        // assert
        AssertAnnotations.assertField(Departments.class, "id", Id.class, Column.class);
        AssertAnnotations.assertField(Departments.class, "code", Column.class);
        AssertAnnotations.assertField(Departments.class, "name", Column.class);
        AssertAnnotations.assertField(Departments.class, "nrAdmgrp", Column.class);
        AssertAnnotations.assertField(Departments.class, "admgrp", Column.class);
        AssertAnnotations.assertField(Departments.class, "eduCode", Column.class);
        AssertAnnotations.assertField(Departments.class, "eduName", Column.class);
        AssertAnnotations.assertField(Departments.class, "area", Column.class);
    }

    @Test
    public void AnnotationOnMethodLevel() {
        // assert
        AssertAnnotations.assertMethod(
                Departments.class, "getId");
        AssertAnnotations.assertMethod(
                Departments.class, "getCode");
        AssertAnnotations.assertMethod(
                Departments.class, "getName");
        AssertAnnotations.assertMethod(
                Departments.class, "getNrAdmgrp");
        AssertAnnotations.assertMethod(
                Departments.class, "getAdmgrp");
        AssertAnnotations.assertMethod(
                Departments.class, "getEduCode");
        AssertAnnotations.assertMethod(
                Departments.class, "getEduName");
        AssertAnnotations.assertMethod(
                Departments.class, "getArea");
    }

    /*
   TEST IF PROPERTIES OF ANNOTATIONS CORRECT (like name,mappedBy,cascade, etc)
    */

    @Test
    public void EntityNameTest() {
        // setup
        Entity a
                = ReflectTool.getClassAnnotation(Departments.class, Entity.class);
        // assert
        Assert.assertEquals("", a.name());
    }

    @Test
    public void TableNameTest() {
        // setup
        Table t
                = ReflectTool.getClassAnnotation(Departments.class, Table.class);
        // assert
        Assert.assertEquals("informatdepartments", t.name());
        Assert.assertEquals("crescendo", t.schema());
    }

    @Test
    public void ColumnNameOnId() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Departments.class, "id", Column.class);
        // assert
        Assert.assertEquals("id", c.name());
    }
    @Test
    public void ColumnNameOnCode() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Departments.class, "code", Column.class);
        // assert
        Assert.assertEquals("code", c.name());
    }

    @Test
    public void ColumnNameOnName() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Departments.class, "name", Column.class);
        // assert
        Assert.assertEquals("name", c.name());
    }

    @Test
    public void ColumnNameOnNrAdmgrp() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Departments.class, "nrAdmgrp", Column.class);
        // assert
        Assert.assertEquals("nrAdmgrp", c.name());
    }

    @Test
    public void ColumnNameOnAdmgrp() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Departments.class, "admgrp", Column.class);
        // assert
        Assert.assertEquals("admgrp", c.name());
    }

    @Test
    public void ColumnNameOnEduCode() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Departments.class, "eduCode", Column.class);
        // assert
        Assert.assertEquals("eduCode", c.name());
    }

    @Test
    public void ColumnNameOnEduName() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Departments.class, "eduName", Column.class);
        // assert
        Assert.assertEquals("eduName", c.name());
    }

    @Test
    public void ColumnNameOnArea() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Departments.class, "area", Column.class);
        // assert
        Assert.assertEquals("area", c.name());
    }

}