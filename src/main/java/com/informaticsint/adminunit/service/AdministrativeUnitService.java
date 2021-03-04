package com.informaticsint.adminunit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.informaticsint.adminunit.dao.DistrictDao;
import com.informaticsint.adminunit.dao.GsDivisionDao;
import com.informaticsint.adminunit.dao.ProvinceDao;
import com.informaticsint.adminunit.dao.SecretariatDao;
import com.informaticsint.adminunit.dto.DistrictDto;
import com.informaticsint.adminunit.dto.GsDivisionDto;
import com.informaticsint.adminunit.dto.ProvinceDto;
import com.informaticsint.adminunit.dto.SecretariatDto;
import com.informaticsint.adminunit.model.District;
import com.informaticsint.adminunit.model.GsDivision;
import com.informaticsint.adminunit.model.Province;
import com.informaticsint.adminunit.model.Secretariat;
import com.informaticsint.common.constants.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

@Service("administrativeUnitService")
public class AdministrativeUnitService {

    private final ProvinceDao provinceDao;
    private final DistrictDao districtDao;
    private final SecretariatDao secretariatDao;
    private final GsDivisionDao gsDivisionDao;
    private final ObjectMapper objectMapper;

    /**
     * constructor injection
     *  @param provinceDao    ProvinceDao
     * @param districtDao    DistrictDao
     * @param secretariatDao SecretariatDao
     * @param gsDivisionDao
     * @param objectMapper   ObjectMapper
     */
    @Autowired
    public AdministrativeUnitService(ProvinceDao provinceDao, DistrictDao districtDao, SecretariatDao secretariatDao, GsDivisionDao gsDivisionDao, ObjectMapper objectMapper) {
        this.provinceDao = provinceDao;
        this.districtDao = districtDao;
        this.secretariatDao = secretariatDao;
        this.gsDivisionDao = gsDivisionDao;
        this.objectMapper = objectMapper;
    }

    /**
     * method for save province
     *
     * @param provinceDto ProvinceDto
     */
    public void saveProvince(ProvinceDto provinceDto) {
        provinceDto.validate();
        Province province = objectMapper.convertValue(provinceDto, Province.class);
        province.setActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        provinceDao.save(province);
    }

