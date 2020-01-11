package com.example.usa2georgiaaddressfinder;

public class Usa2GeorgiaAddress {

  private String address;
  private String workingHours;

  public Usa2GeorgiaAddress() {
  }

  public Usa2GeorgiaAddress(String address, String workingHours) {
    this.address = address;
    this.workingHours = workingHours;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getWorkingHours() {
    return workingHours;
  }

  public void setWorkingHours(String workingHours) {
    this.workingHours = workingHours;
  }
}
