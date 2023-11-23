package be.bstorm.ylorth.demospecificationsjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface BookEntityRepository extends JpaRepository<BookEntity, Long>, JpaSpecificationExecutor<BookEntity> {
    Optional<BookEntity> findByAuthor(String author);
    Optional<BookEntity> findByTitleAndPublicationYear(String title, int year);
    
    
}