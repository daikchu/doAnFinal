package com.daicq.web.rest;

import com.daicq.BookDemoApp;

import com.daicq.domain.NhaXuatBan;
import com.daicq.repository.NhaXuatBanRepository;
import com.daicq.service.NhaXuatBanService;
import com.daicq.service.dto.NhaXuatBanDTO;
import com.daicq.service.mapper.NhaXuatBanMapper;
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
 * Test class for the NhaXuatBanResource REST controller.
 *
 * @see NhaXuatBanResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookDemoApp.class)
public class NhaXuatBanResourceIntTest {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_SDT = "AAAAAAAAAA";
    private static final String UPDATED_SDT = "BBBBBBBBBB";

    private static final Float DEFAULT_CHIET_KHAU = 1F;
    private static final Float UPDATED_CHIET_KHAU = 2F;

    @Autowired
    private NhaXuatBanRepository nhaXuatBanRepository;

    @Autowired
    private NhaXuatBanMapper nhaXuatBanMapper;
    
    @Autowired
    private NhaXuatBanService nhaXuatBanService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restNhaXuatBanMockMvc;

    private NhaXuatBan nhaXuatBan;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NhaXuatBanResource nhaXuatBanResource = new NhaXuatBanResource(nhaXuatBanService);
        this.restNhaXuatBanMockMvc = MockMvcBuilders.standaloneSetup(nhaXuatBanResource)
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
    public static NhaXuatBan createEntity() {
        NhaXuatBan nhaXuatBan = new NhaXuatBan()
            .ten(DEFAULT_TEN)
            .diaChi(DEFAULT_DIA_CHI)
            .sdt(DEFAULT_SDT)
            .chietKhau(DEFAULT_CHIET_KHAU);
        return nhaXuatBan;
    }

    @Before
    public void initTest() {
        mockAuthentication();
        nhaXuatBanRepository.deleteAll();
        nhaXuatBan = createEntity();
    }

