package de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.repository;

import de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
}
