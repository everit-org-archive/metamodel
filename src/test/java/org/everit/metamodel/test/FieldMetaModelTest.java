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

import java.util.List;

import org.everit.metamodel.Attribute;
import org.everit.metamodel.MetamodelUtil;
import org.junit.Test;

public class FieldMetaModelTest {

    public static class AttributeClass<T> {
        public static volatile Attribute<AttributeClass<String>, String> field1;
        public static volatile Attribute<AttributeClass<String>, List<String>> field2;
    }

    @Test
    public void testClassInitialization() {
        MetamodelUtil.initClass(AttributeClass.class);
    }
}
