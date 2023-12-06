package br.com.unifalmg.blog.controller.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable {

    private String name;

    private String username;

    private String email;

    private String phone;

    private String website;

}
