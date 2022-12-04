package com.example.practic_boot.repository;

import com.example.practic_boot.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {


}
