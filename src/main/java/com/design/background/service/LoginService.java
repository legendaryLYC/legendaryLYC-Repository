package com.design.background.service;

import com.design.background.entity.LeadingUser;

public interface LoginService {

	LeadingUser selectUser(String tel,String password);	
}
