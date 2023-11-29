package br.com.unifalmg.blog.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "db", name = "post")
public class Post implements Serializable {

    @Id
    private Integer id;

    private String title;

    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // TODO: Add a list of comments

}
