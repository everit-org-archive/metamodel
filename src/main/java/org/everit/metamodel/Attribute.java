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
import java.lang.reflect.Type;

/**
 * Attribute is a generics typed class-attribute pair where the attribute can be a theoretical or a real field of the
 * class. The attribute helps to write down type graphs like the static metamodel does in JPA spec. The usage of
 * attributes can be useful when we want to write typed API like the Criteria API in JPA spec. <br>
 * <br>
 * An attribute is defined as a public static volatile variable.
 * 
 * @param <M>
 *            The model is a class that the attribute belongs to.
 * @param <A>
 *            The type of the attribute. The method {@link #getAttributeType()} should return the same type.
 */
public interface Attribute<M, A> {

    /**
     * Returns the field that defines the attribute.
     * 
     * @return The attribute.
     */
    Field getDeclaringField();

    /**
     * Returns the name of the attribute. If not specified during creation of the attribute the default value is the
     * name of the {@link #getDeclaringField()}.
     * 
     * @return The name of the attribute.
     */
    String getAttributeName();

    /**
     * Returns the type of the Model. This is the class that the attribute belongs to.
     * 
     * @return The type of the model.
     */
    Type getModelType();

    /**
     * Returns the type of the attribute that is the same as defined in the generic value M of this class.
     * 
     * @return The type of the attribute.
     */
    Type getAttributeType();

    /**
     * Returns the field that is pointed by the attribute if exists.
     * 
     * @return The field that is pointed by the attribute or null if no such attribute exists.
     */
    Field getAttributeField();
}
