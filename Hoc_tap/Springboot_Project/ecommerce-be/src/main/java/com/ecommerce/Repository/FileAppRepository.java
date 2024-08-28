package com.ecommerce.Repository;

import com.ecommerce.Entity.FileApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface FileAppRepository extends JpaRepository<FileApp, Long> {

    List<FileApp> findAllById(Long fileId);
}
