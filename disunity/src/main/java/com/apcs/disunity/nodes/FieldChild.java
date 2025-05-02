package com.apcs.disunity.nodes;

import com.apcs.disunity.annotations.EnforceFieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/// appends the annotated field to return value of {@link Node#getChildren()}.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@EnforceFieldType(Node.class)
public @interface FieldChild {
}