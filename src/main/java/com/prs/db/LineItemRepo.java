package com.prs.db;

import com.prs.db.*;
import com.prs.business.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prs.business.LineItem;

public interface LineItemRepo extends JpaRepository<LineItem, Integer> {
	

}
