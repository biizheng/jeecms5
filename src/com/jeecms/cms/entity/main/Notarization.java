package com.jeecms.cms.entity.main;

import com.jeecms.cms.entity.main.base.BaseNotarization;

public class Notarization extends BaseNotarization{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int POSITON_FIRST=0;
	public static final int POSITON_MIDDLE=1;
	public static final int POSITON_LAST=2;
	
	public void init(){
		setN1(null);
		setN2(null);
		setN3(null);
		setN4(null);
		setN5(null);
		setN6(null);
		setN7(null);
		setN8(null);
	}

}
