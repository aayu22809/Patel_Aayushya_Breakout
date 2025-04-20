package com.apcs.disunity.server;

import java.io.InputStream;
import java.io.OutputStream;

import static com.apcs.disunity.server.Util.decodeBits;
import static com.apcs.disunity.server.Util.encodeBits;

public class SyncableChar extends SyncableWrapper<Character> {
    public SyncableChar(char intialValue) {
        super(intialValue,SyncableChar::encodeChar,SyncableChar::decodeChar);
    }

    public static void encodeChar(char val, OutputStream out) {
        encodeBits(val,Character.BYTES,out);
    }
    public static char decodeChar(InputStream in) {
        return (char) decodeBits(Character.BYTES, in);
    }
}
