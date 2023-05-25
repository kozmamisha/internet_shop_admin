package ua.com.shop.internet_shop_admin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "fix_role")
public class Role implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @Transient
  @ManyToMany(mappedBy = "roles")
  private Set<Users> userset;

  public Role(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String getAuthority() {
    return getName();
  }
}
