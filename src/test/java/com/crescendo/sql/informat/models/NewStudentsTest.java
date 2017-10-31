package com.crescendo.sql.informat.models;

import com.crescendo.sql.helpers.AssertAnnotations;
import com.crescendo.sql.helpers.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Gilles on 31/05/2017.
 */
public class NewStudentsTest {

    /*
    TEST ANNOTATIONS PRESENT
     */

    @Test
    public void AnnotationOnClassLevel() {
        // assert
        AssertAnnotations.assertType(
                NewStudents.class, Entity.class, Table.class);
    }


    /*
   TEST IF PROPERTIES OF ANNOTATIONS CORRECT (like name,mappedBy,cascade, etc)
    */

    @Test
    public void EntityNameTest() {
        // setup
        Entity a
                = ReflectTool.getClassAnnotation(NewStudents.class, Entity.class);
        // assert
        Assert.assertEquals("", a.name());
    }

    @Test
    public void TableNameTest() {
        // setup
        Table t
                = ReflectTool.getClassAnnotation(NewStudents.class, Table.class);
        // assert
        Assert.assertEquals("newinformatstudents", t.name());
        Assert.assertEquals("crescendo", t.schema());
    }
}