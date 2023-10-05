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

@WebServlet(name = "AddressServlet", value = "/address")
public class PhoneServlet extends HttpServlet {

    private PhoneService phoneService = new PhoneServiceImpl();
    private ObjectMapper mapper = new ObjectMapper();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("application/json");
        try {
            String id = request.getParameter("id");

            if (id == null) {
                response.getWriter().write("id must not empty");
                response.setStatus(400);
                //response.
            } else if (id.equals("all")) {
                Set<Phone> phones = phoneService.findAll();
                Set<PhoneDto> phonesDto = phones.stream().map(PhoneDtoMapper.INSTANCE::toDto).collect(Collectors.toSet());
                String jsonString = mapper.writeValueAsString(phonesDto);
                response.getWriter().write(jsonString);

            } else {
                Phone phone = phoneService.findById(Long.valueOf(id));
                PhoneDto phoneDto = PhoneDtoMapper.INSTANCE.toDto(phone);
                String jsonString = mapper.writeValueAsString(phoneDto);
                response.getWriter().write(jsonString);
            }


        } catch ( NullPointerException e) {

        }
    }
}
