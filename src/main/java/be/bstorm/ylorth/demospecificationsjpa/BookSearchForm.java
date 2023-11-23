package be.bstorm.ylorth.demospecificationsjpa;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookSearchForm {
    private String title;

    private String author;

    private int publicationYear;
}
