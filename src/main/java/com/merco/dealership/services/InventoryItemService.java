package com.merco.dealership.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.merco.dealership.dto.InventoryItemResponseDTO;
import com.merco.dealership.entities.InventoryItem;
import com.merco.dealership.mapper.Mapper;
import com.merco.dealership.repositories.InventoryItemRepository;
import com.merco.dealership.services.exceptions.DataViolationException;
import com.merco.dealership.services.exceptions.DatabaseException;
import com.merco.dealership.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@Service
public class InventoryItemService {
	@Autowired
	private InventoryItemRepository repository;

	public List<InventoryItemResponseDTO> findAll() {
		return Mapper.modelMapperList(repository.findAll(), InventoryItemResponseDTO.class);
	}

	public InventoryItemResponseDTO findById(String id) {
		InventoryItem inventoryItem = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return Mapper.modelMapper(inventoryItem, InventoryItemResponseDTO.class);
	}

	@Transactional
	public InventoryItem create(InventoryItem obj) {
		try {
			InventoryItem InventoryItem = repository.save(obj);
			return InventoryItem;
		} catch (DataIntegrityViolationException e) {
			throw new DataViolationException();
		}
	}

	@Transactional
	public void delete(String id) {
		try {
			if (repository.existsById(id)) {
				repository.deleteById(id);
			} else {
				throw new ResourceNotFoundException(id);
			}
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public InventoryItem patch(String id, InventoryItem obj) {
		try {
			InventoryItem entity = repository.getReferenceById(id);
			updateData(entity, obj);
			InventoryItem InventoryItem = repository.save(entity);
			return InventoryItem;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (ConstraintViolationException e) {
			throw new DatabaseException("Some invalid field.");
		} catch (DataIntegrityViolationException e) {
			throw new DataViolationException();
		}
	}

	private void updateData(InventoryItem entity, InventoryItem obj) {
//		if (obj.getName() != null)
//			entity.setName(obj.getName());
//		if (obj.getEmail() != null)
//			entity.setEmail(obj.getEmail());
//		if (obj.getPhone() != null)
//			entity.setPhone(obj.getPhone());
	}
}