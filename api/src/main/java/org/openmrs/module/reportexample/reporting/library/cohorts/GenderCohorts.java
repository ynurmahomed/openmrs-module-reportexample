package org.openmrs.module.reportexample.reporting.library.cohorts;

import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.GenderCohortDefinition;

public class GenderCohorts {
	
	/**
	 * Patients who are female
	 * 
	 * @return the cohort definition
	 */
	public static CohortDefinition femaleCohort() {
		GenderCohortDefinition cohort = new GenderCohortDefinition();
		cohort.setName("FemaleCohort");
		cohort.setFemaleIncluded(true);
		return cohort;
	}
	
	/**
	 * Patients who are male
	 * 
	 * @return the cohort definition
	 */
	public static CohortDefinition maleCohort() {
		GenderCohortDefinition cohort = new GenderCohortDefinition();
		cohort.setName("MaleCohort");
		cohort.setMaleIncluded(true);
		return cohort;
	}
	
}
