package com.crescendo.sql.app.models;

import com.crescendo.sql.helpers.AssertAnnotations;
import com.crescendo.sql.helpers.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static org.junit.Assert.*;

/**
 * Created by Gilles on 31/05/2017.
 */
public class CheckBoxTest {

     /*
    TEST ANNOTATIONS PRESENT
     */

    @Test
    public void AnnotationOnClassLevel() {
        // assert
        AssertAnnotations.assertType(
                CheckBox.class, Entity.class, Table.class);
    }

    @Test
    public void AnnotationOnFieldLevel() {
        // assert
        AssertAnnotations.assertField(CheckBox.class, "checkboxId", Id.class, Column.class, Min.class, Max.class, NotNull.class);
        AssertAnnotations.assertField(CheckBox.class, "checkboxType", Column.class);
        AssertAnnotations.assertField(CheckBox.class, "checkboxState", Column.class, NotNull.class);
    }

    @Test
    public void AnnotationOnMethodLevel() {
        // assert
        AssertAnnotations.assertMethod(
                CheckBox.class, "getCheckboxId");
        AssertAnnotations.assertMethod(
                CheckBox.class, "getCheckboxType");
        AssertAnnotations.assertMethod(
                CheckBox.class, "isCheckboxState");
    }

    /*
   TEST IF PROPERTIES OF ANNOTATIONS CORRECT (like name,mappedBy,cascade, etc)
    */

    @Test
    public void EntityNameTest() {
        // setup
        Entity a
                = ReflectTool.getClassAnnotation(CheckBox.class, Entity.class);
        // assert
        Assert.assertEquals("", a.name());
    }

    @Test
    public void TableNameTest() {
        // setup
        Table t
                = ReflectTool.getClassAnnotation(CheckBox.class, Table.class);
        // assert
        Assert.assertEquals("checkboxstates", t.name());
        Assert.assertEquals("crescendo", t.schema());
    }

    @Test
    public void ColumnNameOnCheckBoxId() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                CheckBox.class, "checkboxId", Column.class);
        // assert
        Assert.assertEquals("checkbox_id", c.name());
    }

    @Test
    public void ColumnNameOnCheckBoxType() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                CheckBox.class, "checkboxType", Column.class);
        Min c2 = ReflectTool.getFieldAnnotation(
                CheckBox.class, "checkboxId", Min.class);
        Max c3 = ReflectTool.getFieldAnnotation(
                CheckBox.class, "checkboxId", Max.class);

        // assert
        Assert.assertEquals("checkbox_type", c.name());
        Assert.assertEquals(1, c2.value());
        Assert.assertEquals(4, c3.value());


    }


    @Test
    public void ColumnNameOnCheckBoxState() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                CheckBox.class, "checkboxState", Column.class);
        // assert

    }
}