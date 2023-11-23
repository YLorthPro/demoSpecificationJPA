package be.bstorm.ylorth.demospecificationsjpa;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    
    private final BookEntityRepository bookEntityRepository;

    public BookController(BookEntityRepository bookEntityRepository) {
        this.bookEntityRepository = bookEntityRepository;
    }

    /**
     * Retrieves a book entity based on its title.
     *
     * @param title The title of the book entity.
     * @return The book entity with the specified title.
     */
    @GetMapping("/one")
    public BookEntity getOneByTitle(@RequestParam String title){

        Specification<BookEntity> spec = ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("title"),title));
        
        return bookEntityRepository.findOne(spec).get();
    }
    
    /**
     * Retrieves a list of book entities based on the search criteria specified in the
     * given BookSearchForm.
     *
     * @param form The BookSearchForm object containing the search criteria.
     * @return A list of book entities that satisfy the search criteria.
     */
    @GetMapping("/allWithSpec")
    public List<BookEntity> getAllBySpec(@RequestBody BookSearchForm form){
        Specification<BookEntity> spec = createSpecification(form);
        return bookEntityRepository.findAll(spec);
    }

    /**
     * Creates a Specification object based on the search criteria specified in the given BookSearchForm.
     *
     * @param form The BookSearchForm object containing the search criteria.
     * @return The Specification object representing the search criteria.
     */
    private Specification<BookEntity> createSpecification(BookSearchForm form) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (form.getTitle() != null)
                predicates.add(criteriaBuilder.equal(root.get("title"),form.getTitle()));

            if (form.getAuthor() != null)
                predicates.add(criteriaBuilder.equal(root.get("author"), form.getAuthor()));

            if (form.getPublicationYear() != 0)
                predicates.add(criteriaBuilder.equal(root.get("publicationYear"), form.getPublicationYear()));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
    
}
