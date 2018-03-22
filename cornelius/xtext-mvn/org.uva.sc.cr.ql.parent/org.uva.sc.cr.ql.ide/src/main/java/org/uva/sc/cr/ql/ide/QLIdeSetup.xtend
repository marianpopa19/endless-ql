/*
 * generated by Xtext 2.14.0-SNAPSHOT
 */
package org.uva.sc.cr.ql.ide

import com.google.inject.Guice
import org.eclipse.xtext.util.Modules2
import org.uva.sc.cr.ql.QLRuntimeModule
import org.uva.sc.cr.ql.QLStandaloneSetup

/**
 * Initialization support for running Xtext languages as language servers.
 */
class QLIdeSetup extends QLStandaloneSetup {

	override createInjector() {
		return Guice.createInjector(Modules2.mixin(new QLRuntimeModule(), new QLIdeModule()))
	}
	
}
