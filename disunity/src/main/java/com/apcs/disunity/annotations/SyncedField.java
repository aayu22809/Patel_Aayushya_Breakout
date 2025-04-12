package com.apcs.disunity.annotations;

import com.apcs.disunity.server.Syncable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/// Informs the network code that this field should be synced via packets.
/// Field type must implement {@link Syncable}.
/// Type check is done during compile time.
/// TODO: implement packet pipeline
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
// @EnforceFieldType(Syncable.class)
public @interface SyncedField {
}