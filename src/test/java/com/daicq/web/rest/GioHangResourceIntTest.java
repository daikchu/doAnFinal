package com.daicq.web.rest;

import com.daicq.BookDemoApp;

import com.daicq.domain.GioHang;
import com.daicq.repository.GioHangRepository;
import com.daicq.service.GioHangService;
import com.daicq.service.dto.GioHangDTO;
import com.daicq.service.mapper.GioHangMapper;
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
 * Test class for the GioHangResource REST controller.
 *
 * @see GioHangResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookDemoApp.class)
public class GioHangResourceIntTest {

    private static final String DEFAULT_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN = "BBBBBBBBBB";

    private static final Long DEFAULT_C_TDH_ID = 1L;
    private static final Long UPDATED_C_TDH_ID = 2L;

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_UPDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_UPDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private GioHangMapper gioHangMapper;
    
    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restGioHangMockMvc;

    private GioHang gioHang;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GioHangResource gioHangResource = new GioHangResource(gioHangService);
        this.restGioHangMockMvc = MockMvcBuilders.standaloneSetup(gioHangResource)
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
    public static GioHang createEntity() {
        GioHang gioHang = new GioHang()
            .token(DEFAULT_TOKEN)
            .cTDHId(DEFAULT_C_TDH_ID)
            .userId(DEFAULT_USER_ID)
            .dateUpdate(DEFAULT_DATE_UPDATE)
            .status(DEFAULT_STATUS);
        return gioHang;
    }

    @Before
    public void initTest() {
        mockAuthentication();
        gioHangRepository.deleteAll();
        gioHang = createEntity();
    }

    @Test
    public void createGioHang() throws Exception {
        int databaseSizeBeforeCreate = gioHangRepository.findAll().size();

        // Create the GioHang
        GioHangDTO gioHangDTO = gioHangMapper.toDto(gioHang);
        restGioHangMockMvc.perform(post("/api/gio-hangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gioHangDTO)))
            .andExpect(status().isCreated());

        // Validate the GioHang in the database
        List<GioHang> gioHangList = gioHangRepository.findAll();
        assertThat(gioHangList).hasSize(databaseSizeBeforeCreate + 1);
        GioHang testGioHang = gioHangList.get(gioHangList.size() - 1);
        assertThat(testGioHang.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testGioHang.getcTDHId()).isEqualTo(DEFAULT_C_TDH_ID);
        assertThat(testGioHang.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testGioHang.getDateUpdate()).isEqualTo(DEFAULT_DATE_UPDATE);
        assertThat(testGioHang.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    public void createGioHangWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gioHangRepository.findAll().size();

        // Create the GioHang with an existing ID
        gioHang.setId("existing_id");
        GioHangDTO gioHangDTO = gioHangMapper.toDto(gioHang);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGioHangMockMvc.perform(post("/api/gio-hangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gioHangDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GioHang in the database
        List<GioHang> gioHangList = gioHangRepository.findAll();
        assertThat(gioHangList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllGioHangs() throws Exception {
        // Initialize the database
        gioHangRepository.save(gioHang);

        // Get all the gioHangList
        restGioHangMockMvc.perform(get("/api/gio-hangs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gioHang.getId())))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN.toString())))
            .andExpect(jsonPath("$.[*].cTDHId").value(hasItem(DEFAULT_C_TDH_ID.intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].dateUpdate").value(hasItem(DEFAULT_DATE_UPDATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    public void getGioHang() throws Exception {
        // Initialize the database
        gioHangRepository.save(gioHang);

        // Get the gioHang
        restGioHangMockMvc.perform(get("/api/gio-hangs/{id}", gioHang.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gioHang.getId()))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN.toString()))
            .andExpect(jsonPath("$.cTDHId").value(DEFAULT_C_TDH_ID.intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.dateUpdate").value(DEFAULT_DATE_UPDATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    public void getNonExistingGioHang() throws Exception {
        // Get the gioHang
        restGioHangMockMvc.perform(get("/api/gio-hangs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateGioHang() throws Exception {
        // Initialize the database
        gioHangRepository.save(gioHang);

        int databaseSizeBeforeUpdate = gioHangRepository.findAll().size();

        // Update the gioHang
        GioHang updatedGioHang = gioHangRepository.findById(gioHang.getId()).get();
        updatedGioHang
            .token(UPDATED_TOKEN)
            .cTDHId(UPDATED_C_TDH_ID)
            .userId(UPDATED_USER_ID)
            .dateUpdate(UPDATED_DATE_UPDATE)
            .status(UPDATED_STATUS);
        GioHangDTO gioHangDTO = gioHangMapper.toDto(updatedGioHang);

        restGioHangMockMvc.perform(put("/api/gio-hangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gioHangDTO)))
            .andExpect(status().isOk());

        // Validate the GioHang in the database
        List<GioHang> gioHangList = gioHangRepository.findAll();
        assertThat(gioHangList).hasSize(databaseSizeBeforeUpdate);
        GioHang testGioHang = gioHangList.get(gioHangList.size() - 1);
        assertThat(testGioHang.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testGioHang.getcTDHId()).isEqualTo(UPDATED_C_TDH_ID);
        assertThat(testGioHang.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testGioHang.getDateUpdate()).isEqualTo(UPDATED_DATE_UPDATE);
        assertThat(testGioHang.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    public void updateNonExistingGioHang() throws Exception {
        int databaseSizeBeforeUpdate = gioHangRepository.findAll().size();

        // Create the GioHang
        GioHangDTO gioHangDTO = gioHangMapper.toDto(gioHang);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGioHangMockMvc.perform(put("/api/gio-hangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gioHangDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GioHang in the database
        List<GioHang> gioHangList = gioHangRepository.findAll();
        assertThat(gioHangList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteGioHang() throws Exception {
        // Initialize the database
        gioHangRepository.save(gioHang);

        int databaseSizeBeforeDelete = gioHangRepository.findAll().size();

        // Get the gioHang
        restGioHangMockMvc.perform(delete("/api/gio-hangs/{id}", gioHang.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GioHang> gioHangList = gioHangRepository.findAll();
        assertThat(gioHangList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GioHang.class);
        GioHang gioHang1 = new GioHang();
        gioHang1.setId("id1");
        GioHang gioHang2 = new GioHang();
        gioHang2.setId(gioHang1.getId());
        assertThat(gioHang1).isEqualTo(gioHang2);
        gioHang2.setId("id2");
        assertThat(gioHang1).isNotEqualTo(gioHang2);
        gioHang1.setId(null);
        assertThat(gioHang1).isNotEqualTo(gioHang2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GioHangDTO.class);
        GioHangDTO gioHangDTO1 = new GioHangDTO();
        gioHangDTO1.setId("id1");
        GioHangDTO gioHangDTO2 = new GioHangDTO();
        assertThat(gioHangDTO1).isNotEqualTo(gioHangDTO2);
        gioHangDTO2.setId(gioHangDTO1.getId());
        assertThat(gioHangDTO1).isEqualTo(gioHangDTO2);
        gioHangDTO2.setId("id2");
        assertThat(gioHangDTO1).isNotEqualTo(gioHangDTO2);
        gioHangDTO1.setId(null);
        assertThat(gioHangDTO1).isNotEqualTo(gioHangDTO2);
    }
}
