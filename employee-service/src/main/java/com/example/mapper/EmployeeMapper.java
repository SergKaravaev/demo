package com.example.mapper;

import com.example.dto.EmployeeDto;
import com.example.dto.EmployeeRequestDto;
import com.example.dto.EmployeeResponseDto;
import com.example.dto.SpecializationDto;
import com.example.dto.UserDto;
import com.example.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "employeeId", source = "employee.employeeId")
    @Mapping(target = "userDto", source = "userDto")
    @Mapping(target = "specializationDto", source = "specializationDto")
    EmployeeResponseDto toDto(Employee employee, UserDto userDto, SpecializationDto specializationDto);

    @Mapping(target = "employeeId", source = "employee.employeeId")
    @Mapping(target = "userDto.userId", source = "employee.userId")
    @Mapping(target = "specializationDto.specializationId", source = "employee.specializationId")
    EmployeeResponseDto toDto(Employee employee);

    @Mapping(target = "employeeId", source = "employee.employeeId")
    @Mapping(target = "userId", source = "employee.userId")
    @Mapping(target = "specializationId", source = "employee.specializationId")
    EmployeeDto toEmployeeDto(Employee employee);

    @Mapping(target = "employeeId", ignore = true)
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "specializationId", source = "specializationId")
    Employee toEntity(EmployeeRequestDto employeeRequestDto);

    @Mapping(target = "employeeId", source = "employeeId")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "specializationId", source = "specializationId")
    Employee toEntity(EmployeeDto employeeDto);
}
