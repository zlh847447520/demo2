package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.DistrictMapper;
import cn.tedu.store.service.IDistrictService;

@Service
public class DistrictServiceImpl implements IDistrictService {
	
	@Autowired private DistrictMapper districtMapper;

	@Override
	public List<District> getByParent(String parent) {
		List<District> list =  districtMapper.findByParent(parent);
		for (District district : list) {
			district.setId(null);
			district.setParent(null);
		}
		return list;
	}

	@Override
	public String getNameByCode(String code) {
		return districtMapper.findNameByCode(code);
	}
	
}
