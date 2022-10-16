// This is a generated file. Not intended for manual editing.
package org.vlang.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.vlang.lang.psi.VlangPsiTreeUtil;
import org.vlang.lang.psi.VlangVarModifier;
import org.vlang.lang.psi.VlangVarModifiers;
import org.vlang.lang.psi.VlangVisitor;

import java.util.List;

public class VlangVarModifiersImpl extends VlangCompositeElementImpl implements VlangVarModifiers {

  public VlangVarModifiersImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull VlangVisitor visitor) {
    visitor.visitVarModifiers(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VlangVisitor) accept((VlangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<VlangVarModifier> getVarModifierList() {
    return VlangPsiTreeUtil.getChildrenOfTypeAsList(this, VlangVarModifier.class);
  }

}
