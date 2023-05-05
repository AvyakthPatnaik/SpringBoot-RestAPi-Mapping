package com.nexential.springBoot.service;

import java.util.List;

import com.nexential.springBoot.entity.User;

public interface userService {

	List<User>findPaginated(int pageNo,int PageSize);
	User update(User user);
}
