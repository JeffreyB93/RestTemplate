package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Phone;
import org.example.service.PhoneService;
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
class PhoneServletTest {

    @InjectMocks
    private PhoneServlet phoneServlet = new PhoneServlet();
    @Mock
    private PhoneService phoneService;
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

        Phone phone = new Phone();
        phone.setPhoneId(1L);
        phone.setPhoneNumber("111");
        Mockito.when(phoneService.findById(1L)).thenReturn(phone);

        phoneServlet.doGet(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(200);
        Mockito.verify(writer).write("{\"phoneNumber\":\"111\"}");
    }

    @Test
    void doGet_findAll() throws ServletException, IOException {
        Mockito.when(request.getParameter("id")).thenReturn("all");
        Mockito.when(response.getWriter()).thenReturn(writer);

        Phone phone1 = new Phone();
        phone1.setPhoneId(1L);
        phone1.setPhoneNumber("111");
        Phone phone2 = new Phone();
        phone2.setPhoneId(2L);
        phone2.setPhoneNumber("222");
        Set<Phone> phones = new HashSet<>(Set.of(phone1, phone2));
        Mockito.when(phoneService.findAll()).thenReturn(phones);

        phoneServlet.doGet(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(200);
        Mockito.verify(writer).write("[{\"phoneNumber\":\"111\"},{\"phoneNumber\":\"222\"}]");
    }

    @Test
    void doGet_null() throws ServletException, IOException {
        Mockito.when(request.getParameter("id")).thenReturn(null);
        Mockito.when(response.getWriter()).thenReturn(writer);

        phoneServlet.doGet(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(400);
        Mockito.verify(writer).write("id must not empty");
    }

    @Test
    void doPost() throws IOException, ServletException {
        Mockito.when(request.getParameter("phoneNumber")).thenReturn("111");
        Mockito.when(response.getWriter()).thenReturn(writer);

        Phone phone = new Phone();
        phone.setPhoneNumber("111");
        Mockito.when(phoneService.save(phone)).thenReturn(phone);

        phoneServlet.doPost(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(200);
        Mockito.verify(writer).write("{\"phoneNumber\":\"111\"}");
    }

    @Test
    void doPost_null() throws IOException, ServletException {
        Mockito.when(request.getParameter("phoneNumber")).thenReturn(null);
        Mockito.when(response.getWriter()).thenReturn(writer);

        phoneServlet.doPost(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(400);
        Mockito.verify(writer).write("phoneNumber must not empty");
    }

    @Test
    void doDelete() throws IOException, ServletException {
        Mockito.when(request.getParameter("id")).thenReturn("1");
        Mockito.when(response.getWriter()).thenReturn(writer);
        Mockito.when(phoneService.deleteById(1L)).thenReturn(true);

        phoneServlet.doDelete(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(200);
        Mockito.verify(writer).write("phoneNumber deleted");
    }
}