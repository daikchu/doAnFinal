package com.daicq.web.rest;

import com.daicq.BookDemoApp;

import com.daicq.domain.QuangCao;
import com.daicq.repository.QuangCaoRepository;
import com.daicq.service.QuangCaoService;
import com.daicq.service.dto.QuangCaoDTO;
import com.daicq.service.mapper.QuangCaoMapper;
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

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

    import static com.daicq.web.rest.TestUtil.mockAuthentication;

import static com.daicq.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the QuangCaoResource REST controller.
 *
 * @see QuangCaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookDemoApp.class)
public class QuangCaoResourceIntTest {

    private static final String DEFAULT_TIEU_DE = "AAAAAAAAAA";
    private static final String UPDATED_TIEU_DE = "BBBBBBBBBB";

    private static final String DEFAULT_NOI_DUNG = "AAAAAAAAAA";
    private static final String UPDATED_NOI_DUNG = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    private static final Instant DEFAULT_TIME_START = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME_START = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_TIME_END = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME_END = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

    @Autowired
    private QuangCaoRepository quangCaoRepository;

    @Autowired
    private QuangCaoMapper quangCaoMapper;
    
    @Autowired
    private QuangCaoService quangCaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restQuangCaoMockMvc;

    private QuangCao quangCao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QuangCaoResource quangCaoResource = new QuangCaoResource(quangCaoService);
        this.restQuangCaoMockMvc = MockMvcBuilders.standaloneSetup(quangCaoResource)
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
    public static QuangCao createEntity() {
        QuangCao quangCao = new QuangCao()
            .tieuDe(DEFAULT_TIEU_DE)
            .noiDung(DEFAULT_NOI_DUNG)
            .imageUrl(DEFAULT_IMAGE_URL)
            .timeStart(DEFAULT_TIME_START)
            .timeEnd(DEFAULT_TIME_END)
            .link(DEFAULT_LINK);
        return quangCao;
    }

    @Before
    public void initTest() {
        mockAuthentication();
        quangCaoRepository.deleteAll();
        quangCao = createEntity();
    }

