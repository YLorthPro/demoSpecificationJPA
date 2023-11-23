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

    @GetMapping("/one")
    public BookEntity getOneByTitle(@RequestParam String title){

        Specification<BookEntity> spec = ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("title"),title));
        
        return bookEntityRepository.findOne(spec).get();
    }
    
    @GetMapping("/allWithSpec")
    public List<BookEntity> getAllBySpec(@RequestBody BookSearchForm form){
        
        Specification<BookEntity> spec = ((root, query, criteriaBuilder) -> {
            
            List<Predicate> predicates = new ArrayList<>();
            
            if(form.getTitle()!=null)
                predicates.add(criteriaBuilder.equal(root.get("title"),form.getTitle()));
            
            if(form.getAuthor()!= null)
                predicates.add(criteriaBuilder.equal(root.get("author"), form.getAuthor()));
            
            if(form.getPublicationYear()!=0)
                predicates.add(criteriaBuilder.equal(root.get("publicationYear"), form.getPublicationYear()));
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            
        });
        
        
        
        return bookEntityRepository.findAll(spec);
    }
    
}
