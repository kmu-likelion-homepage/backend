package kmu.likelion.homepage.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Project_Post")
@DiscriminatorValue("PROJECT")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPost extends Post {

    private String award;

    @Column(name = "competition_name")
    private String competitionName;
}
