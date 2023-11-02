package com.ssafy.Handover.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.Handover.model.MyHandover;

@Repository
public interface MyHandoverRepository extends JpaRepository<MyHandover, Long> {

	List<MyHandover> findAllByTakeID(long id);

}
