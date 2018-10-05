package com.jeya.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadingFromChannelIntoByteBuffer {
	
	public static void main(String[] args) {
		ByteBuffer buffer = createBuffer();
		
		FileChannel fileChannel = null;
		try {
			fileChannel = createFileChannel();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		StringBuilder contents = new StringBuilder();
		try {
			while(fileChannel.read(buffer) != -1)
			{
				contents.append(new String(buffer.array()));
				buffer.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(contents.toString());
	}

	private static FileChannel createFileChannel() throws IOException {
		Path path = Paths.get("ReadFile.txt");
		return FileChannel.open(path);
	}

	private static ByteBuffer createBuffer() {
		int BYTE_BUFFER_LENGTH = 1;
		return ByteBuffer.allocate(BYTE_BUFFER_LENGTH);
	}
}