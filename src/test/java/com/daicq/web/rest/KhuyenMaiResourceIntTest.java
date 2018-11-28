package com.daicq.web.rest;

import com.daicq.BookDemoApp;

import com.daicq.domain.KhuyenMai;
import com.daicq.repository.KhuyenMaiRepository;
import com.daicq.service.KhuyenMaiService;
import com.daicq.service.dto.KhuyenMaiDTO;
import com.daicq.service.mapper.KhuyenMaiMapper;
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
 * Test class for the KhuyenMaiResource REST controller.
 *
 * @see KhuyenMaiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookDemoApp.class)
public class KhuyenMaiResourceIntTest {

    private static final String DEFAULT_TIEU_DE = "AAAAAAAAAA";
    private static final String UPDATED_TIEU_DE = "BBBBBBBBBB";

    private static final String DEFAULT_NOI_DUNG = "AAAAAAAAAA";
    private static final String UPDATED_NOI_DUNG = "BBBBBBBBBB";

    private static final String DEFAULT_LOAI_AP_DUNG = "AAAAAAAAAA";
    private static final String UPDATED_LOAI_AP_DUNG = "BBBBBBBBBB";

    private static final Instant DEFAULT_TIME_START = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME_START = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_TIME_END = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME_END = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_MUC_KM = 1F;
    private static final Float UPDATED_MUC_KM = 2F;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private KhuyenMaiRepository khuyenMaiRepository;

    @Autowired
    private KhuyenMaiMapper khuyenMaiMapper;
    
    @Autowired
    private KhuyenMaiService khuyenMaiService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restKhuyenMaiMockMvc;

    private KhuyenMai khuyenMai;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final KhuyenMaiResource khuyenMaiResource = new KhuyenMaiResource(khuyenMaiService);
        this.restKhuyenMaiMockMvc = MockMvcBuilders.standaloneSetup(khuyenMaiResource)
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
    public static KhuyenMai createEntity() {
        KhuyenMai khuyenMai = new KhuyenMai()
            .tieuDe(DEFAULT_TIEU_DE)
            .noiDung(DEFAULT_NOI_DUNG)
            .loaiApDung(DEFAULT_LOAI_AP_DUNG)
            .timeStart(DEFAULT_TIME_START)
            .timeEnd(DEFAULT_TIME_END)
            .mucKM(DEFAULT_MUC_KM)
            .status(DEFAULT_STATUS);
        return khuyenMai;
    }

    @Before
    public void initTest() {
        mockAuthentication();
        khuyenMaiRepository.deleteAll();
        khuyenMai = createEntity();
    }

