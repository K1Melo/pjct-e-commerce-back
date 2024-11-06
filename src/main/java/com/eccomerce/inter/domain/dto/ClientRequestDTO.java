package com.eccomerce.inter.domain.dto;

import com.eccomerce.inter.domain.entities.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ClientRequestDTO {
    private String username;
    private String email;
    private String address;

    public User converter(ClientRequestDTO clientRequestDTO) {
        User user = new User();
        BeanUtils.copyProperties(clientRequestDTO, user);

        return user;
    }
}
