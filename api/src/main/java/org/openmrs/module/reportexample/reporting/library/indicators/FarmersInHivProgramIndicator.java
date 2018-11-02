package org.openmrs.module.reportexample.reporting.library.indicators;

import org.openmrs.Location;
import org.openmrs.module.reportexample.reporting.library.cohorts.FarmersCohort;
import org.openmrs.module.reportexample.reporting.library.cohorts.InHivProgramCohort;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.openmrs.module.reporting.indicator.Indicator;

import java.util.Date;

import static org.openmrs.module.reporting.evaluation.parameter.Mapped.map;
import static org.openmrs.module.reporting.evaluation.parameter.Mapped.noMappings;
import static org.openmrs.module.reporting.indicator.CohortIndicator.newFractionIndicator;

public class FarmersInHivProgramIndicator {
	
	public static Indicator getIndicator() {
		CohortDefinition farmersCohort = FarmersCohort.getCohortDefinition();
		CohortDefinition inHivProgramCohort = InHivProgramCohort.getCohortDefinition();
		
		Mapped<CohortDefinition> numerator = noMappings(farmersCohort);
		Mapped<CohortDefinition> denominator = map(inHivProgramCohort, "onDate --> ${endDate}");
		
		CohortIndicator farmersInHivProgramIndicator = newFractionIndicator("Farmers in HIV Program Indicator 2", numerator,
		    denominator, null);
		
		farmersInHivProgramIndicator.addParameter(new Parameter("startDate", "startDate", Date.class));
		farmersInHivProgramIndicator.addParameter(new Parameter("endDate", "endDate", Date.class));
		farmersInHivProgramIndicator.addParameter(new Parameter("location", "location", Location.class));
		
		return farmersInHivProgramIndicator;
	}
}
