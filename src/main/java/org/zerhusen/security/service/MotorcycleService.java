package org.zerhusen.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerhusen.security.model.Motorcycle;
import org.zerhusen.security.repository.MotorcycleRepository;

@Service
public class MotorcycleService {

	@Autowired
	private MotorcycleRepository motorcycleRepository;

	public MotorcycleService(MotorcycleRepository motorcycleRepository) {
		this.motorcycleRepository = motorcycleRepository;
	}

	public Motorcycle save(Motorcycle motorcycle) {
		return this.motorcycleRepository.save(motorcycle);
	}

	public Motorcycle get(Long id) throws Exception {
		Optional<Motorcycle> motorcycle = this.motorcycleRepository.findById(id);
		if (!motorcycle.isPresent()) {
			throw new Exception("");
		}

		return motorcycle.get();
	}

	public List<Motorcycle> getAll() {
		return this.motorcycleRepository.findAll();
	}

	public void update(Motorcycle motorcycle) {
		this.motorcycleRepository.save(motorcycle);
	}
	
	public void delete(Long id) throws Exception {
		this.motorcycleRepository.delete(get(id));
	}
}
