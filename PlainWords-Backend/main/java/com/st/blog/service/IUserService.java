package com.st.blog.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.st.blog.beans.User;
import com.st.blog.exceptions.UserNotFound;
@Component
public interface IUserService {
	public User registerUser(User user);
	public User getUserById(Long id) throws UserNotFound;
	public User updateUser(Long id, User updatedUser) throws UserNotFound;
	public User deleteUserById(Long userId) throws UserNotFound;
//	public List<User> getUserPosts(Long userId);
	
}
