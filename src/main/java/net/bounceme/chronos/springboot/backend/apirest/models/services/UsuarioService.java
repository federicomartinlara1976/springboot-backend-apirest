package net.bounceme.chronos.springboot.backend.apirest.models.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.springboot.backend.apirest.aspects.TrackTime;
import net.bounceme.chronos.springboot.backend.apirest.models.dao.IUsuarioDao;
import net.bounceme.chronos.springboot.backend.apirest.models.entity.Usuario;
import net.bounceme.chronos.springboot.backend.apirest.utils.LogWrapper;

/**
 * @author federico
 *
 */
@Service
@Slf4j
public class UsuarioService implements IUsuarioService, UserDetailsService {

	@Autowired
	private IUsuarioDao usuarioDao;

	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	@TrackTime
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if (Objects.isNull(usuario)) {
			String msg = String.format("Error en login: no existe el usuario %s", username);
			LogWrapper.error(log, msg);
			throw new UsernameNotFoundException(msg);
		}

		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> LogWrapper.debug(log, "Role: %s", authority.getAuthority()))
				.collect(Collectors.toList());

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
				authorities);
	}

	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	@TrackTime
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}
}
