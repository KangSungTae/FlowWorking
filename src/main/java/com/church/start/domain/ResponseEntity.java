package com.church.start.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
 
@Builder
@Getter
@Setter
public class ResponseEntity {
	
	public class ResponseCode{
		public static final String SUCCESS = "0000";
		public static final String FAIL_REASON1 = "0001";
		public static final String DUPLICATE = "0002";
		public static final String ETC_FAIL = "9999"; 
	}
	
	public class ResponseMesg{
		public static final String SUCCESS = "성공";
		public static final String FAIL_REASON1 = "저장 가능 개수가 초과되었습니다.";
		public static final String DUPLICATE = "중복된 데이터가 존재합니다.";
		public static final String ETC_FAIL = "실패"; 
	}
	
	private String resCd;
	private String resMesg;
	
}
