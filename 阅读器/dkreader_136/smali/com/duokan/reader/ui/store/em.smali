.class Lcom/duokan/reader/ui/store/em;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnClickListener;


# instance fields
.field final synthetic a:Lcom/duokan/reader/ui/store/el;


# direct methods
.method constructor <init>(Lcom/duokan/reader/ui/store/el;)V
    .locals 0
    .parameter

    .prologue
    .line 80
    iput-object p1, p0, Lcom/duokan/reader/ui/store/em;->a:Lcom/duokan/reader/ui/store/el;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 3
    .parameter

    .prologue
    .line 84
    invoke-static {}, Lcom/duokan/reader/ui/store/fr;->a()Lcom/duokan/reader/ui/store/fr;

    move-result-object v0

    iget-object v1, p0, Lcom/duokan/reader/ui/store/em;->a:Lcom/duokan/reader/ui/store/el;

    invoke-static {v1}, Lcom/duokan/reader/ui/store/el;->a(Lcom/duokan/reader/ui/store/el;)Lcom/duokan/reader/ui/store/cg;

    move-result-object v1

    invoke-interface {v1}, Lcom/duokan/reader/ui/store/cg;->h()Lcom/duokan/reader/ReaderContext;

    move-result-object v1

    iget-object v2, p0, Lcom/duokan/reader/ui/store/em;->a:Lcom/duokan/reader/ui/store/el;

    invoke-static {v2}, Lcom/duokan/reader/ui/store/el;->b(Lcom/duokan/reader/ui/store/el;)Lcom/duokan/reader/domain/bookcity/store/bn;

    move-result-object v2

    invoke-virtual {v0, v1, v2}, Lcom/duokan/reader/ui/store/fr;->a(Lcom/duokan/reader/ReaderContext;Lcom/duokan/reader/domain/bookcity/store/bn;)V

    .line 85
    return-void
.end method
