package com.developer.service.Iservice;

import java.util.List;
import java.util.Optional;

import com.developer.model.Mobile;

public interface MobileService {

	List<Mobile> getAllMobile();

	Optional<Mobile> getMobileById(Long mobileId);
}
