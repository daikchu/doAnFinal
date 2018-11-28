package com.daicq.web.rest;

import com.daicq.BookDemoApp;

import com.daicq.domain.CTDH;
import com.daicq.repository.CTDHRepository;
import com.daicq.service.CTDHService;
import com.daicq.service.dto.CTDHDTO;
import com.daicq.service.mapper.CTDHMapper;
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
 * Test class for the CTDHResource REST controller.
 *
 * @see CTDHResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookDemoApp.class)
public class CTDHResourceIntTest {

    private static final Long DEFAULT_SACH_ID = 1L;
    private static final Long UPDATED_SACH_ID = 2L;

    private static final Long DEFAULT_SO_LUONG = 1L;
    private static final Long UPDATED_SO_LUONG = 2L;

    private static final Float DEFAULT_THANH_TIEN = 1F;
    private static final Float UPDATED_THANH_TIEN = 2F;

    @Autowired
    private CTDHRepository cTDHRepository;

    @Autowired
    private CTDHMapper cTDHMapper;
    
    @Autowired
    private CTDHService cTDHService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restCTDHMockMvc;

    private CTDH cTDH;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CTDHResource cTDHResource = new CTDHResource(cTDHService);
        this.restCTDHMockMvc = MockMvcBuilders.standaloneSetup(cTDHResource)
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
    public static CTDH createEntity() {
        CTDH cTDH = new CTDH()
            .sachId(DEFAULT_SACH_ID)
            .soLuong(DEFAULT_SO_LUONG)
            .thanhTien(DEFAULT_THANH_TIEN);
        return cTDH;
    }

    @Before
    public void initTest() {
        mockAuthentication();
        cTDHRepository.deleteAll();
        cTDH = createEntity();
    }

