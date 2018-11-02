package org.openmrs.module.reportexample.reporting.library.dimensions;

import org.openmrs.module.reportexample.reporting.library.cohorts.GenderCohorts;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.indicator.dimension.CohortDefinitionDimension;
import org.openmrs.module.reporting.indicator.dimension.Dimension;

import static org.openmrs.module.reporting.evaluation.parameter.Mapped.noMappings;

public class GenderDimension {
	
	public static final String MALE_COHORT = "Male-Cohort";
	
	public static final String FEMALE_COHORT = "Female-Cohort";
	
	public static Dimension getDimension() {
		CohortDefinition maleCohort = GenderCohorts.maleCohort();
		CohortDefinition femaleCohort = GenderCohorts.femaleCohort();
		CohortDefinitionDimension dimension = new CohortDefinitionDimension();
		dimension.addCohortDefinition(MALE_COHORT, noMappings(maleCohort));
		dimension.addCohortDefinition(FEMALE_COHORT, noMappings(femaleCohort));
		return dimension;
	}
}
