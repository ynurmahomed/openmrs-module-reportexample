package org.openmrs.module.reportexample.reporting.library.indicators;

import org.openmrs.Location;
import org.openmrs.module.reportexample.reporting.library.cohorts.InProgramCohorts;
import org.openmrs.module.reportexample.reporting.library.cohorts.ObsCohorts;
import org.openmrs.module.reporting.cohort.definition.CodedObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.InProgramCohortDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.indicator.CohortIndicator;

import java.util.Date;

import static org.openmrs.module.reporting.evaluation.parameter.Mapped.map;
import static org.openmrs.module.reporting.evaluation.parameter.Mapped.noMappings;
import static org.openmrs.module.reporting.indicator.CohortIndicator.newFractionIndicator;

public class Indicators {
	
	public static CohortIndicator farmersInHivProgramIndicator() {
		
		CodedObsCohortDefinition farmersCohort = ObsCohorts.farmersCohortDefinition();
		InProgramCohortDefinition inHivProgramCohort = InProgramCohorts.inHivProgramCohortDefinition();
		
		Mapped<CodedObsCohortDefinition> numerator = noMappings(farmersCohort);
		Mapped<InProgramCohortDefinition> denominator = map(inHivProgramCohort, "onDate --> ${endDate}");
		
		CohortIndicator farmersInHivProgramIndicator = newFractionIndicator("Farmers in HIV Program Indicator 2", numerator,
		    denominator, null);
		
		farmersInHivProgramIndicator.addParameter(new Parameter("startDate", "startDate", Date.class));
		farmersInHivProgramIndicator.addParameter(new Parameter("endDate", "endDate", Date.class));
		farmersInHivProgramIndicator.addParameter(new Parameter("location", "location", Location.class));
		
		return farmersInHivProgramIndicator;
		
	}
}
