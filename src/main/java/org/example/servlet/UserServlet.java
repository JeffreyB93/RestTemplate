package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.User;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;
import org.example.servlet.dto.UserDto;
import org.example.servlet.mapper.UserDtoMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    private ObjectMapper mapper = new ObjectMapper();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("application/json");
        try {
            String id = request.getParameter("id");

            if (id == null) {
                response.getWriter().write("id must not empty");
                response.setStatus(400);
            } else if (id.equals("all")) {
                List<User> users = userService.findAll();
                List<UserDto> usersDto = users.stream().map(UserDtoMapper.INSTANCE::toDto).collect(Collectors.toList());
                String jsonString = mapper.writeValueAsString(usersDto);
                response.getWriter().write(jsonString);
                response.setStatus(200);
            } else {
                User user = userService.findById(Long.valueOf(id));
                UserDto userDto = UserDtoMapper.INSTANCE.toDto(user);
                String jsonString = mapper.writeValueAsString(userDto);
                response.getWriter().write(jsonString);
                response.setStatus(200);
            }
        } catch (NullPointerException | NumberFormatException e) {
            response.getWriter().write("id unknown");
            response.setStatus(400);
        }
        finally {
            response.getWriter().close();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        try {
            String name = request.getParameter("name");
            if (name == null) {
                response.getWriter().write("name must not empty");
                response.setStatus(400);
            } else {
                UserDto userDto = new UserDto();
                userDto.setName(name);
                User user = UserDtoMapper.INSTANCE.toEntity(userDto);
                UserDto userDtoSave = UserDtoMapper.INSTANCE.toDto(userService.save(user));
                String jsonString = mapper.writeValueAsString(userDtoSave);
                response.getWriter().write(jsonString);
                response.setStatus(200);
            }
        } catch (NullPointerException | NumberFormatException e) {
            response.getWriter().write("user unknown");
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
                if (userService.deleteById(Long.valueOf(id))) {
                    response.getWriter().write("name deleted");
                    response.setStatus(200);
                } else {
                    response.getWriter().write("name won't find");
                    response.setStatus(200);
                }
            }
        } catch (NullPointerException | NumberFormatException e) {
            response.getWriter().write("id unknown");
            response.setStatus(400);
        }
        finally {
            response.getWriter().close();
        }
    }
}
