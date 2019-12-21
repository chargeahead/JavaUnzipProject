package com.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipFile {

	public static void main(String[] args) throws Exception {
		if(args.length <2 ) {
			throw new Exception("Insufficient no of parameters. Required-Parameter1: Full zip file name, Parameter2: Dest dir");
			
		}
		String zipFileName = args[0];
		String destDirectory = args[1];
		File destDirectoryFolder = new File(destDirectory);
		if (!destDirectoryFolder.exists()) {
			destDirectoryFolder.mkdir();
		}
		byte[] buffer = new byte[1024];
		ZipInputStream zis= new ZipInputStream(new FileInputStream(zipFileName));
		ZipEntry zipEntry = zis.getNextEntry();
		while(zipEntry !=null) {
			String filePath = destDirectory + File.separator + zipEntry.getName();
			System.out.println("Unzipping "+filePath);
			if(!zipEntry.isDirectory()) {
				FileOutputStream fos = new FileOutputStream(filePath);
				int len;
				while ((len = zis.read(buffer)) >0){
					fos.write(buffer,0,len);
				}
				fos.close();
			}
			else {
				File dir = new File(filePath);
				dir.mkdir();
			}
			zis.closeEntry();
			zipEntry = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
		System.out.println("Unzipping complete");

	}

}
