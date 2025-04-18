package com.apcs.disunity.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.apcs.disunity.nodes.Node;

/**
 * Requires a node to have certain children
 * 
 * @author Qinzhao Li
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Requires {
    Class<? extends Node<?>>[] nodes();
}
