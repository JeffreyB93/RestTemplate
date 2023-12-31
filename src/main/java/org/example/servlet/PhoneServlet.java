package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Phone;
import org.example.service.PhoneService;
import org.example.service.impl.PhoneServiceImpl;
import org.example.servlet.dto.PhoneDto;
import org.example.servlet.mapper.PhoneDtoMapper;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name = "PhoneServlet", value = "/phone")
public class PhoneServlet extends HttpServlet {

    private PhoneService phoneService = new PhoneServiceImpl();
    private ObjectMapper mapper = new ObjectMapper();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        try {
        String id = request.getParameter("id");
        if (id == null) {
            response.getWriter().write("id must not empty");
            response.setStatus(400);
        } else if (id.equals("all")) {
            Set<Phone> phones = phoneService.findAll();
            Set<PhoneDto> phonesDto = phones.stream().map(PhoneDtoMapper.INSTANCE::toDto).collect(Collectors.toSet());
            String jsonString = mapper.writeValueAsString(phonesDto);

            response.getWriter().write(jsonString);
            response.setStatus(200);

        } else {
            Phone phone = phoneService.findById(Long.valueOf(id));
            PhoneDto phoneDto = PhoneDtoMapper.INSTANCE.toDto(phone);
            String jsonString = mapper.writeValueAsString(phoneDto);
            response.getWriter().write(jsonString);
            response.setStatus(200);
        }
        } catch (NullPointerException | NumberFormatException e) {
            response.getWriter().write("id unknown");
        } finally {
            response.getWriter().close();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        try {
            String phoneNumber = request.getParameter("phoneNumber");
            if (phoneNumber == null) {
                response.getWriter().write("phoneNumber must not empty");
                response.setStatus(400);
            } else {
                PhoneDto phoneDto = new PhoneDto();
                phoneDto.setPhoneNumber(phoneNumber);
                Phone phone = PhoneDtoMapper.INSTANCE.toEntity(phoneDto);
                PhoneDto phoneDtoSave = PhoneDtoMapper.INSTANCE.toDto(phoneService.save(phone));
                String jsonString = mapper.writeValueAsString(phoneDtoSave);
                response.getWriter().write(jsonString);
                response.setStatus(200);
            }
        } catch (NullPointerException | NumberFormatException e) {
            response.getWriter().write("phoneNumber unknown");
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        try {
            String id = request.getParameter("id");

            if (id == null) {
                response.getWriter().write("id must not empty");
                response.setStatus(400);
            } else {
                if (phoneService.deleteById(Long.valueOf(id))) {
                    response.getWriter().write("phoneNumber deleted");
                    response.setStatus(200);
                } else {
                    response.getWriter().write("phoneNumber won't find");
                    response.setStatus(200);
                }
            }
        } catch (NullPointerException | NumberFormatException e) {
            response.getWriter().write("id unknown");
            response.setStatus(400);
        }
    }
}
