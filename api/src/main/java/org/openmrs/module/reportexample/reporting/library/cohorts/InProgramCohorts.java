package org.openmrs.module.reportexample.reporting.library.cohorts;

import org.openmrs.Program;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.InProgramCohortDefinition;

import java.util.Collections;

public class InProgramCohorts {
	
	private static final String HIV_PROGRAM_UUID = "ac3c2784-d6bd-11e8-847d-0242ac110003";
	
	public static InProgramCohortDefinition inHivProgramCohortDefinition() {
		Program hivProgram = Context.getProgramWorkflowService().getProgramByUuid(HIV_PROGRAM_UUID);
		InProgramCohortDefinition inHivProgramCohort = new InProgramCohortDefinition();
		inHivProgramCohort.setPrograms(Collections.singletonList(hivProgram));
		return inHivProgramCohort;
	}
}
