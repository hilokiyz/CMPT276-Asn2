package sfu.cmpt276.asn2.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{
    //find a list (camel case IS IMPORTANT)
    List<User> findByGPA(double GPA);
    List<User> findByHeightAndWeight(int height, int weight);
}
