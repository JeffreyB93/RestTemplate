package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Role;
import org.example.service.RoleService;
import org.example.service.impl.RoleServiceImpl;
import org.example.servlet.dto.RoleDto;
import org.example.servlet.mapper.RoleDtoMapper;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name = "RoleServlet", value = "/role")
public class RoleServlet extends HttpServlet {

    private RoleService roleService = new RoleServiceImpl();
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
                Set<Role> roles = roleService.findAll();
                Set<RoleDto> rolesDto = roles.stream().map(RoleDtoMapper.INSTANCE::toDto).collect(Collectors.toSet());
                String jsonString = mapper.writeValueAsString(rolesDto);
                response.getWriter().write(jsonString);
                response.setStatus(200);
            } else {
                Role role = roleService.findById(Long.valueOf(id));
                RoleDto roleDto = RoleDtoMapper.INSTANCE.toDto(role);
                String jsonString = mapper.writeValueAsString(roleDto);
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
            String roleName = request.getParameter("roleName");
            if (roleName == null) {
                response.getWriter().write("phoneNumber must not empty");
                response.setStatus(400);
            } else {
                RoleDto phoneDto = new RoleDto();
                phoneDto.setRoleName(roleName);
                Role phone = RoleDtoMapper.INSTANCE.toEntity(phoneDto);
                RoleDto phoneDtoSave = RoleDtoMapper.INSTANCE.toDto(roleService.save(phone));
                String jsonString = mapper.writeValueAsString(phoneDtoSave);
                System.out.println(jsonString);
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
                if (roleService.deleteById(Long.valueOf(id))) {
                    response.getWriter().write("roleName deleted");
                    response.setStatus(200);
                } else {
                    response.getWriter().write("roleName won't find");
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