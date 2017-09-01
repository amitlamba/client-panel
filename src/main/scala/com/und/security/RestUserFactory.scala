package com.und.security


import com.und.model.security.{Authority, User}
import org.springframework.security.core.authority.SimpleGrantedAuthority

import scala.collection.JavaConverters._

object RestUserFactory {
  def create(user: User) = new UndUserDetails(
    user.getId, user.getUsername, user.getFirstname, user.getLastname,
    user.getEmail, user.getPassword,
    mapToGrantedAuthorities( user.getAuthorities.asScala.toList),
    user.getEnabled, user.getLastPasswordResetDate, user.getClientSecret, user.getKey)

  private def mapToGrantedAuthorities(authorities: List[Authority]): java.util.List[SimpleGrantedAuthority] =
    authorities.map(authority => new SimpleGrantedAuthority(authority.getName.name)).asJava
}
