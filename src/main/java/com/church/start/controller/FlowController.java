package com.church.start.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.church.start.domain.Extension;
import com.church.start.domain.ResponseEntity;
import com.church.start.service.FlowService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FlowController {

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private FlowService service;

	@RequestMapping(value="/start", method=RequestMethod.GET) 
	public ModelAndView test() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("Index");
	    return mav;
	}

	@GetMapping("/get")
	public List<Extension> getAllList() {
		return service.getAllList();
	}

	@PostMapping("/update")
	public ResponseEntity save(@RequestBody @Valid Extension extension, BindingResult bindingresult)
			throws IOException {
		/* JPA validate 통과 시 save 호출 */
		if (bindingresult.hasErrors()) {
			return ResponseEntity.builder().resCd(ResponseEntity.ResponseCode.ETC_FAIL)
					.resMesg(bindingresult.getAllErrors().toString()).build();
		} else {
			return service.save(extension);
		}
	}

	@PostMapping("/delete")
	public ResponseEntity delete(@RequestBody @Valid Extension extension, BindingResult bindingresult)
			throws IOException {

		if (bindingresult.hasErrors()) {
			return ResponseEntity.builder().resCd(ResponseEntity.ResponseCode.ETC_FAIL)
					.resMesg(bindingresult.getAllErrors().toString()).build();
		} else {
			return service.delete(extension);
		}
	}
}
