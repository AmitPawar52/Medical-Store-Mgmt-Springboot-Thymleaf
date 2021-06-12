package com.store.medical.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class CustomService {

	private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Date changeDateFormat(String dt) throws ParseException {
		Date date = sdf.parse(dt);
		return date;
	}
}
