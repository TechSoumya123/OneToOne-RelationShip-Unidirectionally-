package com.developer.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.developer.exception.NoMobileFoundException;
import com.developer.model.Mobile;
import com.developer.repository.MobileRepository;
import com.developer.service.Iservice.MobileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MobileServiceImpl implements MobileService {

	private final MobileRepository mobileRepository;

	@Override
	public List<Mobile> getAllMobile() {
		return mobileRepository.findAll();
	}

	@Override
	public Optional<Mobile> getMobileById(Long mobileId) {
		return mobileRepository.findById(mobileId).or(() -> {
			throw new NoMobileFoundException("No mobile found with this id " + mobileId);
		});
	}

}