    @Test
    public void createCTDH() throws Exception {
        int databaseSizeBeforeCreate = cTDHRepository.findAll().size();

        // Create the CTDH
        CTDHDTO cTDHDTO = cTDHMapper.toDto(cTDH);
        restCTDHMockMvc.perform(post("/api/ctdhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cTDHDTO)))
            .andExpect(status().isCreated());

        // Validate the CTDH in the database
        List<CTDH> cTDHList = cTDHRepository.findAll();
        assertThat(cTDHList).hasSize(databaseSizeBeforeCreate + 1);
        CTDH testCTDH = cTDHList.get(cTDHList.size() - 1);
        assertThat(testCTDH.getSachId()).isEqualTo(DEFAULT_SACH_ID);
        assertThat(testCTDH.getSoLuong()).isEqualTo(DEFAULT_SO_LUONG);
        assertThat(testCTDH.getThanhTien()).isEqualTo(DEFAULT_THANH_TIEN);
    }

    @Test
    public void createCTDHWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cTDHRepository.findAll().size();

        // Create the CTDH with an existing ID
        cTDH.setId("existing_id");
        CTDHDTO cTDHDTO = cTDHMapper.toDto(cTDH);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCTDHMockMvc.perform(post("/api/ctdhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cTDHDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CTDH in the database
        List<CTDH> cTDHList = cTDHRepository.findAll();
        assertThat(cTDHList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllCTDHS() throws Exception {
        // Initialize the database
        cTDHRepository.save(cTDH);

        // Get all the cTDHList
        restCTDHMockMvc.perform(get("/api/ctdhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cTDH.getId())))
            .andExpect(jsonPath("$.[*].sachId").value(hasItem(DEFAULT_SACH_ID.intValue())))
            .andExpect(jsonPath("$.[*].soLuong").value(hasItem(DEFAULT_SO_LUONG.intValue())))
            .andExpect(jsonPath("$.[*].thanhTien").value(hasItem(DEFAULT_THANH_TIEN.doubleValue())));
    }
    
    @Test
    public void getCTDH() throws Exception {
        // Initialize the database
        cTDHRepository.save(cTDH);

        // Get the cTDH
        restCTDHMockMvc.perform(get("/api/ctdhs/{id}", cTDH.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cTDH.getId()))
            .andExpect(jsonPath("$.sachId").value(DEFAULT_SACH_ID.intValue()))
            .andExpect(jsonPath("$.soLuong").value(DEFAULT_SO_LUONG.intValue()))
            .andExpect(jsonPath("$.thanhTien").value(DEFAULT_THANH_TIEN.doubleValue()));
    }

    @Test
    public void getNonExistingCTDH() throws Exception {
        // Get the cTDH
        restCTDHMockMvc.perform(get("/api/ctdhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCTDH() throws Exception {
        // Initialize the database
        cTDHRepository.save(cTDH);

        int databaseSizeBeforeUpdate = cTDHRepository.findAll().size();

        // Update the cTDH
        CTDH updatedCTDH = cTDHRepository.findById(cTDH.getId()).get();
        updatedCTDH
            .sachId(UPDATED_SACH_ID)
            .soLuong(UPDATED_SO_LUONG)
            .thanhTien(UPDATED_THANH_TIEN);
        CTDHDTO cTDHDTO = cTDHMapper.toDto(updatedCTDH);

        restCTDHMockMvc.perform(put("/api/ctdhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cTDHDTO)))
            .andExpect(status().isOk());

        // Validate the CTDH in the database
        List<CTDH> cTDHList = cTDHRepository.findAll();
        assertThat(cTDHList).hasSize(databaseSizeBeforeUpdate);
        CTDH testCTDH = cTDHList.get(cTDHList.size() - 1);
        assertThat(testCTDH.getSachId()).isEqualTo(UPDATED_SACH_ID);
        assertThat(testCTDH.getSoLuong()).isEqualTo(UPDATED_SO_LUONG);
        assertThat(testCTDH.getThanhTien()).isEqualTo(UPDATED_THANH_TIEN);
    }

    @Test
    public void updateNonExistingCTDH() throws Exception {
        int databaseSizeBeforeUpdate = cTDHRepository.findAll().size();

        // Create the CTDH
        CTDHDTO cTDHDTO = cTDHMapper.toDto(cTDH);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCTDHMockMvc.perform(put("/api/ctdhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cTDHDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CTDH in the database
        List<CTDH> cTDHList = cTDHRepository.findAll();
        assertThat(cTDHList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCTDH() throws Exception {
        // Initialize the database
        cTDHRepository.save(cTDH);

        int databaseSizeBeforeDelete = cTDHRepository.findAll().size();

        // Get the cTDH
        restCTDHMockMvc.perform(delete("/api/ctdhs/{id}", cTDH.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CTDH> cTDHList = cTDHRepository.findAll();
        assertThat(cTDHList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CTDH.class);
        CTDH cTDH1 = new CTDH();
        cTDH1.setId("id1");
        CTDH cTDH2 = new CTDH();
        cTDH2.setId(cTDH1.getId());
        assertThat(cTDH1).isEqualTo(cTDH2);
        cTDH2.setId("id2");
        assertThat(cTDH1).isNotEqualTo(cTDH2);
        cTDH1.setId(null);
        assertThat(cTDH1).isNotEqualTo(cTDH2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CTDHDTO.class);
        CTDHDTO cTDHDTO1 = new CTDHDTO();
        cTDHDTO1.setId("id1");
        CTDHDTO cTDHDTO2 = new CTDHDTO();
        assertThat(cTDHDTO1).isNotEqualTo(cTDHDTO2);
        cTDHDTO2.setId(cTDHDTO1.getId());
        assertThat(cTDHDTO1).isEqualTo(cTDHDTO2);
        cTDHDTO2.setId("id2");
        assertThat(cTDHDTO1).isNotEqualTo(cTDHDTO2);
        cTDHDTO1.setId(null);
        assertThat(cTDHDTO1).isNotEqualTo(cTDHDTO2);
    }
}
