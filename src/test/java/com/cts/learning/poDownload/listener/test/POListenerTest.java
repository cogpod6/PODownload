package com.cts.learning.poDownload.listener.test;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.util.ReflectionTestUtils;

import com.cts.learning.poDownload.listener.POListener;
import com.cts.learning.poDownload.model.PO;
import com.cts.learning.poDownload.model.POLine;
import com.cts.learning.poDownload.service.POService;

@RunWith(EasyMockRunner.class)
public class POListenerTest {
	
	private POListener poListener;
	POService mockPoService;
	
	@Before
	public void setUp () {
		poListener = new POListener();
		mockPoService = EasyMock.createMock(POService.class);
				
		ReflectionTestUtils.setField(poListener, "poService", mockPoService);
	}
	
	@Test
	public void listenToKafkaTest () {
		EasyMock.expect(mockPoService.createPO(EasyMock.anyObject(PO.class))).andReturn(getPODetails()).times(1);
		EasyMock.replay(mockPoService);
		
		poListener.listenToKafkaTopic(getPODetails());
		EasyMock.verify(mockPoService);
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
