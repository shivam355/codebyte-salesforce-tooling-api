package com.project.helper;

import java.io.File;

import com.project.exception.AppRuntimeException;

/**
 * Util class
 * 
 * @author shivam
 *
 */
public class ArgUtil {
	/**
	 * getDomain
	 * 
	 * @param args
	 * @return
	 */
	public static String getDomain(String[] args) {
		try {
			return args[0];
		} catch (Exception e) {
			throw new AppRuntimeException("Please provide domain");
		}
	}

	/**
	 * getMaxPoolSize
	 * 
	 * @param args
	 * @return
	 */
	public static int getMaxPoolSize(String poolsize) {
		try {
			int size = Integer.parseInt(poolsize);
			if (size <= 0) {
				throw new AppRuntimeException("Max pool size must be greater than Zero");
			}
			return size;
		} catch (AppRuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new AppRuntimeException("Invalid max pool size");
		}
	}

	/**
	 * getBaseDir
	 * 
	 * @return
	 */
	public static String getBaseDir() {
		try {
			return System.getProperty("user.dir") + File.separator + "cache-files";
		} catch (Exception e) {
			throw new AppRuntimeException("Invalid domain");
		}
	}
}
