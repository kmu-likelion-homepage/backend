package kmu.likelion.homepage.post.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Activity_Post")
@DiscriminatorValue("ACTIVITY")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityPost extends Post {

    private String award;
}
