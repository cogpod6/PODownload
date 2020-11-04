package com.cts.learning.poDownload.service.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.util.ReflectionTestUtils;

import com.cts.learning.poDownload.model.PO;
import com.cts.learning.poDownload.model.POEntity;
import com.cts.learning.poDownload.model.POLine;
import com.cts.learning.poDownload.model.POLineEntity;
import com.cts.learning.poDownload.repo.POLineRepository;
import com.cts.learning.poDownload.repo.PORepository;
import com.cts.learning.poDownload.service.POService;

@RunWith(EasyMockRunner.class)
public class POServiceTest {

	private POService poService;

	PORepository mockPoRepository;
	POLineRepository mockPoLineRepository;

	@Before
	public void setUp () {
		poService = new POService();
		mockPoRepository = EasyMock.createMock(PORepository.class);
		mockPoLineRepository = EasyMock.createMock(POLineRepository.class);

		ReflectionTestUtils.setField(poService, "poRepository", mockPoRepository);
		ReflectionTestUtils.setField(poService, "poLineRepository", mockPoLineRepository);
	}
	
	@Test
	public void createPOTest () {
		PO poModel = getPOModel();
		EasyMock.expect(mockPoRepository.save(EasyMock.anyObject(POEntity.class))).andReturn(getPOEntity(poModel)).times(1);
		EasyMock.replay(mockPoRepository);
		
		EasyMock.expect(mockPoLineRepository.save(EasyMock.anyObject(POLineEntity.class))).andReturn(getPOLineEntity(poModel)).times(1);
		EasyMock.replay(mockPoLineRepository);
		
		PO expectedPOResponse = poService.createPO(poModel);
		Assert.assertEquals(expectedPOResponse.getPoNumber(), poModel.getPoNumber());
	}
	
	@Test
	public void getPODetailsTest () {
		PO poModel = getPOModel();
		POEntity poEntity = getPOEntity(poModel);
		Optional<POEntity> op = Optional.of(poEntity);
		
		EasyMock.expect(mockPoRepository.findById(poModel.getPoNumber())).andReturn(op).times(1);
		EasyMock.replay(mockPoRepository);
		
		PO expectedPOResponse = poService.getPODetails(poModel.getPoNumber());
		Assert.assertEquals(expectedPOResponse.getPoNumber(), poModel.getPoNumber());
	}
	
	
	public PO getPOModel () {
		PO poModel = new PO();
		POLine poLine = new POLine();
	    List<POLine> poLines = new ArrayList<>(); 
		
		poLine.setId(1);
		poLine.setItemName("test item");
		poLine.setItemNumber("1234");
		poLine.setPoLineNumber(1);
		poLine.setQuantity(20);
		poLine.setPo(poModel);
		
		poLines.add(poLine);
		
		poModel.setPoNumber("1111111111");
		poModel.setOrderedQuantity(20);
		poModel.setPoAddress("bentonville");
		poModel.setPoDate("2020-11-04");
		poModel.setPoLines(poLines);
		
		return poModel;
	}
	
	public POEntity getPOEntity (PO poModel) {
		POEntity poEntity = new POEntity();
		
		poEntity.setPoNumber(poModel.getPoNumber());
		poEntity.setOrderQuantity(poModel.getOrderedQuantity());
		poEntity.setPoAddress(poModel.getPoAddress());
		try {
			poEntity.setPoDate(new SimpleDateFormat("yyyy-MM-dd").parse(poModel.getPoDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return poEntity;
	}
	
	public POLineEntity getPOLineEntity (PO poModel) {
		POLineEntity poLine = new POLineEntity();
		POEntity poEntity = new POEntity();
		POLine poLineModel = poModel.getPoLines().get(0);
		
		poLine.setItemName(poLineModel.getItemName());
		poLine.setItemNumber(poLineModel.getItemNumber());
		poLine.setPoLineNumber(poLineModel.getPoLineNumber());
		poLine.setPoNumber(poEntity);
		poLine.setQuantity(poLineModel.getQuantity());
		
		return poLine;
	}
	

}
