package com.developer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.NoMobileFoundException;
import com.developer.model.Mobile;
import com.developer.service.Iservice.MobileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MobileController {

	private final MobileService mobileService;

	@GetMapping(path = "/allMobile")
	public ResponseEntity<List<Mobile>> getAllMobile() {
		var allMobile = mobileService.getAllMobile();
		return allMobile.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(allMobile);
	}

	@GetMapping(path = "/byId/{id}")
	public ResponseEntity<?> getMobileById(@PathVariable("id") Long mobileId) {
		try {
			Optional<Mobile> mobile = mobileService.getMobileById(mobileId);
			if (mobile.isPresent()) {
				return ResponseEntity.ok(mobile.get());
			}
		} catch (NoMobileFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

}
