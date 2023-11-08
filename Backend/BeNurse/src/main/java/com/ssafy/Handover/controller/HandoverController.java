package com.ssafy.Handover.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ssafy.Handover.model.Handover;
import com.ssafy.Handover.model.HandoverList;
import com.ssafy.Handover.model.HandoverPostRequest;
import com.ssafy.Handover.service.HandoverListRepository;
import com.ssafy.Handover.service.HandoverRepository;
import com.ssafy.Handover.service.HandoverService;
import com.ssafy.Handover.service.HandoverSetRepository;
import com.ssafy.Handover.service.MyHandoverRepository;
import com.ssafy.common.utils.APIResponse;
import com.ssafy.common.utils.IDRequest;

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
	
	@Autowired
	HandoverService handoverServ;
	
	@Autowired
	HandoverSetRepository handvoersetRepo;
	
	@Autowired
	HandoverListRepository listRepo;
	
	@Autowired
	MyHandoverRepository myhandoverRepo;
	
	// 인계자 인계장 작성 POST
	@PostMapping("")
	@ApiOperation(value = "인계자 인계장 작성", notes = "인계장을 작성하여 DB에 등록, 등록된 인계장 ID를 반환")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = Handover.class),
		@ApiResponse(code = 404, message = "결과 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public APIResponse<Handover> registHandover(@RequestBody HandoverPostRequest req) {
	    
		
		// 데이터베이스에 저장
	    Handover savedHandover = handoverRepo.save(req.getHandover());
	    
	    HandoverList newList = new HandoverList();
	    newList.setHandoverID(savedHandover.getID());
	    newList.setSetID(req.getSetID());
		listRepo.save(newList);

	    return new APIResponse<>(savedHandover, HttpStatus.OK);
	}

	// 인계자 인계장 수정 PUT
	@PutMapping("")
	@ApiOperation(value = "인계자 인계장 수정", notes = "인계장을 수정하여 DB에 등록, 등록된 인계장 ID를 반환")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = Handover.class),
		@ApiResponse(code = 404, message = "결과 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public APIResponse<Handover> updateHandoverById(@RequestBody Handover updatedHandover) {
		try {
			// 업데이트된 인계장 정보를 저장
	    	Handover savedHandover = handoverServ.save(updatedHandover);
	        return new APIResponse<>(savedHandover, HttpStatus.OK);
	    }catch (Exception e) {
	    	e.printStackTrace();
	    	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	    }
	}
	
	// 인수자 인계장 조회 GET
	@GetMapping("")
	@ApiOperation(value = "인수자 인계장 조회", notes = "인계장 ID를 통해 인계장 내용 조회") 
	@ApiResponses({
	    @ApiResponse(code = 200, message = "성공", response = Handover.class),
	    @ApiResponse(code = 404, message = "인계장을 찾을 수 없음"),
	    @ApiResponse(code = 500, message = "서버 오류")
	})
	public APIResponse<Handover> getHandoverById(@RequestParam("ID") long ID) {
	    try {
	    	Handover handover = handoverServ.findById(ID);
	    	return new APIResponse<>(handover, HttpStatus.OK);
	    }catch (Exception e) {
	    	e.printStackTrace();
	    	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	// 인계장 삭제 DELETE
	@DeleteMapping("")
	@ApiOperation(value = "인계장 삭제", notes = "인계장을 삭제한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = Handover.class),
		@ApiResponse(code = 404, message = "결과 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public APIResponse<Void> deleteHandoverById(@RequestBody IDRequest req) {
		try {
	    	handoverServ.delete(req.getID());
			return new APIResponse<>(HttpStatus.OK);
	    }catch (Exception e) {
	    	e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	    }
	} 
} 
