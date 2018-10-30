package org.openmrs.module.reportexample.api.reporting.definitions;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.openmrs.module.reportexample.reporting.definitions.ExampleReportDefinition;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ExampleReportDefinitionTest extends BaseModuleContextSensitiveTest {
	
	@InjectMocks
	ExampleReportDefinition exampleReportDefinition;
	
	@Before
	public void setupMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	@Ignore
	public void constructReportDefinition_shouldReturnExampleReportDefinition() {
		ReportDefinition report = exampleReportDefinition.constructReportDefinition();
		assertThat(report, is(notNullValue()));
		
	}
}
