package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Phone;
import org.example.model.Role;
import org.example.model.User;
import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class UserServletTest {

    @InjectMocks
    private UserServlet userServlet = new UserServlet();
    @Mock
    private UserService userService;
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

        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Админ");

        Phone phone1 = new Phone();
        phone1.setPhoneId(1L);
        phone1.setPhoneNumber("111");

        User user = new User();
        user.setId(1L);
        user.setName("Коля");
        user.setRoles(Set.of(role));
        user.setPhones(Set.of(phone1));


        Mockito.when(userService.findById(1L)).thenReturn(user);

        userServlet.doGet(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(200);
        Mockito.verify(writer).write("{\"name\":\"Коля\",\"phonesDto\":[{\"phoneNumber\":\"111\"}],\"rolesDto\":[{\"roleName\":\"Админ\",\"usersDto\":null}]}");
    }

    @Test
    void doGet_findAll() throws ServletException, IOException {
        Mockito.when(request.getParameter("id")).thenReturn("all");
        Mockito.when(response.getWriter()).thenReturn(writer);

        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Админ");

        Phone phone1 = new Phone();
        phone1.setPhoneId(1L);
        phone1.setPhoneNumber("111");
        Phone phone2 = new Phone();
        phone2.setPhoneId(2L);
        phone2.setPhoneNumber("222");

        User user1 = new User();
        user1.setId(1L);
        user1.setName("Коля");
        user1.setRoles(Set.of(role));
        user1.setPhones(Set.of(phone1));
        User user2 = new User();
        user2.setId(2L);
        user2.setName("Катя");
        user2.setRoles(Set.of(role));
        user2.setPhones(Set.of(phone2));
        ArrayList<User>users = new ArrayList<>(List.of(user1, user2));


        Mockito.when(userService.findAll()).thenReturn(users);

        userServlet.doGet(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(200);
        Mockito.verify(writer).write("[{\"name\":\"Коля\",\"phonesDto\":[{\"phoneNumber\":\"111\"}],\"rolesDto\":[{\"roleName\":\"Админ\",\"usersDto\":null}]},{\"name\":\"Катя\",\"phonesDto\":[{\"phoneNumber\":\"222\"}],\"rolesDto\":[{\"roleName\":\"Админ\",\"usersDto\":null}]}]");
    }

    @Test
    void doGet_null() throws ServletException, IOException {
        Mockito.when(request.getParameter("id")).thenReturn(null);
        Mockito.when(response.getWriter()).thenReturn(writer);

        userServlet.doGet(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(400);
        Mockito.verify(writer).write("id must not empty");
    }

    @Test
    void doPost() throws IOException, ServletException {
        Mockito.when(request.getParameter("name")).thenReturn("Коля");
        Mockito.when(response.getWriter()).thenReturn(writer);

        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Админ");

        User user = new User();
        user.setName("Коля");

        User userSave = new User();
        userSave.setId(1L);
        userSave.setName("Коля");
        Mockito.when(userService.save(user)).thenReturn(userSave);

        userServlet.doPost(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(200);
        Mockito.verify(writer).write("{\"name\":\"Коля\",\"phonesDto\":null,\"rolesDto\":null}");
    }

    @Test
    void doPost_null() throws IOException, ServletException {
        Mockito.when(request.getParameter("name")).thenReturn(null);
        Mockito.when(response.getWriter()).thenReturn(writer);

        userServlet.doPost(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(400);
        Mockito.verify(writer).write("name must not empty");
    }

    @Test
    void doDelete() throws IOException, ServletException {
        Mockito.when(request.getParameter("id")).thenReturn("1");
        Mockito.when(response.getWriter()).thenReturn(writer);
        Mockito.when(userService.deleteById(1L)).thenReturn(true);

        userServlet.doDelete(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(200);
        Mockito.verify(writer).write("name deleted");
    }
}