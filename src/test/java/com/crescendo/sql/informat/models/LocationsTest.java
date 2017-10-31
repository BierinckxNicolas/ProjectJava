package com.crescendo.sql.informat.models;

import com.crescendo.sql.helpers.AssertAnnotations;
import com.crescendo.sql.helpers.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

/**
 * Created by Gilles on 31/05/2017.
 */
public class LocationsTest {
   /*
    TEST ANNOTATIONS PRESENT
     */

    @Test
    public void AnnotationOnClassLevel() {
        // assert
        AssertAnnotations.assertType(
                Locations.class, Entity.class, Table.class);
    }

    @Test
    public void AnnotationOnFieldLevel() {
        // assert
        AssertAnnotations.assertField(Locations.class, "id", Id.class, Column.class);
        AssertAnnotations.assertField(Locations.class, "name", Column.class);
    }

    @Test
    public void AnnotationOnMethodLevel() {
        // assert
        AssertAnnotations.assertMethod(
                Locations.class, "getId");
        AssertAnnotations.assertMethod(
                Locations.class, "getName");
    }

    /*
   TEST IF PROPERTIES OF ANNOTATIONS CORRECT (like name,mappedBy,cascade, etc)
    */

    @Test
    public void EntityNameTest() {
        // setup
        Entity a
                = ReflectTool.getClassAnnotation(Locations.class, Entity.class);
        // assert
        Assert.assertEquals("", a.name());
    }

    @Test
    public void TableNameTest() {
        // setup
        Table t
                = ReflectTool.getClassAnnotation(Locations.class, Table.class);
        // assert
        Assert.assertEquals("informatlocations", t.name());
        Assert.assertEquals("crescendo", t.schema());
    }

    @Test
    public void ColumnNameOnId() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Locations.class, "id", Column.class);
        // assert
        Assert.assertEquals("id", c.name());
    }

    @Test
    public void ColumnNameOnName() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Locations.class, "name", Column.class);
        // assert
        Assert.assertEquals("name", c.name());
    }
}