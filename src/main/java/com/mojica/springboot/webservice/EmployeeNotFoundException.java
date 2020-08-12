package com.mojica.springboot.webservice;

public class EmployeeNotFoundException extends Exception{
    EmployeeNotFoundException(String message){
        super(message);
    }
}
