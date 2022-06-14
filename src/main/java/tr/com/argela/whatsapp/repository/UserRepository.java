package tr.com.argela.whatsapp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;

import tr.com.argela.whatsapp.entity.User;



public interface UserRepository extends JpaRepository<User, Long>{
    
    @Modifying
    @Transactional
    @Query(value = "Select u from User u where u.phone = :phone and u.password = :password")
    public List<User> login(@Param("username") String username, @Param("password") String password);
}
