/*
 * generated by Xtext 2.12.0
 */
package org.uva.sc.pc.ql.ui.tests;

import com.google.inject.Injector;
import org.eclipse.xtext.testing.IInjectorProvider;
import org.uva.sc.pc.ql.ui.internal.QlActivator;

public class QLangUiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return QlActivator.getInstance().getInjector("org.uva.sc.pc.ql.QLang");
	}

}
