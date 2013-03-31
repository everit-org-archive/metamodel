package org.everit.metamodel.test;

/*
 * Copyright (c) 2011, Everit Kft.
 *
 * All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

import java.lang.reflect.Field;
import java.util.List;

import org.everit.metamodel.Attribute;
import org.everit.metamodel.MetamodelUtil;
import org.junit.Assert;
import org.junit.Test;

public class FieldMetaModelTest {

    public static class AttributeClass<T> {
        public static volatile Attribute<AttributeClass<String>, String> simpleField;
        public static volatile Attribute<AttributeClass<String>, List<String>> parameterizedField;
        public static volatile Attribute<AttributeClass<String>, String> alreadySpecifiedField = MetamodelUtil
                .createAttribute(AttributeClass.class, "alreadySpecifiedField", "test");
    }

    @Test
    public void testClassInitialization() {
        @SuppressWarnings("rawtypes")
        Class<AttributeClass> attributeClass = AttributeClass.class;
        MetamodelUtil.initClass(attributeClass);
        try {
            Field simpleField = attributeClass.getField("simpleField");
            Object value = simpleField.get(null);
            Attribute<AttributeClass<String>, String> attribute = (Attribute<AttributeClass<String>, String>) value;
            Assert.assertEquals("simpleField", attribute.getAttributeName());
            
            Field alreadySpecifiedField = attributeClass.getField("alreadySpecifiedField");
            value = alreadySpecifiedField.get(null);
            attribute = (Attribute<AttributeClass<String>, String>) value;
            Assert.assertEquals("test", attribute.getAttributeName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        } catch (SecurityException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
