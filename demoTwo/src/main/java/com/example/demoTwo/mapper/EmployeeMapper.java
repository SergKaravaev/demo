package com.example.demoTwo.mapper;

import com.example.demoTwo.dto.EmployeeRequestDto;
import com.example.demoTwo.dto.EmployeeResponseDto;
import com.example.demoTwo.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "employeeId", source = "employeeId")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    EmployeeResponseDto toDto(Employee employee);

    @Mapping(target = "employeeId", ignore = true)
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    Employee toEntity(EmployeeRequestDto employeeRequestDto);

}
