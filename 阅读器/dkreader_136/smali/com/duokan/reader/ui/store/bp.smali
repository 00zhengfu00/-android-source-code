.class Lcom/duokan/reader/ui/store/bp;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/duokan/reader/domain/cloud/ge;


# instance fields
.field final synthetic a:Lcom/duokan/reader/ui/store/bn;


# direct methods
.method constructor <init>(Lcom/duokan/reader/ui/store/bn;)V
    .locals 0
    .parameter

    .prologue
    .line 71
    iput-object p1, p0, Lcom/duokan/reader/ui/store/bp;->a:Lcom/duokan/reader/ui/store/bn;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public a()V
    .locals 2

    .prologue
    .line 74
    iget-object v0, p0, Lcom/duokan/reader/ui/store/bp;->a:Lcom/duokan/reader/ui/store/bn;

    invoke-static {v0}, Lcom/duokan/reader/ui/store/bn;->b(Lcom/duokan/reader/ui/store/bn;)Lcom/duokan/reader/ui/general/do;

    move-result-object v0

    invoke-static {}, Lcom/duokan/reader/domain/cloud/DkUserShoppingCartManager;->a()Lcom/duokan/reader/domain/cloud/DkUserShoppingCartManager;

    move-result-object v1

    invoke-virtual {v1}, Lcom/duokan/reader/domain/cloud/DkUserShoppingCartManager;->c()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/duokan/reader/ui/general/do;->a(I)V

    .line 75
    iget-object v0, p0, Lcom/duokan/reader/ui/store/bp;->a:Lcom/duokan/reader/ui/store/bn;

    invoke-virtual {v0}, Lcom/duokan/reader/ui/store/bn;->invalidate()V

    .line 76
    return-void
.end method
