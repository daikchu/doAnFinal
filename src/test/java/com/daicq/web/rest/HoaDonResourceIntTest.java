package com.daicq.web.rest;

import com.daicq.BookDemoApp;

import com.daicq.domain.HoaDon;
import com.daicq.repository.HoaDonRepository;
import com.daicq.service.HoaDonService;
import com.daicq.service.dto.HoaDonDTO;
import com.daicq.service.mapper.HoaDonMapper;
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
 * Test class for the HoaDonResource REST controller.
 *
 * @see HoaDonResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookDemoApp.class)
public class HoaDonResourceIntTest {

    private static final Long DEFAULT_KHACH_HANG_ID = 1L;
    private static final Long UPDATED_KHACH_HANG_ID = 2L;

    private static final String DEFAULT_LOAI_KH = "AAAAAAAAAA";
    private static final String UPDATED_LOAI_KH = "BBBBBBBBBB";

    private static final Instant DEFAULT_NGAY_LAP = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_LAP = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TEN_KH_VANG_LAI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_KH_VANG_LAI = "BBBBBBBBBB";

    private static final String DEFAULT_ADDR_KH_VANG_LAI = "AAAAAAAAAA";
    private static final String UPDATED_ADDR_KH_VANG_LAI = "BBBBBBBBBB";

    private static final String DEFAULT_SDT_KH_VANG_LAI = "AAAAAAAAAA";
    private static final String UPDATED_SDT_KH_VANG_LAI = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRANG_THAI_NHAN = 1;
    private static final Integer UPDATED_TRANG_THAI_NHAN = 2;

    private static final Integer DEFAULT_TRANG_THAI_THANH_TOAN = 1;
    private static final Integer UPDATED_TRANG_THAI_THANH_TOAN = 2;

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HoaDonMapper hoaDonMapper;
    
    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restHoaDonMockMvc;

    private HoaDon hoaDon;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HoaDonResource hoaDonResource = new HoaDonResource(hoaDonService);
        this.restHoaDonMockMvc = MockMvcBuilders.standaloneSetup(hoaDonResource)
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
    public static HoaDon createEntity() {
        HoaDon hoaDon = new HoaDon()
            .khachHangId(DEFAULT_KHACH_HANG_ID)
            .loaiKH(DEFAULT_LOAI_KH)
            .ngayLap(DEFAULT_NGAY_LAP)
            .tenKHVangLai(DEFAULT_TEN_KH_VANG_LAI)
            .addrKHVangLai(DEFAULT_ADDR_KH_VANG_LAI)
            .sdtKHVangLai(DEFAULT_SDT_KH_VANG_LAI)
            .trangThaiNhan(DEFAULT_TRANG_THAI_NHAN)
            .trangThaiThanhToan(DEFAULT_TRANG_THAI_THANH_TOAN)
            .note(DEFAULT_NOTE);
        return hoaDon;
    }

    @Before
    public void initTest() {
        mockAuthentication();
        hoaDonRepository.deleteAll();
        hoaDon = createEntity();
    }

