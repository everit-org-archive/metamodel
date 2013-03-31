package org.everit.metamodel;

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
import java.lang.reflect.Modifier;

/**
 * Utility class of Metamodel solution.
 */
public final class MetamodelUtil {

    /**
     * Initializes a class that has Attributes defined in it. Attributes have to be defined as public static volatile
     * Attribute<M, A> variables. After running the function all attributes that meet the requirements will have a
     * value. In case a variable already has a value it will be ignored.
     * 
     * @param clazz
     *            The class that will be initialized.
     */
    public static void initClass(final Class<?> clazz) {
        Field[] declaringFields = clazz.getDeclaredFields();
        for (Field declaringField : declaringFields) {
            initFieldIfDeclaring(declaringField, null);
        }
    }

    private static void initFieldIfDeclaring(final Field declaringField, final String attributeName) {
        if (!isDeclaringField(declaringField)) {
            return;
        }

        Object declaringValue = getValueOfStaticField(declaringField);

        // We initialize the field only if it is null.
        if (declaringValue != null) {
            return;
        }

        @SuppressWarnings("unchecked")
        AttributeImpl<Object, Object> attributeImpl = new AttributeImpl(declaringField, attributeName);
        setValueOfStaticField(declaringField, attributeImpl);
    }

    private static Object getValueOfStaticField(final Field field) {
        Object result = null;
        try {
            result = field.get(null);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Do it with setAccessible
            e.printStackTrace();
        }
        return result;
    }

    private static void setValueOfStaticField(final Field field, Object value) {
        try {
            field.set(null, value);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static boolean isDeclaringField(final Field field) {
        return Modifier.isStatic(field.getModifiers()) && Attribute.class.isAssignableFrom(field.getType());
    }

    private MetamodelUtil() {
        // Private constructor of Util class.
    }
}
