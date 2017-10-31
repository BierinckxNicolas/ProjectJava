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
public class DeletedStudentsTest {
 /*
    TEST ANNOTATIONS PRESENT
     */

    @Test
    public void AnnotationOnClassLevel() {
        // assert
        AssertAnnotations.assertType(
                DeletedStudents.class, Entity.class, Table.class);
    }


    /*
   TEST IF PROPERTIES OF ANNOTATIONS CORRECT (like name,mappedBy,cascade, etc)
    */

    @Test
    public void EntityNameTest() {
        // setup
        Entity a
                = ReflectTool.getClassAnnotation(DeletedStudents.class, Entity.class);
        // assert
        Assert.assertEquals("", a.name());
    }

    @Test
    public void TableNameTest() {
        // setup
        Table t
                = ReflectTool.getClassAnnotation(DeletedStudents.class, Table.class);
        // assert
        Assert.assertEquals("deletedinformatstudents", t.name());
        Assert.assertEquals("crescendo", t.schema());
    }
}