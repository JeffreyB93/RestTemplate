package org.example.model;

import java.util.Objects;

public class Phone {
    private Long phoneId;
    private String phoneNumber;

    public Long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }

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
        Phone phone = (Phone) o;
        return Objects.equals(phoneId, phone.phoneId) && Objects.equals(phoneNumber, phone.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneId, phoneNumber);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phoneId=" + phoneId +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}