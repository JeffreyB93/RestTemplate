package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Phone;
import org.example.model.Role;
import org.example.model.User;
import org.example.service.PhoneService;
import org.example.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class RoleServletTest {

    @InjectMocks
    private RoleServlet roleServlet = new RoleServlet();
    @Mock
    private RoleService roleService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;

    @Test
    void doGet_findById() throws ServletException, IOException {
        Mockito.when(request.getParameter("id")).thenReturn("1");
        Mockito.when(response.getWriter()).thenReturn(writer);

        User user1 = new User();
        user1.setId(1L);
        user1.setName("Коля");

        Role role = new Role();
        role.setId(1L);
        role.setRoleName("111");
        role.setUsers(Set.of(user1));


        Mockito.when(roleService.findById(1L)).thenReturn(role);

        roleServlet.doGet(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(200);
        Mockito.verify(writer).write("{\"roleName\":\"111\",\"usersDto\":[{\"name\":\"Коля\",\"phonesDto\":null,\"rolesDto\":null}]}");
    }

    @Test
    void doGet_findAll() throws ServletException, IOException {
        Mockito.when(request.getParameter("id")).thenReturn("all");
        Mockito.when(response.getWriter()).thenReturn(writer);

        User user1 = new User();
        user1.setId(1L);
        user1.setName("Коля");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Катя");

        Role role1 = new Role();
        role1.setId(1L);
        role1.setRoleName("Админ");
        role1.setUsers(Set.of(user1));

        Role role2 = new Role();
        role2.setId(1L);
        role2.setRoleName("Клиент");
        role2.setUsers(Set.of(user2));

        Set<Role> roles = Set.of(role1, role2);

        Mockito.when(roleService.findAll()).thenReturn(roles);

        roleServlet.doGet(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(200);
        Mockito.verify(writer).write("[{\"roleName\":\"Админ\",\"usersDto\":[{\"name\":\"Коля\",\"phonesDto\":null,\"rolesDto\":null}]},{\"roleName\":\"Клиент\",\"usersDto\":[{\"name\":\"Катя\",\"phonesDto\":null,\"rolesDto\":null}]}]");
    }

    @Test
    void doGet_null() throws ServletException, IOException {
        Mockito.when(request.getParameter("id")).thenReturn(null);
        Mockito.when(response.getWriter()).thenReturn(writer);

        roleServlet.doGet(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(400);
        Mockito.verify(writer).write("id must not empty");
    }

    @Test
    void doPost() throws IOException, ServletException {
        Mockito.when(request.getParameter("roleName")).thenReturn("Админ");
        Mockito.when(response.getWriter()).thenReturn(writer);

        Role role = new Role();
        role.setRoleName("Админ");
        Role roleSave = new Role();
        roleSave.setId(1L);
        roleSave.setRoleName("Админ");
        Mockito.when(roleService.save(role)).thenReturn(roleSave);

        roleServlet.doPost(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(200);
        Mockito.verify(writer).write("{\"roleName\":\"Админ\",\"usersDto\":null}");
    }

    @Test
    void doPost_null() throws IOException, ServletException {
        Mockito.when(request.getParameter("roleName")).thenReturn(null);
        Mockito.when(response.getWriter()).thenReturn(writer);

        roleServlet.doPost(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(400);
        Mockito.verify(writer).write("phoneNumber must not empty");
    }

    @Test
    void doDelete() throws IOException, ServletException {
        Mockito.when(request.getParameter("id")).thenReturn("1");
        Mockito.when(response.getWriter()).thenReturn(writer);
        Mockito.when(roleService.deleteById(1L)).thenReturn(true);

        roleServlet.doDelete(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(200);
        Mockito.verify(writer).write("roleName deleted");
    }
}