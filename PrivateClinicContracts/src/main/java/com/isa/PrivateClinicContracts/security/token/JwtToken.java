package com.isa.PrivateClinicContracts.security.token;

import com.isa.PrivateClinicContracts.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jwt_tokens")
public class JwtToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(unique = true, nullable = false)
  private String token;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TokenType tokenType = TokenType.BEARER;

  @Column(nullable = false)
  private boolean revoked;

  @Column(nullable = false)
  private boolean expired;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @JsonBackReference
  private User user;

  @Override
  public String toString() {
    return "JwtToken{" +
            "id=" + id +
            ", token='" + token + '\'' +
            ", tokenType=" + tokenType +
            ", revoked=" + revoked +
            ", expired=" + expired +
            '}';
  }
}
