package com.prs.db;

import com.prs.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prs.business.*;

public interface LineItemRepo extends JpaRepository<LineItem, Integer> {

	List<LineItem> findByRequestId(int id);






	

}
