package tn.enicarthage.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicarthage.entities.User;
import tn.enicarthage.repositories.UserRepository;


@Service
public class UserService implements IUserService{
	   
	   @Autowired
	   UserRepository userRepository;
	   
	   @Override
	   public void ajouterUser(User u) {
    	   userRepository.save(u);
       }
	   @Override
	   public void modifierUser(User u) {
    	   userRepository.save(u);
       }
	   @Override
	   public void supprimerUserById(int id) {
    	   userRepository.deleteById((long) id);
       }
	   
	   @Override
	   public List<User> listUsers(){

			 List<User> l = (List<User>) userRepository.findAll();
			 return l;
	   }
	   
	   @Override
	   public Optional<User> getUserLogged(String username,String passwd){
		   	return userRepository.findByUsernameAndPassword(username, passwd);	 
	   }
	   
}
