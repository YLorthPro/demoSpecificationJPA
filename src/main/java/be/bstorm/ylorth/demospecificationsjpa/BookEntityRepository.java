package be.bstorm.ylorth.demospecificationsjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * BookEntityRepository is an interface that extends JpaRepository and JpaSpecificationExecutor. It provides methods for retrieving BookEntity objects from the database.
 *
 * Note: 
 * The methods findByAuthor(String author) and findByTitleAndPublicationYear(String title, int year) are no longer necessary and will be deprecated in the future releases.
 *
 * @see JpaRepository
 * @see JpaSpecificationExecutor
 */
public interface BookEntityRepository extends JpaRepository<BookEntity, Long>, JpaSpecificationExecutor<BookEntity> {
    
    // Plus nécessaire
    Optional<BookEntity> findByAuthor(String author);
    Optional<BookEntity> findByTitleAndPublicationYear(String title, int year);
    
    
}