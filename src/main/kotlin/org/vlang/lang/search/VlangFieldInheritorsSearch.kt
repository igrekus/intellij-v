package org.vlang.lang.search

import com.intellij.openapi.application.QueryExecutorBase
import com.intellij.psi.search.searches.DefinitionsScopedSearch
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.Processor
import org.vlang.lang.psi.VlangFieldDefinition
import org.vlang.lang.psi.VlangInterfaceType
import org.vlang.lang.psi.VlangNamedElement
import org.vlang.lang.psi.VlangStructDeclaration
import org.vlang.lang.psi.types.VlangBaseTypeEx.Companion.toEx

class VlangFieldInheritorsSearch : QueryExecutorBase<VlangFieldDefinition, DefinitionsScopedSearch.SearchParameters>(true) {
    override fun processQuery(
        parameter: DefinitionsScopedSearch.SearchParameters,
        processor: Processor<in VlangFieldDefinition>
    ) {
        if (!parameter.isCheckDeep) return

        val field = parameter.element as? VlangFieldDefinition ?: return
        val interfaceType = PsiTreeUtil.getStubOrPsiParentOfType(field, VlangInterfaceType::class.java) ?: return
        val decl = field.getOwner()

        VlangInheritorsSearch().processMethodOwners({ spec: VlangNamedElement ->
            spec as VlangStructDeclaration

            val structFields = spec.structType.getFieldList()

            val structField = structFields.find {
                val lhsTypeEx = it.getType(null).toEx()
                val rhsTypeEx = field.getType(null).toEx()
                it.name == field.name && lhsTypeEx.isEqual(rhsTypeEx)
            }

            structField == null || field == structField || processor.process(structField)
        }, decl, interfaceType, listOf(), mutableListOf(field))
    }
}
