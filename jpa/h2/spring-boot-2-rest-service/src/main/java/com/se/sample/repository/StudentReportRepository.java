package com.se.sample.repository;

import com.se.sample.entity.StudentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentReportRepository extends JpaRepository<StudentReport, Long> {

   List<StudentReport> findAllByStudentId(long studentId);

}