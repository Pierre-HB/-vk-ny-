package fr.pierrehb.graphic;

import java.nio.ByteBuffer;

public class Fliper {

	public static ByteBuffer flip(ByteBuffer buffer) {
		// WARNING : " cannot convert from byte to bytebuffer" ou l'inverse...
		return (ByteBuffer)buffer.flip();
	}
}
