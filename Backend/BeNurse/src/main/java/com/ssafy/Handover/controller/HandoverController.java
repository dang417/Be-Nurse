package com.ssafy.Handover.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.Handover.model.Handover;
import com.ssafy.Handover.service.HandoverRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*")
@Api(value = "인계장 API", tags = { "인계장." })
@RestController
@RequestMapping("/api/benurse/Handover")
public class HandoverController {

	@Autowired
	HandoverRepository handoverRepo;
	
	// 인계자 인계장 작성 POST
	@PostMapping("/create")
	@ApiOperation(value = "인계자 인계장 작성", notes = "인계장을 작성하여 DB에 등록, 등록된 인계장 ID를 반환")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = Handover.class),
		@ApiResponse(code = 404, message = "결과 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<Handover> registHandover(Handover handover) {
		
		handover.setTime(LocalDateTime.now());
		
		Handover savedHandover = handoverRepo.save(handover);
		return new ResponseEntity<>(savedHandover, HttpStatus.OK);
	}
	
	
	// 인계자 인수자 등록 POST
//	@PostMapping("/regitaker")
//	@ApiOperation(value = "인계자 인수자 등록", notes = "등록한 인계장을 인수할 인수자 선택")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "성공", response = Handover.class),
//		@ApiResponse(code = 404, message = "결과 없음"),
//		@ApiResponse(code = 500, message = "서버 오류")
//	})
//	public ResponseEntity<Handover> registHandover(Handover handover) {
//		
//		handover.setTime(LocalDateTime.now());
//		
//		Handover savedHandover = handoverRepo.save(handover);
//		return new ResponseEntity<>(savedHandover, HttpStatus.OK);
//	}
	
	// 인수자 인계장 조회 GET
	@GetMapping("/{id}")
	@ApiOperation(value = "인수자 인계장 조회", notes = "인계장 ID를 통해 인계장 내용 조회") 
	@ApiResponses({
	    @ApiResponse(code = 200, message = "성공", response = Handover.class),
	    @ApiResponse(code = 404, message = "인계장을 찾을 수 없음"),
	    @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<Handover> getHandoverById(@RequestParam("ID") long ID) {
	    Optional<Handover> handover = handoverRepo.findById(ID);

	    if (handover.isPresent())
	        return new ResponseEntity<>(handover.get(), HttpStatus.OK);
	    else
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
