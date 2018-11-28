package com.daicq.web.rest;

import com.daicq.BookDemoApp;

import com.daicq.domain.Book;
import com.daicq.repository.BookRepository;
import com.daicq.service.BookService;
import com.daicq.service.dto.BookDTO;
import com.daicq.service.mapper.BookMapper;
import com.daicq.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

    import static com.daicq.web.rest.TestUtil.mockAuthentication;

import static com.daicq.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BookResource REST controller.
 *
 * @see BookResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookDemoApp.class)
public class BookResourceIntTest {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_TAC_GIA = "AAAAAAAAAA";
    private static final String UPDATED_TAC_GIA = "BBBBBBBBBB";

    private static final String DEFAULT_NHA_XB = "AAAAAAAAAA";
    private static final String UPDATED_NHA_XB = "BBBBBBBBBB";

    private static final String DEFAULT_NAM_XB = "AAAAAAAAAA";
    private static final String UPDATED_NAM_XB = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final Float DEFAULT_GIA_NHAP = 1F;
    private static final Float UPDATED_GIA_NHAP = 2F;

    private static final Float DEFAULT_GIA_BAN_GOC = 1F;
    private static final Float UPDATED_GIA_BAN_GOC = 2F;

    private static final Float DEFAULT_GIA_KM = 1F;
    private static final Float UPDATED_GIA_KM = 2F;

    private static final String DEFAULT_LOAI = "AAAAAAAAAA";
    private static final String UPDATED_LOAI = "BBBBBBBBBB";

    private static final Long DEFAULT_SO_LUONG_CON = 1L;
    private static final Long UPDATED_SO_LUONG_CON = 2L;

    private static final Long DEFAULT_SO_LUONG_NHAP = 1L;
    private static final Long UPDATED_SO_LUONG_NHAP = 2L;

    private static final Long DEFAULT_SO_LAN_XEM = 1L;
    private static final Long UPDATED_SO_LAN_XEM = 2L;

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;
    
    @Autowired
    private BookService bookService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restBookMockMvc;

