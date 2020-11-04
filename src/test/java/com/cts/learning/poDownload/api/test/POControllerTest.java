package com.cts.learning.poDownload.api.test;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.cts.learning.poDownload.api.POController;
import com.cts.learning.poDownload.model.PO;
import com.cts.learning.poDownload.model.POLine;
import com.cts.learning.poDownload.service.POService;

@RunWith(EasyMockRunner.class)
public class POControllerTest {
	
	private POController poController;
	POService mockPoService;
	
	@Before
	public void setUp () {
		poController = new POController();
		mockPoService = EasyMock.createMock(POService.class);
				
		ReflectionTestUtils.setField(poController, "poService", mockPoService);
	}
	
	@Test
	public void createPOTest () {
		EasyMock.expect(mockPoService.createPO(EasyMock.anyObject(PO.class)) ).andReturn(getPODetails()).times(1);
		EasyMock.replay(mockPoService);
		
		ResponseEntity<PO> response = poController.createPO(getPODetails());
		Assert.assertEquals(response.getStatusCodeValue(), 201);
	}
	
	@Test
	public void getPODetailsTest () {
		String poNumber = "1111111111";
		
		EasyMock.expect(mockPoService.getPODetails(poNumber)).andReturn(getPODetails()).times(1);
		EasyMock.replay(mockPoService);
		
		ResponseEntity<PO> response = poController.getPODetails(poNumber);
		Assert.assertEquals(response.getStatusCodeValue(), 200);
	}
	
	public PO getPODetails () {
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

}