    /**
     * method for update province
     *
     * @param identifier  String identifier
     * @param provinceDto ProvinceDto
     */
    public boolean updateProvince(String identifier, ProvinceDto provinceDto) {
        provinceDto.validate();
        Optional<Province> optionalProvince = provinceDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS);
        if (optionalProvince.isPresent()) {
            Province province = optionalProvince.get();
            province.setCode(provinceDto.getCode());
            province.setAbbreviationCode(provinceDto.getAbbreviationCode());
            province.getDescription().setSi(provinceDto.getDescription().getSi());
            province.getDescription().setTa(provinceDto.getDescription().getTa());
            province.getDescription().setEn(provinceDto.getDescription().getEn());
            provinceDao.save(province);
            return true;
        } else {
            return false;
        }
    }

    /**
     * method for delete province
     *
     * @param identifier String identifier
     */
    public boolean deleteProvince(String identifier) {
        Optional<Province> optionalProvince = provinceDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS);
        if (optionalProvince.isPresent()) {
            Province province = optionalProvince.get();
            province.setActiveStatus(ApplicationConstants.INACTIVE_STATUS);
            provinceDao.save(province);
            return true;
        } else {
            return false;
        }
    }

    /**
     * method for get specific province
     *
     * @param identifier String identifier
     */
    public ProvinceDto getProvinceByIdentifier(String identifier) {
        Optional<Province> optionalProvince = provinceDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS);
        return optionalProvince.map(province -> objectMapper.convertValue(province, ProvinceDto.class)).orElse(null);

    }

    /**
     * method for get all provinces
     */
    public List<ProvinceDto> getAllProvinces() {
        List<Province> provinces = provinceDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        return provinces.stream().map(province -> objectMapper.convertValue(province, ProvinceDto.class)).collect(Collectors.toList());
    }

    /**
     * method for save district
     *
     * @param districtDto DistrictDto
     */
    public void saveDistrict(DistrictDto districtDto) {
        districtDto.validate();
        District district = objectMapper.convertValue(districtDto, District.class);
        district.setActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        districtDao.save(district);
    }

    /**
     * method for update district
     *
     * @param identifier  String identifier
     * @param districtDto DistrictDto
     */
    public boolean updateDistrict(String identifier, DistrictDto districtDto) {
        districtDto.validate();
        Optional<District> optionalDistrict = districtDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS);
        if (optionalDistrict.isPresent()) {
            District district = optionalDistrict.get();
            district.setCode(districtDto.getCode());
            district.setProvinceCode(districtDto.getProvinceCode());
            district.setLifeCode(districtDto.getLifeCode());
            district.getDescription().setSi(districtDto.getDescription().getSi());
            district.getDescription().setTa(districtDto.getDescription().getTa());
            district.getDescription().setEn(districtDto.getDescription().getEn());
            districtDao.save(district);
            return true;
        }
        return false;
    }

    /**
     * method for delete district
     *
     * @param identifier String identifier
     */
    public boolean deleteDistrict(String identifier) {
        Optional<District> optionalDistrict = districtDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS);
        if (optionalDistrict.isPresent()) {
            District district = optionalDistrict.get();
            district.setActiveStatus(ApplicationConstants.INACTIVE_STATUS);
            districtDao.save(district);
            return true;
        }
        return false;
    }

    /**
     * method for get specific district
     *
     * @param identifier String identifier
     */
    public DistrictDto getDistrictByIdentifier(String identifier) {
        Optional<District> optionalDistrict = districtDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS);
        return optionalDistrict.map(district -> objectMapper.convertValue(district, DistrictDto.class)).orElse(null);
    }

    /**
     * method for get all districts
     */
    public List<DistrictDto> getAllDistricts() {
        List<District> districts = districtDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        return districts.stream().map(district -> objectMapper.convertValue(district, DistrictDto.class)).collect(Collectors.toList());
    }

    /**
     * method for get all districts by province code
     */
    public List<DistrictDto> getAllDistrictsByProvinceCode(String provinceCode) {
        List<District> districts = districtDao.findAllByProvinceCodeAndActiveStatus(provinceCode, ApplicationConstants.ACTIVE_STATUS);
        return districts.stream().map(district -> objectMapper.convertValue(district, DistrictDto.class)).collect(Collectors.toList());
    }

    /**
     * method for save secretariat
     *
     * @param secretariatDto SecretariatDto
     */
    public void saveSecretariat(SecretariatDto secretariatDto) {
        secretariatDto.validate();
        Secretariat secretariat = objectMapper.convertValue(secretariatDto, Secretariat.class);
        secretariat.setActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        secretariatDao.save(secretariat);
    }

    /**
     * method for update secretariat
     *
     * @param identifier     String identifier
     * @param secretariatDto SecretariatDto
     */
    public boolean updateSecretariat(String identifier, SecretariatDto secretariatDto) {
        secretariatDto.validate();
        Optional<Secretariat> optionalSecretariat = secretariatDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS);
        if (optionalSecretariat.isPresent()) {
            Secretariat secretariat = optionalSecretariat.get();
            secretariat.setCode(secretariatDto.getCode());
            secretariat.setDistrictCode(secretariatDto.getDistrictCode());
            secretariat.setAddressLine1(secretariatDto.getAddressLine1());
            secretariat.setAddressLine2(secretariatDto.getAddressLine2());
            secretariat.setPostalCode(secretariatDto.getPostalCode());
            secretariat.setPhoneNumber(secretariatDto.getPhoneNumber());
            secretariat.setMobileNumber(secretariatDto.getMobileNumber());
            secretariat.setFaxNumber(secretariatDto.getFaxNumber());
            secretariat.setEmail(secretariatDto.getEmail());
            secretariat.setLifeCode(secretariatDto.getLifeCode());
            secretariat.getDescription().setSi(secretariatDto.getDescription().getSi());
            secretariat.getDescription().setTa(secretariatDto.getDescription().getTa());
            secretariat.getDescription().setEn(secretariatDto.getDescription().getEn());
            secretariatDao.save(secretariat);
            return true;
        }
        return false;
    }

    /**
     * method for delete secretariat
     *
     * @param identifier String identifier
     */
    public boolean deleteSecretariat(String identifier) {
        Optional<Secretariat> optionalSecretariat = secretariatDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS);
        if (optionalSecretariat.isPresent()) {
            Secretariat secretariat = optionalSecretariat.get();
            secretariat.setActiveStatus(ApplicationConstants.INACTIVE_STATUS);
            secretariatDao.save(secretariat);
            return true;
        }
        return false;
    }

    /**
     * method for get specific secretariat
     *
     * @param identifier String identifier
     */
    public SecretariatDto getSecretariatByIdentifier(String identifier) {
        Optional<Secretariat> optionalSecretariat = secretariatDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS);
        return optionalSecretariat.map(secretariat -> objectMapper.convertValue(secretariat, SecretariatDto.class)).orElse(null);
    }

    /**
     * method for get all secretariats
     */
    public List<SecretariatDto> getAllSecretariats() {
        List<Secretariat> secretariats = secretariatDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        return secretariats.stream().map(secretariat -> objectMapper.convertValue(secretariat, SecretariatDto.class)).collect(Collectors.toList());
    }

    /**
     * method for get all secretariats by district code
     */
    public List<SecretariatDto> getAllSecretariatsByDistrictCode(String districtCode) {
        List<Secretariat> secretariats = secretariatDao.findAllByDistrictCodeAndActiveStatus(districtCode, ApplicationConstants.ACTIVE_STATUS);
        return secretariats.stream().map(secretariat -> objectMapper.convertValue(secretariat, SecretariatDto.class)).collect(Collectors.toList());
    }

    /**
     * method for save gsDivision
     *
     * @param gsDivisionDto GsDivisionDto
     */
    public void saveGsDivision(GsDivisionDto gsDivisionDto) {
        gsDivisionDto.validate();
        GsDivision gsDivision = objectMapper.convertValue(gsDivisionDto, GsDivision.class);
        gsDivision.setActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        gsDivisionDao.save(gsDivision);
    }

    /**
     * method for update gsDivision
     *
     * @param identifier     String identifier
     * @param gsDivisionDto GsDivisionDto
     */
    public boolean updateGsDivision(String identifier, GsDivisionDto gsDivisionDto) {
        gsDivisionDto.validate();
        Optional<GsDivision> optionalGsDivision = gsDivisionDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS);
        if (optionalGsDivision.isPresent()) {
            GsDivision gsDivision = optionalGsDivision.get();
            gsDivision.setCode(gsDivisionDto.getCode());
            gsDivision.setSecretariatCode(gsDivisionDto.getSecretariatCode());
            gsDivision.setAddressLine1(gsDivisionDto.getAddressLine1());
            gsDivision.setAddressLine2(gsDivisionDto.getAddressLine2());
            gsDivision.setPostalCode(gsDivisionDto.getPostalCode());
            gsDivision.setPhoneNumber(gsDivisionDto.getPhoneNumber());
            gsDivision.setMobileNumber(gsDivisionDto.getMobileNumber());
            gsDivision.setFaxNumber(gsDivisionDto.getFaxNumber());
            gsDivision.setEmail(gsDivisionDto.getEmail());
            gsDivision.setLifeCode(gsDivisionDto.getLifeCode());
            gsDivision.getDescription().setSi(gsDivisionDto.getDescription().getSi());
            gsDivision.getDescription().setTa(gsDivisionDto.getDescription().getTa());
            gsDivision.getDescription().setEn(gsDivisionDto.getDescription().getEn());
            gsDivisionDao.save(gsDivision);
            return true;
        }
        return false;
    }

    /**
     * method for delete gsDivision
     *
     * @param identifier String identifier
     */
    public boolean deleteGsDivision(String identifier) {
        Optional<GsDivision> optionalGsDivision = gsDivisionDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS);
        if (optionalGsDivision.isPresent()) {
            GsDivision gsDivision = optionalGsDivision.get();
            gsDivision.setActiveStatus(ApplicationConstants.INACTIVE_STATUS);
            gsDivisionDao.save(gsDivision);
            return true;
        }
        return false;
    }

    /**
     * method for get specific gsDivision
     *
     * @param identifier String identifier
     */
    public GsDivisionDto getGsDivisionByIdentifier(String identifier) {
        Optional<GsDivision> optionalGsDivision = gsDivisionDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS);
        return optionalGsDivision.map(gsDivision -> objectMapper.convertValue(gsDivision, GsDivisionDto.class)).orElse(null);
    }

    /**
     * method for get all gsDivisions
     */
    public List<GsDivisionDto> getAllGsDivisions() {
        List<GsDivision> gsDivisions = gsDivisionDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        return gsDivisions.stream().map(gsDivision -> objectMapper.convertValue(gsDivision, GsDivisionDto.class)).collect(Collectors.toList());
    }

    /**
     * method for get all gsDivisions by secretariat code
     */
    public List<GsDivisionDto> getAllGsDivisionsBySecretariatCode(String secretariatCode) {
        List<GsDivision> gsDivisions = gsDivisionDao.findAllBySecretariatCodeAndActiveStatus(secretariatCode, ApplicationConstants.ACTIVE_STATUS);
        return gsDivisions.stream().map(gsDivision -> objectMapper.convertValue(gsDivision, GsDivisionDto.class)).collect(Collectors.toList());
    }

}
