package com.wolox.wchallenge.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.wolox.wchallenge.model.PrivilegesManagement;
import com.wolox.wchallenge.repository.IPrivilegeManagementRepository;
import com.wolox.wchallenge.security.ApplicationUserPermission;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrivilegesManagementControllerTest {

    @Autowired
    private TestRestTemplate template;

    @MockBean
    private IPrivilegeManagementRepository privilegeManagementRepository;

    private PrivilegesManagement privilegesManagement;
    private Set<ApplicationUserPermission> permissions;

    @Before
    public void before() {
        permissions = new HashSet<>();
        permissions.add(ApplicationUserPermission.WRITE);
        permissions.add(ApplicationUserPermission.READ);
        privilegesManagement = new PrivilegesManagement(1L, 2L, 1L, permissions);
    }

    @Test
    public void givenAuthRequestOnShareByUserService_shouldSucceedWith200() {
        String url = PrivilegesManagementController.ACCESS_ALBUM + PrivilegesManagementController.SHARE_BY_USER;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("idAlbum", 1);
        personJsonObject.put("idUser", 2);
        personJsonObject.put("permissions", permissions);
        HttpEntity<String> request = new HttpEntity<>(personJsonObject.toString(), headers);

        Mockito.when(privilegeManagementRepository.save(Mockito.any())).thenReturn(privilegesManagement);

        ResponseEntity<String> result = template.withBasicAuth("Bret", "Bret")
                .postForEntity(url, request, String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenAuthRequestOnShareByUserServiceWithEmptyAlbum_shouldFailWith404() {
        String url = PrivilegesManagementController.ACCESS_ALBUM + PrivilegesManagementController.SHARE_BY_USER;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("idAlbum", 0);
        personJsonObject.put("idUser", 2);
        personJsonObject.put("permissions", permissions);
        HttpEntity<String> request = new HttpEntity<>(personJsonObject.toString(), headers);

        Mockito.when(privilegeManagementRepository.save(Mockito.any())).thenReturn(privilegesManagement);

        ResponseEntity<String> result = template.withBasicAuth("Bret", "Bret")
                .postForEntity(url, request, String.class);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void givenAuthRequestOnShareByUserServiceWithEmptyUser_shouldFailWith404() {
        String url = PrivilegesManagementController.ACCESS_ALBUM + PrivilegesManagementController.SHARE_BY_USER;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("idAlbum", 1);
        personJsonObject.put("idUser", 0);
        personJsonObject.put("permissions", permissions);
        HttpEntity<String> request = new HttpEntity<>(personJsonObject.toString(), headers);

        Mockito.when(privilegeManagementRepository.save(Mockito.any())).thenReturn(privilegesManagement);

        ResponseEntity<String> result = template.withBasicAuth("Bret", "Bret")
                .postForEntity(url, request, String.class);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void givenAuthRequestOnUpdatePermissionsService_shouldSucceedWith200() {
        permissions.remove(ApplicationUserPermission.WRITE);
        String url = PrivilegesManagementController.ACCESS_ALBUM + PrivilegesManagementController.UPDATE_PERMISSIONS;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("idAlbum", 1);
        personJsonObject.put("idUser", 2);
        personJsonObject.put("permissions", permissions);
        HttpEntity<String> request = new HttpEntity<>(personJsonObject.toString(), headers);

        Mockito.when(privilegeManagementRepository.save(Mockito.any())).thenReturn(privilegesManagement);
        Mockito.when(privilegeManagementRepository.findByIdUserAndIdAlbum(2L, 1L)).thenReturn(privilegesManagement);

        ResponseEntity<String> result = template.withBasicAuth("Bret", "Bret")
                .exchange(url, HttpMethod.PUT, request, String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenAuthRequestOnUpdatePermissionsServiceWithNotPrivilegesSaved_shouldFailWith404() {
        permissions.remove(ApplicationUserPermission.WRITE);

        String url = PrivilegesManagementController.ACCESS_ALBUM + PrivilegesManagementController.UPDATE_PERMISSIONS;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("idAlbum", 1);
        personJsonObject.put("idUser", 2);
        personJsonObject.put("permissions", permissions);
        HttpEntity<String> request = new HttpEntity<>(personJsonObject.toString(), headers);

        Mockito.when(privilegeManagementRepository.findByIdUserAndIdAlbum(2L, 1L)).thenReturn(null);

        ResponseEntity<String> result = template.withBasicAuth("Bret", "Bret")
                .exchange(url, HttpMethod.PUT, request, String.class);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void givenAuthRequestOnFilteredUsersService_shouldSucceedWith200()  {
        List<Long> idUsers = Lists.newArrayList(1L,2L, 3L, 4L, 5L);

        Map<String, String> urlParams = new ImmutableMap.Builder<String, String >()
                .put("idAlbum", "1")
                .put("permission", ApplicationUserPermission.READ.name())
                .build();
        String uri = PrivilegesManagementController.ACCESS_ALBUM + PrivilegesManagementController.FILTERED_USERS;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri);

        Mockito.when(privilegeManagementRepository.usersWithPermissionsInAlbum(1L, ApplicationUserPermission.READ)).thenReturn(idUsers);

        ResponseEntity<String> result = template.withBasicAuth("Bret", "Bret")
                .exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, null, String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenAuthRequestOnFilteredUsersService_shouldFailWith404()  {
        Map<String, String> urlParams = new ImmutableMap.Builder<String, String >()
                .put("idAlbum", "1")
                .put("permission", ApplicationUserPermission.READ.name())
                .build();
        String uri = PrivilegesManagementController.ACCESS_ALBUM + PrivilegesManagementController.FILTERED_USERS;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri);

        Mockito.when(privilegeManagementRepository.usersWithPermissionsInAlbum(1L, ApplicationUserPermission.READ)).thenReturn(null);

        ResponseEntity<String> result = template.withBasicAuth("Bret", "Bret")
                .exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, null, String.class);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}