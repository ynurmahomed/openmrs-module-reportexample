package org.openmrs.module.reportexample.reporting.library.cohorts;

import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.BaseObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CodedObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.common.SetComparator;

public class FarmersCohort {
	
	private static final String OCCUPATION_CONCEPT_UUID = "7cdcdb2b-f698-43a1-8cb7-567d2a3f15b6";
	
	private static final String FARMER_CONCEPT_UUID = "1f632ae1-2579-460b-9e1a-81dd73ce268f";
	
	public static CohortDefinition getCohortDefinition() {
		Concept occupationConcept = Context.getConceptService().getConceptByUuid(OCCUPATION_CONCEPT_UUID);
		Concept farmer = Context.getConceptService().getConceptByUuid(FARMER_CONCEPT_UUID);
		CodedObsCohortDefinition farmersCohort = new CodedObsCohortDefinition();
		farmersCohort.setTimeModifier(BaseObsCohortDefinition.TimeModifier.LAST);
		farmersCohort.setQuestion(occupationConcept);
		farmersCohort.setOperator(SetComparator.IN);
		farmersCohort.addValue(farmer);
		return farmersCohort;
	}
}
