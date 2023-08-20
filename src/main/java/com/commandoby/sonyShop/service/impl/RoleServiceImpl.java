package com.commandoby.sonyShop.service.impl;

import org.springframework.stereotype.Service;

import com.commandoby.sonyShop.components.Role;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.repository.RoleRepository;
import com.commandoby.sonyShop.service.BaseService;

@Service
public class RoleServiceImpl implements BaseService<Role> {
	
	private final RoleRepository roleRepository;

	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public int create(Role role) throws ServiceException {
		roleRepository.save(role);
		return role.getId();
	}

	@Override
	public Role read(int id) throws ServiceException {
		return roleRepository.findById(id)
				.orElseThrow(() -> new ServiceException("Error retrieving a role from the database by ID: " + id + ".",
						new Exception()));
	}

	@Override
	public void update(Role role) throws ServiceException {
		roleRepository.save(role);
	}

	@Override
	public void delete(Role role) throws ServiceException {
		roleRepository.delete(role);
	}
}
