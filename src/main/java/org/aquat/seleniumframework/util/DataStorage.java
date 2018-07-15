package org.aquat.seleniumframework.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * <p>Class for temporary property storage and retrieve</p>
 * 
 */
public class DataStorage {
	private static final String TMP_DIR = "java.io.tmpdir";
	private static final String ENCODING = "utf-8";
	private File file;
	
	public DataStorage(String name) {
		String fileName = System.getProperty(TMP_DIR) + name;
		this.file = new File(fileName);	
	}
	
	public void create(){
		if (file.exists()) {
			file.delete();
		}
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String key, String value) {
		FileOutputStream out = null;
		try {
			out=new FileOutputStream(file, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(key + "=" + value).append("\r\n");
		
		try {
			out.write(sb.toString().getBytes(ENCODING));
			out.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String read(String key) {
		BufferedReader br = null;
		try {
			br=new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String line = null;
		try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (line != null) {
			String[] str = line.split("=");
			if (str[0].equalsIgnoreCase(key)) {
				return str[1];
			}
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
