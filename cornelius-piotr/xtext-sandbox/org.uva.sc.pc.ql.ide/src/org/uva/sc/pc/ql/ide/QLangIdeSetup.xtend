/*
 * generated by Xtext 2.12.0
 */
package org.uva.sc.pc.ql.ide

import com.google.inject.Guice
import org.eclipse.xtext.util.Modules2
import org.uva.sc.pc.ql.QLangRuntimeModule
import org.uva.sc.pc.ql.QLangStandaloneSetup

/**
 * Initialization support for running Xtext languages as language servers.
 */
class QLangIdeSetup extends QLangStandaloneSetup {

	override createInjector() {
		Guice.createInjector(Modules2.mixin(new QLangRuntimeModule, new QLangIdeModule))
	}
	
}
