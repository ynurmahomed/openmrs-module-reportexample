package org.openmrs.module.reportexample.reporting.reports;

import org.openmrs.module.reportexample.reporting.library.dimensions.GenderDimension;
import org.openmrs.module.reportexample.reporting.library.indicators.FarmersInHivProgramIndicator;
import org.openmrs.module.reporting.indicator.Indicator;
import org.openmrs.module.reporting.indicator.dimension.Dimension;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MaleFarmersInHivProgramPeriodIndicatorReport extends PeriodIndicatorReport {
	
	public static final String GENDER_DIMENSION = "Gender-Dimension";
	
	@Override
	public Map<String, Dimension> getDimensions() {
		HashMap<String, Dimension> map = new HashMap<String, Dimension>();
		Dimension genderDimension = GenderDimension.getDimension();
		map.put(GENDER_DIMENSION, genderDimension);
		return map;
	}
	
	@Override
	public List<IndicatorWithDimensionCategories> getIndicators() {
		Indicator indicator = FarmersInHivProgramIndicator.getIndicator();
		HashMap<String, String> dimensionCategories = new HashMap<String, String>();
		dimensionCategories.put(GENDER_DIMENSION, GenderDimension.MALE_COHORT);
		IndicatorWithDimensionCategories i = new IndicatorWithDimensionCategories(indicator, dimensionCategories);
		return Collections.singletonList(i);
	}
	
	@Override
	public String getUuid() {
		return "3174fb9d-0f45-42bd-9ccd-7d62bc703851";
	}
	
	@Override
	public String getName() {
		return "Male Farmers in HIV Program";
	}
	
	@Override
	public String getDescription() {
		return "Male Farmer in HIV Program created with reporting API";
	}
	
	@Override
	public List<ReportDesign> constructReportDesigns(ReportDefinition reportDefinition) {
		return null;
	}
	
	@Override
	public String getVersion() {
		return "1.0.0-SNAPSHOT";
	}
}
