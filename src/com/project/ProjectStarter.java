package com.project;

import java.util.Scanner;

import com.project.exception.AppRuntimeException;
import com.project.helper.ArgUtil;

/**
 * Execution starts from here
 * 
 * @author shivam
 *
 */
public class ProjectStarter {

	public static void main(String[] args) throws Exception {
		try {
			String domain = ArgUtil.getDomain(args);

			Scanner in = new Scanner(System.in);
			System.out.println("Enter access-token: ");
			String accessToken = in.nextLine();

			System.out.println("Enter max-pool-size: ");
			int maxPoolSize = ArgUtil.getMaxPoolSize(in.nextLine());

			String baseDirectory = ArgUtil.getBaseDir();

			System.out.println("executing... ");
			System.out.println("");
			System.out.println("===========================================");
			System.out.println("EntityName [fieldName1,fieldName2,........]");
			System.out.println("===========================================");
			new ProjectContext(domain, accessToken, maxPoolSize, baseDirectory).start();

			in.close();
		} catch (AppRuntimeException e) {
			System.out.println(e);
		}
	}

}
