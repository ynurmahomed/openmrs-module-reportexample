package org.openmrs.module.reportexample.reporting.reports;

import org.openmrs.module.reportexample.reporting.library.dimensions.GenderDimension;
import org.openmrs.module.reportexample.reporting.library.indicators.FarmersInHivProgramIndicator;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.openmrs.module.reporting.indicator.dimension.CohortDefinitionDimension;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.PeriodIndicatorReportDefinition;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.manager.BaseReportManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaleFarmersInHivProgramReport extends BaseReportManager {
	
	@Override
	public ReportDefinition constructReportDefinition() {
		CohortDefinitionDimension genderDimension = (CohortDefinitionDimension) GenderDimension.getDimension();
		CohortIndicator farmersInHivProgramIndicator = (CohortIndicator) FarmersInHivProgramIndicator.getIndicator();
		PeriodIndicatorReportDefinition report = new PeriodIndicatorReportDefinition();
		report.setupDataSetDefinition();
		report.setName("HIV Program Report 2");
		report.addDimension("Gender-dimension", genderDimension);
		Map<String, String> dimension = new HashMap<String, String>();
		dimension.put("Gender-dimension", "Male-Cohort");
		report.addIndicator(farmersInHivProgramIndicator, dimension);
		return report;
	}
	
	@Override
	public List<ReportDesign> constructReportDesigns(ReportDefinition reportDefinition) {
		return null;
	}
	
	@Override
	public String getUuid() {
		return "284d8aad-4497-4def-9c33-2fe4a41ce674";
	}
	
	@Override
	public String getName() {
		return "HIV Program Report from module";
	}
	
	@Override
	public String getDescription() {
		return "HIV Program Report created with reporting API";
	}
	
	@Override
	public String getVersion() {
		return "1.0.0-SNAPSHOT";
	}
}
