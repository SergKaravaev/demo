package com.example.demoTwo.mapper;

import com.example.demoTwo.dto.EmployeeRequestDto;
import com.example.demoTwo.dto.EmployeeResponseDto;
import com.example.demoTwo.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

//    @Mapping(target = "employeeId", source = "employeeId")
//    @Mapping(target = "userDto.userId", source = "")
//    @Mapping(target = "userDto.firstName", source = "")
//    @Mapping(target = "userDto.lastName", source = "")
//    @Mapping(target = "specializationTitle", source = "specializationTitle")
//    EmployeeResponseDto toDto(Employee employee);

    @Mapping(target = "employeeId", ignore = true)
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "specializationTitle", source = "specializationTitle")
    Employee toEntity(EmployeeRequestDto employeeRequestDto);

}