    @Test
    public void createHoaDon() throws Exception {
        int databaseSizeBeforeCreate = hoaDonRepository.findAll().size();

        // Create the HoaDon
        HoaDonDTO hoaDonDTO = hoaDonMapper.toDto(hoaDon);
        restHoaDonMockMvc.perform(post("/api/hoa-dons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoaDonDTO)))
            .andExpect(status().isCreated());

        // Validate the HoaDon in the database
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        assertThat(hoaDonList).hasSize(databaseSizeBeforeCreate + 1);
        HoaDon testHoaDon = hoaDonList.get(hoaDonList.size() - 1);
        assertThat(testHoaDon.getKhachHangId()).isEqualTo(DEFAULT_KHACH_HANG_ID);
        assertThat(testHoaDon.getLoaiKH()).isEqualTo(DEFAULT_LOAI_KH);
        assertThat(testHoaDon.getNgayLap()).isEqualTo(DEFAULT_NGAY_LAP);
        assertThat(testHoaDon.getTenKHVangLai()).isEqualTo(DEFAULT_TEN_KH_VANG_LAI);
        assertThat(testHoaDon.getAddrKHVangLai()).isEqualTo(DEFAULT_ADDR_KH_VANG_LAI);
        assertThat(testHoaDon.getSdtKHVangLai()).isEqualTo(DEFAULT_SDT_KH_VANG_LAI);
        assertThat(testHoaDon.getTrangThaiNhan()).isEqualTo(DEFAULT_TRANG_THAI_NHAN);
        assertThat(testHoaDon.getTrangThaiThanhToan()).isEqualTo(DEFAULT_TRANG_THAI_THANH_TOAN);
        assertThat(testHoaDon.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    public void createHoaDonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hoaDonRepository.findAll().size();

        // Create the HoaDon with an existing ID
        hoaDon.setId("existing_id");
        HoaDonDTO hoaDonDTO = hoaDonMapper.toDto(hoaDon);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHoaDonMockMvc.perform(post("/api/hoa-dons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoaDonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HoaDon in the database
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        assertThat(hoaDonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllHoaDons() throws Exception {
        // Initialize the database
        hoaDonRepository.save(hoaDon);

        // Get all the hoaDonList
        restHoaDonMockMvc.perform(get("/api/hoa-dons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hoaDon.getId())))
            .andExpect(jsonPath("$.[*].khachHangId").value(hasItem(DEFAULT_KHACH_HANG_ID.intValue())))
            .andExpect(jsonPath("$.[*].loaiKH").value(hasItem(DEFAULT_LOAI_KH.toString())))
            .andExpect(jsonPath("$.[*].ngayLap").value(hasItem(DEFAULT_NGAY_LAP.toString())))
            .andExpect(jsonPath("$.[*].tenKHVangLai").value(hasItem(DEFAULT_TEN_KH_VANG_LAI.toString())))
            .andExpect(jsonPath("$.[*].addrKHVangLai").value(hasItem(DEFAULT_ADDR_KH_VANG_LAI.toString())))
            .andExpect(jsonPath("$.[*].sdtKHVangLai").value(hasItem(DEFAULT_SDT_KH_VANG_LAI.toString())))
            .andExpect(jsonPath("$.[*].trangThaiNhan").value(hasItem(DEFAULT_TRANG_THAI_NHAN)))
            .andExpect(jsonPath("$.[*].trangThaiThanhToan").value(hasItem(DEFAULT_TRANG_THAI_THANH_TOAN)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())));
    }
    
    @Test
    public void getHoaDon() throws Exception {
        // Initialize the database
        hoaDonRepository.save(hoaDon);

        // Get the hoaDon
        restHoaDonMockMvc.perform(get("/api/hoa-dons/{id}", hoaDon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hoaDon.getId()))
            .andExpect(jsonPath("$.khachHangId").value(DEFAULT_KHACH_HANG_ID.intValue()))
            .andExpect(jsonPath("$.loaiKH").value(DEFAULT_LOAI_KH.toString()))
            .andExpect(jsonPath("$.ngayLap").value(DEFAULT_NGAY_LAP.toString()))
            .andExpect(jsonPath("$.tenKHVangLai").value(DEFAULT_TEN_KH_VANG_LAI.toString()))
            .andExpect(jsonPath("$.addrKHVangLai").value(DEFAULT_ADDR_KH_VANG_LAI.toString()))
            .andExpect(jsonPath("$.sdtKHVangLai").value(DEFAULT_SDT_KH_VANG_LAI.toString()))
            .andExpect(jsonPath("$.trangThaiNhan").value(DEFAULT_TRANG_THAI_NHAN))
            .andExpect(jsonPath("$.trangThaiThanhToan").value(DEFAULT_TRANG_THAI_THANH_TOAN))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()));
    }

    @Test
    public void getNonExistingHoaDon() throws Exception {
        // Get the hoaDon
        restHoaDonMockMvc.perform(get("/api/hoa-dons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateHoaDon() throws Exception {
        // Initialize the database
        hoaDonRepository.save(hoaDon);

        int databaseSizeBeforeUpdate = hoaDonRepository.findAll().size();

        // Update the hoaDon
        HoaDon updatedHoaDon = hoaDonRepository.findById(hoaDon.getId()).get();
        updatedHoaDon
            .khachHangId(UPDATED_KHACH_HANG_ID)
            .loaiKH(UPDATED_LOAI_KH)
            .ngayLap(UPDATED_NGAY_LAP)
            .tenKHVangLai(UPDATED_TEN_KH_VANG_LAI)
            .addrKHVangLai(UPDATED_ADDR_KH_VANG_LAI)
            .sdtKHVangLai(UPDATED_SDT_KH_VANG_LAI)
            .trangThaiNhan(UPDATED_TRANG_THAI_NHAN)
            .trangThaiThanhToan(UPDATED_TRANG_THAI_THANH_TOAN)
            .note(UPDATED_NOTE);
        HoaDonDTO hoaDonDTO = hoaDonMapper.toDto(updatedHoaDon);

        restHoaDonMockMvc.perform(put("/api/hoa-dons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoaDonDTO)))
            .andExpect(status().isOk());

        // Validate the HoaDon in the database
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        assertThat(hoaDonList).hasSize(databaseSizeBeforeUpdate);
        HoaDon testHoaDon = hoaDonList.get(hoaDonList.size() - 1);
        assertThat(testHoaDon.getKhachHangId()).isEqualTo(UPDATED_KHACH_HANG_ID);
        assertThat(testHoaDon.getLoaiKH()).isEqualTo(UPDATED_LOAI_KH);
        assertThat(testHoaDon.getNgayLap()).isEqualTo(UPDATED_NGAY_LAP);
        assertThat(testHoaDon.getTenKHVangLai()).isEqualTo(UPDATED_TEN_KH_VANG_LAI);
        assertThat(testHoaDon.getAddrKHVangLai()).isEqualTo(UPDATED_ADDR_KH_VANG_LAI);
        assertThat(testHoaDon.getSdtKHVangLai()).isEqualTo(UPDATED_SDT_KH_VANG_LAI);
        assertThat(testHoaDon.getTrangThaiNhan()).isEqualTo(UPDATED_TRANG_THAI_NHAN);
        assertThat(testHoaDon.getTrangThaiThanhToan()).isEqualTo(UPDATED_TRANG_THAI_THANH_TOAN);
        assertThat(testHoaDon.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    public void updateNonExistingHoaDon() throws Exception {
        int databaseSizeBeforeUpdate = hoaDonRepository.findAll().size();

        // Create the HoaDon
        HoaDonDTO hoaDonDTO = hoaDonMapper.toDto(hoaDon);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHoaDonMockMvc.perform(put("/api/hoa-dons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoaDonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HoaDon in the database
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        assertThat(hoaDonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteHoaDon() throws Exception {
        // Initialize the database
        hoaDonRepository.save(hoaDon);

        int databaseSizeBeforeDelete = hoaDonRepository.findAll().size();

        // Get the hoaDon
        restHoaDonMockMvc.perform(delete("/api/hoa-dons/{id}", hoaDon.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        assertThat(hoaDonList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HoaDon.class);
        HoaDon hoaDon1 = new HoaDon();
        hoaDon1.setId("id1");
        HoaDon hoaDon2 = new HoaDon();
        hoaDon2.setId(hoaDon1.getId());
        assertThat(hoaDon1).isEqualTo(hoaDon2);
        hoaDon2.setId("id2");
        assertThat(hoaDon1).isNotEqualTo(hoaDon2);
        hoaDon1.setId(null);
        assertThat(hoaDon1).isNotEqualTo(hoaDon2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HoaDonDTO.class);
        HoaDonDTO hoaDonDTO1 = new HoaDonDTO();
        hoaDonDTO1.setId("id1");
        HoaDonDTO hoaDonDTO2 = new HoaDonDTO();
        assertThat(hoaDonDTO1).isNotEqualTo(hoaDonDTO2);
        hoaDonDTO2.setId(hoaDonDTO1.getId());
        assertThat(hoaDonDTO1).isEqualTo(hoaDonDTO2);
        hoaDonDTO2.setId("id2");
        assertThat(hoaDonDTO1).isNotEqualTo(hoaDonDTO2);
        hoaDonDTO1.setId(null);
        assertThat(hoaDonDTO1).isNotEqualTo(hoaDonDTO2);
    }
}
