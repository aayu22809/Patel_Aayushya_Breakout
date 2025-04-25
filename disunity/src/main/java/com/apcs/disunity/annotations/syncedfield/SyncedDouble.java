package com.apcs.disunity.annotations.syncedfield;

import com.apcs.disunity.annotations.EnforceFieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@EnforceFieldType(double.class)
public @interface SyncedDouble {
}
