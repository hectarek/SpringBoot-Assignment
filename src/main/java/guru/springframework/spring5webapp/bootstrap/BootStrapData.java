package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Publisher nyp = new Publisher("New York Publishing", "123 Manhattan St.", "New York City", "NY", 12312);
        Book ddd = new Book("Domain Driven Design", "123123", nyp);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(nyp);
        
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(nyp);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "393939565", nyp);
        eric.getBooks().add(noEJB);
        ddd.getAuthors().add(rod);
        
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(nyp);

        System.out.println("Started in Bootstrap:");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + nyp.getBooks().size());

    }

}