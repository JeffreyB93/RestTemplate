package org.example;


import jakarta.json.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.model.Phone;
import org.example.repository.PhoneRepository;
import org.example.repository.impl.PhoneRepositoryImpl;

import java.sql.*;
import java.util.Map;
import java.util.Set;


public class Main {
    public static void main(String[] args) throws SQLException, JsonProcessingException {


        /*AddressRepositoryImpl addressRepository = new AddressRepositoryImpl();
        Address qwe = addressRepository.findById(1);
        System.out.println(qwe.getId());
        System.out.println(qwe.getPostCode());*/

        // Проверка адреса
        /*AddressService addressService = new AddressServiceImpl();
        Address eAdress = addressService.findById(11);

        System.out.println(eAdress.getId());
        System.out.println(eAdress.getPostCode());*/

        // Проверка юзера
        /*UserRepository userRepository = new UserRepositoryImpl();
        User user = userRepository.findById(1L);

        System.out.println(user.getId());
        System.out.println(user.getPhones());*/


        PhoneRepository userRepository = new PhoneRepositoryImpl();
        Set<Phone> phones = userRepository.findAll();
        Phone phone = userRepository.findById(3L);

        //Json.createDiff("qwe", phone.getPhoneId());
        JsonObjectBuilder objectBuilder = (JsonObjectBuilder) Json.createObjectBuilder()
                .add("id", phone.getPhoneId())
                .add("PhoneNumber", phone.getPhoneNumber()).build();



        System.out.println(objectBuilder);

        //String mapper = new ObjectMapper().writer().writeValueAsString(phones);
        //Gson gson = new Gson();
        //String json = gson.toJson(phones);

        //System.out.println(mapper);
        //System.out.println(phones.getPhoneNumber());






        //System.out.println(connectionManager);

        /*List<Address> addressList = new ArrayList<>();
        String sql = "SELECT ID, POST_CODE FROM public.ADDRESS";
        Statement statement = null;
        ConnectionManagerImpl connection =  new ConnectionManagerImpl();
        statement = connection.pre();*/




        /*ConnectionManagerImpl connectionManagerImpl = new ConnectionManagerImpl();
        connectionManagerImpl.getConnection();
        PreparedStatement preparedStatement = connectionManagerImpl.getConnection().prepareStatement(sql);*/
        //preparedStatement.положить

        /*ResultSet resultSet = preparedStatement.executeQuery();*/





    }
}