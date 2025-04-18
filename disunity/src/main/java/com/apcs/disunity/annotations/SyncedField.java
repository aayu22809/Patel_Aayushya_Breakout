package com.apcs.disunity.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.apcs.disunity.server.Syncable;

/// Informs the network code that this field should be synced via packets.
/// Field type must implement {@link Syncable} or be a primative.
/// Type check is done during compile time.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
// @EnforceFieldType(Syncable.class)
public @interface SyncedField {
}