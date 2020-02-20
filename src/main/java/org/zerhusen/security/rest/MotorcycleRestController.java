package org.zerhusen.security.rest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.security.model.Motorcycle;
import org.zerhusen.security.service.MotorcycleService;

@RestController
@RequestMapping("/api/motorcycles")
public class MotorcycleRestController {

	@Autowired
	private MotorcycleService motorcycleService;

	@PostMapping
	public ResponseEntity<Motorcycle> save(@Valid @RequestBody Motorcycle motorcycle) {
		Motorcycle created = this.motorcycleService.save(motorcycle);

		return ResponseEntity.created(URI.create("/api/motorcycles/" + created.getId())).build();
	}

	@PutMapping
	public ResponseEntity<Void> update(@Valid @RequestBody Motorcycle motorcycle) {
		this.motorcycleService.save(motorcycle);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<Motorcycle> get(@PathVariable(required = true) Long id) {
		Motorcycle motorcycle = null;
		try {
			motorcycle = this.motorcycleService.get(id);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(motorcycle);
	}

	@GetMapping
	public ResponseEntity<List<Motorcycle>> getAll() {
		List<Motorcycle> motorcycles = this.motorcycleService.getAll();

		if (motorcycles.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(motorcycles);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable(required = true) Long id) {
		try {
			this.motorcycleService.delete(id);

			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}
