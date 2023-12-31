package com.ssafy.Schedule.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.Schedule.model.Schedule;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	List<Schedule> findAllByworkdateBetweenAndWorktimeNot(LocalDate startDate, LocalDate endDate, String worktime);
	List<Schedule> findByNurseIDAndWorkdateBetweenAndWorktimeNot(long nurseID, LocalDate startDate, LocalDate endDate, String worktime);
	List<Schedule> findByHospitalIDAndWorkdateBetweenAndWorktimeNot(long hospitalID, LocalDate startDate, LocalDate endDate, String worktime);
	List<Schedule> findByNurseIDAndWorkdateBetween(long id, LocalDate startDate, LocalDate endDate);
	List<Schedule> findByHospitalIDAndWorkdateBetweenAndWorktimeNotAndNurseIDNot(long hospitalID, LocalDate startDate,
			LocalDate endDate, String string, long id);
	Optional<Schedule> findByNurseIDAndWorkdate(long giveID, LocalDate localDate);
}
