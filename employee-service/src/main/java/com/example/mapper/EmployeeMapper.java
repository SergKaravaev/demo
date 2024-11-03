package com.example.mapper;

import com.example.dto.EmployeeRequestDto;
import com.example.dto.EmployeeResponseDto;
import com.example.dto.UserDto;
import com.example.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "employeeId", source = "employee.employeeId")
    @Mapping(target = "userDto", source = "userDto")
    @Mapping(target = "specializationTitle", source = "employee.specializationTitle")
    EmployeeResponseDto toDto(Employee employee, UserDto userDto);

    @Mapping(target = "employeeId", source = "employee.employeeId")
    @Mapping(target = "userDto.userId", source = "employee.userId")
    @Mapping(target = "specializationTitle", source = "employee.specializationTitle")
    EmployeeResponseDto toDto(Employee employee);

    @Mapping(target = "employeeId", ignore = true)
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "specializationTitle", source = "specializationTitle")
    Employee toEntity(EmployeeRequestDto employeeRequestDto);

}
