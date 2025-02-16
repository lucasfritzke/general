package br.com.beatrizcarmo.service.impl;

import br.com.beatrizcarmo.dto.BookDto;
import br.com.beatrizcarmo.dto.mapper.BookMapper;
import br.com.beatrizcarmo.exceptions.NotFoundException;
import br.com.beatrizcarmo.exceptions.WrongParametersException;
import br.com.beatrizcarmo.models.Book;
import br.com.beatrizcarmo.models.User;
import br.com.beatrizcarmo.repository.BookRepository;
import br.com.beatrizcarmo.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	public BookServiceImpl bookService;

	@Mock
	public UserRepository userRepository;

	@Mock
	private BookMapper bookMapper;

	public User user;
	public Book book;

	@Before
	public void setUp() {
		user = new User();
		book = new Book();
	}
	//==========> Exemplo video curso testes unitários
	@Test
	public void checkBookingPossibility_shouldBePossible() {
		BookServiceImpl service = new BookServiceImpl();
		User user = new User();
		user.setIsPunished(false);

		Book book = new Book();
		book.setIsBorrowed(false);

		boolean result = service.checkBookingPossibility(user, book);

		assertEquals(true, result);;
	}
	//==========> Exemplos video curso mockito
	@Test
	public void verifyIfBookIsBorrowed_shouldBookIsPresent() {
		//Arrange
		UUID id = UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b334");
		book.setIsBorrowed(false);

		when(bookRepository.findById(id)).thenReturn(Optional.of(book));

		//Act
		boolean result = bookService.verifyIfBookIsBorrowed(id);

		//Assert
		Assertions.assertThat(result).isFalse();
	}
	@Test
	public void verifyIfBookIsBorrowed_shouldBookIsNotPresent() {
		//Arrange
		UUID id = UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b334");

		when(bookRepository.findById(id)).thenReturn(Optional.empty());

		//Act
		Throwable exception = catchThrowable(() -> bookService.verifyIfBookIsBorrowed(id));

		//Assert
		Assertions.assertThat(exception).isInstanceOf(NotFoundException.class).hasMessage("The object was not found");
	}
	@Test
	public void verifyIfIsPossibleToBuyBookWithValue_shouldBookIsPresentAndValueGreaterBookCost() {
		//Arrange
		UUID id = UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b334");
		book.setCost(50.0f);

		when(bookRepository.findBookById(id)).thenReturn(Optional.of(book));

		//Act
		boolean result = bookService.verifyIfIsPossibleToBuyBookWithValue(id, 60.0f);

		//Assert
		Assertions.assertThat(result).isTrue();
	}
	@Test
	public void verifyIfIsPossibleToBuyBookWithValue_shouldBookIsPresentAndValueLowerBookCost() {
		//Arrange
		UUID id = UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b334");
		book.setCost(50.0f);

		when(bookRepository.findBookById(id)).thenReturn(Optional.of(book));

		//Act
		boolean result = bookService.verifyIfIsPossibleToBuyBookWithValue(id, 40.0f);

		//Assert
		Assertions.assertThat(result).isFalse();
	}
	@Test
	public void verifyIfIsPossibleToBuyBookWithValue_shouldBookIsNotPresent() {
		//Arrange
		UUID id = UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b334");

		when(bookRepository.findBookById(id)).thenReturn(Optional.empty());

		//Act
		Throwable exception = catchThrowable(() -> bookService.verifyIfIsPossibleToBuyBookWithValue(id, 60.0f));

		//Assert
		Assertions.assertThat(exception).isInstanceOf(NotFoundException.class).hasMessage("The object was not found");
	}
	@Test
	public void deletBook_shouldBookIsPresent() {
		//Arrange
		UUID id = UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b334");

		when(bookRepository.findById(id)).thenReturn(Optional.of(book));

		//Act
		bookService.deletBook(id);

		//Assert
		verify(bookRepository).findById(UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b334"));
		verify(bookRepository).delete(book);
	}
	@Test
	public void deletBook_shouldBookIsNotPresent() {
		//Arrange
		UUID id = UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b334");

		when(bookRepository.findById(id)).thenReturn(Optional.empty());

		//Act
		Throwable exception = catchThrowable(() -> bookService.deletBook(id));

		//Asser
		verify(bookRepository).findById(id);
		assertThat(exception).isInstanceOf(NotFoundException.class).hasMessage("The object was not found");
	}



	//====================== Exercícios - Mockito ===========================
	@Test
	public void lendBookToUser_shouldBookIsPresentAndUserIsNotPunished() {
		//Arrange
		UUID idBook = UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b334");
		UUID idUser= UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b335");
		book.setIsBorrowed(false);
		user.setIsPunished(false);

		when(bookRepository.findById(idBook)).thenReturn(Optional.of(book));
		when(userRepository.findById(idUser)).thenReturn(Optional.of(user));

		//Act + Assert
		bookService.lendBookToUser(idUser, idBook);
		verify(bookRepository).findById(idBook);
		verify(bookRepository).save(book);
	}

	@Test
	public void lendBookToUser_shouldBookIsPresentAndUserIsPunished() {
		//Arrange
		UUID idBook = UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b334");
		UUID idUser= UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b335");
		book.setIsBorrowed(false);
		user.setIsPunished(true);

		when(bookRepository.findById(idBook)).thenReturn(Optional.of(book));
		when(userRepository.findById(idUser)).thenReturn(Optional.of(user));

		//Act
		IllegalArgumentException ae = assertThrows(IllegalArgumentException.class, () -> bookService.lendBookToUser(idUser, idBook));
		assertEquals("O usuário não está autorizado para pegar novos livros", ae.getMessage());

		//Assert
		verify(bookRepository).findById(idBook);
	}


	@Test
	public void lendBookToUser_shouldBookNotPresentAndUserIsPunished() {
		//Arrange
		UUID idBook = UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b334");
		UUID idUser= UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b335");
		book.setIsBorrowed(true);
		user.setIsPunished(true);

		when(bookRepository.findById(idBook)).thenReturn(Optional.of(book));
		when(userRepository.findById(idUser)).thenReturn(Optional.of(user));

		//Act + Assert
		IllegalArgumentException ae = assertThrows(IllegalArgumentException.class, () -> bookService.lendBookToUser(idUser, idBook));
		assertEquals("Livro já foi emprestado", ae.getMessage());
		verify(bookRepository).findById(idBook);
	}


	@Test
	public void updateBookPriceAccordingYearEdition_shouldBookIsPresent() {
		//Arrange
		UUID id = UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b334");
		book.setYearEdition(LocalDate.now().minusYears(10));
		book.setCost(50.0f);

		when(bookRepository.findById(id)).thenReturn(Optional.of(book));

		//Act
		bookService.updateBookPriceAccordingYearEdition(id);

		//Assert
		assertEquals(45.1f, book.getCost(), 0.1);
		verify(bookRepository).findById(id);
		verify(bookRepository).save(book);
	}

	@Test
	public void updateBookPriceAccordingYearEdition_shouldYearIsNull() {
		//Arrange
		UUID id = UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b334");
		book.setYearEdition(null);
		book.setCost(50.0f);

		//Act
		when(bookRepository.findById(id)).thenReturn(Optional.of(book));


		//Assert
		IllegalArgumentException ae = assertThrows(IllegalArgumentException.class, () -> bookService.updateBookPriceAccordingYearEdition(id));
		assertEquals("Ano de lançamento não encontrado", ae.getMessage());
		verify(bookRepository).findById(id);
	}

	@Test
	public void updateBookPriceAccordingYearEdition_shouldCostIsNull() {
		//Arrange
		UUID id = UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b334");
		book.setYearEdition(null);
		book.setYearEdition(LocalDate.now().minusYears(10));
		book.setCost(null);
		//Act
		when(bookRepository.findById(id)).thenReturn(Optional.of(book));

		//Assert
		IllegalArgumentException ae = assertThrows(IllegalArgumentException.class, () -> bookService.updateBookPriceAccordingYearEdition(id));
		assertEquals("Custo do livro não encontrado", ae.getMessage());
		verify(bookRepository).findById(id);
	}

	@Test
	public void insertBook_shouldBookIsPresent() {
		//Arrange
		BookDto dto = new BookDto();
		dto.author = "Author";
		dto.name = "Name";
		book.setName(dto.name);
		book.setAuthor(dto.author);

		//Act
		when(bookRepository.save(book)).thenReturn(book);
		when(bookMapper.toEntity(dto)).thenReturn(book);
		when(bookMapper.toDto(book)).thenReturn(dto);
		BookDto bookresult =  bookService.insertBook(dto);

		verify(bookRepository).save(book);
		assertEquals(bookresult.author, book.getAuthor());
		assertEquals(bookresult.name, book.getName());
	}

	@Test
	public void insertBook_shouldBookIsNotPresent() {
		//Arrange
		BookDto dto = new BookDto();
		dto.author = "Author";
		dto.name = "";

		assertThrows(NotFoundException.class, () -> bookService.insertBook(dto));

	}
	@Test
	public void insertBook_shouldBookIsNotPresent02() {
		//Arrange
		BookDto dto = new BookDto();
		dto.author = "";
		dto.name = "nanem";

		assertThrows(NotFoundException.class, () -> bookService.insertBook(dto));

	}

	@Test
	public void getBooks_shouldReturnListOfBookDtos() {
		// Arrange
		BookDto bookDto = new BookDto();
		List<Book> books = Arrays.asList(book);
		List<BookDto> bookDtos = Arrays.asList(bookDto);

		when(bookRepository.findAll()).thenReturn(books);
		when(bookMapper.toDto(books)).thenReturn(bookDtos);

		// Act
		List<BookDto> result = bookService.getBooks();

		// Assert
		assertThat(result).isEqualTo(bookDtos);
		verify(bookRepository).findAll();
		verify(bookMapper).toDto(books);
	}

	@Test
	public void getBookById_shouldReturnBookDtoWhenBookIsPresent() {
		// Arrange
		BookDto bookDto = new BookDto();
		UUID bookId = book.getId();
		when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
		when(bookMapper.toDto(book)).thenReturn(bookDto);

		// Act
		BookDto result = bookService.getBookById(bookId);

		// Assert
		assertThat(result).isEqualTo(bookDto);
		verify(bookRepository).findById(bookId);
		verify(bookMapper).toDto(book);
	}

	@Test
	public void getBookById_shouldThrowNotFoundExceptionWhenBookIsNotPresent() {
		// Arrange

		UUID bookId = UUID.randomUUID();
		when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

		// Act
		Throwable thrown = catchThrowable(() -> bookService.getBookById(bookId));

		// Assert
		assertThat(thrown).isInstanceOf(NotFoundException.class);
		verify(bookRepository).findById(bookId);
	}

	@Test
	public void updateBook_shouldUpdateBookWhenBookIsPresent() {
		// Arrange
		UUID id = UUID.randomUUID();
		BookDto bookDto = new BookDto();
		bookDto.name = "Name";
		bookDto.author = "Author";
		bookDto.id = id.toString();

		when(bookRepository.findById(id)).thenReturn(Optional.of(book));
		when(bookRepository.save(book)).thenReturn(book);
		when(bookMapper.toDto(book)).thenReturn(bookDto);

		BookDto result = bookService.updateBook(bookDto, id);

		assertEquals(bookDto, result);
		verify(bookRepository).findById(id);
		verify(bookRepository).save(book);

	}

	@Test
	public void updateBook_shouldThrowNotFoundExceptionWhenBookIsNotPresent() {
		// Arrange
		UUID id = UUID.randomUUID();
		BookDto bookDto = new BookDto();
		bookDto.name = "";
		bookDto.author = "";
		bookDto.id = id.toString();

		// Act
		Throwable thrown = catchThrowable(() -> bookService.updateBook(bookDto, id));

		// Assert
		assertThat(thrown).isInstanceOf(WrongParametersException.class);
	}

	@Test
	public void removeUserLoans_shouldRemoveLoansWhenUserHasLoans() {

		UUID userId = UUID.randomUUID();
		book.setUser(user);
		book.setDevolutionDate(LocalDate.now());
		List<Book> books = Arrays.asList(book);

		when(bookRepository.findAll()).thenReturn(books);
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));


		bookService.removeUserLoans(userId);


		assertThat(book.getUser()).isNull();
		assertThat(book.getDevolutionDate()).isNull();
		verify(bookRepository).findAll();
		verify(userRepository).findById(userId);
		verify(bookRepository).save(book);
	}

	@Test
	public void removeUserLoans_shouldThrowExceptionWhenUserHasNoLoans() {
		// Arrange
		UUID userId = user.getId();
		List<Book> books = Arrays.asList(book);

		when(bookRepository.findAll()).thenReturn(books);
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));


		Throwable thrown = catchThrowable(() -> bookService.removeUserLoans(userId));


		assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Não há nenhum livro emprestado para esse usuário");
		verify(bookRepository).findAll();
		verify(userRepository).findById(userId);
	}
}
