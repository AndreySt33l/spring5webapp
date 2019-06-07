package org.asteel.spring5webapp.bootstrap;

import org.asteel.spring5webapp.model.Author;
import org.asteel.spring5webapp.model.Book;
import org.asteel.spring5webapp.repositories.AuthorRepository;
import org.asteel.spring5webapp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    public void initData(){
        Author author1 = new Author("Leo", "Tolstoy");
        Book book1 = new Book("World & Piece", "1306", "Harper Colins");
        author1.getBooks().add(book1);
        book1.getAuthors().add(author1);

        authorRepository.save(author1);
        bookRepository.save(book1);

        Author author2 = new Author("Alexander", "Pushkin");
        Book book2 = new Book("Eugeniy Onegin", "1387", "Russian Lit");
        author2.getBooks().add(book1);
        book2.getAuthors().add(author1);

        authorRepository.save(author2);
        bookRepository.save(book2);
    }
}
