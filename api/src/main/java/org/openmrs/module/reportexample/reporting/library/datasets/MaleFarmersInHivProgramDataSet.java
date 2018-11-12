package org.openmrs.module.reportexample.reporting.library.datasets;

import org.openmrs.module.reportexample.reporting.library.dimensions.GenderDimension;
import org.openmrs.module.reportexample.reporting.library.indicators.FarmersInHivProgramIndicator;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.openmrs.module.reporting.indicator.dimension.CohortDefinitionDimension;
import org.openmrs.module.reporting.indicator.util.IndicatorUtil;

public class MaleFarmersInHivProgramDataSet {
	
	public static DataSetDefinition getDataSetDefinition() {
		CohortIndicator indicator = (CohortIndicator) FarmersInHivProgramIndicator.getIndicator();
		Mapped<? extends CohortIndicator> mapped = Mapped.mapStraightThrough(indicator);
		CohortDefinitionDimension dimension = (CohortDefinitionDimension) GenderDimension.getDimension();
		Mapped<CohortDefinitionDimension> mappedDimension = Mapped.mapStraightThrough(dimension);
		CohortIndicatorDataSetDefinition ds = new CohortIndicatorDataSetDefinition();
		ds.setName("MaleFarmersInHivProgramDataSet");
		ds.addParameters(IndicatorUtil.getDefaultParameters());
		ds.addColumn("MaleFarmersInHivProgram", "Male Farmers in Hiv Program", mapped, "GenderDimension=Male-Cohort");
		ds.addDimension("GenderDimension", mappedDimension);
		return ds;
	}
}