    @Test
    public void createQuangCao() throws Exception {
        int databaseSizeBeforeCreate = quangCaoRepository.findAll().size();

        // Create the QuangCao
        QuangCaoDTO quangCaoDTO = quangCaoMapper.toDto(quangCao);
        restQuangCaoMockMvc.perform(post("/api/quang-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quangCaoDTO)))
            .andExpect(status().isCreated());

        // Validate the QuangCao in the database
        List<QuangCao> quangCaoList = quangCaoRepository.findAll();
        assertThat(quangCaoList).hasSize(databaseSizeBeforeCreate + 1);
        QuangCao testQuangCao = quangCaoList.get(quangCaoList.size() - 1);
        assertThat(testQuangCao.getTieuDe()).isEqualTo(DEFAULT_TIEU_DE);
        assertThat(testQuangCao.getNoiDung()).isEqualTo(DEFAULT_NOI_DUNG);
        assertThat(testQuangCao.getImageUrl()).isEqualTo(DEFAULT_IMAGE_URL);
        assertThat(testQuangCao.getTimeStart()).isEqualTo(DEFAULT_TIME_START);
        assertThat(testQuangCao.getTimeEnd()).isEqualTo(DEFAULT_TIME_END);
        assertThat(testQuangCao.getLink()).isEqualTo(DEFAULT_LINK);
    }

    @Test
    public void createQuangCaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quangCaoRepository.findAll().size();

        // Create the QuangCao with an existing ID
        quangCao.setId("existing_id");
        QuangCaoDTO quangCaoDTO = quangCaoMapper.toDto(quangCao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuangCaoMockMvc.perform(post("/api/quang-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quangCaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuangCao in the database
        List<QuangCao> quangCaoList = quangCaoRepository.findAll();
        assertThat(quangCaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkTieuDeIsRequired() throws Exception {
        int databaseSizeBeforeTest = quangCaoRepository.findAll().size();
        // set the field null
        quangCao.setTieuDe(null);

        // Create the QuangCao, which fails.
        QuangCaoDTO quangCaoDTO = quangCaoMapper.toDto(quangCao);

        restQuangCaoMockMvc.perform(post("/api/quang-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quangCaoDTO)))
            .andExpect(status().isBadRequest());

        List<QuangCao> quangCaoList = quangCaoRepository.findAll();
        assertThat(quangCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllQuangCaos() throws Exception {
        // Initialize the database
        quangCaoRepository.save(quangCao);

        // Get all the quangCaoList
        restQuangCaoMockMvc.perform(get("/api/quang-caos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quangCao.getId())))
            .andExpect(jsonPath("$.[*].tieuDe").value(hasItem(DEFAULT_TIEU_DE.toString())))
            .andExpect(jsonPath("$.[*].noiDung").value(hasItem(DEFAULT_NOI_DUNG.toString())))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL.toString())))
            .andExpect(jsonPath("$.[*].timeStart").value(hasItem(DEFAULT_TIME_START.toString())))
            .andExpect(jsonPath("$.[*].timeEnd").value(hasItem(DEFAULT_TIME_END.toString())))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK.toString())));
    }
    
    @Test
    public void getQuangCao() throws Exception {
        // Initialize the database
        quangCaoRepository.save(quangCao);

        // Get the quangCao
        restQuangCaoMockMvc.perform(get("/api/quang-caos/{id}", quangCao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quangCao.getId()))
            .andExpect(jsonPath("$.tieuDe").value(DEFAULT_TIEU_DE.toString()))
            .andExpect(jsonPath("$.noiDung").value(DEFAULT_NOI_DUNG.toString()))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL.toString()))
            .andExpect(jsonPath("$.timeStart").value(DEFAULT_TIME_START.toString()))
            .andExpect(jsonPath("$.timeEnd").value(DEFAULT_TIME_END.toString()))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK.toString()));
    }

    @Test
    public void getNonExistingQuangCao() throws Exception {
        // Get the quangCao
        restQuangCaoMockMvc.perform(get("/api/quang-caos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateQuangCao() throws Exception {
        // Initialize the database
        quangCaoRepository.save(quangCao);

        int databaseSizeBeforeUpdate = quangCaoRepository.findAll().size();

        // Update the quangCao
        QuangCao updatedQuangCao = quangCaoRepository.findById(quangCao.getId()).get();
        updatedQuangCao
            .tieuDe(UPDATED_TIEU_DE)
            .noiDung(UPDATED_NOI_DUNG)
            .imageUrl(UPDATED_IMAGE_URL)
            .timeStart(UPDATED_TIME_START)
            .timeEnd(UPDATED_TIME_END)
            .link(UPDATED_LINK);
        QuangCaoDTO quangCaoDTO = quangCaoMapper.toDto(updatedQuangCao);

        restQuangCaoMockMvc.perform(put("/api/quang-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quangCaoDTO)))
            .andExpect(status().isOk());

        // Validate the QuangCao in the database
        List<QuangCao> quangCaoList = quangCaoRepository.findAll();
        assertThat(quangCaoList).hasSize(databaseSizeBeforeUpdate);
        QuangCao testQuangCao = quangCaoList.get(quangCaoList.size() - 1);
        assertThat(testQuangCao.getTieuDe()).isEqualTo(UPDATED_TIEU_DE);
        assertThat(testQuangCao.getNoiDung()).isEqualTo(UPDATED_NOI_DUNG);
        assertThat(testQuangCao.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
        assertThat(testQuangCao.getTimeStart()).isEqualTo(UPDATED_TIME_START);
        assertThat(testQuangCao.getTimeEnd()).isEqualTo(UPDATED_TIME_END);
        assertThat(testQuangCao.getLink()).isEqualTo(UPDATED_LINK);
    }

    @Test
    public void updateNonExistingQuangCao() throws Exception {
        int databaseSizeBeforeUpdate = quangCaoRepository.findAll().size();

        // Create the QuangCao
        QuangCaoDTO quangCaoDTO = quangCaoMapper.toDto(quangCao);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuangCaoMockMvc.perform(put("/api/quang-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quangCaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuangCao in the database
        List<QuangCao> quangCaoList = quangCaoRepository.findAll();
        assertThat(quangCaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteQuangCao() throws Exception {
        // Initialize the database
        quangCaoRepository.save(quangCao);

        int databaseSizeBeforeDelete = quangCaoRepository.findAll().size();

        // Get the quangCao
        restQuangCaoMockMvc.perform(delete("/api/quang-caos/{id}", quangCao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<QuangCao> quangCaoList = quangCaoRepository.findAll();
        assertThat(quangCaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuangCao.class);
        QuangCao quangCao1 = new QuangCao();
        quangCao1.setId("id1");
        QuangCao quangCao2 = new QuangCao();
        quangCao2.setId(quangCao1.getId());
        assertThat(quangCao1).isEqualTo(quangCao2);
        quangCao2.setId("id2");
        assertThat(quangCao1).isNotEqualTo(quangCao2);
        quangCao1.setId(null);
        assertThat(quangCao1).isNotEqualTo(quangCao2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuangCaoDTO.class);
        QuangCaoDTO quangCaoDTO1 = new QuangCaoDTO();
        quangCaoDTO1.setId("id1");
        QuangCaoDTO quangCaoDTO2 = new QuangCaoDTO();
        assertThat(quangCaoDTO1).isNotEqualTo(quangCaoDTO2);
        quangCaoDTO2.setId(quangCaoDTO1.getId());
        assertThat(quangCaoDTO1).isEqualTo(quangCaoDTO2);
        quangCaoDTO2.setId("id2");
        assertThat(quangCaoDTO1).isNotEqualTo(quangCaoDTO2);
        quangCaoDTO1.setId(null);
        assertThat(quangCaoDTO1).isNotEqualTo(quangCaoDTO2);
    }
}
