package com.crescendo.sql.informat.models;

import com.crescendo.sql.helpers.AssertAnnotations;
import com.crescendo.sql.helpers.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

/**
 * Created by Gilles on 31/05/2017.
 */
public class ModulesTest {
  /*
    TEST ANNOTATIONS PRESENT
     */

    @Test
    public void AnnotationOnClassLevel() {
        // assert
        AssertAnnotations.assertType(
                Modules.class, Entity.class, Table.class);
    }

    @Test
    public void AnnotationOnFieldLevel() {
        // assert
        AssertAnnotations.assertField(Modules.class, "id", Id.class, Column.class);
        AssertAnnotations.assertField(Modules.class, "exchangeCode", Column.class);
        AssertAnnotations.assertField(Modules.class, "code",Column.class);
        AssertAnnotations.assertField(Modules.class, "moduleCode", Column.class);
        AssertAnnotations.assertField(Modules.class, "name", Column.class);
        AssertAnnotations.assertField(Modules.class, "smartschoolModule", Column.class);
        AssertAnnotations.assertField(Modules.class, "coursesSet", ManyToMany.class, JoinTable.class);
    }

    @Test
    public void AnnotationOnMethodLevel() {
        // assert
        AssertAnnotations.assertMethod(
                Modules.class, "getId");
        AssertAnnotations.assertMethod(
                Modules.class, "getExchangeCode");
        AssertAnnotations.assertMethod(
                Modules.class, "getCode");
        AssertAnnotations.assertMethod(
                Modules.class, "getModuleCode");
        AssertAnnotations.assertMethod(
                Modules.class, "getName");
        AssertAnnotations.assertMethod(
                Modules.class, "isSmartschoolModule");
        AssertAnnotations.assertMethod(
                Modules.class, "getCoursesSet");
    }

    /*
   TEST IF PROPERTIES OF ANNOTATIONS CORRECT (like name,mappedBy,cascade, etc)
    */

    @Test
    public void EntityNameTest() {
        // setup
        Entity a
                = ReflectTool.getClassAnnotation(Modules.class, Entity.class);
        // assert
        Assert.assertEquals("", a.name());
    }

    @Test
    public void TableNameTest() {
        // setup
        Table t
                = ReflectTool.getClassAnnotation(Modules.class, Table.class);
        // assert
        Assert.assertEquals("informatmodules", t.name());
        Assert.assertEquals("crescendo", t.schema());
    }

    @Test
    public void ColumnNameOnId() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Modules.class, "id", Column.class);
        // assert
        Assert.assertEquals("id", c.name());
    }

    @Test
    public void ColumnNameOnExchangeCode() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Modules.class, "exchangeCode", Column.class);
        // assert
        Assert.assertEquals("exchangeCode", c.name());
    }


    @Test
    public void ColumnNameOnCode() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Modules.class, "code", Column.class);
        // assert
        Assert.assertEquals("code", c.name());
    }

    @Test
    public void ColumnNameOnModuleCode() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Modules.class, "moduleCode", Column.class);
        // assert
        Assert.assertEquals("moduleCode", c.name());
    }

    @Test
    public void ColumnNameOnName() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Modules.class, "name", Column.class);
        // assert
        Assert.assertEquals("name", c.name());
    }

    @Test
    public void ColumnNameOnSmartschoolModule() {
        // setup
        Column c
                = ReflectTool.getFieldAnnotation(
                Modules.class, "smartschoolModule", Column.class);
        // assert
        Assert.assertEquals("smartschoolModule", c.name());
    }

    @Test
    public void ColumnRelationOnCoursesSet() {
        // setup
        ManyToMany a
                = ReflectTool.getFieldAnnotation(
                Modules.class, "coursesSet", ManyToMany.class);
        // assert
        Assert.assertEquals(Courses.class, a.targetEntity());
        Assert.assertEquals(CascadeType.MERGE, a.cascade()[1]);
    }

    @Test
    public void ColumnJoinOnCoursesSet() {
        // setup
        JoinTable a
                = ReflectTool.getFieldAnnotation(
                Modules.class, "coursesSet", JoinTable.class);
        // assert
        Assert.assertEquals("informatcourses_informatmodules", a.name());
        Assert.assertEquals(1, a.joinColumns().length);
        Assert.assertEquals(1, a.inverseJoinColumns().length);
    }
}