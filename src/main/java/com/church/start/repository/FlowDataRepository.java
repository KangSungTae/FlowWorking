package com.church.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.church.start.domain.Extension;

public interface FlowDataRepository extends JpaRepository<Extension, String>{

}
