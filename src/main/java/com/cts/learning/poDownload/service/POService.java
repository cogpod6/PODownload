package com.cts.learning.poDownload.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.learning.poDownload.model.PO;
import com.cts.learning.poDownload.model.POEntity;
import com.cts.learning.poDownload.model.POLine;
import com.cts.learning.poDownload.model.POLineEntity;
import com.cts.learning.poDownload.repo.POLineRepository;
import com.cts.learning.poDownload.repo.PORepository;

@Service
public class POService {
	
	@Autowired
	PORepository poRepository;
	
	@Autowired
	POLineRepository poLineRepository;
	
	public PO createPO (PO poModel) {
		POEntity poEntity = mapToEntity(poModel);
		POEntity responseEntity = poRepository.save(poEntity);
		
		List<POLineEntity> poLineEntityList = new ArrayList<>(); 
		for (POLine poLine : poModel.getPoLines()) {
			POLineEntity poLineEO = new POLineEntity();
			
			poLineEO.setPoNumber(responseEntity);
			poLineEO.setPoLineNumber(poLine.getPoLineNumber());
			poLineEO.setItemNumber(poLine.getItemNumber());
			poLineEO.setItemName(poLine.getItemName());
			poLineEO.setQuantity(poLine.getQuantity());
			
			poLineEntityList.add(poLineEO);
		}
		
		Iterable<POLineEntity> iterable = () -> new Iterator<POLineEntity>() {
			private int index = 0;
			
			@Override
			public boolean hasNext() {
				return poLineEntityList.size() > index;
			}
			
			@Override
			public POLineEntity next() {
				return poLineEntityList.get(index++);
			}
		};
		poLineRepository.saveAll(iterable);
		
		return mapToModel(responseEntity);
	}
	
	public PO getPODetails (String poNumber) {
		return poRepository.findById(poNumber).map(po -> {
			return mapToModel(po);
		}).orElseThrow(() -> new RuntimeException("No Records Found"));
	}
	
	
	public POEntity mapToEntity (PO poModel) {
		POEntity poEntity = new POEntity();
		
		poEntity.setPoNumber(poModel.getPoNumber());
		try {
			poEntity.setPoDate(new SimpleDateFormat("yyyy-MM-dd").parse(poModel.getPoDate()) );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		poEntity.setOrderQuantity(poModel.getOrderedQuantity());
		poEntity.setPoAddress(poModel.getPoAddress());
		
		return poEntity;
	}
	
	public PO mapToModel (POEntity poEntity) {
		PO poModel = new PO();
		
		poModel.setPoNumber(poEntity.getPoNumber());
		poModel.setPoDate(new SimpleDateFormat("yyyy-MM-dd").format(poEntity.getPoDate()) );
		poModel.setPoAddress(poEntity.getPoAddress());
		poModel.setOrderedQuantity(poEntity.getOrderQuantity());
		
		return poModel;
	}
	
	

}
