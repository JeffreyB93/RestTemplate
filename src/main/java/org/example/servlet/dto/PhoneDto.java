package org.example.servlet.dto;

import java.util.Objects;

public class PhoneDto {
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneDto phoneDto = (PhoneDto) o;
        return Objects.equals(phoneNumber, phoneDto.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    @Override
    public String toString() {
        return "PhoneDto{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
