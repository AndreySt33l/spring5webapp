package org.asteel.spring5webapp.bootstrap;

import org.asteel.spring5webapp.model.Author;
import org.asteel.spring5webapp.model.Book;
import org.asteel.spring5webapp.model.Publisher;
import org.asteel.spring5webapp.repositories.AuthorRepository;
import org.asteel.spring5webapp.repositories.BookRepository;
import org.asteel.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    public void initData(){
        Publisher publisher1 = new Publisher();
        publisher1.setName("Publisher name1");

        publisherRepository.save(publisher1);

        Author author1 = new Author("Leo", "Tolstoy");
        Book book1 = new Book("World & Piece", "1306", publisher1);
        author1.getBooks().add(book1);
        book1.getAuthors().add(author1);

        authorRepository.save(author1);
        bookRepository.save(book1);

        Publisher publisher2 = new Publisher();
        publisher2.setName("Publisher name2");

        publisherRepository.save(publisher2);

        Author author2 = new Author("Alexander", "Pushkin");
        Book book2 = new Book("Eugeniy Onegin", "1387", publisher2);
        author2.getBooks().add(book1);
        book2.getAuthors().add(author1);

        authorRepository.save(author2);
        bookRepository.save(book2);
    }
}
