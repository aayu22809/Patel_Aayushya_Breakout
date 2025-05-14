/// Annotations in this package indicates the {@link
/// com.apcs.disunity.app.network.packet.SyncHandler} to include the annotated
/// field in the packet.
/// You should use corresponding annotation for each type, or else {@link
/// com.apcs.disunity.annotations.FieldTypeEnforcer}
/// would throw a compiler errror (assuming you have set up the dependency
/// correctly.)
/// The codecs bound to these annotations are defiend in {@link
/// com.apcs.disunity.app.network.packet.CODEC}.
package com.apcs.disunity.app.network.packet.annotation;