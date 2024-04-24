package tn.enicarthage.services;

import java.util.List;
import java.util.Optional;

import tn.enicarthage.entities.User;

public interface IUserService {
       public void ajouterUser(User u);
       public void supprimerUserById(int id);
       public void modifierUser(User u);
       public List<User> listUsers();
       public Optional<User> getUserLogged(String username,String passwd);

}
