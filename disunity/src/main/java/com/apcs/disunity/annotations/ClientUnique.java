package com.apcs.disunity.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/// has higher priority than SyncedField. Objects instantiated with this annotation
/// will not get Synced. Temporal solution for server reconsiliation.
/// TODO: make netowrk code working without using this
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientUnique {
}

