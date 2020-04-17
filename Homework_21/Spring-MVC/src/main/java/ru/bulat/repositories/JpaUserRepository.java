package ru.bulat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bulat.model.User;

@Repository
interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository{
    User findByEmail(String email);

}
