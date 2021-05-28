package com.project;

public class ProjectStarter {

	public static void main(String[] args) throws Exception {
		String domain = "peoplestrong2-dev-ed.my.salesforce.com";
		String accessToken = "00D5g000005EzQm!ARIAQEYvYEoLBFV0_.pXOoyAq3pUFgZuWZe6mRoLsngU57qQ3Cslicd2equELqqktKi8mxF1YHUsOQF.ZoZjcvJ8_Uc.xqGr";
		String baseDirectory = "/home/shivam/Desktop/abc";
		int maxPoolSize = 50;
		new ProjectContext(domain, accessToken, maxPoolSize, baseDirectory).start();
	}

}
