package org.openmrs.module.reportexample.reporting.reports;

import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.openmrs.module.reporting.indicator.Indicator;
import org.openmrs.module.reporting.indicator.dimension.CohortDefinitionDimension;
import org.openmrs.module.reporting.indicator.dimension.Dimension;
import org.openmrs.module.reporting.report.definition.PeriodIndicatorReportDefinition;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.manager.BaseReportManager;

import java.util.List;
import java.util.Map;

public abstract class PeriodIndicatorReport extends BaseReportManager {
	
	public class IndicatorWithDimensionCategories {
		
		Indicator indicator;
		
		Map<String, String> dimensionCategories;
		
		public IndicatorWithDimensionCategories(Indicator indicator, Map<String, String> dimensionCategories) {
			this.indicator = indicator;
			this.dimensionCategories = dimensionCategories;
		}
	}
	
	@Override
	public ReportDefinition constructReportDefinition() {
		PeriodIndicatorReportDefinition report = new PeriodIndicatorReportDefinition();
		report.setName(this.getName());
		report.setupDataSetDefinition();
		for (Map.Entry<String, Dimension> entry : getDimensions().entrySet()) {
			report.addDimension(entry.getKey(), (CohortDefinitionDimension) entry.getValue());
		}
		for (IndicatorWithDimensionCategories indicator : getIndicators()) {
			// TODO validate dimensionCategories keys against dimensions for this report
			report.addIndicator((CohortIndicator) indicator.indicator, indicator.dimensionCategories);
		}
		return report;
	}
	
	public abstract Map<String, Dimension> getDimensions();
	
	public abstract List<IndicatorWithDimensionCategories> getIndicators();
	
}
