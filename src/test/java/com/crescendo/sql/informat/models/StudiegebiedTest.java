package com.crescendo.sql.informat.models;

import com.crescendo.sql.helpers.AssertAnnotations;
import com.crescendo.sql.helpers.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

/**
 * Created by Gilles on 31/05/2017.
 */
public class StudiegebiedTest {
 /*
    TEST ANNOTATIONS PRESENT
     */

    @Test
    public void AnnotationOnClassLevel() {
        // assert
        AssertAnnotations.assertType(
                Studiegebied.class, Entity.class, Table.class);
    }

    @Test
    public void AnnotationOnFieldLevel() {
        // assert
        AssertAnnotations.assertField(Studiegebied.class, "name", Id.class, Column.class);
        AssertAnnotations.assertField(Studiegebied.class, "code", Column.class);
        AssertAnnotations.assertField(Studiegebied.class, "type", Column.class);
    }

    @Test
    public void AnnotationOnMethodLevel() {
        // assert
        AssertAnnotations.assertMethod(
                Studiegebied.class, "getName");
        AssertAnnotations.assertMethod(
                Studiegebied.class, "getCode");
        AssertAnnotations.assertMethod(
                Studiegebied.class, "getType");
    }

    /*
   TEST IF PROPERTIES OF ANNOTATIONS CORRECT (like name,mappedBy,cascade, etc)
    */

    @Test
    public void EntityNameTest() {
        // setup
        Entity a
                = ReflectTool.getClassAnnotation(Studiegebied.class, Entity.class);
        // assert
        Assert.assertEquals("", a.name());
    }

    @Test
    public void TableNameTest() {
        // setup
        Table t
                = ReflectTool.getClassAnnotation(Studiegebied.class, Table.class);
        // assert
        Assert.assertEquals("informatstudiegebied", t.name());
        Assert.assertEquals("crescendo", t.schema());
    }

    @Test
    public void ColumnNameOnName() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Studiegebied.class, "name", Column.class);
        // assert
        Assert.assertEquals("name", c.name());
    }
    @Test
    public void ColumnNameOnCode() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Studiegebied.class, "code", Column.class);
        // assert
        Assert.assertEquals("code", c.name());
    }

    @Test
    public void ColumnNameOnType() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Studiegebied.class, "type", Column.class);
        // assert
        Assert.assertEquals("type", c.name());
    }
}