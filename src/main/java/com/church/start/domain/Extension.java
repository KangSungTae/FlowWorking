package com.church.start.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Extension {
	
    @Id 
    @Size(max=20 , message = "확장자를 20자 이내로 입력해주세요.")
    public String ext_id; 
    
    @Size(max=1 , message = "Y / N 값만 입력이 가능합니다.")
	public String main_yn;
    
    @Size(max=1 , message = "Y / N 값만 입력이 가능합니다.")
	public String check_yn;
	
    public String reg_dtm;
	public String upt_dtm;
}