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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Simple implementation of the {@link Attribute} interface.
 * 
 * @param <M>
 *            See the definition at {@link Attribute} interface.
 * @param <A>
 *            See the definition at {@link Attribute} interface.
 */
class AttributeImpl<M, A> implements Attribute<M, A> {

    /**
     * See {@link Attribute#getDeclaringField()}.
     */
    private final Field declaringField;

    /**
     * See {@link Attribute#getAttributeName()}.
     */
    private final String attributeName;

    /**
     * See {@link Attribute#getModelType()}.
     */
    private final Type modelType;

    /**
     * See {@link Attribute#getAttributeType()}.
     */
    private final Type attributeType;

    /**
     * See {@link Attribute#getAttributeField()}.
     */
    private final Field attributeField;

    AttributeImpl(final Field declaringField, String attributeName) {
        this.declaringField = declaringField;
        if (attributeName != null) {
            this.attributeName = attributeName;
        } else {
            this.attributeName = declaringField.getName();
        }

        Type declaringFieldType = declaringField.getGenericType();
        if (!(declaringFieldType instanceof ParameterizedType)) {
            throw new IllegalArgumentException(
                    "The declaringField parameter must be a parameterized Attribute and not "
                            + declaringFieldType.toString());
        }

        ParameterizedType parameterizedType = (ParameterizedType) declaringFieldType;
        if (!parameterizedType.getRawType().equals(Attribute.class)) {
            throw new IllegalArgumentException("The type of declaring field can only be Attribute and not "
                    + parameterizedType.toString());
        }
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        this.modelType = typeArguments[0];
        this.attributeType = typeArguments[1];

        Class<?> modelClass = null;
        if (modelType instanceof Class) {
            modelClass = (Class<?>) modelType;
        } else if (modelType instanceof ParameterizedType) {
            modelClass = (Class<?>) ((ParameterizedType) modelType).getRawType();
        } else {
            throw new IllegalArgumentException("The type of model can be only ParameterizedType or Class and not "
                    + modelType.toString());
        }

        Field tmpAttributeField = null;
        try {
            tmpAttributeField = modelClass.getField(this.attributeName);
        } catch (NoSuchFieldException e) {
            // TODO debug line to do nothing
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        attributeField = tmpAttributeField;
    }

    @Override
    public String getAttributeName() {
        return attributeName;
    }

    @Override
    public Type getModelType() {
        return modelType;
    }

    @Override
    public Field getDeclaringField() {
        return declaringField;
    }

    @Override
    public Type getAttributeType() {
        return attributeType;
    }

    @Override
    public Field getAttributeField() {
        return attributeField;
    }
}
