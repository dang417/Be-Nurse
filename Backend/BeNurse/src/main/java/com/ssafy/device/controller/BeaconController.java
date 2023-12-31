package com.ssafy.device.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ssafy.common.utils.APIResponse;
import com.ssafy.device.model.Beacon;
import com.ssafy.device.service.BeaconRepository;
import com.ssafy.device.service.BeaconService;
import com.ssafy.nurse.model.Nurse;
import com.ssafy.oauth.serivce.OauthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*")
@Api(value = "비콘 API", tags = { "비콘." })
@RestController
@RequestMapping("/api/benurse/beacon")
public class BeaconController {

	@Autowired
	BeaconRepository beaconRepo;
	
	@Autowired
	BeaconService beaconServ;
	
	@Autowired
	OauthService oauthService;
	
	// 비콘 등록 POST
	@PostMapping("")
	@ApiOperation(value = "비콘 등록", notes = "비콘을 등록한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = Beacon.class),
		@ApiResponse(code = 404, message = "결과 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public APIResponse<Beacon> registBeacon(@RequestHeader("Authorization") String token, @RequestBody Beacon beacon) {
		Nurse nurse;
		// 사용자 조회
		try {
			nurse = oauthService.getUser(token);
		}catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		if(!nurse.isAdmin())
			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
		
	    Beacon savedBeacon = beaconRepo.save(beacon);
	    return new APIResponse<>(savedBeacon, HttpStatus.OK);
	}
	
	// 비콘 정보 수정 PUT
	@PutMapping("")
	@ApiOperation(value = "비콘 정보 수정", notes = "비콘 정보 수정") 
	@ApiResponses({
	    @ApiResponse(code = 200, message = "성공", response = Beacon.class),
	    @ApiResponse(code = 404, message = "게시글을 찾을 수 없음"),
	    @ApiResponse(code = 500, message = "서버 오류")
	})
	public APIResponse<Beacon> updateBeaconById(@RequestHeader("Authorization") String token, @RequestBody Beacon updatedBeacon){
		Nurse nurse;
		// 사용자 조회
		try {
			nurse = oauthService.getUser(token);
		}catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		if(!nurse.isAdmin())
			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
		
		try {
			// 업데이트된 병원 정보를 저장
			Beacon savedBeacon = beaconServ.save(updatedBeacon);
	        return new APIResponse<>(savedBeacon, HttpStatus.OK);
	    }catch (Exception e) {
	    	e.printStackTrace();
	    	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	    }
	    
	}
	
	// 비콘 삭제 DELETE
	@DeleteMapping("")
	@ApiOperation(value = "비콘 삭제", notes = "비콘을 삭제한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = Beacon.class),
		@ApiResponse(code = 404, message = "결과 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public APIResponse<Void> deleteBeaconById(@RequestHeader("Authorization") String token, @RequestParam("ID") String ID) {
		Nurse nurse;
		// 사용자 조회
		try {
			nurse = oauthService.getUser(token);
		}catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		if(!nurse.isAdmin())
			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
		
		try {
	    	beaconServ.delete(ID);
			return new APIResponse<>(HttpStatus.OK);
	    }catch (Exception e) {
	    	e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	    }
	}
	
	// 전체 바콘 조회 GET
	@GetMapping("/all")
	@ApiOperation(value = "전체 비콘 조회", notes = "모든 비콘을 조회한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공", response = Beacon.class),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public APIResponse<List<Beacon>> getAllBeacon(@RequestHeader("Authorization") String token) {
		Nurse nurse;
		// 사용자 조회
		try {
			nurse = oauthService.getUser(token);
		}catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		List<Beacon> beacon = beaconRepo.findAllByHospitalID(nurse.getHospitalID());
	    return new APIResponse<>(beacon, HttpStatus.OK);
	}
	
	// 특정 비콘 조회 GET
	@GetMapping("")
	@ApiOperation(value = "특정 비콘 조회", notes = "비콘 ID로 특정 비콘 조회") 
	@ApiResponses({
	    @ApiResponse(code = 200, message = "성공", response = Beacon.class),
	    @ApiResponse(code = 404, message = "비콘을 찾을 수 없음"),
	    @ApiResponse(code = 500, message = "서버 오류")
	})
	public APIResponse<Beacon> getBeaconById(@RequestParam("ID") String ID) {
		try {
			Beacon beacon = beaconServ.findById(ID);
			return new APIResponse<>(beacon, HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
	    	throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
		}
	}
}