    private Book book;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BookResource bookResource = new BookResource(bookService);
        this.restBookMockMvc = MockMvcBuilders.standaloneSetup(bookResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Book createEntity() {
        Book book = new Book()
            .ten(DEFAULT_TEN)
            .imageUrl(DEFAULT_IMAGE_URL)
            .tacGia(DEFAULT_TAC_GIA)
            .nhaXB(DEFAULT_NHA_XB)
            .namXB(DEFAULT_NAM_XB)
            .moTa(DEFAULT_MO_TA)
            .giaNhap(DEFAULT_GIA_NHAP)
            .giaBanGoc(DEFAULT_GIA_BAN_GOC)
            .giaKM(DEFAULT_GIA_KM)
            .loai(DEFAULT_LOAI)
            .soLuongCon(DEFAULT_SO_LUONG_CON)
            .soLuongNhap(DEFAULT_SO_LUONG_NHAP)
            .soLanXem(DEFAULT_SO_LAN_XEM)
            .note(DEFAULT_NOTE);
        return book;
    }

    @Before
    public void initTest() {
        mockAuthentication();
        bookRepository.deleteAll();
        book = createEntity();
    }

    @Test
    public void createBook() throws Exception {
        int databaseSizeBeforeCreate = bookRepository.findAll().size();

        // Create the Book
        BookDTO bookDTO = bookMapper.toDto(book);
        restBookMockMvc.perform(post("/api/books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookDTO)))
            .andExpect(status().isCreated());

        // Validate the Book in the database
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeCreate + 1);
        Book testBook = bookList.get(bookList.size() - 1);
        assertThat(testBook.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testBook.getImageUrl()).isEqualTo(DEFAULT_IMAGE_URL);
        assertThat(testBook.getTacGia()).isEqualTo(DEFAULT_TAC_GIA);
        assertThat(testBook.getNhaXB()).isEqualTo(DEFAULT_NHA_XB);
        assertThat(testBook.getNamXB()).isEqualTo(DEFAULT_NAM_XB);
        assertThat(testBook.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testBook.getGiaNhap()).isEqualTo(DEFAULT_GIA_NHAP);
        assertThat(testBook.getGiaBanGoc()).isEqualTo(DEFAULT_GIA_BAN_GOC);
        assertThat(testBook.getGiaKM()).isEqualTo(DEFAULT_GIA_KM);
        assertThat(testBook.getLoai()).isEqualTo(DEFAULT_LOAI);
        assertThat(testBook.getSoLuongCon()).isEqualTo(DEFAULT_SO_LUONG_CON);
        assertThat(testBook.getSoLuongNhap()).isEqualTo(DEFAULT_SO_LUONG_NHAP);
        assertThat(testBook.getSoLanXem()).isEqualTo(DEFAULT_SO_LAN_XEM);
        assertThat(testBook.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    public void createBookWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bookRepository.findAll().size();

        // Create the Book with an existing ID
        book.setId("existing_id");
        BookDTO bookDTO = bookMapper.toDto(book);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBookMockMvc.perform(post("/api/books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Book in the database
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkTenIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookRepository.findAll().size();
        // set the field null
        book.setTen(null);

        // Create the Book, which fails.
        BookDTO bookDTO = bookMapper.toDto(book);

        restBookMockMvc.perform(post("/api/books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookDTO)))
            .andExpect(status().isBadRequest());

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllBooks() throws Exception {
        // Initialize the database
        bookRepository.save(book);

        // Get all the bookList
        restBookMockMvc.perform(get("/api/books?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(book.getId())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN.toString())))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL.toString())))
            .andExpect(jsonPath("$.[*].tacGia").value(hasItem(DEFAULT_TAC_GIA.toString())))
            .andExpect(jsonPath("$.[*].nhaXB").value(hasItem(DEFAULT_NHA_XB.toString())))
            .andExpect(jsonPath("$.[*].namXB").value(hasItem(DEFAULT_NAM_XB.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].giaNhap").value(hasItem(DEFAULT_GIA_NHAP.doubleValue())))
            .andExpect(jsonPath("$.[*].giaBanGoc").value(hasItem(DEFAULT_GIA_BAN_GOC.doubleValue())))
            .andExpect(jsonPath("$.[*].giaKM").value(hasItem(DEFAULT_GIA_KM.doubleValue())))
            .andExpect(jsonPath("$.[*].loai").value(hasItem(DEFAULT_LOAI.toString())))
            .andExpect(jsonPath("$.[*].soLuongCon").value(hasItem(DEFAULT_SO_LUONG_CON.intValue())))
            .andExpect(jsonPath("$.[*].soLuongNhap").value(hasItem(DEFAULT_SO_LUONG_NHAP.intValue())))
            .andExpect(jsonPath("$.[*].soLanXem").value(hasItem(DEFAULT_SO_LAN_XEM.intValue())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())));
    }
    
    @Test
    public void getBook() throws Exception {
        // Initialize the database
        bookRepository.save(book);

        // Get the book
        restBookMockMvc.perform(get("/api/books/{id}", book.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(book.getId()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN.toString()))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL.toString()))
            .andExpect(jsonPath("$.tacGia").value(DEFAULT_TAC_GIA.toString()))
            .andExpect(jsonPath("$.nhaXB").value(DEFAULT_NHA_XB.toString()))
            .andExpect(jsonPath("$.namXB").value(DEFAULT_NAM_XB.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.giaNhap").value(DEFAULT_GIA_NHAP.doubleValue()))
            .andExpect(jsonPath("$.giaBanGoc").value(DEFAULT_GIA_BAN_GOC.doubleValue()))
            .andExpect(jsonPath("$.giaKM").value(DEFAULT_GIA_KM.doubleValue()))
            .andExpect(jsonPath("$.loai").value(DEFAULT_LOAI.toString()))
            .andExpect(jsonPath("$.soLuongCon").value(DEFAULT_SO_LUONG_CON.intValue()))
            .andExpect(jsonPath("$.soLuongNhap").value(DEFAULT_SO_LUONG_NHAP.intValue()))
            .andExpect(jsonPath("$.soLanXem").value(DEFAULT_SO_LAN_XEM.intValue()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()));
    }

    @Test
    public void getNonExistingBook() throws Exception {
        // Get the book
        restBookMockMvc.perform(get("/api/books/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBook() throws Exception {
        // Initialize the database
        bookRepository.save(book);

        int databaseSizeBeforeUpdate = bookRepository.findAll().size();

        // Update the book
        Book updatedBook = bookRepository.findById(book.getId()).get();
        updatedBook
            .ten(UPDATED_TEN)
            .imageUrl(UPDATED_IMAGE_URL)
            .tacGia(UPDATED_TAC_GIA)
            .nhaXB(UPDATED_NHA_XB)
            .namXB(UPDATED_NAM_XB)
            .moTa(UPDATED_MO_TA)
            .giaNhap(UPDATED_GIA_NHAP)
            .giaBanGoc(UPDATED_GIA_BAN_GOC)
            .giaKM(UPDATED_GIA_KM)
            .loai(UPDATED_LOAI)
            .soLuongCon(UPDATED_SO_LUONG_CON)
            .soLuongNhap(UPDATED_SO_LUONG_NHAP)
            .soLanXem(UPDATED_SO_LAN_XEM)
            .note(UPDATED_NOTE);
        BookDTO bookDTO = bookMapper.toDto(updatedBook);

        restBookMockMvc.perform(put("/api/books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookDTO)))
            .andExpect(status().isOk());

        // Validate the Book in the database
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeUpdate);
        Book testBook = bookList.get(bookList.size() - 1);
        assertThat(testBook.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testBook.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
        assertThat(testBook.getTacGia()).isEqualTo(UPDATED_TAC_GIA);
        assertThat(testBook.getNhaXB()).isEqualTo(UPDATED_NHA_XB);
        assertThat(testBook.getNamXB()).isEqualTo(UPDATED_NAM_XB);
        assertThat(testBook.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testBook.getGiaNhap()).isEqualTo(UPDATED_GIA_NHAP);
        assertThat(testBook.getGiaBanGoc()).isEqualTo(UPDATED_GIA_BAN_GOC);
        assertThat(testBook.getGiaKM()).isEqualTo(UPDATED_GIA_KM);
        assertThat(testBook.getLoai()).isEqualTo(UPDATED_LOAI);
        assertThat(testBook.getSoLuongCon()).isEqualTo(UPDATED_SO_LUONG_CON);
        assertThat(testBook.getSoLuongNhap()).isEqualTo(UPDATED_SO_LUONG_NHAP);
        assertThat(testBook.getSoLanXem()).isEqualTo(UPDATED_SO_LAN_XEM);
        assertThat(testBook.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    public void updateNonExistingBook() throws Exception {
        int databaseSizeBeforeUpdate = bookRepository.findAll().size();

        // Create the Book
        BookDTO bookDTO = bookMapper.toDto(book);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBookMockMvc.perform(put("/api/books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Book in the database
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteBook() throws Exception {
        // Initialize the database
        bookRepository.save(book);

        int databaseSizeBeforeDelete = bookRepository.findAll().size();

        // Get the book
        restBookMockMvc.perform(delete("/api/books/{id}", book.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Book.class);
        Book book1 = new Book();
        book1.setId("id1");
        Book book2 = new Book();
        book2.setId(book1.getId());
        assertThat(book1).isEqualTo(book2);
        book2.setId("id2");
        assertThat(book1).isNotEqualTo(book2);
        book1.setId(null);
        assertThat(book1).isNotEqualTo(book2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BookDTO.class);
        BookDTO bookDTO1 = new BookDTO();
        bookDTO1.setId("id1");
        BookDTO bookDTO2 = new BookDTO();
        assertThat(bookDTO1).isNotEqualTo(bookDTO2);
        bookDTO2.setId(bookDTO1.getId());
        assertThat(bookDTO1).isEqualTo(bookDTO2);
        bookDTO2.setId("id2");
        assertThat(bookDTO1).isNotEqualTo(bookDTO2);
        bookDTO1.setId(null);
        assertThat(bookDTO1).isNotEqualTo(bookDTO2);
    }
}
