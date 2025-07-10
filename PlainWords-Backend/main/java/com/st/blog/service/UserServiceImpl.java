package com.st.blog.service;

import com.st.blog.beans.User;
import com.st.blog.exceptions.UserNotFound;
import com.st.blog.service.IUserService;
import com.st.blog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
    private  UserRepository userRepository;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
    
    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    @Override
    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        return userRepository.save(existingUser);
    }

	@Override
	public User deleteUserById(Long userId) throws UserNotFound {
		Optional<User> findById=userRepository.findById(userId);
		if(findById.isEmpty())
			throw new UserNotFound("Appointment not found by appointmentId: "+userId);
		
		userRepository.deleteById(userId);
		return findById.get();
	}

//    public List<User> getUserPosts(Long userId) {
//        return userRepository.findByUserId(userId);
//    }
}