    @Test
    public void createNhaXuatBan() throws Exception {
        int databaseSizeBeforeCreate = nhaXuatBanRepository.findAll().size();

        // Create the NhaXuatBan
        NhaXuatBanDTO nhaXuatBanDTO = nhaXuatBanMapper.toDto(nhaXuatBan);
        restNhaXuatBanMockMvc.perform(post("/api/nha-xuat-bans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhaXuatBanDTO)))
            .andExpect(status().isCreated());

        // Validate the NhaXuatBan in the database
        List<NhaXuatBan> nhaXuatBanList = nhaXuatBanRepository.findAll();
        assertThat(nhaXuatBanList).hasSize(databaseSizeBeforeCreate + 1);
        NhaXuatBan testNhaXuatBan = nhaXuatBanList.get(nhaXuatBanList.size() - 1);
        assertThat(testNhaXuatBan.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testNhaXuatBan.getDiaChi()).isEqualTo(DEFAULT_DIA_CHI);
        assertThat(testNhaXuatBan.getSdt()).isEqualTo(DEFAULT_SDT);
        assertThat(testNhaXuatBan.getChietKhau()).isEqualTo(DEFAULT_CHIET_KHAU);
    }

    @Test
    public void createNhaXuatBanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nhaXuatBanRepository.findAll().size();

        // Create the NhaXuatBan with an existing ID
        nhaXuatBan.setId("existing_id");
        NhaXuatBanDTO nhaXuatBanDTO = nhaXuatBanMapper.toDto(nhaXuatBan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNhaXuatBanMockMvc.perform(post("/api/nha-xuat-bans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhaXuatBanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NhaXuatBan in the database
        List<NhaXuatBan> nhaXuatBanList = nhaXuatBanRepository.findAll();
        assertThat(nhaXuatBanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkTenIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhaXuatBanRepository.findAll().size();
        // set the field null
        nhaXuatBan.setTen(null);

        // Create the NhaXuatBan, which fails.
        NhaXuatBanDTO nhaXuatBanDTO = nhaXuatBanMapper.toDto(nhaXuatBan);

        restNhaXuatBanMockMvc.perform(post("/api/nha-xuat-bans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhaXuatBanDTO)))
            .andExpect(status().isBadRequest());

        List<NhaXuatBan> nhaXuatBanList = nhaXuatBanRepository.findAll();
        assertThat(nhaXuatBanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllNhaXuatBans() throws Exception {
        // Initialize the database
        nhaXuatBanRepository.save(nhaXuatBan);

        // Get all the nhaXuatBanList
        restNhaXuatBanMockMvc.perform(get("/api/nha-xuat-bans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhaXuatBan.getId())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN.toString())))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI.toString())))
            .andExpect(jsonPath("$.[*].sdt").value(hasItem(DEFAULT_SDT.toString())))
            .andExpect(jsonPath("$.[*].chietKhau").value(hasItem(DEFAULT_CHIET_KHAU.doubleValue())));
    }
    
    @Test
    public void getNhaXuatBan() throws Exception {
        // Initialize the database
        nhaXuatBanRepository.save(nhaXuatBan);

        // Get the nhaXuatBan
        restNhaXuatBanMockMvc.perform(get("/api/nha-xuat-bans/{id}", nhaXuatBan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nhaXuatBan.getId()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN.toString()))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI.toString()))
            .andExpect(jsonPath("$.sdt").value(DEFAULT_SDT.toString()))
            .andExpect(jsonPath("$.chietKhau").value(DEFAULT_CHIET_KHAU.doubleValue()));
    }

    @Test
    public void getNonExistingNhaXuatBan() throws Exception {
        // Get the nhaXuatBan
        restNhaXuatBanMockMvc.perform(get("/api/nha-xuat-bans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateNhaXuatBan() throws Exception {
        // Initialize the database
        nhaXuatBanRepository.save(nhaXuatBan);

        int databaseSizeBeforeUpdate = nhaXuatBanRepository.findAll().size();

        // Update the nhaXuatBan
        NhaXuatBan updatedNhaXuatBan = nhaXuatBanRepository.findById(nhaXuatBan.getId()).get();
        updatedNhaXuatBan
            .ten(UPDATED_TEN)
            .diaChi(UPDATED_DIA_CHI)
            .sdt(UPDATED_SDT)
            .chietKhau(UPDATED_CHIET_KHAU);
        NhaXuatBanDTO nhaXuatBanDTO = nhaXuatBanMapper.toDto(updatedNhaXuatBan);

        restNhaXuatBanMockMvc.perform(put("/api/nha-xuat-bans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhaXuatBanDTO)))
            .andExpect(status().isOk());

        // Validate the NhaXuatBan in the database
        List<NhaXuatBan> nhaXuatBanList = nhaXuatBanRepository.findAll();
        assertThat(nhaXuatBanList).hasSize(databaseSizeBeforeUpdate);
        NhaXuatBan testNhaXuatBan = nhaXuatBanList.get(nhaXuatBanList.size() - 1);
        assertThat(testNhaXuatBan.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testNhaXuatBan.getDiaChi()).isEqualTo(UPDATED_DIA_CHI);
        assertThat(testNhaXuatBan.getSdt()).isEqualTo(UPDATED_SDT);
        assertThat(testNhaXuatBan.getChietKhau()).isEqualTo(UPDATED_CHIET_KHAU);
    }

    @Test
    public void updateNonExistingNhaXuatBan() throws Exception {
        int databaseSizeBeforeUpdate = nhaXuatBanRepository.findAll().size();

        // Create the NhaXuatBan
        NhaXuatBanDTO nhaXuatBanDTO = nhaXuatBanMapper.toDto(nhaXuatBan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhaXuatBanMockMvc.perform(put("/api/nha-xuat-bans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhaXuatBanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NhaXuatBan in the database
        List<NhaXuatBan> nhaXuatBanList = nhaXuatBanRepository.findAll();
        assertThat(nhaXuatBanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteNhaXuatBan() throws Exception {
        // Initialize the database
        nhaXuatBanRepository.save(nhaXuatBan);

        int databaseSizeBeforeDelete = nhaXuatBanRepository.findAll().size();

        // Get the nhaXuatBan
        restNhaXuatBanMockMvc.perform(delete("/api/nha-xuat-bans/{id}", nhaXuatBan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NhaXuatBan> nhaXuatBanList = nhaXuatBanRepository.findAll();
        assertThat(nhaXuatBanList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhaXuatBan.class);
        NhaXuatBan nhaXuatBan1 = new NhaXuatBan();
        nhaXuatBan1.setId("id1");
        NhaXuatBan nhaXuatBan2 = new NhaXuatBan();
        nhaXuatBan2.setId(nhaXuatBan1.getId());
        assertThat(nhaXuatBan1).isEqualTo(nhaXuatBan2);
        nhaXuatBan2.setId("id2");
        assertThat(nhaXuatBan1).isNotEqualTo(nhaXuatBan2);
        nhaXuatBan1.setId(null);
        assertThat(nhaXuatBan1).isNotEqualTo(nhaXuatBan2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhaXuatBanDTO.class);
        NhaXuatBanDTO nhaXuatBanDTO1 = new NhaXuatBanDTO();
        nhaXuatBanDTO1.setId("id1");
        NhaXuatBanDTO nhaXuatBanDTO2 = new NhaXuatBanDTO();
        assertThat(nhaXuatBanDTO1).isNotEqualTo(nhaXuatBanDTO2);
        nhaXuatBanDTO2.setId(nhaXuatBanDTO1.getId());
        assertThat(nhaXuatBanDTO1).isEqualTo(nhaXuatBanDTO2);
        nhaXuatBanDTO2.setId("id2");
        assertThat(nhaXuatBanDTO1).isNotEqualTo(nhaXuatBanDTO2);
        nhaXuatBanDTO1.setId(null);
        assertThat(nhaXuatBanDTO1).isNotEqualTo(nhaXuatBanDTO2);
    }
}
