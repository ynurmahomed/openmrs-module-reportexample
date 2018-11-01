package org.openmrs.module.reportexample.reporting.library.cohorts;

import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.BaseObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CodedObsCohortDefinition;
import org.openmrs.module.reporting.common.SetComparator;

public class ObsCohorts {
	
	private static final String OCCUPATION_CONCEPT_UUID = "824f3aaf-08f4-4164-94b0-2c4fe496c6a5";
	
	private static final String FARMER_CONCEPT_UUID = "529c645e-0497-42c2-9df1-afa467d4499f";
	
	public static CodedObsCohortDefinition farmersCohortDefinition() {
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
