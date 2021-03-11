package wiele.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import wiele.springframework.spring5webapp.domain.Author;
import wiele.springframework.spring5webapp.domain.Book;
import wiele.springframework.spring5webapp.domain.Publisher;
import wiele.springframework.spring5webapp.repositories.AuthorRepository;
import wiele.springframework.spring5webapp.repositories.BookRepository;
import wiele.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository,
                         BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("ST Petersburg");
        publisher.setState("FL");
        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "91837521");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "91837732");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        Publisher mitp = new Publisher("mitp","Hasenstrasse 13", "Hammerstadt", "Ambossen", "73815");
        publisherRepository.save(mitp);

        Publisher heise = new Publisher("Heise", "Ankerstrasse 23", "Oldenburg", "Niedersachsen", "26123");
        publisherRepository.save(heise);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Publisher Number of Books: " +publisher.getBooks().size());
        System.out.println("Number of Books: " + bookRepository.count());
    }
}
