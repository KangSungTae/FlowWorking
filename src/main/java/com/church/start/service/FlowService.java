package com.church.start.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.church.start.domain.Extension;
import com.church.start.domain.ResponseEntity;
import com.church.start.repository.FlowDataRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class FlowService {
	
	private final FlowDataRepository repo;
	
	
	/* 저장 and 업데이트 */
	@Transactional
    public ResponseEntity save(Extension data) {
		
		Date time = new Date();
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
		String date = format.format(time);
		
		ResponseEntity response;
		
		/* 저장된 확장자 수가 200이 넘는다면 fail로 리턴 */
		int maximum = repo.findAll().size();

		
		if(maximum > 200) {
			response = ResponseEntity.builder()
						.resCd(ResponseEntity.ResponseCode.FAIL_REASON1)
						.resMesg(ResponseEntity.ResponseMesg.FAIL_REASON1)
						.build();
		}else {
			/* argument 이외 repo 저장을 위한 객체 신규 생성 */
			Extension ext = new Extension();
			
			/* Main 여부를 확인 후 Main확장자일 경우 (checkbox) */
			if(data.getMain_yn().equals("Y")) {
				Optional<Extension> option = repo.findById(data.getExt_id());
				ext = option.isPresent()?option.get() : ext;
				/* 업데이트 일자를 최신화 */
				ext.setUpt_dtm(date);
			}else if(!data.getMain_yn().equals("Y")){ 
			/*  Main확장자가 아닐 경우 등록일자와 Main 여부 값 세팅 후 저장 */
				ext.setReg_dtm(date);
				ext.setMain_yn("N");
			}else if(checkExtensionDuplication(data)) {
				return ResponseEntity.builder()
						.resCd(ResponseEntity.ResponseCode.DUPLICATE)
						.resMesg(ResponseEntity.ResponseMesg.DUPLICATE)
						.build();
			}
			
			ext.setExt_id(data.getExt_id());
			ext.setCheck_yn(data.getCheck_yn());
			
			Extension result = repo.save(ext);
			
			if(result != null) {
				/* 성공 코드 */
				response = ResponseEntity.builder()
						.resCd(ResponseEntity.ResponseCode.SUCCESS)
						.resMesg(ResponseEntity.ResponseMesg.SUCCESS)
						.build();
			}else {
				/* 실패 코드 - 9999로 응답 */
				response = ResponseEntity.builder()
						.resCd(ResponseEntity.ResponseCode.ETC_FAIL)
						.resMesg(ResponseEntity.ResponseMesg.ETC_FAIL)
						.build();
			}
		} 
		return response;
    }
	
	/* 화면 진입 시 최초 1번 수행 - 저장리스트 호출 */
    public List<Extension> getAllList() {
        return repo.findAll();
    }
    
    /* custom 확장자 삭제 */
    public ResponseEntity delete(Extension data) { 
    	repo.delete(data); 
    	
		return ResponseEntity.builder()
				 .resCd(ResponseEntity.ResponseCode.SUCCESS)
				 .resMesg(ResponseEntity.ResponseMesg.SUCCESS)
				 .build();
    }

	/* 중복 확인 */ 
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
	public boolean checkExtensionDuplication(Extension data) {
		boolean check = repo.existsById(data.getExt_id());
		return check;
	}

}
