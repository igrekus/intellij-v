// This is a generated file. Not intended for manual editing.
package org.vlang.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.vlang.lang.psi.VlangExpression;
import org.vlang.lang.psi.VlangPsiTreeUtil;
import org.vlang.lang.psi.VlangRangeExpr;
import org.vlang.lang.psi.VlangVisitor;

import java.util.List;

import static org.vlang.lang.VlangTypes.RANGE;
import static org.vlang.lang.VlangTypes.TRIPLE_DOT;

public class VlangRangeExprImpl extends VlangExpressionImpl implements VlangRangeExpr {

  public VlangRangeExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull VlangVisitor visitor) {
    visitor.visitRangeExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof VlangVisitor) accept((VlangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<VlangExpression> getExpressionList() {
    return VlangPsiTreeUtil.getChildrenOfTypeAsList(this, VlangExpression.class);
  }

  @Override
  @Nullable
  public PsiElement getRange() {
    return findChildByType(RANGE);
  }

  @Override
  @Nullable
  public PsiElement getTripleDot() {
    return findChildByType(TRIPLE_DOT);
  }

  @Override
  @NotNull
  public VlangExpression getLeft() {
    List<VlangExpression> p1 = getExpressionList();
    return p1.get(0);
  }

  @Override
  @Nullable
  public VlangExpression getRight() {
    List<VlangExpression> p1 = getExpressionList();
    return p1.size() < 2 ? null : p1.get(1);
  }

}
