package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Phone;
import org.example.model.User;
import org.example.service.PhoneService;
import org.example.service.UserService;
import org.example.service.impl.PhoneServiceImpl;
import org.example.service.impl.UserServiceImpl;
import org.example.servlet.dto.PhoneDto;
import org.example.servlet.mapper.PhoneDtoMapper;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {

    private PhoneDto phoneDto;
    private PhoneService phoneService = new PhoneServiceImpl();
    private ObjectMapper mapper = new ObjectMapper();
    private UserService userService = new UserServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();


        List<User> users = userService.findAll();

        String jsonString = mapper.writeValueAsString(users);

        //String mapper = new ObjectMapper().writer().writeValueAsString(phone);

        response.getWriter().write(jsonString);





        Set<Phone> phones = phoneService.findAll();
        Set<PhoneDto> phoneDtos = phones.stream().map(PhoneDtoMapper.INSTANCE::toDto).collect(Collectors.toSet());
        Phone phone = phoneService.findById(3L);

        //List<User> users = userService.findAll();


        //String jsonString = mapper.writeValueAsString(users);

        //String mapper = new ObjectMapper().writer().writeValueAsString(phone);

        response.getWriter().write(jsonString);

    }
}