    @Test
    public void createKhuyenMai() throws Exception {
        int databaseSizeBeforeCreate = khuyenMaiRepository.findAll().size();

        // Create the KhuyenMai
        KhuyenMaiDTO khuyenMaiDTO = khuyenMaiMapper.toDto(khuyenMai);
        restKhuyenMaiMockMvc.perform(post("/api/khuyen-mais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khuyenMaiDTO)))
            .andExpect(status().isCreated());

        // Validate the KhuyenMai in the database
        List<KhuyenMai> khuyenMaiList = khuyenMaiRepository.findAll();
        assertThat(khuyenMaiList).hasSize(databaseSizeBeforeCreate + 1);
        KhuyenMai testKhuyenMai = khuyenMaiList.get(khuyenMaiList.size() - 1);
        assertThat(testKhuyenMai.getTieuDe()).isEqualTo(DEFAULT_TIEU_DE);
        assertThat(testKhuyenMai.getNoiDung()).isEqualTo(DEFAULT_NOI_DUNG);
        assertThat(testKhuyenMai.getLoaiApDung()).isEqualTo(DEFAULT_LOAI_AP_DUNG);
        assertThat(testKhuyenMai.getTimeStart()).isEqualTo(DEFAULT_TIME_START);
        assertThat(testKhuyenMai.getTimeEnd()).isEqualTo(DEFAULT_TIME_END);
        assertThat(testKhuyenMai.getMucKM()).isEqualTo(DEFAULT_MUC_KM);
        assertThat(testKhuyenMai.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    public void createKhuyenMaiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = khuyenMaiRepository.findAll().size();

        // Create the KhuyenMai with an existing ID
        khuyenMai.setId("existing_id");
        KhuyenMaiDTO khuyenMaiDTO = khuyenMaiMapper.toDto(khuyenMai);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKhuyenMaiMockMvc.perform(post("/api/khuyen-mais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khuyenMaiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KhuyenMai in the database
        List<KhuyenMai> khuyenMaiList = khuyenMaiRepository.findAll();
        assertThat(khuyenMaiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkTieuDeIsRequired() throws Exception {
        int databaseSizeBeforeTest = khuyenMaiRepository.findAll().size();
        // set the field null
        khuyenMai.setTieuDe(null);

        // Create the KhuyenMai, which fails.
        KhuyenMaiDTO khuyenMaiDTO = khuyenMaiMapper.toDto(khuyenMai);

        restKhuyenMaiMockMvc.perform(post("/api/khuyen-mais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khuyenMaiDTO)))
            .andExpect(status().isBadRequest());

        List<KhuyenMai> khuyenMaiList = khuyenMaiRepository.findAll();
        assertThat(khuyenMaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllKhuyenMais() throws Exception {
        // Initialize the database
        khuyenMaiRepository.save(khuyenMai);

        // Get all the khuyenMaiList
        restKhuyenMaiMockMvc.perform(get("/api/khuyen-mais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(khuyenMai.getId())))
            .andExpect(jsonPath("$.[*].tieuDe").value(hasItem(DEFAULT_TIEU_DE.toString())))
            .andExpect(jsonPath("$.[*].noiDung").value(hasItem(DEFAULT_NOI_DUNG.toString())))
            .andExpect(jsonPath("$.[*].loaiApDung").value(hasItem(DEFAULT_LOAI_AP_DUNG.toString())))
            .andExpect(jsonPath("$.[*].timeStart").value(hasItem(DEFAULT_TIME_START.toString())))
            .andExpect(jsonPath("$.[*].timeEnd").value(hasItem(DEFAULT_TIME_END.toString())))
            .andExpect(jsonPath("$.[*].mucKM").value(hasItem(DEFAULT_MUC_KM.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    public void getKhuyenMai() throws Exception {
        // Initialize the database
        khuyenMaiRepository.save(khuyenMai);

        // Get the khuyenMai
        restKhuyenMaiMockMvc.perform(get("/api/khuyen-mais/{id}", khuyenMai.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(khuyenMai.getId()))
            .andExpect(jsonPath("$.tieuDe").value(DEFAULT_TIEU_DE.toString()))
            .andExpect(jsonPath("$.noiDung").value(DEFAULT_NOI_DUNG.toString()))
            .andExpect(jsonPath("$.loaiApDung").value(DEFAULT_LOAI_AP_DUNG.toString()))
            .andExpect(jsonPath("$.timeStart").value(DEFAULT_TIME_START.toString()))
            .andExpect(jsonPath("$.timeEnd").value(DEFAULT_TIME_END.toString()))
            .andExpect(jsonPath("$.mucKM").value(DEFAULT_MUC_KM.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    public void getNonExistingKhuyenMai() throws Exception {
        // Get the khuyenMai
        restKhuyenMaiMockMvc.perform(get("/api/khuyen-mais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateKhuyenMai() throws Exception {
        // Initialize the database
        khuyenMaiRepository.save(khuyenMai);

        int databaseSizeBeforeUpdate = khuyenMaiRepository.findAll().size();

        // Update the khuyenMai
        KhuyenMai updatedKhuyenMai = khuyenMaiRepository.findById(khuyenMai.getId()).get();
        updatedKhuyenMai
            .tieuDe(UPDATED_TIEU_DE)
            .noiDung(UPDATED_NOI_DUNG)
            .loaiApDung(UPDATED_LOAI_AP_DUNG)
            .timeStart(UPDATED_TIME_START)
            .timeEnd(UPDATED_TIME_END)
            .mucKM(UPDATED_MUC_KM)
            .status(UPDATED_STATUS);
        KhuyenMaiDTO khuyenMaiDTO = khuyenMaiMapper.toDto(updatedKhuyenMai);

        restKhuyenMaiMockMvc.perform(put("/api/khuyen-mais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khuyenMaiDTO)))
            .andExpect(status().isOk());

        // Validate the KhuyenMai in the database
        List<KhuyenMai> khuyenMaiList = khuyenMaiRepository.findAll();
        assertThat(khuyenMaiList).hasSize(databaseSizeBeforeUpdate);
        KhuyenMai testKhuyenMai = khuyenMaiList.get(khuyenMaiList.size() - 1);
        assertThat(testKhuyenMai.getTieuDe()).isEqualTo(UPDATED_TIEU_DE);
        assertThat(testKhuyenMai.getNoiDung()).isEqualTo(UPDATED_NOI_DUNG);
        assertThat(testKhuyenMai.getLoaiApDung()).isEqualTo(UPDATED_LOAI_AP_DUNG);
        assertThat(testKhuyenMai.getTimeStart()).isEqualTo(UPDATED_TIME_START);
        assertThat(testKhuyenMai.getTimeEnd()).isEqualTo(UPDATED_TIME_END);
        assertThat(testKhuyenMai.getMucKM()).isEqualTo(UPDATED_MUC_KM);
        assertThat(testKhuyenMai.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    public void updateNonExistingKhuyenMai() throws Exception {
        int databaseSizeBeforeUpdate = khuyenMaiRepository.findAll().size();

        // Create the KhuyenMai
        KhuyenMaiDTO khuyenMaiDTO = khuyenMaiMapper.toDto(khuyenMai);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKhuyenMaiMockMvc.perform(put("/api/khuyen-mais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khuyenMaiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KhuyenMai in the database
        List<KhuyenMai> khuyenMaiList = khuyenMaiRepository.findAll();
        assertThat(khuyenMaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteKhuyenMai() throws Exception {
        // Initialize the database
        khuyenMaiRepository.save(khuyenMai);

        int databaseSizeBeforeDelete = khuyenMaiRepository.findAll().size();

        // Get the khuyenMai
        restKhuyenMaiMockMvc.perform(delete("/api/khuyen-mais/{id}", khuyenMai.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<KhuyenMai> khuyenMaiList = khuyenMaiRepository.findAll();
        assertThat(khuyenMaiList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KhuyenMai.class);
        KhuyenMai khuyenMai1 = new KhuyenMai();
        khuyenMai1.setId("id1");
        KhuyenMai khuyenMai2 = new KhuyenMai();
        khuyenMai2.setId(khuyenMai1.getId());
        assertThat(khuyenMai1).isEqualTo(khuyenMai2);
        khuyenMai2.setId("id2");
        assertThat(khuyenMai1).isNotEqualTo(khuyenMai2);
        khuyenMai1.setId(null);
        assertThat(khuyenMai1).isNotEqualTo(khuyenMai2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KhuyenMaiDTO.class);
        KhuyenMaiDTO khuyenMaiDTO1 = new KhuyenMaiDTO();
        khuyenMaiDTO1.setId("id1");
        KhuyenMaiDTO khuyenMaiDTO2 = new KhuyenMaiDTO();
        assertThat(khuyenMaiDTO1).isNotEqualTo(khuyenMaiDTO2);
        khuyenMaiDTO2.setId(khuyenMaiDTO1.getId());
        assertThat(khuyenMaiDTO1).isEqualTo(khuyenMaiDTO2);
        khuyenMaiDTO2.setId("id2");
        assertThat(khuyenMaiDTO1).isNotEqualTo(khuyenMaiDTO2);
        khuyenMaiDTO1.setId(null);
        assertThat(khuyenMaiDTO1).isNotEqualTo(khuyenMaiDTO2);
    }
}
