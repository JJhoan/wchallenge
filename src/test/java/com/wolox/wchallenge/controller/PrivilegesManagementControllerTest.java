package com.wolox.wchallenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolox.wchallenge.WchallengeApplication;
import com.wolox.wchallenge.dto.PrivilegeManagementDto;
import com.wolox.wchallenge.model.PrivilegesManagement;
import com.wolox.wchallenge.repository.IPrivilegeManagementRepository;
import com.wolox.wchallenge.repository.PrivilegesManagementMapper;
import com.wolox.wchallenge.security.ApplicationUserPermission;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = WchallengeApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.yml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PrivilegesManagementControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private IPrivilegeManagementRepository privilegeManagementRepository;

    @Autowired
    private PrivilegesManagementMapper privilegesManagementMapper;

    private PrivilegeManagementDto privilegeManagementDto;
    private Set<ApplicationUserPermission> permissions;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void before() {
        permissions = new HashSet<>();
        permissions.add(ApplicationUserPermission.READ);
        privilegeManagementDto = new PrivilegeManagementDto();
        privilegeManagementDto.setIdUser(2L);
        privilegeManagementDto.setIdAlbum(1L);
        privilegeManagementDto.setPermissions(permissions);
    }

    @Test
    @WithMockUser(username = "Bret", password = "Bret")
    public void givenAuthRequestOnShareByUserService_shouldSucceedWith200() throws Exception {
        String url = PrivilegesManagementController.ACCESS_ALBUM + PrivilegesManagementController.SHARE_BY_USER;

        mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(privilegeManagementDto)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        PrivilegesManagement result = privilegeManagementRepository.findById(1L).orElse(null);
        Assert.assertNotNull(result);

    }

    @Test
    @WithMockUser(username = "Bret", password = "Bret")
    public void givenAuthRequestOnShareByUserService_shouldFailWith404WhenTheAlbumNotExist() throws Exception {
        String url = PrivilegesManagementController.ACCESS_ALBUM + PrivilegesManagementController.SHARE_BY_USER;

        privilegeManagementDto.setIdAlbum(0L);

        mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(privilegeManagementDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "Bret", password = "Bret")
    public void givenAuthRequestOnShareByUserService_shouldFailWith409WhenThePermissionAlreadyExist() throws Exception {
        String url = PrivilegesManagementController.ACCESS_ALBUM + PrivilegesManagementController.SHARE_BY_USER;
        PrivilegesManagement privilegesManagement = privilegesManagementMapper.mapToEntity(privilegeManagementDto);

        privilegeManagementRepository.saveAndFlush(privilegesManagement);

        mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(privilegeManagementDto)))
                .andExpect(status().isConflict());
    }

    @Test
    @WithMockUser(username = "Bret", password = "Bret")
    public void givenAuthRequestOnShareByUserService_shouldFailWith404WhenEmptyUserNotExist() throws Exception {
        String url = PrivilegesManagementController.ACCESS_ALBUM + PrivilegesManagementController.SHARE_BY_USER;

        privilegeManagementDto.setIdUser(0L);

        mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(privilegeManagementDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "Bret", password = "Bret")
    public void givenAuthRequestOnUpdatePermissionsService_shouldSucceedWith200() throws Exception {
        String url = PrivilegesManagementController.ACCESS_ALBUM + PrivilegesManagementController.UPDATE_PERMISSIONS;
        PrivilegesManagement privilegesManagement = privilegesManagementMapper.mapToEntity(privilegeManagementDto);

        privilegesManagement = privilegeManagementRepository.saveAndFlush(privilegesManagement);

        permissions.add(ApplicationUserPermission.WRITE);
        privilegeManagementDto.setPermissions(permissions);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(privilegeManagementDto)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        PrivilegesManagement result = privilegeManagementRepository
                .findById(privilegesManagement.getId())
                .orElse(null);

        Assert.assertNotNull(result);
    }

    @Test
    @WithMockUser(username = "Bret", password = "Bret")
    public void givenAuthRequestOnUpdatePermissionsService_shouldFailWith404WhenHaveNotPrivilegesSaved() throws Exception {
        String url = PrivilegesManagementController.ACCESS_ALBUM + PrivilegesManagementController.UPDATE_PERMISSIONS;

        permissions.add(ApplicationUserPermission.WRITE);
        privilegeManagementDto.setPermissions(permissions);
        mvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(privilegeManagementDto)))
                .andExpect(status().isNotFound());

    }

    @Test
    @WithMockUser(username = "Bret", password = "Bret")
    public void givenAuthRequestOnFilteredUsersService_shouldSucceedWith200() throws Exception {
        String url = PrivilegesManagementController.ACCESS_ALBUM  + "/1/READ";
        PrivilegesManagement privilegesManagement = privilegesManagementMapper.mapToEntity(privilegeManagementDto);
        privilegeManagementRepository.saveAndFlush(privilegesManagement);

        privilegeManagementDto.setIdUser(3L);
        permissions.add(ApplicationUserPermission.WRITE);
        privilegeManagementDto.setPermissions(permissions);
        PrivilegesManagement privilegesManagement2 = privilegesManagementMapper.mapToEntity(privilegeManagementDto);
        privilegeManagementRepository.saveAndFlush(privilegesManagement2);

        mvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Ervin Howell")))
                .andExpect(jsonPath("$[1].name", is("Clementine Bauch")));
    }

    @Test
    @WithMockUser(username = "Bret", password = "Bret")
    public void givenAuthRequestOnFilteredUsersService_shouldFailWith404WhenNotExistThePermission() throws Exception {
        String url = PrivilegesManagementController.ACCESS_ALBUM  + "/1/READ";
        mvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
