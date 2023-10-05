package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.SimpleEntity;
import org.example.service.SimpleService;
import org.example.servlet.dto.IncomingDto;
import org.example.servlet.dto.OutGoingDto;
import org.example.servlet.mapper.SimpleDtomapper;

import java.io.IOException;


@WebServlet(name = "SimpleServlet", value = "/simple")
public class SimpleServlet extends HttpServlet {
    private SimpleService service;
    private SimpleDtomapper dtomapper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleEntity byId = service.findById(1L);
        OutGoingDto outGoingDto = dtomapper.map(byId);
        // return our DTO
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleEntity simpleEntity = dtomapper.map(new IncomingDto());
        SimpleEntity saved = service.save(simpleEntity);
        OutGoingDto map = dtomapper.map(saved);
        // return our DTO, not necessary
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {

    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp){
    }
}