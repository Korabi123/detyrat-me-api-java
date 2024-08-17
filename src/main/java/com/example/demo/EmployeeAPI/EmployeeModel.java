package com.example.demo.EmployeeAPI;

public class EmployeeModel {
  public enum EmployeeStatus {
    ACTIVE,
    INACTIVE,
    PENDING,
    DELETED,
  }

  public enum EmployeeType {
    MANAGER,
    DIRECTOR,
    EMPLOYEE,
    OTHER,
  }


  private String name;
  private String email;
  private String phone;
  private String id;
  private EmployeeStatus status;
  private EmployeeType type;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public EmployeeType getType() {
    return type;
  }

  public void setType(EmployeeType type) {
    this.type = type;
  }

  public EmployeeStatus getStatus() {
    return status;
  }

  public void setStatus(EmployeeStatus status) {
    this.status = status;
  }
}
