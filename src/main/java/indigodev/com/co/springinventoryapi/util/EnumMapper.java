package indigodev.com.co.springinventoryapi.util;

import indigodev.com.co.springinventoryapi.domain.enums.MovementType;
import indigodev.com.co.springinventoryapi.domain.enums.UserRole;
import indigodev.com.co.springinventoryapi.exception.InvalidValueException;
import org.springframework.stereotype.Component;

@Component
public class EnumMapper {

    public UserRole userRoleMapper(String role){
        UserRole userRole;
        try {
        userRole = UserRole.valueOf(role);
        }catch (IllegalArgumentException e){
            throw new InvalidValueException(role + " is a invalid user role");
        }
        return userRole;
    }

    public MovementType movementTypeMapper(String type){
        MovementType movementType;
        try {
            movementType = MovementType.valueOf(type);
        }catch (IllegalArgumentException e){
            throw new InvalidValueException(type + " is a invalid movement type");
        }
        return movementType;
    }

}
