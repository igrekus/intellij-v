package org.vlang.lang.configuration.run

import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.ConfigurationFromContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationTypeUtil
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement

class VlangRunConfigurationProducer : LazyRunConfigurationProducer<VlangRunConfiguration>() {
    override fun getConfigurationFactory(): ConfigurationFactory =
        ConfigurationTypeUtil.findConfigurationType(VlangRunConfigurationType.ID)!!
            .configurationFactories[0]

    override fun isConfigurationFromContext(
        configuration: VlangRunConfiguration,
        context: ConfigurationContext
    ): Boolean {
        val element = context.location?.psiElement ?: return false
        val containingFile = element.containingFile

        return configuration.scriptName == containingFile.virtualFile.path
    }

    override fun setupConfigurationFromContext(
        configuration: VlangRunConfiguration,
        context: ConfigurationContext,
        sourceElement: Ref<PsiElement>
    ): Boolean {
        val element = sourceElement.get()
        val containingFile = element.containingFile

        configuration.name = "V Run ${containingFile.name}"
        configuration.scriptName = containingFile.virtualFile.path

        return true
    }

    override fun shouldReplace(self: ConfigurationFromContext, other: ConfigurationFromContext) =
        other.configuration !is VlangRunConfiguration
}
