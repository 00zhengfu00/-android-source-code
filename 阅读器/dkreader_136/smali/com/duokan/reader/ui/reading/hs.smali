.class Lcom/duokan/reader/ui/reading/hs;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnClickListener;


# instance fields
.field final synthetic a:Lcom/duokan/reader/ui/reading/hq;


# direct methods
.method constructor <init>(Lcom/duokan/reader/ui/reading/hq;)V
    .locals 0
    .parameter

    .prologue
    .line 48
    iput-object p1, p0, Lcom/duokan/reader/ui/reading/hs;->a:Lcom/duokan/reader/ui/reading/hq;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 2
    .parameter

    .prologue
    .line 51
    invoke-virtual {p1}, Landroid/view/View;->isSelected()Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    invoke-virtual {p1, v0}, Landroid/view/View;->setSelected(Z)V

    .line 52
    iget-object v0, p0, Lcom/duokan/reader/ui/reading/hs;->a:Lcom/duokan/reader/ui/reading/hq;

    invoke-static {v0}, Lcom/duokan/reader/ui/reading/hq;->a(Lcom/duokan/reader/ui/reading/hq;)Lcom/duokan/reader/ui/reading/eb;

    move-result-object v0

    invoke-virtual {p1}, Landroid/view/View;->isSelected()Z

    move-result v1

    invoke-interface {v0, v1}, Lcom/duokan/reader/ui/reading/eb;->d(Z)V

    .line 53
    return-void

    .line 51
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method
